package com.myapplications.wholetthedogsout.ui

import androidx.lifecycle.*
import com.myapplications.wholetthedogsout.data.UrlsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val urlsRepository: UrlsRepository) : ViewModel() {

    private val dogUrls = urlsRepository.dogUrls

    val dogUrlsAndSums : LiveData<List<Pair<String,Int>>> = Transformations.map(dogUrls){ urlsList ->
        val listUrlsAndSums : MutableList<Pair<String,Int>> = mutableListOf()
        urlsList.forEach { url ->
            listUrlsAndSums.add(Pair(url, getSumFromUrl(url)))
        }
        listUrlsAndSums
    }

    fun loadPhotos(){
        viewModelScope.launch {
            urlsRepository.getListOfUrlsFromJson()
        }
    }

    fun getSumFromUrl(url: String): Int {
        val numberString = url.substringAfterLast("_").substringBeforeLast(".jpg")
        return numberString.sumOf { it.digitToInt() }
    }
}