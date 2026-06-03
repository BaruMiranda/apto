package org.barcode.apptv

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform