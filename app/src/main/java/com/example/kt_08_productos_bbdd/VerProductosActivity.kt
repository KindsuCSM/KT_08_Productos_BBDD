package com.example.kt_08_productos_bbdd

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kt_08_productos_bbdd.databinding.ActivityVerProductosBinding
import com.example.kt_08_productos_bbdd.model.Producto
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VerProductosActivity : AppCompatActivity() {
    private lateinit var binding : ActivityVerProductosBinding
    private lateinit var productosList : ArrayList<Producto>
    private lateinit var productosRecyclerView : RecyclerView
    private lateinit var database : DatabaseReference
    private lateinit var adapterProducto: AdapterProducto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVerProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        productosRecyclerView = binding.rvProductos
        productosRecyclerView.layoutManager = LinearLayoutManager(this)
        productosRecyclerView.setHasFixedSize(true)

        productosList = arrayListOf<Producto>()

        adapterProducto = AdapterProducto(productosList)
        productosRecyclerView.adapter = adapterProducto

        getProductos()
    }

    private fun getProductos(){
        database = FirebaseDatabase.getInstance().getReference("Productos")

        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    productosList.clear()
                    for(productosSnapshot in snapshot.children){
                        val producto = productosSnapshot.getValue(Producto::class.java)
                        productosList.add(producto!!)
                    }
                    adapterProducto.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}