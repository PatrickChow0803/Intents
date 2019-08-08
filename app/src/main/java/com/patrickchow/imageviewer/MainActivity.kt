package com.patrickchow.imageviewer

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val IMAGE_REQUEST_CODE = 1
    }

    var myList = mutableListOf<ImageData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //On Click listener - To tell the OS that you want content
        image_btn.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            //The content is any image
            intent.type = "image/*"
            //If the device has any applicable applications to solve the problem, then start the new activity
            if(intent.resolveActivity(packageManager)!=null){
                startActivityForResult(intent, IMAGE_REQUEST_CODE)
            }
        }

    }
    fun createTextView(text: String, index: Int):TextView{
        val view = TextView(this)
        view.text = "$text - $index"
        view.textSize = 24f

        return view
    }

    //Continuation from image btn - override onActivityResult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            //Take the data and place it into a variable
            val image = data?.data
            //Create a new object using the data
            val myImage = ImageData(image)
            myList.add(myImage)
            //Create the new textview and add it to the linear layout. Note: myImage.name is a uri that has been converted to a string
            val view = createTextView(myImage.name, 0)
            linear_layout.addView(view)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
