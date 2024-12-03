package br.unitins.tp1.placadevideo.service.placadevideo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp1.placadevideo.dto.request.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.SaidaVideoRequestDTO;
import br.unitins.tp1.placadevideo.model.Fornecedor;
import br.unitins.tp1.placadevideo.model.placadevideo.Fan;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.model.placadevideo.SaidaVideo;
import br.unitins.tp1.placadevideo.repository.placadevideo.PlacaDeVideoRepository;
import br.unitins.tp1.placadevideo.service.fornecedor.FornecedorService;
import br.unitins.tp1.placadevideo.service.lote.LoteService;
import br.unitins.tp1.placadevideo.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlacaDeVideoServiceImpl implements PlacaDeVideoService {

    @Inject
    public PlacaDeVideoRepository placaDeVideoRepository;

    @Inject
    public LoteService loteService;

    @Inject
    public FornecedorService fornecedorService;

    @Override
    public PlacaDeVideo findById(Long id) {
        return placaDeVideoRepository.findById(id);
    }

    @Override
    public PlacaDeVideo findByDescricao(String descricao) {
        return placaDeVideoRepository.findByDescricao(descricao);
    }

    @Override
    public List<PlacaDeVideo> findByModelo(String modelo) {
        return placaDeVideoRepository.findByModelo(modelo);
    }

    @Override
    public List<PlacaDeVideo> findAll() {
        return placaDeVideoRepository.findAll().list();
    }

    @Override
    @Transactional
    public PlacaDeVideo create(PlacaDeVideoRequestDTO dto) {
        PlacaDeVideo placaDeVideo = new PlacaDeVideo();

        placaDeVideo.setModelo(dto.modelo());
        placaDeVideo.setCategoria(dto.categoria());
        placaDeVideo.setPreco(dto.preco());
        placaDeVideo.setResolucao(dto.resolucao());
        placaDeVideo.setEnergia(dto.energia());
        placaDeVideo.setDescricao(dto.descricao());
        placaDeVideo.setCompatibilidade(dto.compatibilidade());
        placaDeVideo.setClockBase(dto.clockBase());
        placaDeVideo.setClockBoost(dto.clockBoost());
        placaDeVideo.setFan(Fan.valueOf(dto.idFan()));
        placaDeVideo.setSuporteRayTracing(dto.suporteRayTracing());
        placaDeVideo.setMemoria(dto.memoria().intoEntity());
        placaDeVideo.setTamanho(dto.tamanho().intoEntity());
        // saidasVideo
        List<SaidaVideo> saidas = new ArrayList<>();
            saidas = dto.saidas().stream()
                    .map(SaidaVideoRequestDTO::intoEntity)
                    .collect(Collectors.toList());
      
        placaDeVideo.setSaidas(saidas);

        Fornecedor fornecedor = fornecedorService.findByIdComTelefones(dto.idFornecedor());
        fornecedor.getTelefones().size();
        placaDeVideo.setFornecedor(fornecedor);
        // Atualiza o placadevideo no banco
        placaDeVideoRepository.persist(placaDeVideo);

        return placaDeVideo;
    }

    @Override
    @Transactional
    public PlacaDeVideo update(Long id, PlacaDeVideoRequestDTO dto) {
        PlacaDeVideo placaDeVideo = placaDeVideoRepository.findById(id);
        if (placaDeVideo == null) {
            throw new EntityNotFoundException("PlacaDeVideo não encontrado");
        }

        placaDeVideo.setModelo(dto.modelo());
        placaDeVideo.setCategoria(dto.categoria());
        placaDeVideo.setPreco(dto.preco());
        placaDeVideo.setResolucao(dto.resolucao());
        placaDeVideo.setEnergia(dto.energia());
        placaDeVideo.setDescricao(dto.descricao());
        placaDeVideo.setCompatibilidade(dto.compatibilidade());
        placaDeVideo.setClockBase(dto.clockBase());
        placaDeVideo.setClockBoost(dto.clockBoost());
        placaDeVideo.setFan(Fan.valueOf(dto.idFan()));
        placaDeVideo.setSuporteRayTracing(dto.suporteRayTracing());
        placaDeVideo.setMemoria(dto.memoria().intoEntity());
        placaDeVideo.setTamanho(dto.tamanho().intoEntity());

        // saidasVideo
        List<SaidaVideo> saidas = new ArrayList<>();
            saidas = dto.saidas().stream()
                    .map(SaidaVideoRequestDTO::intoEntity)
                    .collect(Collectors.toList());
        

        placaDeVideo.setSaidas(saidas);

        Fornecedor fornecedor = fornecedorService.findByIdComTelefones(dto.idFornecedor());
        fornecedor.getTelefones().size();
        placaDeVideo.setFornecedor(fornecedor);
    
        // Persistindo as alterações do placadevideo
        placaDeVideoRepository.persist(placaDeVideo);
        return placaDeVideo;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        placaDeVideoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PlacaDeVideo updateNomeImagem(Long id, String nomeImagem) {
        PlacaDeVideo placaDeVideo = placaDeVideoRepository.findById(id);
        if (placaDeVideo == null)
            throw new ValidationException("idPlacaDeVideo", "PlacaDeVideo nao encontrado");

        if (placaDeVideo.getListaImagem() == null)
            placaDeVideo.setListaImagem(new ArrayList<>());

        placaDeVideo.getListaImagem().add(nomeImagem);
        return placaDeVideo;
    }

}
