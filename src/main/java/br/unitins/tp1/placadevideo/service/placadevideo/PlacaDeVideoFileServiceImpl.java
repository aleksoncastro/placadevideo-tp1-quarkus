package br.unitins.tp1.placadevideo.service.placadevideo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jboss.logging.Logger;

import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.repository.placadevideo.PlacaDeVideoRepository;
import br.unitins.tp1.placadevideo.resource.placadevideo.PlacaDeVideoResource;
import br.unitins.tp1.placadevideo.service.fileservice.FileService;
import br.unitins.tp1.placadevideo.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlacaDeVideoFileServiceImpl implements FileService {

    private static final Logger LOG = Logger.getLogger(PlacaDeVideoResource.class);

    private final String PATH_USER = System.getProperty("user.home")
            + File.separator + "quarkus"
            + File.separator + "images"
            + File.separator + "placa" + File.separator;
    // "C:\\Users\\Alêkson\\placadevideo-tp1-quarkus\\placasdevideo";
    @Inject
    PlacaDeVideoRepository placaDeVideoRepository;

    private static final List<String> SUPPORTED_MIME_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png",
            "image/gif");

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10 MB

    @Override
    @Transactional
    public void salvar(Long id, String nomeImagem, byte[] imagem) throws IOException {
        PlacaDeVideo placa = placaDeVideoRepository.findById(id);

        try {
            String novoNomeImagem = salvarImagem(imagem, nomeImagem);
            List<String> imagens = placa.getListaImagem();
            if (imagens == null) {
                imagens = new ArrayList<>();
            }
            imagens.add(novoNomeImagem);
            placa.setListaImagem(imagens);

            placaDeVideoRepository.persist(placa);
            // excluir a imagem antiga (trabalho pra quem????)
        } catch (IOException e) {
            throw e;
        }
    }

    private String salvarImagem(byte[] imagem, String nomeImagem) throws IOException {
        
        // verificando o tipo da imagem
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif");
        if (!listMimeType.contains(mimeType)) {
            throw new IOException("Tipo de imagem não suportada.");
        }

        // verificando o tamanho do arquivo, não permitor maior que 10 megas
        if (imagem.length > (1024 * 1024 * 10))
            throw new IOException("Arquivo muito grande.");

        // criando as pastas quando não existir
        File diretorio = new File(PATH_USER);
        if (!diretorio.exists())
            diretorio.mkdirs();

        // gerando o nome do arquivo
        String nomeArquivo = UUID.randomUUID()
        +"."+mimeType.substring(mimeType.lastIndexOf("/")+1);

        String path = PATH_USER + nomeArquivo;

        // salvando o arquivo
        File file = new File(path);
        // alunos (melhorar :)
        if (file.exists())
            throw new IOException("O nome gerado da imagem está repedido.");

        // criando um arquivo no S.O.
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagem);
        // garantindo o envio do ultimo lote de bytes
        fos.flush();
        fos.close();

        return nomeArquivo;
    
    }

    @Override
    public File download(String nomeArquivo) {
        File file = new File(PATH_USER+nomeArquivo);
        return file;
    }
}