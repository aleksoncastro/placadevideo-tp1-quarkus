package br.unitins.tp1.placadevideo.service.jwt;

import br.unitins.tp1.placadevideo.dto.Response.UsuarioResponseDTO;

public interface JwtService {
    String generateJwt(UsuarioResponseDTO dto);
}
