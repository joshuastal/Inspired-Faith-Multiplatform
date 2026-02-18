package com.myapp.inspiredfaithmultiplatform.CalendarAPI.CalendarDayObjectFiles

import kotlinx.serialization.Serializable

@Serializable
data class Story(
    val story: String,
    val title: String
)