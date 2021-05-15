package edu.uw.wensix.dotify.repository

import edu.uw.wensix.dotify.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

//https://raw.githubusercontent.com/echeeUW/codesnippets/master/user_info.json

class DataRepository {

    private val userService = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserService::class.java)

    suspend fun getUser() = userService.getUser()

}

interface UserService {
    @GET("echeeUW/codesnippets/master/user_info.json")
    suspend fun getUser(): User
}