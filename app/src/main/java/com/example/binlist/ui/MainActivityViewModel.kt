package com.example.binlist.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.binlist.data.local.BinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: BinRepository) :
    ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    fun getBin(id: Int) = repository.getBin(id)

    fun deleteBins() {
        viewModelScope.launch {
            repository.delete()
            Timber.d("Records deleted")
        }
    }

}