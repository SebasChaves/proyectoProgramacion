package com.carnes_don_fernando

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.carnes_don_fernando.databinding.ActivityCrearCuentaBinding
import com.carnes_don_fernando.databinding.ActivityLoginBinding
import com.carnes_don_fernando.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth

class crear_cuenta : AppCompatActivity() {
    private lateinit var binding: ActivityCrearCuentaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        title="Crear Cuenta"

        binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreaCuenta.setOnClickListener { crearCuenta() }
    }

    private fun crearCuenta(){
        val correo= binding.tvEmail.text
        val contrasenia = binding.tvContrasenia.text
        val segundaContrasenia = binding.tvSegundaContrasenia.text

        if(correo.isNotEmpty() && contrasenia.isNotEmpty() && segundaContrasenia.isNotEmpty()){
            println(contrasenia)
            println(segundaContrasenia)
            if(contrasenia.contentEquals(segundaContrasenia)){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo.toString(),contrasenia.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        println("AQUIIII")
                        val intent= Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        mensajeError("Usuario existente")
                    }
                }
            }else{
                mensajeError("Las contraseñas no coinciden")
            }
        }else{
            mensajeError("Por favor llenar todos los espacios")
        }
    }

    private fun mensajeError(mensaje:String){


        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage(mensaje)
        builder.setPositiveButton("ACEPTAR",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
}