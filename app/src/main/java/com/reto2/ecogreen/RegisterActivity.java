package com.reto2.ecogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private CheckBox checkBoxTerms;
    private Button btnRegister;
    private Button btnBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar vistas

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
        btnRegister = findViewById(R.id.btnRegister);
        btnBackToLogin = findViewById(R.id.btnBackToLogin);

        // Configurar el listener para el botón de registro

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para registrar el usuario
                String firstName = editTextFirstName.getText().toString().trim();
                String lastName = editTextLastName.getText().toString().trim();
                String username = editTextUsername.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                // Validaciones básicas
                if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||
                        TextUtils.isEmpty(username) || TextUtils.isEmpty(email) ||
                        TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!checkBoxTerms.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Almacenar datos del usuario localmente (usando SharedPreferences para pruebas)
                SharedPreferences preferences = getSharedPreferences("usuarios", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                // Agregar otros datos según sea necesario
                editor.apply();

                // Notificar al usuario que se ha registrado correctamente
                Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                // Navegar de nuevo a la pantalla de inicio de sesión
                abrirActividadInicioSesion();
            }
        });

        // Configurar el listener para el botón de volver a inicio de sesión
        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navegación a la actividad de inicio de sesión
                abrirActividadInicioSesion();
            }
        });
    }

    // Método para abrir la actividad de inicio de sesión

    private void abrirActividadInicioSesion() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual para que el usuario no pueda volver atrás con el botón de retroceso
    }
}