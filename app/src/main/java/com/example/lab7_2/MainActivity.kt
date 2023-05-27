package com.example.lab7_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.example.lab7_2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder().
        baseUrl("https://api.coindesk.com").
        addConverterFactory(GsonConverterFactory.create()).
        build()

        val infoAPI = retrofit.create(InfoAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val info = infoAPI.getInformation()
            runOnUiThread{
                val value = info.bpi.USD.rate.substringBefore(".") + " " + info.bpi.USD.code

                binding.tvDate.text = info.time.updated
                binding.tvValue.text = value
            }
        }

        binding.ivBitcoin.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch {
                val info = infoAPI.getInformation()
                runOnUiThread{
                    val spinner: Spinner = binding.spinner
                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            val selectedItem = parent?.getItemAtPosition(position).toString()

                            if(selectedItem == "USD"){
                                val value = info.bpi.USD.rate.substringBefore(".") + " " + info.bpi.USD.code
                                binding.tvValue.text = value
                            }else  if(selectedItem == "GBP"){
                                val value = info.bpi.GBP.rate.substringBefore(".") + " " + info.bpi.GBP.code
                                binding.tvValue.text = value
                            }else if(selectedItem == "EUR"){
                                val value = info.bpi.EUR.rate.substringBefore(".") + " " + info.bpi.EUR.code
                                binding.tvValue.text = value
                            }
                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
                    binding.tvDate.text = info.time.updated
            }
        }
    }
}
}