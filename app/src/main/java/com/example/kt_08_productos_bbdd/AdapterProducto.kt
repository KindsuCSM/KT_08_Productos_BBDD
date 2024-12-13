package com.example.kt_08_productos_bbdd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kt_08_productos_bbdd.model.Producto

class AdapterProducto (private val productos: ArrayList<Producto>): RecyclerView.Adapter<AdapterProducto.ViewHolder>() {
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val nombre : TextView = itemView.findViewById(R.id.tvNombre)
        val precio : TextView = itemView.findViewById(R.id.tvPrecio)
        val descripcion : TextView = itemView.findViewById(R.id.tvDescripcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProducto.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_productos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterProducto.ViewHolder, position: Int) {
        val producto = productos[position]
        holder.nombre.text = producto.nombre
        holder.precio.text = producto.precio.toString()
        holder.descripcion.text = producto.descripcion
    }

    override fun getItemCount(): Int {
        return productos.size
    }
}