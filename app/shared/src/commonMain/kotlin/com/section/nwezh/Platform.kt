package com.section.nwezh

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform