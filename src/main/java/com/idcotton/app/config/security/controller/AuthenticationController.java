package com.idcotton.app.config.security.controller;

import com.idcotton.app.config.security.dto.JwtAuthenticationDTO;
import com.idcotton.app.config.security.dto.JwtTokenDTO;
import com.idcotton.app.config.security.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.Serializable;

@Log4j2
@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthenticationController implements Serializable {

    private static final long serialVersionUID = -2780929821893198185L;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<JwtTokenDTO> gerarTokenJwt(@Valid @RequestBody JwtAuthenticationDTO authenticationDTO) {

        log.info("Gerando token para o login {}.", authenticationDTO.getLogin());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getSenha()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDatails = ((UserDetails) authentication.getPrincipal());
        String token = jwtTokenUtil.gerarToken(userDatails);

        return ResponseEntity.ok(JwtTokenDTO.builder().token(token).build());

    }

}
