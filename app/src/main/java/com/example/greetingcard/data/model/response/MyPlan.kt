package com.example.greetingcard.data.model.response


data class MyPlan(
    val id: Int,
    val planId: Int,
    val title: String,
    val startDate: String,
    val endDate: String,
    val backgroundImg: String?
)