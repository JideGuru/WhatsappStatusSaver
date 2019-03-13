package com.jideguru.whatsappstatussaver.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.img_item.view.*
import java.io.File
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.R.attr.path
import android.graphics.Color
import android.media.ThumbnailUtils
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


class ImageAdapter(val items : Array<File>, val context: Context) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(com.jideguru.whatsappstatussaver.R.layout.img_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.filePath?.text = items[position].name
//        Toast.makeText(context, items[position].extension, Toast.LENGTH_LONG).show()

        if(items[position].extension == "mp4"){
            val thumb = ThumbnailUtils.createVideoThumbnail(
                items[position].absolutePath,
                MediaStore.Images.Thumbnails.MINI_KIND
            )
            holder.fileThumb?.setImageBitmap(thumb)//image set to the image view
        }else{
            val myBitmap = BitmapFactory.decodeFile(items[position].absolutePath) //this is the bitmap for the image

            holder.fileThumb?.setImageBitmap(myBitmap)//image set to the image view
        }

        holder.fileThumb.setOnClickListener {
            val builder = AlertDialog.Builder(context)

            builder.setTitle("Info")

            builder.setMessage("Save this Status update?")

            builder.setPositiveButton("Save"){dialog, which ->
                val StatusDirectory = "${Environment.getExternalStorageDirectory()}/WAStatus/${items[position].name}"
                File(items[position].absolutePath).copyTo(File(StatusDirectory))

            }


            builder.setNegativeButton("View"){dialog,which ->
            }


            builder.setNeutralButton("Cancel"){_,_ ->
            }

            val dialog: AlertDialog = builder.create()

            dialog.show()
        }

        fun File.copyTo(file: File) {
            inputStream().use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val filePath = itemView.txtView
        val fileThumb = itemView.preview


    }

}

