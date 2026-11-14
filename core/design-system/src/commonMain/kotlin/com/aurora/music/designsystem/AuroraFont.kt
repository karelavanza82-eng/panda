package com.aurora.music.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.aurora.music.designsystem.resources.Res
import com.aurora.music.designsystem.resources.sf_pro_text_regular
import com.aurora.music.designsystem.resources.sf_pro_text_medium
import com.aurora.music.designsystem.resources.sf_pro_text_semibold
import com.aurora.music.designsystem.resources.sf_pro_text_bold
import com.aurora.music.designsystem.resources.chillax_semibold
import org.jetbrains.compose.resources.Font

/** The same font assets and weight mapping used by the production Android client. */
@Composable
fun auroraFontFamily(): FontFamily = FontFamily(
    Font(Res.font.sf_pro_text_regular, FontWeight.Normal),
    Font(Res.font.sf_pro_text_medium, FontWeight.Medium),
    Font(Res.font.sf_pro_text_semibold, FontWeight.SemiBold),
    Font(Res.font.sf_pro_text_bold, FontWeight.Bold),
)

@Composable
fun auroraWordmarkFontFamily(): FontFamily = FontFamily(Font(Res.font.chillax_semibold, FontWeight.SemiBold))
