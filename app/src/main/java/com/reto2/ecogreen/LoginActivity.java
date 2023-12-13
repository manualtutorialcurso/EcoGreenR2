package com.reto2.ecogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.reto2.ecogreen.modelo.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar vistas

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // Configurar el listener para el botón de inicio de sesión

        btnLogin.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                // Lógica para verificar las credenciales y realizar la autenticación
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Lógica de autenticación

                if (autenticarUsuario(username, password)) {
                    // Navegación a la siguiente actividad o acción después del inicio de sesión
                    // Por ejemplo, puedes abrir la actividad principal aquí
                    abrirActividadPrincipal();
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Configurar el listener para el botón de registro
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navegación a la actividad de registro
                abrirActividadRegistro();
            }
        });
    }
    // Método para autenticar al usuario
    private boolean autenticarUsuario(String username, String password) {
        // Aquí debes implementar la lógica real de autenticación
        // Por ejemplo, puedes comparar con una lista de usuarios registrados o consultar una base de datos
        // En un entorno de producción, deberías utilizar técnicas más seguras, como Firebase Authentication o un servidor de autenticación
        // También puedes implementar la lógica para almacenar y comparar contraseñas seguras
        // A continuación, se proporciona un ejemplo básico para demostración:

        // NOTA: Este es solo un ejemplo básico y no debe utilizarse en producción.
        // Se recomienda utilizar bibliotecas de seguridad como Bcrypt para almacenar contraseñas de forma segura.

        // Ejemplo de autenticación básica
        Usuario usuario = obtenerUsuarioPorNombre(username);
        return usuario != null && usuario.getContrasena().equals(password);
    }

    // Método para abrir la actividad de registro
    private void abrirActividadRegistro() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    // Método para abrir la actividad principal después del inicio de sesión
    private void abrirActividadPrincipal() {
        // Implementa la lógica de apertura de la actividad principal aquí
    }

    // Método para obtener un usuario por su nombre de usuario (simulación)
    private Usuario obtenerUsuarioPorNombre(String username) {
        // Implementa la lógica de obtención de usuario desde una fuente de datos (por ejemplo, base de datos)
        // Aquí se utiliza una simulación básica
        if (username.equals("usuario1")) {
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario("usuario1");
            usuario.setContrasena("contraseña1");
            // Agrega otros campos según sea necesario
            return usuario;
        }
        return null;
    }
}