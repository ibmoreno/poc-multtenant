package com.idcotton.app.config.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    public PasswordUtils() {
    }

    public static String gerarBCrypt(String senha) {

        if (senha == null) {
            return senha;
        }

        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.encode(senha);

    }

    public static boolean senhaValida(String senha, String senhaEncoded) {
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.matches(senha, senhaEncoded);
    }

}
