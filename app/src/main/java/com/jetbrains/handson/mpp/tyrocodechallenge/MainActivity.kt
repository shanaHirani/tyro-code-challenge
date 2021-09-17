package com.jetbrains.handson.mpp.tyrocodechallenge

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.jetbrains.handson.mpp.tyrocodechallenge.databinding.ActivityMainBinding

//
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

    }
}