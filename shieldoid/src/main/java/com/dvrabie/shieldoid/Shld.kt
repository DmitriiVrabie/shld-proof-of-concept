package com.dvrabie.shieldoid

import android.content.Context
import android.content.pm.ApplicationInfo


fun Context.isDebugMode() =
    0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE && BuildConfig.DEBUG

fun isEmulator(): Boolean = try {
    val goldfish = getSystemProperty("ro.hardware").contains("goldfish")
    val emu: Boolean = getSystemProperty("ro.kernel.qemu").isNotEmpty()
    val sdk = getSystemProperty("ro.product.model") == "sdk"
    emu || goldfish || sdk
} catch (e: Exception) {
    false
}

@Throws(Exception::class)
private fun getSystemProperty(name: String): String {
    val systemPropertyClazz = Class.forName("android.os.SystemProperties")
    return systemPropertyClazz.getMethod(
        "get", String::class.java
    ).invoke(systemPropertyClazz, name) as String
}