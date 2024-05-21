package com.example.quotes

import android.content.Context
import androidx.lifecycle.ViewModel
import java.util.Arrays

class MainViewModel(val context: Context):ViewModel() {
    private var _count = 0
    private var quotelist : Array<Quote> = emptyArray()

    init {
        quotelist = loadQuote()
    }

    private fun loadQuote(): Array<Quote> {
return quotelist
    }
}