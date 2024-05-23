package com.example.mvp

interface MainView {
    fun setData(arrUpdates: List<DataItem>)
    fun setDataError(strError: String)
}