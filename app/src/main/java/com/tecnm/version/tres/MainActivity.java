package com.tecnm.version.tres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tecnm.version.tres.utils.Notify;
import com.tecnm.version.tres.utils.Sesion;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonIngresar;
    private TextView textViewLinkRegistro;

    private static String emailStatic;
    private static String passwordStatic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.INTERNET
            }, 1000);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 1000);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 1000);
        }

        this.editTextEmail = (EditText) findViewById(R.id.editTextIngresoEmail);
        this.editTextPassword = (EditText) findViewById(R.id.editTextIngresoPassword);
        this.buttonIngresar = (Button) findViewById(R.id.buttonIngresoIngresar);
        this.textViewLinkRegistro = (TextView) findViewById(R.id.textViewIngresoLinkRegistro);

    }

    @Override
    protected void onResume() {
        if (Sesion.isSesionIniciada()) {
            Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
            startActivityForResult(intent, 0);
        }

        super.onResume();
    }

    public void buttonIngresar_Click(View v)
    {
        String email = this.editTextEmail.getText().toString();
        Log.d("buttonIngresar_Click", "Se recibió el siguiente email: " + email);
        if (email.equals(""))
        {
            Notify.showToast(getApplicationContext(), "Ingresa un email válido");
            Log.d("buttonIngresar_Click", "El email viene vacío");
            return;
        }

        String password = this.editTextPassword.getText().toString();
        if (password.equals(""))
        {
            Notify.showToast(getApplicationContext(), "Ingresa una contraseña válida");
            Log.d("buttonIngresar_Click", "La contraseña viene vacía");
            return;
        }

        emailStatic = email;
        passwordStatic = password;

        Log.d("buttonIngresar_Click", "Empieza la petición post");
        realizarPost();
    }

    public void textViewLinkRegistro_Click(View v)
    {
        this.editTextEmail.setText("");
        this.editTextPassword.setText("");
        Intent intent = new Intent(v.getContext(), RegistroActivity.class);
        startActivityForResult(intent, 0);
    }

    public void realizarPost()
    {
        String url = "https://mybusinesstests.com/public/post/usuarios/validar";
        Log.d("MainActivity.realizarPo", "Realizando petición a : " + url);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("MainActivity.realizarPo", "Respesta recibida: " + response);
                        if (response.contains("true"))
                        {
                            Sesion.abrirSesion();
                            Log.d("MainActivity.realizarPo", "Sesión abierta");
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivityForResult(intent, 0);
                        }
                        else
                        {
                            Log.d("MainActivity.realizarPo", "Correo o contraseña no válidos");
                            Notify.showToast(getApplicationContext(), "Correo o contraseña no válidos");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                // the POST parameters:
                params.put("email-usuario", emailStatic);
                params.put("password-usuario", passwordStatic);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }
}
