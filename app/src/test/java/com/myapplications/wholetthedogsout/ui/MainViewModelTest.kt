package com.myapplications.wholetthedogsout.ui

import android.content.Context
import com.myapplications.wholetthedogsout.data.UrlsRepository
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest{

    @Mock
    private lateinit var mockContext : Context

    @Test
    fun getSumFromUrlTest(){
        mockContext = mock(Context::class.java)
        val repository = UrlsRepository(mockContext)
        val viewModel = MainViewModel(repository)
        val url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1126.jpg"
        val result = viewModel.getSumFromUrl(url)
        assertEquals(10,result)
    }

}