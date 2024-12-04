package br.unitins.tp1.placadevideo.service.endereco;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.model.usuario.Endereco;
import br.unitins.tp1.placadevideo.repository.endereco.EnderecoRepository;
import br.unitins.tp1.placadevideo.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    public EnderecoRepository enderecoRepository;

    @Override
    public Endereco findById(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("id", "O ID deve ser um valor positivo e válido.");
        }
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null) {
            throw new EntityNotFoundException("Endereço não encontrado.");
        }
        return endereco;
    }

    @Override
    public Endereco findByCep(String cep) {
        
        Endereco endereco = enderecoRepository.findByCep(cep);
        if (endereco == null) {
            throw new EntityNotFoundException("Endereço não encontrado com o CEP informado.");
        }
        return endereco;
    }

    @Override
    public List<Endereco> findByCliente(Long idCliente) {
        if (idCliente == null || idCliente <= 0) {
            throw new ValidationException("idCliente", "O ID do cliente deve ser um valor positivo e válido.");
        }
        List<Endereco> enderecos = enderecoRepository.findByCliente(idCliente);
        if (enderecos == null || enderecos.isEmpty()) {
            throw new EntityNotFoundException("Nenhum endereço encontrado para o cliente especificado.");
        }
        return enderecos;
    }

    @Override
    public List<Endereco> findAll() {
        return enderecoRepository.findAll().list();
    }

    @Override
    @Transactional
    public Endereco create(EnderecoRequestDTO dto) {
        if (dto == null) {
            throw new ValidationException("enderecoRequestDTO", "O objeto DTO não pode ser nulo.");
        }

        Endereco endereco = new Endereco();
        endereco.setCep(dto.cep());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setBairro(dto.bairro());
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());

        enderecoRepository.persist(endereco);
        return endereco;
    }

    @Override
    @Transactional
    public Endereco update(Long id, EnderecoRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new ValidationException("id", "O ID deve ser um valor positivo e válido.");
        }
        if (dto == null) {
            throw new ValidationException("enderecoRequestDTO", "O objeto DTO não pode ser nulo.");
        }

        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null) {
            throw new EntityNotFoundException("Endereço não encontrado.");
        }

        endereco.setCep(dto.cep());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setBairro(dto.bairro());
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());

        enderecoRepository.persist(endereco);
        return endereco;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("id", "O ID deve ser um valor positivo e válido.");
        }
        if (!enderecoRepository.deleteById(id)) {
            throw new EntityNotFoundException("Endereço não encontrado para exclusão.");
        }
    }

    @Override
    @Transactional
    public void deleteByCliente(Long idCliente) {
        if (idCliente == null || idCliente <= 0) {
            throw new ValidationException("idCliente", "O ID do cliente deve ser um valor positivo e válido.");
        }
        List<Endereco> enderecos = enderecoRepository.findByCliente(idCliente);
        if (enderecos == null || enderecos.isEmpty()) {
            throw new EntityNotFoundException("Nenhum endereço encontrado para o cliente especificado.");
        }
        enderecoRepository.deleteByCliente(idCliente);
    }
}
