package net.dambakk.lydbrett.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// https://material.io/resources/color/#!/?view.left=0&view.right=1&primary.color=292929&secondary.color=78c0c8&primary.text.color=ffffff&secondary.text.color=000000
private val DarkColorPalette = darkColors(
        primary = rrPrimary,
        primaryVariant = rrPrimaryLight,
        onPrimary = Color.White,
        secondary = rrSecondary,
        onSecondary = Color.Black,
        background = darkThemeSurface,

)

//private val LightColorPalette = lightColors(
//        primary = purple500,
//        primaryVariant = purple700,
//        secondary = teal200,
//        background = Color.White,

        /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
//)

@Composable
fun LydbrettTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {

    MaterialTheme(
            colors = DarkColorPalette,
            typography = typography,
            shapes = shapes,
            content = content
    )
}