package br.unitins.tp1.placadevideo.service.placadevideo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp1.placadevideo.dto.request.FiltroPlacaDeVideoDTO;
import br.unitins.tp1.placadevideo.dto.request.PaginacaoDTO;
import br.unitins.tp1.placadevideo.dto.request.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.PlacaDeVideoResponseDTO;
import br.unitins.tp1.placadevideo.model.Fornecedor;
import br.unitins.tp1.placadevideo.model.placadevideo.Fan;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.model.placadevideo.SaidaVideo;
import br.unitins.tp1.placadevideo.repository.placadevideo.PlacaDeVideoRepository;
import br.unitins.tp1.placadevideo.service.fornecedor.FornecedorService;
import br.unitins.tp1.placadevideo.service.lote.LoteService;
import br.unitins.tp1.placadevideo.validation.ValidationException;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PlacaDeVideoServiceImpl implements PlacaDeVideoService {

    @Inject
    public PlacaDeVideoRepository placaDeVideoRepository;

    @Inject
    public LoteService loteService;

    @Inject
    public FornecedorService fornecedorService;

    @Override
    @Transactional
    public PlacaDeVideo findById(Long id) {
        return placaDeVideoRepository.findById(id);
    }

    @Override
    public PlacaDeVideo findByDescricao(String descricao) {
        return placaDeVideoRepository.findByDescricao(descricao);
    }

    @Override
    public List<PlacaDeVideo> findByModelo(String nome, Integer page, Integer pageSize) {
        if (nome == null || nome.isEmpty()) {
            throw new ValidationException("nome", "O nome do fornecedor não pode ser nulo ou vazio.");
        }
        return placaDeVideoRepository.findByModelo(nome).page(page, pageSize).list();
    }

    @Override
    public List<PlacaDeVideo> findByLancamentos(String prefixo1, String prefixo2, String valor1, String valor2) {
        return placaDeVideoRepository.findByUltimosLancamentos(prefixo1, prefixo2, valor1, valor2);
    }

    @Override
    public PaginacaoDTO findAll(Integer page, Integer pageSize) {
        if (page == null || pageSize == null) {
            page = 0;
            pageSize = 20;
        }

        PanacheQuery<PlacaDeVideo> query = placaDeVideoRepository.findAll().page(page, pageSize);

        long totalRecords = placaDeVideoRepository.count(); // Conta o total de registros

        // Retorna o DTO com as informações de paginação
        return new PaginacaoDTO(totalRecords, page, pageSize);
    }

    @Override
    public List<PlacaDeVideoResponseDTO> findPage(int page, int pageSize) {
        return placaDeVideoRepository.findAll()
                .page(page, pageSize)
                .list()
                .stream()
                .map(PlacaDeVideoResponseDTO::valueOf)
                .toList();
    }

    @Override
    @Transactional
    public PlacaDeVideo create(@Valid PlacaDeVideoRequestDTO dto) {
        PlacaDeVideo placaDeVideo = new PlacaDeVideo();

        placaDeVideo.setModelo(dto.modelo());
        placaDeVideo.setCategoria(dto.categoria());
        placaDeVideo.setPreco(dto.preco());
        placaDeVideo.setResolucao(dto.resolucao());
        placaDeVideo.setEnergia(dto.energia());
        placaDeVideo.setDescricao(dto.descricao());
        placaDeVideo.setBarramento(dto.barramento());
        placaDeVideo.setClockBase(dto.clockBase());
        placaDeVideo.setClockBoost(dto.clockBoost());
        placaDeVideo.setFan(Fan.valueOf(dto.idFan()));
        placaDeVideo.setSuporteRayTracing(dto.suporteRayTracing());
        placaDeVideo.setMemoria(dto.memoria().intoEntity());
        placaDeVideo.setTamanho(dto.tamanho().intoEntity());
        // saidasVideo
        List<SaidaVideo> saidas = dto.saidas().stream()
                .map(dtoSaida -> {
                    SaidaVideo saida = dtoSaida.intoEntity();
                    saida.setPlacaDeVideo(placaDeVideo); // importante para manter a integridade da relação
                    return saida;
                })
                .collect(Collectors.toList());

        placaDeVideo.setSaidas(saidas);

        Fornecedor fornecedor = fornecedorService.findByIdComTelefones(dto.idFornecedor());
        if (fornecedor == null) {
            throw new ValidationException("idFornecedor", "Fornecedor não encontrado");
        }
        fornecedor.getTelefones().size();
        placaDeVideo.setFornecedor(fornecedor);

        // Atualiza o placadevideo no banco
        placaDeVideoRepository.persist(placaDeVideo);

        return placaDeVideo;
    }

    @Override
    @Transactional
    public PlacaDeVideo update(Long id, @Valid PlacaDeVideoRequestDTO dto) {
        PlacaDeVideo placaDeVideo = placaDeVideoRepository.findById(id);
        if (placaDeVideo == null) {
            throw new EntityNotFoundException("PlacaDeVideo não encontrado");
        }

        // Atualiza os campos básicos
        placaDeVideo.setModelo(dto.modelo());
        placaDeVideo.setCategoria(dto.categoria());
        placaDeVideo.setPreco(dto.preco());
        placaDeVideo.setResolucao(dto.resolucao());
        placaDeVideo.setEnergia(dto.energia());
        placaDeVideo.setDescricao(dto.descricao());
        placaDeVideo.setBarramento(dto.barramento());
        placaDeVideo.setClockBase(dto.clockBase());
        placaDeVideo.setClockBoost(dto.clockBoost());
        placaDeVideo.setFan(Fan.valueOf(dto.idFan()));
        placaDeVideo.setSuporteRayTracing(dto.suporteRayTracing());
        placaDeVideo.setMemoria(dto.memoria().intoEntity());
        placaDeVideo.setTamanho(dto.tamanho().intoEntity());

        // Atualização da lista de `saidas`
        List<SaidaVideo> novasSaidas = dto.saidas().stream()
                .map(saidaDto -> {
                    SaidaVideo saida = saidaDto.intoEntity();
                    saida.setPlacaDeVideo(placaDeVideo); // estabelece relação reversa
                    return saida;
                })
                .collect(Collectors.toList());

        placaDeVideo.getSaidas().clear(); // remove as antigas
        placaDeVideo.getSaidas().addAll(novasSaidas); // adiciona as novas

        // Atualiza o fornecedor
        Fornecedor fornecedor = fornecedorService.findByIdComTelefones(dto.idFornecedor());
        if (fornecedor == null) {
            throw new ValidationException("idFornecedor", "Fornecedor não encontrado");
        }
        fornecedor.getTelefones().size(); // Garante que telefones estão inicializados
        placaDeVideo.setFornecedor(fornecedor);

        // Persiste as alterações
        placaDeVideoRepository.persist(placaDeVideo);
        return placaDeVideo;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        placaDeVideoRepository.deleteById(id);
    }

    @Override
    public List<PlacaDeVideoResponseDTO> findByTexto(String texto, Integer page, Integer pageSize) {
        if (texto == null || texto.isEmpty()) {
            throw new ValidationException("texto", "O texto para busca não pode ser nulo ou vazio.");
        }
        PanacheQuery<PlacaDeVideo> query = placaDeVideoRepository.findByTexto(texto).page(page, pageSize);

        return query.list().stream()
                .map(PlacaDeVideoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlacaDeVideoResponseDTO> findByFiltros(
            FiltroPlacaDeVideoDTO filtro,
            Integer page,
            Integer pageSize
    ) {
        PanacheQuery<PlacaDeVideo> query = placaDeVideoRepository.findByFiltros(
                filtro.categoria(),
                filtro.memoriaMin(),
                filtro.memoriaMax(),
                filtro.precoMin(),
                filtro.precoMax(),
                filtro.rayTracing()
        ).page(page, pageSize);

        return query.list().stream()
                .map(PlacaDeVideoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PlacaDeVideo updateNomeImagem(Long id, String nomeImagem) {
        PlacaDeVideo placaDeVideo = placaDeVideoRepository.findById(id);
        if (placaDeVideo == null) {
            throw new ValidationException("idPlacaDeVideo", "PlacaDeVideo não encontrado");
        }

        if (placaDeVideo.getListaImagem() == null) {
            placaDeVideo.setListaImagem(new ArrayList<>());
        }

        placaDeVideo.getListaImagem().add(nomeImagem);
        placaDeVideoRepository.persist(placaDeVideo);
        return placaDeVideo;
    }

    @Override
    public long count() {
        return placaDeVideoRepository.findAll().count();
    }

    @Override
    public long count(String nome) {
        return placaDeVideoRepository.findByModelo(nome).count();
    }

}
