package br.unitins.tp1.placadevideo.service.cartao;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.CartaoRequestDTO;
import br.unitins.tp1.placadevideo.model.usuario.Cartao;
import br.unitins.tp1.placadevideo.repository.cartao.CartaoRepository;
import br.unitins.tp1.placadevideo.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@ApplicationScoped
public class CartaoServiceImpl implements CartaoService {

    @Inject
    public CartaoRepository cartaoRepository;

    @Override
    public Cartao findById(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("id", "O ID deve ser um valor positivo e válido.");
        }

        Cartao cartao = cartaoRepository.findById(id);
        if (cartao == null) {
            throw new EntityNotFoundException("Cartão não encontrado.");
        }

        return cartao;
    }

    @Override
    public List<Cartao> findByTitular(String titular) {
        if (titular == null || titular.trim().isEmpty()) {
            throw new ValidationException("titular", "O titular não pode estar vazio.");
        }

        return cartaoRepository.findByTitular(titular);
    }

    @Override
    public List<Cartao> findAll() {
        return cartaoRepository.findAll().list();
    }

    @Override
    @Transactional
    public Cartao create(@Valid CartaoRequestDTO dto) {
        if (dto == null) {
            throw new ValidationException("cartaoRequestDTO", "O objeto DTO não pode ser nulo.");
        }

        if (dto.numero() == null || dto.numero().trim().isEmpty()) {
            throw new ValidationException("numero", "O número do cartão não pode estar vazio.");
        }

        if (dto.cpf() == null || !dto.cpf().matches("\\d{11}")) { // Exemplo de validação de CPF
            throw new ValidationException("cpf", "CPF inválido. Deve conter 11 dígitos numéricos.");
        }

        Cartao cartao = new Cartao();
        cartao.setNumero(dto.numero());
        cartao.setTitular(dto.titular());
        cartao.setDataValidade(dto.dataValidade());
        cartao.setCvv(dto.cvv());
        cartao.setCpf(dto.cpf());

        cartaoRepository.persist(cartao);
        return cartao;
    }

    @Override
    @Transactional
    public Cartao update(Long id, CartaoRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new ValidationException("id", "O ID deve ser um valor positivo e válido.");
        }

        if (dto == null) {
            throw new ValidationException("cartaoRequestDTO", "O objeto DTO não pode ser nulo.");
        }

        Cartao cartao = cartaoRepository.findById(id);
        if (cartao == null) {
            throw new EntityNotFoundException("Cartão não encontrado.");
        }

        if (dto.numero() == null || dto.numero().trim().isEmpty()) {
            throw new ValidationException("numero", "O número do cartão não pode estar vazio.");
        }

        cartao.setNumero(dto.numero());
        cartao.setTitular(dto.titular());
        cartao.setDataValidade(dto.dataValidade());
        cartao.setCvv(dto.cvv());
        cartao.setCpf(dto.cpf());

        cartaoRepository.persist(cartao);
        return cartao;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("id", "O ID deve ser um valor positivo e válido.");
        }

        if (!cartaoRepository.deleteById(id)) {
            throw new EntityNotFoundException("Cartão não encontrado.");
        }
    }
}
