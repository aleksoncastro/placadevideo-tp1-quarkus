package br.unitins.tp1.placadevideo.service.jwt;

import br.unitins.tp1.placadevideo.dto.response.UsuarioResponseDTO;

public interface JwtService {
    String generateJwt(UsuarioResponseDTO dto);
}
