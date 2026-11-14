/**
 * Aurora Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.aurora.music.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.LocalContentColor
import androidx.navigation.NavController
import com.aurora.music.R
import java.util.Calendar

/** Colours a tile is drawn with, so the same tile works on the Home page and on Settings. */
data class AuroraTileColors(
    val container: Color,
    val content: Color,
    val secondary: Color,
)

data class AuroraTileData(
    val title: String,
    val caption: String?,
    val icon: Int,
    val tint: Color,
    val badge: String? = null,
    val onClick: () -> Unit,
)

/**
 * Aurora's own building block, used instead of grouped list rows: a rounded card with a
 * coloured glyph bubble on top and the label underneath. Plain foundation widgets only,
 * so it costs next to nothing to draw.
 */
@Composable
fun AuroraTile(
    data: AuroraTileData,
    colors: AuroraTileColors,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(colors.container)
            .clickable(onClick = data.onClick)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier.size(42.dp).clip(CircleShape).background(data.tint),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(data.icon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(22.dp),
                )
            }
            Spacer(Modifier.weight(1f))
            if (data.badge != null) {
                Box(
                    Modifier.clip(CircleShape).background(data.tint).padding(horizontal = 9.dp, vertical = 3.dp),
                ) {
                    BasicText(data.badge, style = TextStyle(color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold))
                }
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            BasicText(
                data.title,
                style = TextStyle(color = colors.content, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, lineHeight = 20.sp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            if (!data.caption.isNullOrBlank()) {
                BasicText(
                    data.caption,
                    style = TextStyle(color = colors.secondary, fontSize = 12.sp, lineHeight = 16.sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

/** Two tiles per row, equal height within a row. */
@Composable
fun AuroraTileGrid(
    tiles: List<AuroraTileData>,
    colors: AuroraTileColors,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        tiles.chunked(2).forEach { pair ->
            Row(
                Modifier.fillMaxWidth().height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                pair.forEach { tile -> AuroraTile(tile, colors, Modifier.weight(1f).fillMaxHeight()) }
                if (pair.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}

@StringRes
private fun greetingRes(): Int = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
    in 5..10 -> R.string.aurora_greeting_morning
    in 11..14 -> R.string.aurora_greeting_afternoon
    in 15..17 -> R.string.aurora_greeting_evening
    else -> R.string.aurora_greeting_night
}

/**
 * Top of Home: the mascot, a time-of-day greeting and four shortcuts. Replaces the old
 * "straight into shelves" start with something you can act on without scrolling.
 */
@Composable
fun AuroraHomeHeader(navController: NavController, modifier: Modifier = Modifier) {
    val content = LocalContentColor.current
    val colors = remember(content) {
        AuroraTileColors(
            container = content.copy(alpha = 0.08f),
            content = content,
            secondary = content.copy(alpha = 0.6f),
        )
    }
    val greeting = remember { greetingRes() }
    val tiles = listOf(
        AuroraTileData("History", null, R.drawable.music_history, Color(0xFF3A7ADD)) { navController.navigate("history") },
        AuroraTileData("Stats", null, R.drawable.stats, Color(0xFFFF9F0A)) { navController.navigate("stats") },
        AuroraTileData("Library", null, R.drawable.library_music, Color(0xFF30B0C7)) { navController.navigate("library") },
        AuroraTileData("Together", null, R.drawable.group, Color(0xFFAF52DE)) { navController.navigate("listen_together") },
    )
    Column(
        modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            AuroraLogo(size = 60.dp)
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                BasicText(
                    stringResource(greeting),
                    style = TextStyle(color = content, fontSize = 24.sp, fontWeight = FontWeight.Bold),
                )
                BasicText(
                    stringResource(R.string.aurora_home_tagline),
                    style = TextStyle(color = content.copy(alpha = 0.6f), fontSize = 13.sp),
                )
            }
        }
        AuroraTileGrid(tiles, colors)
    }
}
