package hr.project.mrcook.factory

import java.net.URL
import javax.net.ssl.HttpsURLConnection

private const val TIMEOUT = 10000
private const val METHOD ="GET"
private const val USER_AGENT = "User-Agent"
private const val MOZILLA = "Mozilla/5.0"

fun createGetHttpUrlConnection(path: String): HttpsURLConnection {
    val url = URL(path)
    return (url.openConnection() as HttpsURLConnection).apply {
        connectTimeout = TIMEOUT
        readTimeout = TIMEOUT
        requestMethod = METHOD
        addRequestProperty(USER_AGENT, MOZILLA)
    }
}