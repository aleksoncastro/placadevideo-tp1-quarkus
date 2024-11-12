package br.unitins.tp1.placadevideo.service.pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.unitins.tp1.placadevideo.dto.Request.ItemPedidoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.PedidoRequestDTO;
import br.unitins.tp1.placadevideo.model.ItemPedido;
import br.unitins.tp1.placadevideo.model.Lote;
import br.unitins.tp1.placadevideo.model.Pedido;
import br.unitins.tp1.placadevideo.model.StatusPedido;
import br.unitins.tp1.placadevideo.repository.pedido.PedidoRepository;
import br.unitins.tp1.placadevideo.service.lote.LoteService;
import br.unitins.tp1.placadevideo.service.usuario.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

   @Inject
   public PedidoRepository pedidoRepository;

   @Inject
   public LoteService loteService;

   @Inject
   public UsuarioService usuarioService;

   @Override
   public Pedido findById(Long id) {
      return pedidoRepository.findById(id);
   }

   @Override
   public List<Pedido> findByUsername(String username) {
      //buscar pelo username
    return pedidoRepository.findByUsername(username);

   }

   @Override
   @Transactional
   public Pedido create(PedidoRequestDTO dto,String username) {
      Pedido pedido = new Pedido();
      pedido.setData(LocalDateTime.now());
      pedido.setUsuario(usuarioService.findByUsername(username));

      pedido.setListaItemPedido(new ArrayList<ItemPedido>());

      for(ItemPedidoRequestDTO itemDTO: dto.listaItemPedido()){
         ItemPedido item = new ItemPedido();

         Lote lote = loteService.findByIdPlacaDeVideo(itemDTO.idProduto());
         item.setLote(lote);
         if(itemDTO.preco() != item.getPreco()){
            throw new IllegalArgumentException("Os preços não coincidem: esperado " 
            + item.getPreco() + ", mas foi fornecido " + itemDTO.preco());
         }
         item.setPreco(itemDTO.preco());
         item.setQuantidade(itemDTO.quantidade());

         pedido.getListaItemPedido().add(item);
      }


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