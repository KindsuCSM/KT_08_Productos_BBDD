package com.example.kt_08_productos_bbdd

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kt_08_productos_bbdd.databinding.ActivityMainBinding
import com.example.kt_08_productos_bbdd.model.Producto
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    // 1 - Nos vamos al archivo libs.versions.toml y agregamos [version],[libraries] y [plugins]
    // 2 - Nos vamos al build.gradle.kts :app
    // 3 - Nos vamos al build.gradle.kts : module
    // 4 - Nos vamos a la pagina de Firebase, creamos un nuevo proyecto, importamos el json en el proyecto,
    //          comentamos el codigo necesario para que no de error de sincronizacion e
    //          implementamos lo que nos pide en nuestro código, encontraremos
    //          el nombre del proyecto en build.gradle.kts : module -> namespace
    // 5 - Creamos la base de datos en tiempo real (Cloud Firestore) y añadimos la región
    // 6 - Creamos la clase en el paquete model
    // 7 - Nos vamos a libs.versions.toml y añadimos en libraries
    // 8 - Nos vamos a build.gradle : app
    // 9 - Volvemos a firebase y descargamos la nueva version del .json y la sustituimos por el anterior (copiamos y reemplazamos el contenido)
    private lateinit var binding : ActivityMainBinding
    // Firebase Database
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Iniciar la base de datos y ruta de almacenamiento
        database = FirebaseDatabase.getInstance().getReference("Productos")
        // Obtener datos e insertar
        binding.btnGuardarProducto.setOnClickListener{
            // Obtenemos los datos del formulario
            val nombre = binding.etNombreProducto.text.toString()
            val precio = binding.etPrecioProducto.text.toString()
            val descripcion = binding.etDescripcionProducto.text.toString()

            // Generamos el id de forma que sea único
            val id = database.child("Productos").push().key

            // Una vez obtenidos los valores, comprobaremos si son nulos. Si son nulos mandamos un mensaje de error
            if (nombre.isEmpty()) {
                binding.etNombreProducto.error = "Nombre requerido"
                return@setOnClickListener
            }
            if (precio.isEmpty()) {
                binding.etPrecioProducto.error = "Precio requerido"
                return@setOnClickListener
            }
            if (descripcion.isEmpty()) {
                binding.etDescripcionProducto.error = "Descripción requerida"
                return@setOnClickListener
            }

            val producto = Producto(id, nombre, precio, descripcion)
            database.child(id!!).setValue(producto).addOnSuccessListener {
                Toast.makeText(this, "Producto nuevo añadido", Toast.LENGTH_LONG).show()
                binding.etNombreProducto.setText("")
                binding.etPrecioProducto.setText("")
                binding.etDescripcionProducto.setText("")
            }.addOnFailureListener{
                Toast.makeText(this, "Error al insertar nuevo producto", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnVerProducto.setOnClickListener{
            val intent = Intent(this, VerProductosActivity::class.java)
            startActivity(intent)
        }
    }
}