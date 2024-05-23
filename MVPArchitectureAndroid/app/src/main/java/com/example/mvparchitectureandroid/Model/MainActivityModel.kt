package com.example.mvparchitectureandroid.Model

import com.example.mvparchitectureandroid.Contract.ContractInterface

class MainActivityModel: ContractInterface.Model {

    private var mCounter = 0

    override fun getCounter()= mCounter

    override fun incrementCounter() {
        mCounter++
    }
}