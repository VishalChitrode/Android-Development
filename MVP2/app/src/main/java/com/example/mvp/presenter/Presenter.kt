package com.example.mvp.presenter


class Presenter(
    private var mainView: Contract.View?,
    private val model: Contract.Model) : Contract.Presenter,
    Contract.Model.OnFinishedListener  {
    override fun onButtonClick() {
        if (mainView != null) {
            mainView!!.showProgress()

        }
        model.getNextCourse(this)
    }

    override fun onDestroy() {
        mainView = null
    }

    // method to return the string
    // which will be displayed in the
    // Course Detail TextView
    override fun onFinished(string: String?) {
        if (mainView != null) {
//            mainView!!.setString(string)
            mainView!!.hideProgress()
            mainView!!.setImage(string)
        }
    }

}