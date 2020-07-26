package com.jdroid.gradle.unusedandroidcode.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jdroid.gradle.unusedandroidcode.sample.databinding.UsedInActivityAsDatabindingLayoutBinding

class DataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Test for generated object from layout
        val binding = UsedInActivityAsDatabindingLayoutBinding.inflate(layoutInflater)
    }
}
