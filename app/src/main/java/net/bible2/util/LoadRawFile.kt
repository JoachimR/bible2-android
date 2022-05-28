package net.bible2.util

import android.content.Context
import androidx.annotation.RawRes
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

fun loadRawFile(context: Context, @RawRes resId: Int): String? {
    var string: String?
    val stringBuilder = StringBuilder()
    val inputStream = context.resources.openRawResource(resId)
    val reader = BufferedReader(InputStreamReader(inputStream))
    try {
        while (reader.readLine().also { string = it } != null) {
            stringBuilder.append(string)
        }
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    } finally {
        try {
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return stringBuilder.toString()
}
