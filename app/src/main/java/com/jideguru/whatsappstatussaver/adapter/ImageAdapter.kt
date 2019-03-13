package com.jideguru.whatsappstatussaver.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.img_item.view.*
import java.io.File
import android.graphics.BitmapFactory



class ImageAdapter(val items : Array<File>, val context: Context) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(com.jideguru.whatsappstatussaver.R.layout.img_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.filePath?.text = items[position].name

        val myBitmap = BitmapFactory.decodeFile(items[position].absolutePath) //this is the bitmap for the image

        holder?.fileThumb?.setImageBitmap(myBitmap)//image set to the image view
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val filePath = view.txtView
        val fileThumb = view.preview
    }

}

