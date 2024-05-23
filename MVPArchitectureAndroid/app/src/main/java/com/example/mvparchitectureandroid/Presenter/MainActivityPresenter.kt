package com.example.mvparchitectureandroid.Presenter

import com.example.mvparchitectureandroid.Contract.ContractInterface
import com.example.mvparchitectureandroid.MainActivity
import com.example.mvparchitectureandroid.Model.MainActivityModel


class MainActivityPresenter(_view: MainActivity): ContractInterface.Presenter {

    private var view: MainActivity = _view
    private var model: ContractInterface.Model = MainActivityModel()

    init {
        view.initView()
    }

    override fun incrementValue() {
        model.incrementCounter()
        view.updateViewData()
    }

    override fun getCounter() = model.getCounter().toString()

}