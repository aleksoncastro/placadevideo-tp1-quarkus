package br.unitins.tp1.placadevideo.service.cartao;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.CartaoRequestDTO;
import br.unitins.tp1.placadevideo.model.usuario.Cartao;

public interface CartaoService {

    Cartao findById(Long id);

    List<Cartao> findByTitular(String titular);

    List<Cartao> findAll();

    Cartao create(CartaoRequestDTO dto);

    Cartao update(Long id, CartaoRequestDTO dto);

    void delete(Long id);


}
