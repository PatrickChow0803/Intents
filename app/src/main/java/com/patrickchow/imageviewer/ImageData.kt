package com.patrickchow.imageviewer

import android.net.Uri
import java.io.Serializable

class ImageData(image: Uri?): Serializable{
    val name = image.toString()
    val toUri = Uri.parse(name)
}
