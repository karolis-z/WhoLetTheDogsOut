package com.myapplications.wholetthedogsout.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

class UrlsRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private val TAG = "UrlsRepository"

    private val _dogUrls = MutableLiveData<List<String>>()
    val dogUrls : LiveData<List<String>> = _dogUrls

    init {
        try {
            var jsonString = context.assets.open("json/dog_urls.json")
                .bufferedReader()
                .use { it.readText() }
            val jsonObject = JSONObject(jsonString)

            val urlsJSONArray = jsonObject.getJSONArray("urls")
            val listOfUrls : MutableList<String> = mutableListOf()
            for (i in 0 until urlsJSONArray.length()){
                listOfUrls.add(urlsJSONArray.getString(i))
            }
            _dogUrls.value = listOfUrls

        } catch (e : IOException) {
            Log.e(TAG, "IOException while reading json file: $e")
        }
    }
}
