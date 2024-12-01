package com.example.plantsapp.data.remote

import android.util.Log
import com.example.plantsapp.utils.Env
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import java.net.HttpURLConnection


class HttpInterceptor() : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            var originalRequest = chain.request()
            // Add the Bearer token to the request header
            val modifiedRequest = originalRequest.newBuilder()
            modifiedRequest.header("Content-Type", "application/json")
            modifiedRequest.header("Accept", "application/json")
            if (Env.store.getBoolean("isLogin")) {
                modifiedRequest.header(
                    "Authorization", String.format("Bearer %s", Env.store.getString("access_token"))
                )
                Log.d("TOKEN_INFO", Env.store.getString("token"))
            }
            originalRequest = modifiedRequest.build()
            // return chain.proceed(originalRequest)
            val response = chain.proceed(originalRequest)
            //  val oldRes = response

            if (response.code == HttpURLConnection.HTTP_OK) {
                val responseBody = response.body
                val content = responseBody?.string() ?: ""

                try {
                    Log.d("Requests", "${originalRequest.url}\n$content")
                } catch (e: Exception) {
                    Log.d("Requests", "${e.message}")
                }

                // Rebuild the response body and return the response
                val mediaType = responseBody?.contentType()
                val newResponseBody = content.toResponseBody(mediaType)
                return response.newBuilder().body(newResponseBody).build()


            } else {
                return response
            }
        } catch (e: IOException) {
            throw e
        }

    }
}