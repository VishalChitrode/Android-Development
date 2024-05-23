package com.example.androidarchitecturecomponents

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.example.androidarchitecturecomponents.ApiClient.client
import com.example.androidarchitecturecomponents.databinding.ActivityMainBinding
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var coinGeckoApi: CoinGeckoApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        coinGeckoApi = client!!.create(CoinGeckoApi::class.java)

        binding.fetchPriceButton.setOnClickListener {
            bitcoinPrice()
        }

        val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false)
        setAppTheme(isDarkTheme)

        binding.toggleButton.setOnClickListener {
            toggleTheme()
        }
    }

    private fun bitcoinPrice() {
        val call = coinGeckoApi!!.getPrice("bitcoin", "usd")
        call!!.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                if (response.isSuccessful) {
                    val jsonObject = response.body()
                    if (jsonObject != null) {
                        val price = jsonObject.getAsJsonObject("bitcoin")["usd"].asString
                        Log.d("MainActivity", "Bitcoin price: $price")
                        val editablePrice = Editable.Factory.getInstance().newEditable(price)
                        binding.priceTextView.text = editablePrice
                        Toast.makeText(
                            this@MainActivity,
                            "Bitcoin price: $price",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {
                Log.e("MainActivity", "Error fetching price", t)
            }
        })
    }

    private fun toggleTheme() {
        val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false)
        val newIsDarkTheme = !isDarkTheme
        sharedPreferences.edit().putBoolean("isDarkTheme", newIsDarkTheme).apply()
        setAppTheme(newIsDarkTheme)

        // Restart the activity with a fade animation
        restartActivityWithAnimation()
    }

    private fun setAppTheme(isDarkTheme: Boolean) {
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun restartActivityWithAnimation() {
        val intent = Intent(this, MainActivity::class.java)
        val options = ActivityOptionsCompat.makeCustomAnimation(
            this,
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
        finish()

        // Optionally, delay the restart slightly to ensure the theme change is applied
        Handler(Looper.getMainLooper()).postDelayed({
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }, 100)
    }
}
