package com.micoder.retrofitmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micoder.retrofitmvvm.model.Jokes
import com.micoder.retrofitmvvm.repository.MemesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemesViewModel(private val memesRepository: MemesRepository) : ViewModel() {

    init {
        viewModelScope.launch (Dispatchers.IO) {
            memesRepository.getMemes()
        }
    }

    val memes : LiveData<Jokes>
    get() = memesRepository.memes

}