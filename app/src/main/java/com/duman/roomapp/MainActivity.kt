package com.duman.roomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duman.roomapp.db.DBMahasiswa
import com.duman.roomapp.db.Mahasiswa

class MainActivity : AppCompatActivity() {

    private lateinit var inputNama : EditText
    private lateinit var inputEmail : EditText
    private lateinit var tombolSave : Button
    private lateinit var tombolReset : Button
    private lateinit var viewModel : ViewModelMahasiswa
    private lateinit var adapter: MahasiswaAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNama = findViewById(R.id.etNama)
        inputEmail = findViewById(R.id.etEmail)
        tombolSave = findViewById(R.id.btSave)
        tombolReset = findViewById(R.id.btReset)
        recyclerView = findViewById(R.id.rvMahasiswa)

        val dao = DBMahasiswa.getInstance(application).daoMahasiswa()
        val factory = ViewModelMahasiswaFactory(dao)


        viewModel = ViewModelProvider(this, factory).get(ViewModelMahasiswa::class.java)

        tombolSave.setOnClickListener {
            saveDataMahasiswa()
        }
        tombolReset.setOnClickListener{
            resetDataMahasiswa()
        }
        rv_setup()
        observeViewModel()
    }

    private fun rv_setup() {
        adapter = MahasiswaAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun resetDataMahasiswa() {
       viewModel.deleteAllMahasiswa()
    }

    private fun saveDataMahasiswa() {
        var nama = inputNama.text.toString()
        var email = inputEmail.text.toString()
        val mahasiswa = Mahasiswa(0,nama,email)
        viewModel.insertMahasiswa(mahasiswa)
    }
    private fun observeViewModel(){
        viewModel.getAllMahasiswa().observe(this) { mahasiswas -> adapter.submitList(mahasiswas) }
    }
}

