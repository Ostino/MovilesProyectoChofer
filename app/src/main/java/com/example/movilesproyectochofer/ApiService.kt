package com.example.movilesproyectochofer


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    data class LoginRequest(val email: String, val password: String)

    data class LoginResponse(val access_token: String)

    data class RegisterRequest(
        val name: String,
        val email: String,
        val password: String,
        val role: Int = 2 // Role fijo para usuarios chofer
    )

    data class Product(
        val id: Int,
        val name: String,
        val description: String,
        val price: String,
        val restaurant_id: Int,
        val image: String
    )
    data class Pedido(
        val id: Int,
        val user_id: Int,
        val restaurant_id: Int,
        val total: String,
        val latitude: String,
        val longitude: String,
        val address: String,
        val driver_id: Int?,
        val status: String,
        val created_at: String,
        val delivery_proof: String,
        val order_details: List<Detalle>
    )
    data class Detalle(
        val id: Int,
        val quantity: Int,
        val price: String,
        val product: Product
    )

    @POST("users/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("users")
    fun register(@Body request: RegisterRequest): Call<Unit>

    @GET("orders/free")
    fun getOrders(
        @Header("Authorization") authorization: String
    ): Call<List<Pedido>>
}