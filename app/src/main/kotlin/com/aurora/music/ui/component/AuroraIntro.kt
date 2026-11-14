/**
 * Aurora Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.aurora.music.ui.component

import android.provider.Settings
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aurora.music.R

/**
 * Cold-start intro: the black-and-white sketch of the mascot and the word "Aurora" fade in,
 * hold, then the whole screen fades out onto the live app. Fades only.
 *
 * Built to be nearly free on low-end phones: one Animatable drives everything, every alpha is
 * read inside a graphicsLayer block (so nothing recomposes per frame), the layers use
 * ModulateAlpha (no offscreen buffers) and the only bitmap is a 640px sketch.
 */
@Composable
fun AuroraIntro(onFinished: () -> Unit) {
    val context = LocalContext.current
    val reducedMotion = remember {
        Settings.Global.getFloat(context.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f) == 0f
    }
    val clock = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        if (!reducedMotion) clock.animateTo(TOTAL_MS, tween(TOTAL_MS.toInt(), easing = LinearEasing))
        onFinished()
    }
    if (reducedMotion) return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.ModulateAlpha
                alpha = 1f - smooth(clock.value, FADE_OUT_START, TOTAL_MS)
            }
            .background(Color.White)
            // Swallow touches until the app is revealed.
            .pointerInput(Unit) { awaitPointerEventScope { while (true) awaitPointerEvent().changes.forEach { it.consume() } } },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.aurora_sketch),
            contentDescription = null,
            modifier = Modifier
                .size(220.dp)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.ModulateAlpha
                    alpha = smooth(clock.value, 0f, LOGO_IN_END)
                },
        )
        Spacer(Modifier.height(18.dp))
        BasicText(
            text = "Aurora",
            style = TextStyle(
                color = Color(0xFF111111),
                fontSize = 34.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 6.sp,
            ),
            modifier = Modifier.graphicsLayer {
                compositingStrategy = CompositingStrategy.ModulateAlpha
                alpha = smooth(clock.value, TEXT_IN_START, TEXT_IN_END)
            },
        )
    }
}

private const val LOGO_IN_END = 550f
private const val TEXT_IN_START = 350f
private const val TEXT_IN_END = 900f
private const val FADE_OUT_START = 1350f
private const val TOTAL_MS = 1750f

/** Smoothstep from 0 at [start] to 1 at [end]. */
private fun smooth(t: Float, start: Float, end: Float): Float {
    val x = ((t - start) / (end - start)).coerceIn(0f, 1f)
    return x * x * (3f - 2f * x)
}
