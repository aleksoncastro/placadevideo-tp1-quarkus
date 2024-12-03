/*
 * package br.unitins.tp1.placadevideo.service.pessoafisica;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.unitins.tp1.placadevideo.service.fileservice.FileService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaFisicaFileServiceImpl implements FileService {

    private final String PATH_PESSOA_FISICA = 
    System.getProperty("user.home") + 
    File.separator + "quarkus" +
    File.separator + "images" + 
    File.separator + "pessoafisica" + File.separator;

    private static final List<String> SUPPORTED_MIME_TYPES = 
        Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif");

    private static final int MAX_FILE_SIZE = 1024*1024*10;// 10mb

    @Override
    public String save(String nomeArquivo, byte[] arquivo) throws IOException {
        
        //verificarTipoArquivo(nomeArquivo);

       // verificarTamanhoArquivo(arquivo);
      
       Path diretorio = Paths.get(PATH_PESSOA_FISICA);
       Files.createDirectory(diretorio);

       String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
       String extensao = mimeType.substring(mimeType.lastIndexOf("/")+1);
       String novoNomeArquivo = UUID.randomUUID()+"."+extensao;
       
       //caminho final do arqivo
       Path filePath = diretorio.resolve(novoNomeArquivo);

       if(filePath.toFile().exists()){
        // o ideal eh gerar um novo nome para o arquivo
        throw new IOException("O nome do arquivo (gerado) já existe: "+ novoNomeArquivo);
       }
       //tratar quando o arquivo existir
       
        //salvando a imagem
        try(FileOutputStream fos = new FileOutputStream(filePath.toFile())){
            fos.write(arquivo);
        }

        return novoNomeArquivo;
    }

    @Override
    public File find(String nomeArquivo) {
       //eh ideal verificar se existe para retornar um file vazio   
        return new File(PATH_PESSOA_FISICA + nomeArquivo);
    }
    

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.home"));


    }

}

 */