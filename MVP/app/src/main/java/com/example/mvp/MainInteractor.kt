package com.example.mvp

class MainInteractor {
    interface OnFinishedListener {
        fun onFinished(data: List<DataItem>)
        fun onFailure(t: Throwable)
    }

    fun fetchData(listener: OnFinishedListener) {
        // Simulate fetching data asynchronously
        // This could be a network call, database query, etc.
        try {
            val data = listOf<DataItem>() // Replace with actual data fetching logic
            listener.onFinished(data)
        } catch (e: Exception) {
            listener.onFailure(e)
        }
    }
}
