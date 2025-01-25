package com.example.greetingcard.data.model.dto.user

data class UserDTO(
    var userId: String,
    var userName: String = "",
    var password: String,
    var email: String = "",
) {
    companion object {
        fun from(
            userId: String,
            userName: String = "",
            password: String,
            email: String = ""
        ): UserDTO {
            return UserDTO(
                userId = userId,
                userName = userName,
                password = password,
                email = email
            )
        }
    }
}
