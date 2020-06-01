package com.tecnm.version.tres.data;

import java.util.ArrayList;
import java.util.List;

public class Loader {

    public static List<Restaurante> restaurantes = new ArrayList<Restaurante>();

    public static void loadData()
    {
        restaurantes.clear();

        Restaurante kfc = new Restaurante("KFC", "Av. Francisco I. Madero 109", 21.881301, -102.295825);
        kfc.agregarComida(new Comida("Mega bucket mamá", 435));
        kfc.agregarComida(new Comida("11 Bucket", 249));
        kfc.agregarComida(new Comida("Family Fast", 279));
        restaurantes.add(kfc);

        Restaurante amerixBurger = new Restaurante("Amerix Burger", "Av. Siglo XXI 5113", 21.887897, -102.251651);
        amerixBurger.agregarComida(new Comida("Regular", 43));
        amerixBurger.agregarComida(new Comida("Moy", 54));
        amerixBurger.agregarComida(new Comida("Betty", 49));
        restaurantes.add(amerixBurger);

        Restaurante sushiRoll = new Restaurante("Sushi Roll", "1er Anillo Periférico, 20236 Aguascalientes, Aguascalientes", 21.867898, -102.308306);
        sushiRoll.agregarComida(new Comida("Kani Gyozas (3 piezas)", 60));
        sushiRoll.agregarComida(new Comida("Rokka Fish", 45));
        sushiRoll.agregarComida(new Comida("Edamames", 76));
        restaurantes.add(sushiRoll);

        Restaurante burgerKing = new Restaurante("Burger King", "Av. Siglo XXI 5113, 2000 Aguascalientes, Aguascalientes", 21.888189, -102.261829);
        burgerKing.agregarComida(new Comida("Whopper", 90));
        burgerKing.agregarComida(new Comida("Rodeo Crispy King", 100));
        burgerKing.agregarComida(new Comida("King de pollo con guacamole", 110));
        restaurantes.add(burgerKing);

        Restaurante mcDonalds = new Restaurante("MCDonalds", "Independiencia 2351, 20128 Aguascalientes, Aguascalientes", 21.923772, -102.294795);
        mcDonalds.agregarComida(new Comida("Big mac", 85));
        mcDonalds.agregarComida(new Comida("Cuarto de Libra", 95));
        mcDonalds.agregarComida(new Comida("MCnifica", 100));
        restaurantes.add(mcDonalds);

        Restaurante dominosPizza = new Restaurante("Domino's Pizza", "Av. Hacienda de Ojocaliente Aguascalientes, Aguascalientes", 21.889666, -102.244701);
        dominosPizza.agregarComida(new Comida("Hawaiana", 80));
        dominosPizza.agregarComida(new Comida("Honolulu", 90));
        dominosPizza.agregarComida(new Comida("Chicken Hawaiana", 70));
        restaurantes.add(dominosPizza);

        Restaurante littleCeasar = new Restaurante("Little Ceasar Pizza", "Avenida Héroe de Nacozari Sur 2428, Aguascalientes, Aguascalientes", 21.854479, -102.282243);
        littleCeasar.agregarComida(new Comida("Crazy Crunch", 95));
        littleCeasar.agregarComida(new Comida("Pepperoni Clásica", 110));
        littleCeasar.agregarComida(new Comida("3 Meat Treat", 120));
        restaurantes.add(littleCeasar);

    }
}
