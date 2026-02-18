package com.myapp.inspiredfaithmultiplatform.CalendarAPI.CalendarDayObjectFiles

import kotlinx.serialization.Serializable

@Serializable
data class Passage(
    val book: String,
    val chapter: Int,
    val content: String,
    val paragraph_start: Boolean,
    val verse: Int
)