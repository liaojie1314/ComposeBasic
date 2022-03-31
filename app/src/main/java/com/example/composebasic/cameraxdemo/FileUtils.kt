package com.example.composebasic.cameraxdemo

import android.content.Context
import java.io.File

interface FileUtils {
    fun createDirectoryIfNotExist(context:Context)
    fun createFile(context: Context):File
}