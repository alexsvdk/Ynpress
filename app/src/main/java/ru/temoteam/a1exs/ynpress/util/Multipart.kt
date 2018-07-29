package ru.temoteam.a1exs.ynpress.util

import org.jsoup.helper.HttpConnection
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class Multipart
/**
 * This constructor initializes a new HTTP POST request with content type
 * is set to multipart/form-data
 * @param url
 * *
 * @throws IOException
 */
@Throws(IOException::class)
constructor(url: URL, cookie:String="") {

    @Throws(IOException::class)
    constructor(url: String, cookie:String="") : this(URL(url),cookie)

    companion object {
        private const val LINE_FEED = "\r\n"
        private const val maxBufferSize = 1024 * 1024
        private const val charset = "UTF-8"
    }

    // creates a unique boundary based on time stamp
    private val boundary: String = "----WebKitFormBoundary" + System.currentTimeMillis().toString()
    private val httpConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
    private val outputStream: OutputStream
    private val writer: PrintWriter

    init {
        HttpURLConnection.setFollowRedirects(false)

        httpConnection.setRequestProperty("Accept-Charset", "UTF-8")
        httpConnection.setRequestProperty("Connection", "Keep-Alive")
        httpConnection.setRequestProperty("Cache-Control", "no-cache")
        httpConnection.setRequestProperty("Cookie",cookie)
        httpConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=$boundary")

        httpConnection.setChunkedStreamingMode(maxBufferSize)
        httpConnection.doInput = true
        httpConnection.doOutput = true    // indicates POST method
        httpConnection.useCaches = false
        httpConnection.instanceFollowRedirects=false
        outputStream = httpConnection.outputStream
        //print("Content-Type: multipart/form-data; boundary=$boundary\n")
        writer = PrintWriter(OutputStreamWriter(outputStream, charset), true)


    }

    /**
     * Adds a form field to the request
     * @param name  field name
     * *
     * @param value field value
     */
    fun addFormField(name: String, value: String) {
        writer.append("--").append(boundary).append(LINE_FEED)
        writer.append("Content-Disposition: form-data; name=\"").append(name).append("\"")
                .append(LINE_FEED)
        writer.append(LINE_FEED)
        writer.append(value).append(LINE_FEED)
        writer.flush()
    }

    fun addFormFields(fields: Map<String,String>) {
        fields.forEach { k, v -> addFormField(k,v) }
    }

    /**
     * Adds a upload file section to the request
     * @param fieldName  - name attribute in <input type="file" name="..."></input>
     * *
     * @param uploadFile - a File to be uploaded
     * *
     * @throws IOException
     */
    @Throws(IOException::class)
    fun addFilePart(fieldName: String, uploadFile: File, fileName: String, fileType: String) {
        writer.append("--").append(boundary).append(LINE_FEED)
        writer.append("Content-Disposition: file; name=\"").append(fieldName)
                .append("\"; filename=\"").append(fileName).append("\"").append(LINE_FEED)
        writer.append("Content-Type: ").append(fileType).append(LINE_FEED)
        writer.append(LINE_FEED)
        writer.flush()

        val inputStream = FileInputStream(uploadFile)
        inputStream.copyTo(outputStream, maxBufferSize)

        outputStream.flush()
        inputStream.close()
        writer.append(LINE_FEED)
        writer.flush()
    }

    /**
     * Adds a header field to the request.
     * @param name  - name of the header field
     * *
     * @param value - value of the header field
     */
    fun addHeaderField(name: String, value: String) {
        writer.append("$name: $value").append(LINE_FEED)
        writer.flush()
    }

    fun addHeaderFields(fields: Map<String,String>) {
        fields.forEach { k, v -> addHeaderField(k,v) }
    }

    /**
     * Upload the file and receive a response from the server.
     * @param onFileUploadedListener
     * *
     * @throws IOException
     */
    @Throws(IOException::class)
    fun upload():Response {
        writer.append(LINE_FEED).flush()
        writer.append("--").append(boundary).append("--").append(LINE_FEED)
        writer.close()

        // checks server's status code first
        val status = httpConnection.responseCode
        val cookie = httpConnection.getHeaderField("Set-Cookie")
        return if (status == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(InputStreamReader(httpConnection.inputStream))
            val response = reader.use(BufferedReader::readText)

            httpConnection.disconnect()
            Response(status,response,cookie)
        } else {
            Response(status,null,cookie)
        }
    }

    data class Response(
            val statusCode: Int,
            val text: String?,
            val setCoocie: String?
    )

}