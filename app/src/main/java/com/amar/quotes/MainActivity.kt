package com.amar.quotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.amar.quotes.Factory.MainViewModelFactory
import com.amar.quotes.Model.Quote
import com.amar.quotes.ViewModel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var tvQuote: TextView;
    private lateinit var tvTitle: TextView;
    private lateinit var btnPrevious: AppCompatButton;
    private lateinit var btnNext: AppCompatButton;
    private lateinit var btnShare: FloatingActionButton;

    private lateinit var mainViewModel: MainViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvQuote     = findViewById(R.id.tvQuotes);
        tvTitle     = findViewById(R.id.tvTitle);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext     = findViewById(R.id.btnNext);
        btnShare    = findViewById(R.id.btnShare);

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application))[MainViewModel::class.java]

        setQuote(mainViewModel.getQuote())


        btnPrevious.setOnClickListener {
            setQuote(mainViewModel.previousQuote());
        }
        btnNext.setOnClickListener {
            setQuote(mainViewModel.nextQuote());
        }
        btnShare.setOnClickListener{
            val quote: String = "${mainViewModel.getQuote()?.text}\n\n '${mainViewModel.getQuote()?.author}'"
            val intent: Intent = Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, quote);
            startActivity(intent);
        }

    }

    private fun setQuote(quote: Quote?){
        tvTitle.text = quote?.author;
        tvQuote.text = quote?.text;
    }

}