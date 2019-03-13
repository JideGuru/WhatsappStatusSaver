package com.jideguru.whatsappstatussaver

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        File f = new File(Environment.getExternalStorageDirectory() + "/somedir")
//        if(f.isDirectory()) {
//
//        }

        val WAdirectory = "${Environment.getExternalStorageDirectory()}/WhatsApp"
        val WABdirectory = "${Environment.getExternalStorageDirectory()}/WhatsApp Business"

        val WAStatus = "${Environment.getExternalStorageDirectory()}/WhatsApp/Media/.Statuses"
        val WABStatus = "${Environment.getExternalStorageDirectory()}/WhatsApp Business/Media/.Statuses"
//        Log.i("DIRECTORY",WAdirectory)


        var WA = File(WAdirectory)
        var WAB = File(WABdirectory)

//        var fileExists = file.exists()

        if(WA.isDirectory){
            Toast.makeText(this, "WhatsApp is detected", Toast.LENGTH_SHORT).show()
            fileReader(File(WAStatus))
        }else if(WAB.isDirectory ){
            Toast.makeText(this, "WhatsApp is detected Business", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "Please install WhatsApp to use this app", Toast.LENGTH_SHORT).show()
        }

        File(WAStatus).walkTopDown().forEach { Log.i("DIRECTORY", it.toString()) }


    }

    fun fileReader(root: File) {
        val fileList: ArrayList<File> = ArrayList()
        val listAllFiles = root.listFiles()

        if (listAllFiles != null && listAllFiles.size > 0) {
            for (currentFile in listAllFiles) {
                if (currentFile.name.endsWith(".jpeg")) {
                    // File absolute path
                    Log.e("downloadFilePath", currentFile.getAbsolutePath())
                    // File Name
                    Log.e("downloadFileName", currentFile.getName())
                    fileList.add(currentFile.absoluteFile)
                }
            }
            Log.w("fileList", "" + fileList.size)
        }
    }
}
