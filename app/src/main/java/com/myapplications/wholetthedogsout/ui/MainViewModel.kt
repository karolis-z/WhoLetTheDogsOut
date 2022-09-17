package com.myapplications.wholetthedogsout.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.json.JSONObject
import java.io.IOException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "MainViewModel"

    private val _dogUrls = MutableLiveData<List<Pair<String,Int>>>()
    val dogUrls : LiveData<List<Pair<String,Int>>> = _dogUrls

    init {
        try {
            var jsonString = application.assets.open("json/dog_urls.json")
                .bufferedReader()
                .use { it.readText() }
            val jsonObject = JSONObject(jsonString)
//            Log.d(TAG, "jsonObject $jsonObject")
//            Log.d(TAG, "jsonString $jsonString")

            val urlsJSONArray = jsonObject.getJSONArray("urls")
            var listOfUrlAndSum : MutableList<Pair<String,Int>> = mutableListOf()
            for (i in 0 until urlsJSONArray.length()) {
                val url = urlsJSONArray.getString(i)
                val numberString = url.substringAfterLast("_").substringBeforeLast(".jpg")
                val sum = numberString.sumOf { it.digitToInt() }
                listOfUrlAndSum.add(Pair(url,sum))
            }

            _dogUrls.value = listOfUrlAndSum

        } catch (e : IOException) {
            Log.d(TAG, "IOException while reading json file: $e")
        }
    }


    //            _dogUrls.value = Gson().fromJson(jsonString, object : TypeToken<List<String>>() {}.type)
    //val jsonObject = Gson().toJsonTree(jsonString)
    //val jsonArray = jsonObject.asJsonArray
    //val jsonArray = JSONArray(jsonString)
    //Log.d(TAG, "jsonString $jsonString")
    //Log.d(TAG, "jsonObject $jsonObject")
//            val newJson = Gson().toJsonTree(jsonString)
//            val isJsonArray = newJson.isJsonArray
//            val isJsonObject = newJson.isJsonObject
//            val isJsonPrimitive = newJson.isJsonPrimitive
//            Log.d(TAG, "newJson $newJson")
//            Log.d(TAG, "isJsonArray $isJsonArray")
//            Log.d(TAG, "isJsonObject $isJsonObject")
//            Log.d(TAG, "isJsonPrimitive $isJsonPrimitive")
//
//            val newJson2 = Gson().toJson(newJson)
//            Log.d(TAG, "newJson2 $newJson2")
//            val typeToken = object : TypeToken<Array<String>>() {}.type
//            val array1 = Gson().fromJson<Array<String>>(newJson, typeToken)
//            Log.d(TAG, "array1 $array1")
}