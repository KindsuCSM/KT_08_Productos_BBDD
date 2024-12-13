package com.example.kt_08_productos_bbdd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kt_08_productos_bbdd.model.Producto
import com.google.firebase.database.FirebaseDatabase

class AdapterProducto(
    private val productos: ArrayList<Producto>,
    private val onClickDelete: (Int) -> Unit,
    private val context: Context // Agregar el contexto como parámetro
) : RecyclerView.Adapter<AdapterProducto.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tvNombre)
        val precio: TextView = itemView.findViewById(R.id.tvPrecio)
        val descripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val delete: TextView = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_productos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos[position]
        holder.nombre.text = producto.nombre
        holder.precio.text = producto.precio.toString()
        holder.descripcion.text = producto.descripcion
        holder.delete.setOnClickListener {
            deleteProduct(producto.id, context) // Ahora puedes usar el contexto correctamente
            // Eliminar la posición actual y notificar al adaptador
            productos.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    private fun deleteProduct(id: String?, context: Context) {
        val db = FirebaseDatabase.getInstance()
        val productosRef = db.getReference("Productos")

        productosRef.child(id!!).removeValue().addOnSuccessListener {
            Toast.makeText(context, "Producto eliminado exitosamente", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(context, "Error al eliminar el producto", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return productos.size
    }
}
