package com.example.greetingcard.data.model.dto.user

data class UserDTO(
    private val userId: String,
    private val userName: String = "",
    private val password: String,
    private val email: String = "",
) {
    companion object {
        fun from(userId: String, userName: String = "" , password: String, email: String = ""): UserDTO {
            return UserDTO(
                userId = userId,
                userName = userName,
                password = password,
                email = email)
        }
    }
}
