package com.myapplications.wholetthedogsout.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowInsetsControllerCompat
import com.bumptech.glide.Glide
import com.google.android.material.elevation.SurfaceColors
import com.myapplications.wholetthedogsout.Constants
import com.myapplications.wholetthedogsout.R
import com.myapplications.wholetthedogsout.databinding.ActivityPhotoBinding

class PhotoActivity : AppCompatActivity() {

    private var _binding : ActivityPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBarAndStatusBar()

        val bundle : Bundle? = intent.extras
        val url = bundle?.getString(Constants.INTENT_KEY_URL)

        Glide.with(this )
            .load(url)
            .fitCenter()
            .into(binding.imgFullScreenPhoto)
    }

    private fun setUpActionBarAndStatusBar(){
        setSupportActionBar(binding.toolbarPhotoActivity)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbarPhotoActivity.setNavigationOnClickListener {
            onBackPressed()
        }
        val surface2Color = SurfaceColors.SURFACE_2.getColor(this)
        binding.toolbarPhotoActivity.setBackgroundColor(surface2Color)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        window.statusBarColor = surface2Color
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}