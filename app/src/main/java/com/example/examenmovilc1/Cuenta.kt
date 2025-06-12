package com.example.examenmovilc1

class Cuenta {

    var numeroCuenta: String = ""
    var nombreCliente: String = ""
    var banco: String = ""
    var saldo: Float = 0.0f

    fun consultarSaldo(): Float = saldo

    fun depositar(cantidad: Float) {
        if (cantidad > 0) saldo += cantidad
    }

    fun retirarDinero(cantidad: Float): Boolean {
        return if (cantidad <= saldo) {
            saldo -= cantidad
            true
        } else false
}

    fun hacerDeposito(cantidad: Float) {
        depositar(cantidad)
    }
    fun obtenerSaldo(): Float {
        return consultarSaldo()
    }
}
