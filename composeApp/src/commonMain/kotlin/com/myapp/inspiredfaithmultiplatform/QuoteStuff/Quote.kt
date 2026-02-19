package com.myapp.inspiredfaithmultiplatform.QuoteStuff

import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val Author: String,
    val Quote: String,
)