package edu.uw.wensix.dotify.repository

import com.ericchee.songdataprovider.Song
import edu.uw.wensix.dotify.model.MusicLibrary
import edu.uw.wensix.dotify.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class DataRepository {

    private val userService = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserService::class.java)

    suspend fun getUser() = userService.getUser()
    suspend fun getSongLibrary() = userService.getSongLibrary()
}

interface UserService {
    @GET("echeeUW/codesnippets/master/user_info.json")
    suspend fun getUser(): User

    @GET("echeeUW/codesnippets/master/musiclibrary.json")
    suspend fun getSongLibrary(): MusicLibrary
}