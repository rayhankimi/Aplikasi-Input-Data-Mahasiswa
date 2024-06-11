package com.duman.roomapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duman.roomapp.db.DaoMahasiswa
import com.duman.roomapp.db.Mahasiswa
import kotlinx.coroutines.launch

class ViewModelMahasiswa(private val dao:DaoMahasiswa) : ViewModel() {

    fun insertMahasiswa(mahasiswa: Mahasiswa) = viewModelScope.launch {
        dao.insertMahasiswa(mahasiswa)
    }

    fun deleteAllMahasiswa() {
        viewModelScope.launch {
            dao.deleteAllMahasiswa()
        }
    }

    fun getAllMahasiswa(): LiveData<List<Mahasiswa>> {
        return dao.getAllMahasiswa()
    }

}