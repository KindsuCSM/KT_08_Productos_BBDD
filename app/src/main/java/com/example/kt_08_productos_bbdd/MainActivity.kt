package com.example.kt_08_productos_bbdd

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kt_08_productos_bbdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 1 - Nos vamos al archivo libs.versions.toml y agregamos [version],[libraries] y [plugins]
    // 2 - Nos vamos al build.gradle.kts :app
    // 3 - Nos vamos al build.gradle.kts : module
    // 4 - Nos vamos a la pagina de Firebase, creamos un nuevo proyecto, importamos el json en el proyecto,
    //          comentamos el codigo necesario para que no de error de sincronizacion e
    //          implementamos lo que nos pide en nuestro código, encontraremos
    //          el nombre del proyecto en build.gradle.kts : module -> namespace
    // 5 - Creamos la base de datos en tiempo real (Cloud Firestore) y añadimos la región

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}