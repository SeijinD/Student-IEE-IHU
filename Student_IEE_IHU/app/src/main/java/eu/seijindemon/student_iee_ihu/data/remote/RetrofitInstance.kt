package eu.seijindemon.student_iee_ihu.data.remote

import retrofit2.Retrofit
import eu.seijindemon.student_iee_ihu.utils.Constants.Companion.BASE_URL_REMOTE_DATABASE
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val client = OkHttpClient.Builder()
        .addInterceptor(ResponseInterceptor())
        .callTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_REMOTE_DATABASE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val dbApi: DbApi by lazy {
        retrofit.create(DbApi::class.java)
    }

}