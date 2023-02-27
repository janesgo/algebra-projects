package hr.project.mrcook.handler

import android.content.Context
import android.util.Log
import hr.project.mrcook.factory.createGetHttpUrlConnection
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.net.ssl.HttpsURLConnection

fun downloadImage(context: Context, title: String, url: String): String? {
    if (!url.isNullOrBlank()) {
        val ext = url.substring(url.lastIndexOf("."))
        val file: File = createFile(context, title, ext)

        try {
            val con: HttpsURLConnection = createGetHttpUrlConnection(url)
            Files.copy(con.inputStream, Paths.get(file.toURI()))
            return file.absolutePath
        } catch (e: Exception) {
            Log.e("Download image", e.message, e)
        }
    }
    return null
}

fun createFile(context: Context, title: String, ext: String): File {
    val dir = context.getExternalFilesDir(null)
    val file = File(dir, File.separator + title + ext)
    if (file.exists()) {
        file.delete()
    }
    return file
}
