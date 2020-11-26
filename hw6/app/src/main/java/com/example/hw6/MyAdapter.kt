package com.example.hw6

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter constructor(private val data:ArrayList<MainActivity.Data>,private val view:Int )
    : BaseAdapter() {
    private class ViewHolder(v: View) {
        val imageView: ImageView = v.findViewById(R.id.imageView)
        val name: TextView = v.findViewById(R.id.name)
    }

    override fun getCount() = data.size
    override fun getItem(position: Int) = data[position]
    override fun getItemId(position: Int) = 0L
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view1: View
        val holder: ViewHolder

        if (convertView == null) {
            view1 = View.inflate(parent.context, view, null)
            holder = ViewHolder(view1)
            view1.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view1 = convertView
        }
        holder.imageView.setImageResource(data[position].photo)
        holder.name.text = data[position].name

        return view1

    }
}