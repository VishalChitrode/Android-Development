package com.example.mvp

class MainPresenter(private val view: MainView, private val interactor: MainInteractor) {

    fun getData() {
        // Fetch data using the interactor and provide it to the view
        interactor.fetchData(object : MainInteractor.OnFinishedListener {
            override fun onFinished(data: List<DataItem>) {
                view.setData(data)
            }

            override fun onFailure(t: Throwable) {
                view.setDataError(t.message ?: "Unknown Error")
            }
        })
    }

    fun onDestroy() {
        // Clean up resources if needed
    }
}
