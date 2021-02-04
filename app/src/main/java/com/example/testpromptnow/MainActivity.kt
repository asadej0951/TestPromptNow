package com.example.testpromptnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.tools.build.jetifier.core.utils.Log
import com.example.testpromptnow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var mAdapterData: AdapterData
    private val data = ArrayList<String>()
    private var positionChek = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setRecyclerView()
        onClickListener()
    }

    private fun onClickListener() {
        viewModel.onClickListener.observe(this, Observer {
            when (it) {
                "Add" -> {
                    data.add(0, binding.edInput.text.toString())
                    mAdapterData.notifyItemInserted(0)
                    mAdapterData.notifyDataSetChanged()
                    binding.edInput.text!!.clear()
                }
                "Delete" -> {
                    for (i in positionChek.indices) {
                        data.removeAt(positionChek[i])
                        mAdapterData.notifyItemRemoved(positionChek[i])
                    }
                    mAdapterData.notifyDataSetChanged()
                    positionChek.clear()
                }
            }
        })
        viewModel.onClickCheck.observe(this, Observer {
           positionChek.add(it)
        })

    }

    private fun setRecyclerView() {
        mAdapterData = AdapterData(data, viewModel.onClickCheck)
        binding.recyclerViewData.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapterData
            mAdapterData.notifyDataSetChanged()
        }
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.dataViewModel = viewModel
    }
}