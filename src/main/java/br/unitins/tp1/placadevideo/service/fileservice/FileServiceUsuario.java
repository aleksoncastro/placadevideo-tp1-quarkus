package br.unitins.tp1.placadevideo.service.fileservice;

import java.io.File;
import java.io.IOException;

public interface FileServiceUsuario {

    void salvar(Long id, String nomeImagem, byte[] imagem) throws IOException;

    void deletarImagem(Long id, String nomeImagem) throws IOException;

    File download(String nomeArquivo); 

}
