
package com.example.testandroidfetures.audio.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.testandroidfetures.R
import com.example.testandroidfetures.audio.deleteRecord
import com.example.testandroidfetures.audio.playRecord
import com.example.tryandroidnotif.com.example.recordaudio.deleteRecord
import com.example.tryandroidnotif.com.example.recordaudio.playRecord

class ListViewModelAdapter(val context: Context, val listModelArrayList: ArrayList<ListViewModel>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val record: ViewHolder

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.record_list, null, false)
            record = ViewHolder(view)
            view.tag = record
        } else {
            view = convertView
            record = view.tag as ViewHolder
        }

        record.rec_name.text = listModelArrayList[position].record_name

        record.keep_rec.setOnClickListener {
            deleteRecord(name = "", paths = listModelArrayList[position].id)
        }
        record.rec_name.setOnClickListener {
            playRecord(name= "", paths = listModelArrayList[position].id)
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
    val rec_name: TextView = view?.findViewById<TextView>(R.id.rec_name) as TextView
    val play_rec : ImageView = view?.findViewById(R.id.play) as ImageView
    val keep_rec : ImageView = view?.findViewById<ImageView>(R.id.keep) as ImageView
}