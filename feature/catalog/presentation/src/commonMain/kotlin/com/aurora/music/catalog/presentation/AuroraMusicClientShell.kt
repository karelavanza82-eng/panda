package com.aurora.music.catalog.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.music.designsystem.AuroraDesignTokens
import com.aurora.music.ui.ios.component.IosText
import com.aurora.music.ui.ios.foundation.IosTheme
import com.aurora.music.ui.ios.foundation.ProvideIosTheme
import com.aurora.music.ui.ios.foundation.darkIosColors
import com.aurora.music.ui.ios.foundation.iosTypography

/** Shared client chrome used by the iOS and Android KMP hosts. */
@Composable
fun AuroraMusicClientShell(
    accountSubtitle: String,
    accountActionLabel: String,
    accountActionEnabled: Boolean,
    statusMessages: List<String> = emptyList(),
    onAccountAction: () -> Unit,
    onModulesAction: (() -> Unit)? = null,
    content: @Composable (Modifier) -> Unit,
) {
    ProvideIosTheme(colors = darkIosColors(), typography = iosTypography(com.aurora.music.designsystem.auroraFontFamily())) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AuroraDesignTokens.Colors.Background),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    IosText(
                        text = "Aurora Music",
                        style = IosTheme.typography.title2,
                        color = IosTheme.colors.label,
                    )
                    IosText(
                        text = accountSubtitle,
                        style = IosTheme.typography.footnote,
                        color = IosTheme.colors.secondaryLabel,
                    )
                }
                onModulesAction?.let { action ->
                    IosText(
                        text = "Modules",
                        modifier = Modifier.clickable(onClick = action).padding(12.dp),
                        color = IosTheme.colors.accent,
                    )
                }
                Row(
                    modifier = Modifier
                        .background(
                            color = if (accountActionEnabled) AuroraDesignTokens.Colors.AccentRed
                            else AuroraDesignTokens.Colors.CardSecondary,
                            shape = RoundedCornerShape(18.dp),
                        )
                        .clickable(enabled = accountActionEnabled, onClick = onAccountAction)
                        .padding(horizontal = 16.dp, vertical = 9.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IosText(
                        text = accountActionLabel,
                        style = IosTheme.typography.footnote,
                        color = Color.White,
                    )
                }
            }
            statusMessages.filter(String::isNotBlank).forEach { message ->
                IosText(
                    text = message,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp),
                    style = IosTheme.typography.footnote,
                    color = IosTheme.colors.secondaryLabel,
                )
            }
            content(Modifier.weight(1f))
        }
    }
}
