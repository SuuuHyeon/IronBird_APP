package com.example.greetingcard.data.dto

data class UserDTO(
    private val userName: String,
    private val password: String
) {
    companion object {
        fun from(userName: String, password: String): UserDTO {
            return UserDTO(userName = userName, password = password)
        }
    }
}
