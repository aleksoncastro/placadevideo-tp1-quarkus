package br.unitins.tp1.placadevideo.service.fileservice;

import java.io.File;
import java.io.IOException;

public interface FileService {

    String save(String nomeArquivo, byte[] arquivo) throws IOException;

    File find(String nomearquivo);

}
