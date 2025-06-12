package com.example.examenmovilc1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*


class MainActivity : AppCompatActivity() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnIngresar: Button
    private lateinit var imgLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        iniciarComponentes()
        eventos()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    public fun iniciarComponentes() {
        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)
        btnIngresar = findViewById(R.id.btnIngresar)
        imgLogo = findViewById(R.id.imgLogo)
    }

        public fun eventos() {
            btnIngresar.setOnClickListener {
                val usuario = txtUser.text.toString()
                val clave = txtPassword.text.toString()

                if (usuario.isEmpty() || clave.isEmpty()) {
                    Toast.makeText(this, "Por favor completa los campos", Toast.LENGTH_SHORT).show()
                } else if (usuario == getString(R.string.user) && clave == getString(R.string.pass)) {
                    val intent = Intent(this, CuentaBancoActivity::class.java)
                    intent.putExtra("usuario", usuario)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
}