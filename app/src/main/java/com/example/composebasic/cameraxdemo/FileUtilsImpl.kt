package com.example.composebasic.cameraxdemo

import android.content.Context
import android.os.Environment
import java.io.File

class FileUtilsImpl : FileUtils {
    companion object {
        private const val IMAGE_PREFIX = "Image_"
        private const val JPG_SUFFIX = ".jpg"
        private const val FOLDER_NAME = "Photo"
    }

    override fun createDirectoryIfNotExist(context: Context) {
        val folder = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath +
                    File.separator + FOLDER_NAME
        )
        if (!folder.exists()) {
            folder.mkdirs()
        }
    }

    override fun createFile(context: Context) = File(
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath +
                File.pathSeparator + FOLDER_NAME + File.separator + IMAGE_PREFIX + System.currentTimeMillis() + JPG_SUFFIX
    )

}