package com.tecnm.version.tres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tecnm.version.tres.data.Loader;
import com.tecnm.version.tres.data.Restaurante;
import com.tecnm.version.tres.utils.Notify;
import com.tecnm.version.tres.utils.Sesion;

public class HomeActivity extends AppCompatActivity {

    private Button buttonAlternativas;
    private Button buttonSorprendeme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.buttonAlternativas = (Button) findViewById(R.id.buttonHomeAlternativas);
        this.buttonSorprendeme = (Button) findViewById(R.id.buttonHomeSorprendeme);

        Loader.loadData();
    }

    @Override
    protected void onResume() {
        if (!Sesion.isSesionIniciada()) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(intent, 0);
        }

        super.onResume();
    }

    public void buttonAlternativas_Click(View v)
    {
        Intent intent = new Intent(getApplicationContext(), MapsPruebasActivity.class);
        startActivityForResult(intent, 0);
    }

    public void buttonSorprendeme_Click(View v)
    {
        double random = Math.random()*6 + 1;
        Sesion.restaurante = (int) random;
        Intent intent = new Intent(getApplicationContext(), RestauranteActivity.class);
        startActivityForResult(intent, 0);
    }

    public void textViewHomeCerrarSesion_Click(View v)
    {
        Sesion.cerrarSesion();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }
}
