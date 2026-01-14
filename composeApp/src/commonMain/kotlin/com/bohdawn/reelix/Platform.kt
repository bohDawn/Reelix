package com.bohdawn.reelix

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform