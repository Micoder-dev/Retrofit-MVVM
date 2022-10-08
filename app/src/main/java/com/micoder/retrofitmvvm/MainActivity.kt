package com.micoder.retrofitmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.micoder.retrofitmvvm.api.ApiInterface
import com.micoder.retrofitmvvm.api.ApiUtilities
import com.micoder.retrofitmvvm.repository.MemesRepository
import com.micoder.retrofitmvvm.viewmodel.MemesViewModel
import com.micoder.retrofitmvvm.viewmodel.MemesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var memesViewModel: MemesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val memesRepository = MemesRepository(apiInterface)

        memesViewModel = ViewModelProvider(this, MemesViewModelFactory(memesRepository)).get(MemesViewModel::class.java)

        memesViewModel.memes.observe(this) {
            //Log.d("MUGU", "onCreate: ${it.toString()}")

            it.data.memes.iterator().forEach {

                Log.d("MUGU", "name: ${it.name}\nimage: ${it.url}")

            }
        }

    }
}