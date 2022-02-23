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
    private lateinit var mRecyclerView: RecyclerView

    private var fibonacciList = mutableListOf<Long>()

    private val steps: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        loadFibonacci()

        val mLayoutManager = LinearLayoutManager(this)
        val recyclerViewAdapter = RecyclerViewAdapter(fibonacciList)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        mRecyclerView.adapter = recyclerViewAdapter
        mRecyclerView.adapter?.notifyItemChanged(fibonacciList.size)
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mLayoutManager.findLastCompletelyVisibleItemPosition() >= mLayoutManager.itemCount - 1) {
                    loadFibonacci()
                }
            }
        })
    }

    private fun initViews() {
        mRecyclerView = findViewById(R.id.recyclerView)

    }

    private fun loadFibonacci() = GlobalScope.launch(Dispatchers.Default) {
        var n = fibonacciList.size.toLong()
        val tempList: MutableList<Long> = arrayListOf()
        while (n < steps + fibonacciList.size && steps + fibonacciList.size < Long.MAX_VALUE) {
            val result = fib(n)
            tempList.add(result)
            Thread.sleep(100)
            n++
        }

        withContext(Dispatchers.Main) {
            val adapter = mRecyclerView.adapter as RecyclerViewAdapter
            adapter.addItems(tempList)
        }

    }

    private fun fib(n: Long): Long {
        return if (n <= 1) n
        else fib(n - 1) + fib(n - 2)
    }


}