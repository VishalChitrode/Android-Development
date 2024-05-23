package com.example.mvp

import android.graphics.ColorSpace
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.jetbrains.annotations.Contract
import com.example.mvp.presenter.Presenter
import com.example.mvp.models.Model

class MainActivity : AppCompatActivity(), Contract.View {
    //    private lateinit var textView: TextView
    private lateinit var button: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var imageView: ImageView

    private var presenter: Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)
        imageView = findViewById(R.id.imageView)

        // instantiating object of Presenter Interface
        presenter = Presenter(this, ColorSpace.get(ColorSpace.Named.SRGB))

        this.button.setOnClickListener { presenter!!.onButtonClick() }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.onDestroy()
    }

    // method to display the Course Detail TextView
    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        imageView.visibility = View.INVISIBLE
    }

    // method to hide the Course Detail TextView
    override fun hideProgress() {
        progressBar.visibility = View.GONE
        imageView.visibility = View.VISIBLE
    }

    // method to set random string
    // in the Course Detail TextView
//    override fun setString(string: String?) {
//        textView.text = string
//    }

    override fun setImage(string: String?) {
        Glide.with(this)
            .load(string)
            .into(imageView)
    }
}

package com.example.mvp.presenter

interface Contract {
    interface View {
        // method to display progress bar
        // when next random course details
        // is being fetched
        fun showProgress()

        // method to hide progress bar
        // when next random course details
        // is being fetched
        fun hideProgress()

        // method to set random
        // text on the TextView
//        fun setString(string: String?)

        fun setImage(string: String?)
    }

    interface Model {
        // nested interface to be
        interface OnFinishedListener {
            // function to be called
            // once the Handler of Model class
            // completes its execution
            fun onFinished(string: String?)
        }

        fun getNextCourse(onFinishedListener: OnFinishedListener?)
    }

    interface Presenter {
        // method to be called when
        // the button is clicked
        fun onButtonClick()

        // method to destroy
        // lifecycle of MainActivity
        fun onDestroy()
    }
}