package com.myapp.inspiredfaithmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform