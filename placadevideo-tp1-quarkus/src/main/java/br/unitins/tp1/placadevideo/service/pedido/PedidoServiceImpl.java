package br.unitins.tp1.placadevideo.service.pedido;

import java.util.List;
import java.util.Optional;

import br.unitins.tp1.placadevideo.dto.PedidoRequestDTO;
import br.unitins.tp1.placadevideo.model.Pedido;
import br.unitins.tp1.placadevideo.model.StatusPedido;
import br.unitins.tp1.placadevideo.repository.pedido.PedidoRepository;
import br.unitins.tp1.placadevideo.service.estado.EstadoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

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
      // buscar pelo username
      // Usuario usuario = usuarioRepository.findByUsername(String Username)
      // return pedidoRepository.findByUsuario(idUsuario);
      return null;
   }

   @Override
   @Transactional
   public Pedido create(PedidoRequestDTO dto) {
      //Pedido pedido = new Pedido();
      


      return null;
   }

   @Override
   public void cancelarPedido(Long id) {
      Optional<Pedido> pedidoOpt = pedidoRepository.findByIdOptional(id);

      if (pedidoOpt.isPresent()) {
         Pedido pedido = pedidoOpt.get();
         if (pedido.getStatusPedido() != StatusPedido.AGUARDANDO_PAGAMENTO) {
            throw new RuntimeException("O pedido não pode ser cancelado porque já foi processado.");
         }
         pedido.setStatusPedido(StatusPedido.CANCELADO);
         pedidoRepository.persist(pedido);
      } else {
         throw new RuntimeException("Pedido não encontrado");
      }
   }

}