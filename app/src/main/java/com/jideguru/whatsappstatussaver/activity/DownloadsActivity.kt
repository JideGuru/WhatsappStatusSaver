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

class DownloadsActivity : AppCompatActivity() {

    private lateinit var layoutManager: GridLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_downloads)

        setTitle("Downloads")
        val StatusDirectory = "${Environment.getExternalStorageDirectory()}/WAStatus"
        var WA = File(StatusDirectory)
        layoutManager = GridLayoutManager(this, 2)
        rv.layoutManager = layoutManager


        if(!WA.exists()){
            Toast.makeText(this, "No Status Saved", Toast.LENGTH_SHORT).show()
        }else{
            val files= fileReader(WA)
            rv.adapter= ImageAdapter(files, this)
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
