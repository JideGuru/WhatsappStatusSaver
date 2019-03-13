package com.jideguru.whatsappstatussaver.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.jideguru.whatsappstatussaver.R
import com.jideguru.whatsappstatussaver.adapter.ImageAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {


    private lateinit var layoutManager: GridLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val WAdirectory = "${Environment.getExternalStorageDirectory()}/WhatsApp"
        val WABdirectory = "${Environment.getExternalStorageDirectory()}/WhatsApp Business"

        val WAStatus = "${Environment.getExternalStorageDirectory()}/WhatsApp/Media/.Statuses"
        val WABStatus = "${Environment.getExternalStorageDirectory()}/WhatsApp Business/Media/.Statuses"


        var WA = File(WAdirectory)
        var WAB = File(WABdirectory)

        layoutManager = GridLayoutManager(this, 2)
        rv.layoutManager = layoutManager


        if(WA.isDirectory){
            Toast.makeText(this, "WhatsApp is detected", Toast.LENGTH_SHORT).show()
            val files= fileReader(File(WAStatus))
            rv.adapter= ImageAdapter(files, this)
        }else if(WAB.isDirectory ){
            Toast.makeText(this, "WhatsApp is detected Business", Toast.LENGTH_SHORT).show()
            val files= fileReader(File(WABStatus))
            rv.adapter= ImageAdapter(files, this)
        } else{
            Toast.makeText(this, "Please install WhatsApp to use this app", Toast.LENGTH_SHORT).show()
        }

    }

    fun fileReader(dir: File): Array<File> {
//        var dir = File(root)
//        val files = Arrays.asList(dir.list())
        val files = dir.listFiles()
//        Log.i("DIRECTORY", files.toString())

        for (i in 0..files.size-1){
            Log.i("DIRECTORY", files[i].path)
        }

        return files
    }
}
