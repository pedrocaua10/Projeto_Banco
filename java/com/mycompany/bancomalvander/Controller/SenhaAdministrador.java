package com.mycompany.bancomalvander.Controller;

public class SenhaAdministrador {

    // Senha do administrador (pode ser configurada de forma mais segura no futuro)
    private static final String senhaAdmin = "1234";

    // Método para verificar se a senha fornecida é a correta
    public static boolean verificarSenha(String senha) {
        return senha.equals(senhaAdmin);
    }
}
