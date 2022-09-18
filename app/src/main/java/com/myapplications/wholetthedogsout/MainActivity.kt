package com.myapplications.wholetthedogsout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.elevation.SurfaceColors
import com.myapplications.wholetthedogsout.databinding.ActivityMainBinding
import com.myapplications.wholetthedogsout.ui.MainViewModel
import com.myapplications.wholetthedogsout.ui.PhotoActivity
import com.myapplications.wholetthedogsout.ui.PhotosAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val TAG = "MainActivity"
    private lateinit var urlsAndSumsList : List<Pair<String,Int>>

//    private val mainViewModel : MainViewModel =
//        ViewModelProvider.AndroidViewModelFactory(this.application).create(MainViewModel::class.java)

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBarAndStatusBar()

        //mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.rvPhotos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mainViewModel.dogUrlsAndSums.observe(this){ urlsAndSums ->
            if (urlsAndSums != null){
                Log.d(TAG, "dogUrlsAndSums size: ${urlsAndSums.size}")
                urlsAndSumsList = urlsAndSums
                val adapter = PhotosAdapter(urlsAndSumsList, this){ url ->
                    launchPhotoActivity(url)
                }
                binding.rvPhotos.adapter = adapter
                binding.btnLoadPhotos.isEnabled = true
            }
        }

        binding.btnLoadPhotos.setOnClickListener {
            binding.rvPhotos.visibility = View.VISIBLE
        }
    }

    private fun launchPhotoActivity(url : String){
        val intent = Intent(this,PhotoActivity::class.java)
        intent.putExtra(Constants.INTENT_KEY_URL, url)
        startActivity(intent)
    }

    private fun setUpActionBarAndStatusBar(){
        setSupportActionBar(binding.toolbarMainActivity)
        val surface2Color = SurfaceColors.SURFACE_2.getColor(this)
        binding.toolbarMainActivity.setBackgroundColor(surface2Color)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        window.statusBarColor = surface2Color
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}