package com.desarrollodroide.simplecleancode.api

import com.desarrollodroide.simplecleancode.model.DummyObject
import com.google.gson.stream.MalformedJsonException
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class ApiServiceTest {

/** Using Koin for inject ApiService

    class ApiServiceKoinTest: KoinTest {

    val service: ApiService by inject()
    private lateinit var server: MockWebServer

    @Before
    fun setup() {
        server = MockWebServer()
        startKoin{ modules(configureTestAppComponent(server.url("/").toString()))}
    }

    @After
    fun teardown() {
        server.close()
        stopKoin()
    }
    ...

**/

    private lateinit var server: MockWebServer
    private lateinit var service: ApiService

    @Before
    fun setup() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun teardown() {
        server.close()
    }

    @Test
    fun testSuccess() = runBlocking<Unit> {
        val sampleResponse = this::class.java.getResource("/sample_response.json")?.readText()?:""
        server.enqueue( MockResponse().setBody(sampleResponse) )
        val response = service.getDataFromServer().body()
        assertEquals(2, response?.size)
        assertEquals("quas fugiat ut perspiciatis vero provident", response?.firstOrNull()?.title)
    }

    @Test
    fun testEmpty() = runBlocking<Unit> {
        val sampleResponse = "[]"
        server.enqueue( MockResponse().setBody(sampleResponse) )
        val response = service.getDataFromServer().body()
        assertEquals(0, response?.size)
    }

    @Test
    fun testServerError400() = runBlocking<Unit> {
        server.enqueue( MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST) )
        val response = service.getDataFromServer().body()
        assertNull(response)
    }
}




