package com.amar.quotes.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.amar.quotes.Model.Quote
import com.google.gson.Gson
import java.io.IOException

class MainViewModel(private val context: Context): ViewModel() {

    private var quoteList: Array<Quote>? = emptyArray();
    private var index: Int = 0;

    init {
        quoteList = loadQuotesFromAsset();
    }

    private fun loadQuotesFromAsset(): Array<Quote>? {
        return try {
            val inputStream = context.assets.open("quotes.json");
            val size: Int = inputStream.available();
            val buffer = ByteArray(size);
            inputStream.read(buffer);
            inputStream.close();

            val json = String(buffer, Charsets.UTF_8)

            val gson = Gson();
            gson.fromJson(json, Array<Quote>::class.java)
        } catch (ex: IOException){
            ex.printStackTrace()
            null
        }
    }

    fun getQuote() = quoteList?.get(index)

    fun nextQuote(): Quote? {
        return if ((index+1)==quoteList?.size){
            quoteList?.get(index)
        } else {
            quoteList?.get(++index)
        }
    }

    fun previousQuote(): Quote?{
        return if (index==0){
            quoteList?.get(index)
        } else {
            quoteList?.get(--index)
        }
    }

}