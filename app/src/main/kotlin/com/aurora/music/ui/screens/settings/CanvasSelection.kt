/**
 * Aurora Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.aurora.music.ui.screens.settings

import com.aurora.music.ui.utils.appTopBarWindowInsets
import androidx.compose.animation.animateColorAsState
import com.aurora.music.ui.utils.bounceClick
import com.aurora.music.ui.utils.combinedBounceClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import com.aurora.music.ui.ios.compat.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import com.aurora.music.ui.ios.compat.Icon
import com.aurora.music.ui.ios.compat.AppTheme
import com.aurora.music.ui.ios.compat.RadioButton
import com.aurora.music.ui.ios.compat.Switch
import com.aurora.music.ui.ios.compat.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aurora.music.LocalPlayerAwareWindowInsets
import com.aurora.music.R
import com.aurora.music.constants.CanvasSource
import com.aurora.music.constants.CanvasSourceKey
import com.aurora.music.constants.CanvasThumbnailAnimationKey
import com.aurora.music.ui.component.ExpressiveIconButton
import com.aurora.music.ui.component.IconButton
import com.aurora.music.ui.component.IosSettingsGroup
import com.aurora.music.ui.component.IosSettingsItem
import com.aurora.music.ui.component.ModernSwitch
import com.aurora.music.ui.utils.backToMain
import com.aurora.music.utils.rememberEnumPreference
import com.aurora.music.utils.rememberPreference

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CanvasSelection(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val (canvasThumbnailAnimation, onCanvasThumbnailAnimationChange) = rememberPreference(
        CanvasThumbnailAnimationKey,
        defaultValue = true
    )
    val (canvasSource, onCanvasSourceChange) = rememberEnumPreference(
        CanvasSourceKey,
        defaultValue = CanvasSource.AUTO
    )

    SettingsPage(
        navController = navController,
        title = stringResource(R.string.aurora_canvas),
        pageColor = AppTheme.colorScheme.background,
        horizontalPadding = 24.dp,
    ) {
        Text(
            text = stringResource(R.string.aurora_canvas_desc),
            style = AppTheme.typography.bodyMedium,
            color = AppTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 24.dp, top = 16.dp)
        )

        // Large capsule banner for main toggle
        val containerColor by animateColorAsState(
            targetValue = if (canvasThumbnailAnimation) {
                AppTheme.colorScheme.primaryContainer
            } else {
                AppTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            },
            label = "containerColor"
        )

        val contentColor = if (canvasThumbnailAnimation) {
            AppTheme.colorScheme.onPrimaryContainer
        } else {
            AppTheme.colorScheme.onSurfaceVariant
        }

        Card(
            onClick = { onCanvasThumbnailAnimationChange(!canvasThumbnailAnimation) },
            shape = RoundedCornerShape(50),
            colors = CardDefaults.cardColors(containerColor = containerColor),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.use_canvas),
                    style = AppTheme.typography.titleMedium,
                    color = contentColor
                )
                ModernSwitch(
                    checked = canvasThumbnailAnimation,
                    onCheckedChange = onCanvasThumbnailAnimationChange
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        IosSettingsGroup(
            title = stringResource(R.string.canvas_source),
            items = listOf(
                IosSettingsItem(
                    leadingContent = {
                        RadioButton(
                            selected = canvasSource == CanvasSource.AUTO,
                            onClick = null,
                            enabled = canvasThumbnailAnimation
                        )
                    },
                    title = { Text(stringResource(R.string.canvas_source_auto)) },
                    description = { Text(stringResource(R.string.canvas_source_auto_desc)) },
                    enabled = canvasThumbnailAnimation,
                    onClick = { onCanvasSourceChange(CanvasSource.AUTO) }
                ),
                IosSettingsItem(
                    leadingContent = {
                        RadioButton(
                            selected = canvasSource == CanvasSource.APPLE_MUSIC,
                            onClick = null,
                            enabled = canvasThumbnailAnimation
                        )
                    },
                    title = { Text(stringResource(R.string.canvas_source_apple_music)) },
                    description = { Text(stringResource(R.string.canvas_source_apple_music_desc)) },
                    enabled = canvasThumbnailAnimation,
                    onClick = { onCanvasSourceChange(CanvasSource.APPLE_MUSIC) }
                ),
                IosSettingsItem(
                    leadingContent = {
                        RadioButton(
                            selected = canvasSource == CanvasSource.AURORA,
                            onClick = null,
                            enabled = canvasThumbnailAnimation
                        )
                    },
                    title = { Text(stringResource(R.string.canvas_source_aurora)) },
                    description = { Text(stringResource(R.string.canvas_source_aurora_desc)) },
                    enabled = canvasThumbnailAnimation,
                    onClick = { onCanvasSourceChange(CanvasSource.AURORA) }
                ),
                IosSettingsItem(
                    leadingContent = {
                        RadioButton(
                            selected = canvasSource == CanvasSource.TIDAL,
                            onClick = null,
                            enabled = canvasThumbnailAnimation
                        )
                    },
                    title = { Text(stringResource(R.string.canvas_source_tidal)) },
                    description = { Text(stringResource(R.string.canvas_source_tidal_desc)) },
                    enabled = canvasThumbnailAnimation,
                    onClick = { onCanvasSourceChange(CanvasSource.TIDAL) }
                ),
                IosSettingsItem(
                    leadingContent = {
                        RadioButton(
                            selected = canvasSource == CanvasSource.SPOTIFY,
                            onClick = null,
                            enabled = canvasThumbnailAnimation
                        )
                    },
                    title = { Text(stringResource(R.string.canvas_source_spotify)) },
                    description = { Text(stringResource(R.string.canvas_source_spotify_desc)) },
                    enabled = canvasThumbnailAnimation,
                    onClick = { onCanvasSourceChange(CanvasSource.SPOTIFY) }
                )
            )
        )
        Spacer(modifier = Modifier.height(36.dp))
    
    }
}
