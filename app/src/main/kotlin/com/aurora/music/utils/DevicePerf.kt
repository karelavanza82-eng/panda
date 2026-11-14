/**
 * Aurora Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.aurora.music.utils

import android.app.ActivityManager
import android.content.Context

/**
 * One cheap, cached answer to "is this a small phone?". True for devices Android flags as
 * low-RAM and for anything with roughly 3 GB of RAM or less, which is where blur, glass and
 * animated artwork cost frames and heat. Everything heavy keys off this single check.
 */
object DevicePerf {
    private const val LOW_END_TOTAL_RAM_BYTES = 3_400_000_000L

    @Volatile
    private var cached: Boolean? = null

    fun isLowEnd(context: Context): Boolean {
        cached?.let { return it }
        val manager = context.applicationContext.getSystemService(ActivityManager::class.java)
        val info = ActivityManager.MemoryInfo()
        manager?.getMemoryInfo(info)
        val lowEnd = manager?.isLowRamDevice == true || (info.totalMem in 1..LOW_END_TOTAL_RAM_BYTES)
        cached = lowEnd
        return lowEnd
    }
}
