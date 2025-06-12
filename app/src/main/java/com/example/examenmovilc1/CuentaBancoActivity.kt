package com.example.examenmovilc1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*

class CuentaBancoActivity : AppCompatActivity() {


    private lateinit var lblNombre: TextView
    private lateinit var txtSaldoActual: TextView
    private lateinit var txtNumCuenta: EditText
    private lateinit var txtNombre: EditText
    private lateinit var txtBanco: EditText
    private lateinit var txtSaldo: EditText
    private lateinit var txtCantidad: EditText


    private lateinit var rdbDeposito: RadioButton
    private lateinit var rdbRetiro: RadioButton
    private lateinit var rdbConsulta: RadioButton

    private lateinit var btnRegistrar: Button
    private lateinit var btnAplicar: Button

    private var cuenta = Cuenta()
    private var cuentaRegistrada = false


    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cuenta_banco)

        iniciarComponentes()
        eventosClic()
        btnAplicar.isEnabled = false

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun iniciarComponentes() {
        lblNombre = findViewById(R.id.lblNombre)
        txtSaldoActual = findViewById(R.id.txtSaldoActual)
        txtNumCuenta = findViewById(R.id.txtNumCuenta)
        txtNombre = findViewById(R.id.txtNombre)
        txtBanco = findViewById(R.id.txtBanco)
        txtSaldo = findViewById(R.id.txtSaldo)
        txtCantidad = findViewById(R.id.txtCantidad)

        rdbDeposito = findViewById(R.id.rdbDeposito)
        rdbRetiro = findViewById(R.id.rdbRetiro)
        rdbConsulta = findViewById(R.id.rdbConsulta)

        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnAplicar = findViewById(R.id.btnAplicar)

        btnAplicar.isEnabled = false
        val usuario = intent.getStringExtra("usuario")
        lblNombre.text = getString(R.string.usuario_conectado, usuario)
    }
    @SuppressLint("DefaultLocale")
    private fun eventosClic() {
        btnRegistrar.setOnClickListener {
            if (txtNumCuenta.text.isEmpty() || txtNombre.text.isEmpty() || txtBanco.text.isEmpty() || txtSaldo.text.isEmpty()) {
                Toast.makeText(this, getString(R.string.msg_faltan_datos), Toast.LENGTH_SHORT)
                    .show()
            } else {
                cuenta.numeroCuenta = txtNumCuenta.text.toString()
                cuenta.nombreCliente = txtNombre.text.toString()
                cuenta.banco = txtBanco.text.toString()
                cuenta.saldo = txtSaldo.text.toString().toFloat()
                cuentaRegistrada = true
                btnAplicar.isEnabled = true


                Toast.makeText(this, getString(R.string.msg_cuenta_registrada), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btnAplicar.setOnClickListener (View.OnClickListener{
            if (!cuentaRegistrada) {
                Toast.makeText(this, getString(R.string.msg_registra_cuenta), Toast.LENGTH_SHORT).show()

            }

            when {
                rdbDeposito.isChecked || rdbRetiro.isChecked -> {
                    if (txtCantidad.text.isEmpty()) {
                        Toast.makeText(this, getString(R.string.msg_captura_cantidad), Toast.LENGTH_SHORT).show()

                    }

                    val cantidad = txtCantidad.text.toString().toFloat()
                    if (cantidad <= 0f) {
                        Toast.makeText(this, getString(R.string.msg_mayor_cero), Toast.LENGTH_SHORT).show()

                    }

                    if (rdbDeposito.isChecked) {
                        cuenta.hacerDeposito(cantidad)
                        Toast.makeText(this, getString(R.string.msg_deposito_exito), Toast.LENGTH_SHORT).show()
                    } else if (rdbRetiro.isChecked) {
                        if (!cuenta.retirarDinero(cantidad)) {
                            Toast.makeText(this, getString(R.string.msg_saldo_insuficiente), Toast.LENGTH_SHORT).show()

                        } else {
                            Toast.makeText(this, getString(R.string.msg_retiro_exito), Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                rdbConsulta.isChecked -> {
                    Toast.makeText(this, getString(R.string.msg_saldo_actual, String.format("%.2f", cuenta.consultarSaldo())), Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(this, getString(R.string.msg_selecciona_movimiento), Toast.LENGTH_SHORT).show()

                }
            }

            txtSaldoActual.text = getString(
                R.string.msg_saldo_actual,
                String.format("%.2f", cuenta.obtenerSaldo())
            )
            txtCantidad.setText("")


    })
    }
}