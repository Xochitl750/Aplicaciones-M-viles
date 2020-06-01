package com.tecnm.version.tres.data;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    public String nombre;

    public List<Comida> platillos;

    public String direccion;

    public double latitud;

    public double longitud;

    public boolean vegano;

    public Restaurante(String nombre, String direccion, double latitud, double longitud) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.vegano = false;
    }

    public void agregarComida(Comida comida)
    {
        if (platillos == null)
            this.platillos = new ArrayList<Comida>();
        this.platillos.add(comida);
    }

    public Comida obtenerComida(int posicion)
    {
        return this.platillos.get(posicion);
    }
}
