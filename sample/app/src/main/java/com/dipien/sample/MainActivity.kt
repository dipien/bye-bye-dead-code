package com.dipien.sample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.used_in_activity_layout)

        val fab = findViewById<ImageView>(R.id.fab)
        fab.setImageResource(R.drawable.used_in_kotlin_drawable)
        fab.setImageResource(R.drawable
                .used_in_kotlin_with_linebreak_drawable)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.used_in_kotlin_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.used_in_kotlin_anim, R.anim.used_in_kotlin_anim)
    }
}
