package com.carnes_don_fernando

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.carnes_don_fernando.databinding.ActivityLoginBinding
import com.carnes_don_fernando.ui.home.HomeFragment

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title="Inicio de sesión"

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIniciarSesion.setOnClickListener { iniciarSesion() }
        binding.btnCreaCuenta.setOnClickListener {
            val intent= Intent(this, crear_cuenta::class.java)
            startActivity(intent)
        }
        binding.tvCambiarContrasenia.setOnClickListener  {
            val intent= Intent(this, CambiarContraseniaActivity::class.java)
            startActivity(intent)
        }


    }

    private fun iniciarSesion(){
        val email=binding.tvEmail.text
        val contrasenia=binding.tvContrasenia.text

        if(email.isNotEmpty()&&contrasenia.isNotEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.toString(),contrasenia.toString()).addOnCompleteListener {
                if(it.isSuccessful){
                    val intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    mensajeError("Los datos ingresados no coinciden")
                }
            }
        }else{
            mensajeError("Por favor llenar todos los espacios")
        }
    }

    private fun mensajeError(mensaje: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage(mensaje)
        builder.setPositiveButton("ACEPTAR",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }



}