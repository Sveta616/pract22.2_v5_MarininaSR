package com.bignerdranch.android.pract22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class CurrencyRate : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_rate)
        textView = findViewById(R.id.textViewResult)
        button = findViewById(R.id.buttonResult)
    }
    fun fetchCurrencyRate(view: View) {
        val url = "https://currate.ru/api/?get=rates&pairs=USDRUB&key=ebcb1260977673bbec98c665d7e073d9&date=2024-09-26T15:00:00"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
           { response ->
                val jsonResponse = JSONObject(response)
                val status = jsonResponse.getInt("status")
               Log.d("MyLog", "Responce:${status}")
                val message = jsonResponse.getString("message")
               Log.d("MyLog", "Responce:${message}")
                val rate = jsonResponse.getJSONObject("data").getString("USDRUB")
               Log.d("MyLog", "Responce:${rate}")

                textView.text = "Статус: $status\nСообщение: $message\nКурс USD к RUB: $rate"
            },
            {  Log.d("MyLog","Volley error:$it")

            }
        )
        queue.add(stringRequest)
    }
}


