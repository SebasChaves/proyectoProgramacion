package com.carnes_don_fernando

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.carnes_don_fernando.databinding.ActivityCambiarContraseniaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import java.text.SimpleDateFormat
import java.util.*

private lateinit var binding: ActivityCambiarContraseniaBinding
private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
private lateinit var auth: FirebaseAuth
class CambiarContraseniaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_contrasenia)
        //title=""
        binding = ActivityCambiarContraseniaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnContinuar.setOnClickListener {
            //binding.progressBar.visibility=ProgressBar.VISIBLE
            /*crearNotificacion()
            generarNotificacion()*/

            FirebaseAuth.getInstance().sendPasswordResetEmail("sebas.chacon.e@gmail.com")
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Correo enviado", Toast.LENGTH_LONG).show()
                        crearNotificacion()
                        generarNotificacion()
                        FirebaseAuth.getInstance()
                    } else {
                        Toast.makeText(this, "No se envio el correo", Toast.LENGTH_LONG).show()
                    }
                }
        }


    }

    private fun crearNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {   //(1)
            val name = "Notificaciones Básicas"   // (2)
            val channelId = "basic_channel" // (3)
            val descriptionText = "Canal para notificaciones sencillas" // (4)
            val importance = NotificationManager.IMPORTANCE_DEFAULT // (5)

            val channel = NotificationChannel(channelId, name, importance).apply { // (6)
                description = descriptionText
            }
            val nm: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager // (7)
            nm.createNotificationChannel(channel) // (8)
        }
    }
    private fun generarNotificacion() {
        val channelId = "basic_channel" // (1)
        val largeIcon = BitmapFactory.decodeResource( // (2)
            resources,
            R.mipmap.ic_launcher
        )
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())

        val notification = NotificationCompat.Builder(this, channelId) // (3)
            .setLargeIcon(largeIcon) // (4)
            .setSmallIcon(R.mipmap.ic_launcher) // (5)
            .setContentTitle("Correo enviado") // (6)
            .setContentText("Se ha enviado un correo") // (7)
            .setSubText(currentDate) // (8)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // (9)
            .addAction(R.mipmap.ic_launcher,"Leer más tarde", null) // (10)
            .build()

        with(NotificationManagerCompat.from(this)) {
            notify(1, notification)
        }
    }
}