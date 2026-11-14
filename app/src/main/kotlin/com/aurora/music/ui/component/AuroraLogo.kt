/**
 * Aurora Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.aurora.music.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aurora.music.R

/**
 * The Aurora mascot, drawn in its own colours (never tinted) and clipped to a circle.
 * Used everywhere the app shows its mark, including the "loading music" placeholders.
 */
@Composable
fun AuroraLogo(modifier: Modifier = Modifier, size: Dp = 20.dp) {
    Image(
        painter = painterResource(R.drawable.aurora_logo),
        contentDescription = null,
        modifier = modifier.size(size).clip(CircleShape),
    )
}
