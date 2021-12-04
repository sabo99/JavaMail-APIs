package com.sabo.javamailapis.Kotlin

import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import javax.activation.DataSource

class ByteArrayDataSource_Kotlin(private val data: ByteArray, private val type: String?): DataSource {
    override fun getInputStream(): InputStream {
        return ByteArrayInputStream(data)
    }

    override fun getOutputStream(): OutputStream {
        throw IOException("Not Supported")
    }

    override fun getContentType(): String {
        return type ?: "application/octet-stream"

    }

    override fun getName(): String {
        return "ByteArrayDataSource"
    }

}