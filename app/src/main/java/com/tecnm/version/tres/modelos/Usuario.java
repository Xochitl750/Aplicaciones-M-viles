package com.tecnm.version.tres.modelos;

public class Usuario {

    private String email;

    private String password;

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
