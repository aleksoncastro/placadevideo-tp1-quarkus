package br.unitins.tp1.placadevideo.service.pedido;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.PedidoRequestDTO;
import br.unitins.tp1.placadevideo.model.Pedido;
import br.unitins.tp1.placadevideo.repository.pedido.PedidoRepository;
import br.unitins.tp1.placadevideo.service.estado.EstadoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService{

    @Inject
    public PedidoRepository pedidoRepository;

    @Inject
    public EstadoService estadoService;

    @Override
    public Pedido findById(Long id) {
       return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> findByUsername(String username) { 
        //buscar pelo username
        //Usuario usuario = usuarioRepository.findByUsername(String Username)
        //return pedidoRepository.findByUsuario(idUsuario);
        return null;
    }

    

    @Override
    @Transactional
    public Pedido create(PedidoRequestDTO dto) {
      /* Pedido pedido = new Pedido();
       pedido.setNome(dto.nome());
       pedido.setEstado(estadoService.findById(dto.idEstado()));
       pedidoRepository.persist(pedido);
       */
       
       return null;       
    }

   //

}