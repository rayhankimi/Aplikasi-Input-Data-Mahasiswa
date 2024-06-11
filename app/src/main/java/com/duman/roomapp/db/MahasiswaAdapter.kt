package com.duman.roomapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.duman.roomapp.db.Mahasiswa

class MahasiswaAdapter : ListAdapter<Mahasiswa, MahasiswaAdapter.MahasiswaViewHolder>(MahasiswaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_mahasiswa, parent, false)
        return MahasiswaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val mahasiswa = getItem(position)
        holder.bind(mahasiswa)
    }

    class MahasiswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val namaTextView: TextView = itemView.findViewById(R.id.textName)
        private val nimTextView: TextView = itemView.findViewById(R.id.textNim)

        fun bind(mahasiswa: Mahasiswa) {
            namaTextView.text = mahasiswa.nama
            nimTextView.text = mahasiswa.nim
        }
    }

    class MahasiswaDiffCallback : DiffUtil.ItemCallback<Mahasiswa>() {
        override fun areItemsTheSame(oldItem: Mahasiswa, newItem: Mahasiswa): Boolean {
            return oldItem.ida == newItem.ida
        }

        override fun areContentsTheSame(oldItem: Mahasiswa, newItem: Mahasiswa): Boolean {
            return oldItem == newItem
        }
    }
}
