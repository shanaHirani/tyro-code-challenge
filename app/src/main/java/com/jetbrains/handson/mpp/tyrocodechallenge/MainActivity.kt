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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val search = menu?.findItem(R.id.search_movie)
        val searchview = search?.actionView as SearchView
        searchview.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchview.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchview.clearFocus()
                searchview.setQuery("",false)
                search.collapseActionView()
                query?.let { Log.i("sss", it) }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }
}