package com.example.appcitaexpress.data.model

data class User(
    var id: Int,
    var email: String,
    var name: String,
    var lastName: String,
    var address: String,
    var fullName: String?,
)
