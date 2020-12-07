package com.example.testandroidfetures.tryandroidnotif.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.testandroidfetures.R

val sharedPrefFile = "kotlinsharedpreference"


class ListViewModelAdapter(val context: Context, val listModelArrayList: ArrayList<ListViewModel>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val channel: ViewHolder

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.rows, parent, false)
            channel = ViewHolder(view)
            view.tag = channel
        } else {
            view = convertView
            channel = view.tag as ViewHolder
        }

        channel.ChannleId.text = listModelArrayList[position].channel_id
        channel.allowChannel.isChecked = listModelArrayList[position].allow_channel!!

        channel.allowChannel.setOnClickListener {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean(channel.ChannleId.text.toString(), channel.allowChannel.isChecked)
            editor.apply()
            editor.commit()
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return listModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listModelArrayList.size
    }
}

private class ViewHolder(view: View?) {
    val ChannleId: TextView = view?.findViewById<TextView>(R.id.channel_id) as TextView
    val allowChannel = view?.findViewById<CheckBox>(R.id.allow_channel) as CheckBox
}