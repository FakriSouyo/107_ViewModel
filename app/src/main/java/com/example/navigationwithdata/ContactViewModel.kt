package com.example.navigationwithdata

import androidx.lifecycle.ViewModel
import com.example.navigationwithdata.data.ContactUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContactViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(ContactUiState())
    val stateUI: StateFlow<ContactUiState> = _stateUI.asStateFlow()

            fun setContact(listContact: MutableList<String>) {
                _stateUI.update { stateSaatIni ->
                    stateSaatIni.copy(
                        Nama = listContact[0],
                        Nohp = listContact[1],
                        Alamat = listContact[2]


                    )
                }
            }
}
