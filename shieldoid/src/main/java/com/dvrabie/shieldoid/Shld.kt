package com.dvrabie.shieldoid

import android.content.Context
import android.content.pm.ApplicationInfo


fun Context.isDebugMode() =
    0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE && BuildConfig.DEBUG