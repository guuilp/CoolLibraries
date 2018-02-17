package guuilp.github.com.coollibraries.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import guuilp.github.com.coollibraries.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)

        setupListeners()
    }

    private fun setupListeners(){
        movieList.setOnClickListener {
            startActivity<MoviesList>()
        }
    }
}
