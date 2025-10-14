package com.example.appcitaexpress.data.model

data class PaginatorSpecialty(
    val count: Int,
    val total_pages: Int,
    val current_page: Int,
    val next: String?,
    val previous: String?,
    val results: List<Specialty>
)
