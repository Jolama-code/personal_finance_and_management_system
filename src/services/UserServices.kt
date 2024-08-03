package services

import models.User

object UserServices {
    private val users = mutableListOf<User>()


    fun register(username: String, password: String): Boolean {
        if (users.any { it.username == username }) return false
        users.add(User(username, password))
        return true
    }


    fun login(username: String, password: String): Boolean {
        return users.any { it.username == username && it.password == password }
    }


}