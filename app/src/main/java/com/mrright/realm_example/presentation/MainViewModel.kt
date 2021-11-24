package com.mrright.realm_example.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrright.realm_example.domain.use_cases.InsertResult
import com.mrright.realm_example.domain.use_cases.StudentInsertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val studentInsertUseCase: StudentInsertUseCase
) : ViewModel() {

    private val _state: Channel<State> = Channel()
    val state = _state.receiveAsFlow()

    private val _nameErr: MutableLiveData<String?> = MutableLiveData(null)
    val nameErr: LiveData<String?> = _nameErr

    private val _stdErr: MutableLiveData<String?> = MutableLiveData(null)
    val stdErr: LiveData<String?> = _stdErr

    private val _addressErr: MutableLiveData<String?> = MutableLiveData(null)
    val addressErr: LiveData<String?> = _addressErr

    fun insert(
        name: String,
        std: String,
        address: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {

            studentInsertUseCase(name, std, address).collect {
                when (it) {
                    is InsertResult.FieldVal -> {
                        _nameErr.postValue(it.nameErr)
                        _stdErr.postValue(it.stdErr)
                        _addressErr.postValue(it.addressErr)
                    }
                    is InsertResult.Success -> _state.send(State.Toast("Inserted"))
                    is InsertResult.Ex -> _state.send(State.Toast(it.ex))
                }
            }
        }
    }

}

sealed class State {
    data class Toast(val msg: String) : State()
}