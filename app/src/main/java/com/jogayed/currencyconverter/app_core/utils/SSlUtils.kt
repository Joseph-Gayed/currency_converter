package com.jogayed.currencyconverter.app_core.utils

import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


fun getSocketFactory(): SSLSocketFactory {
    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(null, getTrustAllManager(), SecureRandom())
    return sslContext.socketFactory
}


fun getTrustAllManager(): Array<TrustManager> {
    return arrayOf(
        object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }

        }
    )
}

fun getX509TrustManager(): X509TrustManager {
    val trustAllCerts = getTrustAllManager()
    return trustAllCerts[0] as X509TrustManager
}