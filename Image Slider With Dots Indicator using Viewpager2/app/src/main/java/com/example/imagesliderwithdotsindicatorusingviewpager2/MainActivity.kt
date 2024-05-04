package com.example.imagesliderwithdotsindicatorusingviewpager2

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.imagesliderwithdotsindicatorusingviewpager2.adapters.ImageAdapter
import com.example.imagesliderwithdotsindicatorusingviewpager2.databinding.ActivityMainBinding
import com.example.imagesliderwithdotsindicatorusingviewpager2.model.ImageItem
import java.util.UUID

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var ViewPager2: ViewPager2
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback


    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply{
        setMargins(8,0,8,0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ViewPager2 = binding.ViewPager2

        val imageList = arrayListOf(

              ImageItem(
                UUID.randomUUID().toString(),
                  "android.resource://" + packageName + "/" + R.drawable.banner
            ),ImageItem(
                UUID.randomUUID().toString(),
                  "android.resource://" + packageName + "/" + R.drawable.banner
            ),ImageItem(
                UUID.randomUUID().toString(),
                  "android.resource://" + packageName + "/" + R.drawable.banner
            ),ImageItem(
                UUID.randomUUID().toString(),
                  "android.resource://" + packageName + "/" + R.drawable.banner
            )
            )

        val imageAdapter = ImageAdapter()
        ViewPager2.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        val slidedot = binding.Slider

        val slideDotLL = findViewById<LinearLayout>(com.google.android.material.R.id.slide)
        val dotsImage = Array(imageList.size) { ImageView( this)}
        dotsImage.forEach {
            it.setImageResource(
                R.drawable.non_active_item
            )
            slidedot.addView(it,params)
        }

        dotsImage[0].setImageResource(R.drawable.active_item)


        pageChangeListener = object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected (position: Int) {
                dotsImage.mapIndexed{ index, imageView ->
                    if (position == index){
                        imageView.setImageResource(R.drawable.active_item)
                    }else
                    {
                        imageView.setImageResource(R.drawable.non_active_item)
                    }

                }
            super.onPageSelected(position)
            }
        }
        ViewPager2.registerOnPageChangeCallback(pageChangeListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        ViewPager2.unregisterOnPageChangeCallback(pageChangeListener)
    }

}