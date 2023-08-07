package com.example.marsapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.MarsViewModel
import com.example.marsapp.Model.MarsApiClass
import com.example.myapp58.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var _Binding: ActivityMainBinding
    private val marsViewModel: MarsViewModel by viewModels()
    private lateinit var marsList:List<MarsApiClass>
    private lateinit var recycler: RecyclerView
    private lateinit var itemAdapter: AdapterMars

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_Binding.root)
        recycler = _Binding.RecyclerView

        marsViewModel.getMars().observe(this, Observer { mars ->
            marsList= mars ?: emptyList()
            Log.d("MainActivity", "Cantidad de usuarios: ${marsList.size}")
            recycler.layoutManager=GridLayoutManager(this,2)
            itemAdapter= AdapterMars(marsList)
            recycler.adapter=itemAdapter
        })


    }
}