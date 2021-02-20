package com.example.binlist.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.binlist.data.local.BinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: BinRepository) :
    ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _bin = _id.switchMap { id ->
        repository.getBin(id)
    }
    //val bin: LiveData<Resource<BinModel>> = _bin

    fun start(id: Int) {
        _id.value = id
    }

    fun getBin(id: Int) = repository.getBin(id)
}