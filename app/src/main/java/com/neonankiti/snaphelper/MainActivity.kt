package com.neonankiti.snaphelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = RecyclerViewAdapter()
        val horizontalLinearLayoutManage = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = horizontalLinearLayoutManage
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(recyclerView)
    }
}

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val items = listOf("焼肉", "カレー", "牛丼", "オムライス", "バターコーヒー", "納豆")
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when(holder){
        is ViewHolder -> {
           holder.bind(items[position])
        }
        else -> throw IllegalArgumentException("$holder cannot allowed here")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ViewHolder.create(parent)

}

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(view: ViewGroup) = ViewHolder(LayoutInflater.from(view.context)
                .inflate(R.layout.item_view_content , view, false))
    }

    fun bind(data: String) {
        itemView.findViewById<TextView>(R.id.text).text = data
        val params = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT)
        params.width = 600
        itemView.findViewById<TextView>(R.id.text).layoutParams = params
    }
}
