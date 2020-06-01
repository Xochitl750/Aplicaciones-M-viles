package com.tecnm.version.tres.utils;

public class Sesion {
    private static boolean sesionIniciada;

    public static int restaurante;

    public static void abrirSesion()
    {
        sesionIniciada = true;
    }

    public static boolean isSesionIniciada()
    {
        return sesionIniciada;
    }

    public static void cerrarSesion()
    {
        sesionIniciada = false;
    }
}
