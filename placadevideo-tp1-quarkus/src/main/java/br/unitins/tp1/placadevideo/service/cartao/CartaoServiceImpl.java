package br.unitins.tp1.placadevideo.service.cartao;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.CartaoRequestDTO;
import br.unitins.tp1.placadevideo.model.Cartao;
import br.unitins.tp1.placadevideo.repository.cartao.CartaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CartaoServiceImpl implements CartaoService {

    @Inject
    public CartaoRepository cartaoRepository;

    @Override
    public Cartao findById(Long id) {
        return cartaoRepository.findById(id);
    }

    @Override
    public List<Cartao> findByTitular(String titular) {
        return cartaoRepository.findByTitular(titular);
    }

    @Override
    public List<Cartao> findAll() {
        return cartaoRepository.findAll().list();
    }

    @Override
    @Transactional
    public Cartao create(CartaoRequestDTO dto) {
        Cartao cartao = new Cartao();
        cartao.setNumero(dto.numero());
        cartao.setTitular(dto.Titular());
        cartao.setDataValidade(dto.dataValidade());
        cartao.setCvv(dto.cvv());

        //Atualiza o cartao no banco
        cartaoRepository.persist(cartao);

        return cartao;
    }


  
    @Override
    @Transactional
    public Cartao update(Long id, CartaoRequestDTO dto) {
        Cartao cartao = cartaoRepository.findById(id);
        if (cartao == null) {
            throw new EntityNotFoundException("Cartao não encontrado");
        }
        cartao.setNumero(dto.numero());
        cartao.setTitular(dto.Titular());
        cartao.setDataValidade(dto.dataValidade());
        cartao.setCvv(dto.cvv());

        // Persistindo as alterações do cartao
        cartaoRepository.persist(cartao);
        return cartao;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cartaoRepository.deleteById(id);
    }

}
