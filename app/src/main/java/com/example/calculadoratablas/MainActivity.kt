package com.example.calculadoratablas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pantalla = findViewById<TextView>(R.id.pantalla)
        var numero1: Double = 0.0
        var numero2: Double = 0.0
        var operador: String = ""
        var resetPantalla = false

        val botonesNumericos = listOf<Button>(
            findViewById(R.id.uno),
            findViewById(R.id.dos),
            findViewById(R.id.tres),
            findViewById(R.id.cuatro),
            findViewById(R.id.cinco),
            findViewById(R.id.seis),
            findViewById(R.id.siete),
            findViewById(R.id.ocho),
            findViewById(R.id.nueve),
            findViewById(R.id.cero)
        )

        botonesNumericos.forEach { boton ->
            boton.setOnClickListener {
                if (resetPantalla) {
                    pantalla.text = ""
                    resetPantalla = false
                }
                pantalla.append(boton.text)
            }
        }

        val operadores = listOf<Button>(
            findViewById(R.id.sumar),
            findViewById(R.id.restar),
            findViewById(R.id.multiplicar),
            findViewById(R.id.dividir),
            findViewById(R.id.punto)
        )

        operadores.forEach { boton ->
            boton.setOnClickListener {
                if (resetPantalla) {
                    resetPantalla = false
                }
                pantalla.append(boton.text)
                operador = boton.text.toString()
            }
        }

        findViewById<Button>(R.id.reset).setOnClickListener {
            pantalla.text = ""
            resetPantalla = false
        }

        findViewById<Button>(R.id.igual).setOnClickListener {
            val numeros = pantalla.text.split(operador)
            numero1 = numeros[0].toDouble()
            numero2 = numeros[1].toDouble()

            val resultado = when (operador) {
                "+" -> numero1 + numero2
                "-" -> numero1 - numero2
                "*" -> numero1 * numero2
                "/" -> if (numero2 != 0.0) numero1 / numero2 else null
                else -> null
            }

            pantalla.text = resultado?.toString() ?: "Error"
            resetPantalla = true
        }
    }
}
