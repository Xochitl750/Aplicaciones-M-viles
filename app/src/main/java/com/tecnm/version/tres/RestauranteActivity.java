package com.tecnm.version.tres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnm.version.tres.data.Loader;
import com.tecnm.version.tres.data.Restaurante;
import com.tecnm.version.tres.utils.Sesion;

public class RestauranteActivity extends AppCompatActivity {

    private TextView textViewNombre;
    private ImageView imageViewLogo;
    private TextView textViewDireccion;
    private TextView textViewComidaNombre1;
    private TextView textViewComidaPrecio1;
    private TextView textViewComidaNombre2;
    private TextView textViewComidaPrecio2;
    private TextView textViewComidaNombre3;
    private TextView textViewComidaPrecio3;
    private Button buttonAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);

        this.textViewNombre = (TextView) findViewById(R.id.textViewRestauranteNombre);
        this.imageViewLogo = (ImageView) findViewById(R.id.imageViewRestauranteLogotipo);
        this.textViewDireccion = (TextView) findViewById(R.id.textViewRestauranteDireccion);
        this.textViewComidaNombre1 = (TextView) findViewById(R.id.textViewRestauranteComida1);
        this.textViewComidaNombre2 = (TextView) findViewById(R.id.textViewRestauranteNombre2);
        this.textViewComidaNombre3 = (TextView) findViewById(R.id.textViewRestauranteNombre3);
        this.buttonAtras = (Button) findViewById(R.id.buttonRestauranteAtras);

        Restaurante aux = Loader.restaurantes.get(Sesion.restaurante);

        this.textViewNombre.setText(aux.nombre);
        this.textViewDireccion.setText(aux.direccion);
        this.textViewComidaNombre1.setText(aux.obtenerComida(0).nombre + ". . ." + "$" + String.valueOf(aux.obtenerComida(0).precio));
        this.textViewComidaNombre2.setText(aux.obtenerComida(1).nombre + ". . ." + "$" + String.valueOf(aux.obtenerComida(1).precio));
        this.textViewComidaNombre3.setText(aux.obtenerComida(2).nombre + ". . ." + "$" + String.valueOf(aux.obtenerComida(2).precio));
    }

    public void buttonAtras_Click(View v)
    {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivityForResult(intent, 0);
    }
}
