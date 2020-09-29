package com.drak.http.cookies

import okhttp3.Cookie
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

class OkHttpCookies : Serializable {

    private lateinit var cookies: Cookie
    private var clientCookies: Cookie? = null

    private constructor()

    constructor(cookies: Cookie?) {
        this.cookies = cookies!!
    }

    fun getCookies(): Cookie? {
        var bestCookies: Cookie = cookies
        if (clientCookies != null) {
            bestCookies = clientCookies as Cookie
        }
        return bestCookies
    }

    @Throws(IOException::class)
    private fun writeObject(out: ObjectOutputStream) {
        out.writeObject(cookies.name())
        out.writeObject(cookies.value())
        out.writeLong(cookies.expiresAt())
        out.writeObject(cookies.domain())
        out.writeObject(cookies.path())
        out.writeBoolean(cookies.secure())
        out.writeBoolean(cookies.httpOnly())
        out.writeBoolean(cookies.hostOnly())
        out.writeBoolean(cookies.persistent())
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(inputStream: ObjectInputStream) {
        val name = inputStream.readObject() as String
        val value = inputStream.readObject() as String
        val expiresAt = inputStream.readLong()
        val domain = inputStream.readObject() as String
        val path = inputStream.readObject() as String
        val secure = inputStream.readBoolean()
        val httpOnly = inputStream.readBoolean()
        val hostOnly = inputStream.readBoolean()
        var builder = Cookie.Builder()
        builder = builder.name(name)
        builder = builder.value(value)
        builder = builder.expiresAt(expiresAt)
        builder = if (hostOnly) builder.hostOnlyDomain(domain) else builder.domain(domain)
        builder = builder.path(path)
        builder = if (secure) builder.secure() else builder
        builder = if (httpOnly) builder.httpOnly() else builder
        clientCookies = builder.build()
    }
}