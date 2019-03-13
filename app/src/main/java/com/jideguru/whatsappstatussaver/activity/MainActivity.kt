package com.jideguru.whatsappstatussaver.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.jideguru.whatsappstatussaver.R
import com.jideguru.whatsappstatussaver.adapter.ImageAdapter
import com.jideguru.whatsappstatussaver.util.ManagePermissions
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var layoutManager: GridLayoutManager
    private val PermissionsRequestCode = 123
    private lateinit var managePermissions: ManagePermissions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = listOf<String>(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        managePermissions = ManagePermissions(this,list,PermissionsRequestCode)
        managePermissions.checkPermissions()


        val StatusDirectory = "${Environment.getExternalStorageDirectory()}/WAStatus"
        try{
            File(StatusDirectory).mkdirs()
        }catch (e: Exception){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()

            Toast.makeText(this, "Something Went wrong. Provide application with storage access", Toast.LENGTH_SHORT).show()
        }


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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // actions on click menu1 items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_info -> {

            // Initialize a new instance of
            val builder = AlertDialog.Builder(this@MainActivity)

            // Set the alert dialog title
            builder.setTitle("Info")

            // Display a message on alert dialog
            builder.setMessage("Made By JideGuru with Kotlin")

            // Set a positive button and its click listener on alert dialog
//            builder.setPositiveButton("YES"){dialog, which ->
//                // Do something when user press the positive button
//                Toast.makeText(applicationContext,"Ok, we change the app background.",Toast.LENGTH_SHORT).show()
//
//                // Change the app background color
//                root_layout.setBackgroundColor(Color.RED)
//            }


//            // Display a negative button on alert dialog
//            builder.setNegativeButton("No"){dialog,which ->
//                Toast.makeText(applicationContext,"You are not agree.",Toast.LENGTH_SHORT).show()
//            }


            // Display a neutral button on alert dialog
            builder.setNeutralButton("OK"){_,_ ->
//                Toast.makeText(applicationContext,"You cancelled the dialog.",Toast.LENGTH_SHORT).show()
            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
            true
        }
        R.id.action_download -> {
            val intent = Intent(this, DownloadsActivity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                   grantResults: IntArray) {
        when (requestCode) {
            PermissionsRequestCode ->{
                val isPermissionsGranted = managePermissions
                    .processPermissionsResult(requestCode,permissions,grantResults)

                if(isPermissionsGranted){
                    // Do the task now
                    toast("Permissions granted.")
                }else{
                    toast("Permissions denied.")
                }
                return
            }
        }
    }
}
