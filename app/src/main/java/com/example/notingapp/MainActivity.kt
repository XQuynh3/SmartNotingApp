package com.example.notingapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.notingapp.data.AppDatabase
import com.example.notingapp.fragments.HomeFragment
import com.example.notingapp.model.Tag
import com.example.notingapp.network.QuoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var quoteText: TextView
    lateinit var quoteAuthor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        quoteText = findViewById(R.id.quoteText)
        quoteAuthor = findViewById(R.id.quoteAuthor)

        insertDefaultTags()
        loadQuote()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment())
            .commit()
    }

    private fun insertDefaultTags() {

        lifecycleScope.launch(Dispatchers.IO) {

            val dao = AppDatabase.getDatabase(this@MainActivity).tagDao()

            if (dao.count() == 0) {

                dao.insert(Tag(name = "Personal"))
                dao.insert(Tag(name = "Work"))
                dao.insert(Tag(name = "Study"))
                dao.insert(Tag(name = "Idea"))
            }
        }
    }

    private fun loadQuote() {

        lifecycleScope.launch {

            try {

                val quote = QuoteService.api.getRandomQuote()

                quoteText.text = quote.content
                quoteAuthor.text = "- ${quote.author}"

            } catch (e: Exception) {

                quoteText.text = "Stay focused and keep coding."
                quoteAuthor.text = ""
            }
        }
    }
}