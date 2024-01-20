package com.example.navigationwithdata


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.navigationwithdata.data.dataform
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val HARGA_PER_CUP = 3000

class CobaViewModel : ViewModel() {
    var namaUsr: String by mutableStateOf("")
        private set
    var noTlp: String by mutableStateOf("")
        private set
    var Alamat: String by mutableStateOf("")
        private set
    var jenisKl: String by mutableStateOf("")
        private set
    var status : String by mutableStateOf("")
        private set
    var email : String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(dataform())
    val uiState: StateFlow<dataform> = _uiState.asStateFlow()

    fun insertData(nm: String, tlp: String, eml : String ,almt: String, jk: String) {
        namaUsr = nm;
        noTlp = tlp;
        email = eml;
        Alamat = almt;
        jenisKl = jk;
    }

    fun setJenisK(pilihJK: String) {
        _uiState.update { currentState -> currentState.copy(sex = pilihJK)}
        fun setstatus(pilihST: String) {
            _uiState.update { currrentState -> currrentState.copy(sex = pilihST) }
        }
    }

}