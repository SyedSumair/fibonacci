package com.sumair.fibonaccichallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumair.fibonaccichallenge.ui.RecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null

    var fibonaccilist = mutableListOf<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        fibonacci(Long.MAX_VALUE)
        val mLayoutManager = LinearLayoutManager(this)
        val recyclerViewAdapter = RecyclerViewAdapter(fibonaccilist);
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.itemAnimator = DefaultItemAnimator()
        mRecyclerView!!.adapter = recyclerViewAdapter
        mRecyclerView!!.adapter?.notifyDataSetChanged()
        mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mLayoutManager.findLastCompletelyVisibleItemPosition() >= mLayoutManager.itemCount - 1) {
                    recyclerViewAdapter.addItems(fibonaccilist)
                }
            }
        })

    }

    private fun fibonacci(n: Long) = GlobalScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.Main) {
            fibonaccilist.add(n)
        }
        if (n > 1){
            fibonacci(n - 1) + fibonacci(n - 2)
        }

    }

    private fun initViews() {
        mRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)

    }



}