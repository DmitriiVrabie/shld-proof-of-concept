package com.dvrabie.shieldoid

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Base64
import java.security.MessageDigest

enum class Status {
    VALID,
    INVALID
}

const val GENUINE_SIGNATURE = "8uuqrHuWUKZEZ6FQFXmBR6rqPtk=\n"

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

fun Context.checkAppSignature(): String {
    var currentSignature = ""
    var status = Status.INVALID
    try {
        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        for (signature in packageInfo.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            currentSignature = Base64.encodeToString(md.digest(), Base64.DEFAULT)
            if (GENUINE_SIGNATURE == currentSignature) status = Status.VALID
        }
    } catch (e: Exception) {
        //  assumes an issue in checking signature., but we let the caller decide on what to do.
    }

    return String.format(
        "Current signature: %s\nExpected signature: %s\nIs signature valid: %s",
        currentSignature,
        GENUINE_SIGNATURE,
        status.name
    )
}