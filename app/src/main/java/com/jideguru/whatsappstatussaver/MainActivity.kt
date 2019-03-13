package com.jideguru.whatsappstatussaver

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
//        Log.i("DIRECTORY",WAdirectory)


        var WA = File(WAdirectory)
        var WAB = File(WABdirectory)

//        var fileExists = file.exists()

        when {
            WA.isDirectory -> Toast.makeText(this, "WhatsApp is detected", Toast.LENGTH_SHORT).show()
            WAB.isDirectory -> Toast.makeText(this, "WhatsApp is detected Business", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "Please install Whatsapp to use this app", Toast.LENGTH_SHORT).show()
        }

    }
}
