package com.myapplications.wholetthedogsout

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.myapplications.wholetthedogsout.ui.MainViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

//    private val mainViewModel : MainViewModel =
//        ViewModelProvider.AndroidViewModelFactory(this.application).create(MainViewModel::class.java)

    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.dogUrls.observe(this){ dogUrls ->
            if (dogUrls != null){
                Log.d(TAG, "dogUrls size: ${dogUrls.size}")
            }
        }
    }
}