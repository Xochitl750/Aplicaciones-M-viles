package com.tecnm.version.tres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegistroActivity extends AppCompatActivity {

    // Controles
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPasswordVerif;
    private Button buttonRegistro;
    private TextView textViewLinkIngresar;

    private static String emailStatic;
    private static String passwordStatic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Log.d("onCreate", "Se están inicializando los componentes");
        this.editTextEmail = (EditText) findViewById(R.id.editTextRegistroEmail);
        this.editTextPassword = (EditText) findViewById(R.id.editTextRegistroPassword);
        this.editTextPasswordVerif = (EditText) findViewById(R.id.editTextRegistroPasswordVerif);
        this.buttonRegistro = (Button) findViewById(R.id.buttonRegistroRegistrar);
        this.textViewLinkIngresar = (TextView) findViewById(R.id.textViewRegistroIngresar);
    }

    @Override
    protected void onResume() {
        if (Sesion.isSesionIniciada()) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(intent, 0);
        }

        super.onResume();
    }

    public void buttonRegistro_Click(View v)
    {
        String email = this.editTextEmail.getText().toString();
        if (email.equals(""))
        {
            Notify.showToast(getApplicationContext(), "Ingresa un email válido");
            return;
        }

        String passwordOne = this.editTextPassword.getText().toString();
        if (passwordOne.equals(""))
        {
            Notify.showToast(getApplicationContext(), "Ingresa una contraseña válida");
            return;
        }

        String passwordTwo = this.editTextPasswordVerif.getText().toString();
        if (passwordTwo.equals(""))
        {
            Notify.showToast(getApplicationContext(), "Repite tu contraseña");
            return;
        }

        if (!passwordOne.equals(passwordTwo))
        {
            Notify.showToast(getApplicationContext(), "Las contraseñas no coinciden");
            return;
        }

        emailStatic = email;
        passwordStatic = passwordTwo;

        realizarPost();
    }

    public void textViewLinkIngresar_Click(View v)
    {
        this.editTextEmail.setText("");
        this.editTextPassword.setText("");
        this.editTextPasswordVerif.setText("");
        Intent intent = new Intent(v.getContext(), HomeActivity.class);
        startActivityForResult(intent, 0);
    }

    public void realizarPost()
    {
        String url = "https://mybusinesstests.com/public/post/usuarios/crear";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("realizarPost", response);
                        if (response.contains("userId"))
                        {
                            Notify.showToast(getApplicationContext(), "Registro exitoso, ingresa a tu sesión");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivityForResult(intent, 0);
                        }
                        else
                        {
                            Notify.showToast(getApplicationContext(), "Ha ocurrido un error inesperado:(");
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
                params.put("nombre-usuario", "Usuario Pruebas");
                params.put("email-usuario", emailStatic);
                params.put("password-usuario", passwordStatic);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }

}
