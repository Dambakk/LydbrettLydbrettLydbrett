package net.dambakk.lydbrett

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import net.dambakk.lydbrett.ui.LydbrettTheme
import net.dambakk.lydbrett.ui.darkThemeSurface
import net.dambakk.lydbrett.ui.rrSecondary
import net.dambakk.lydbrett.ui.rrSecondaryLight
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LydbrettTheme {
                Scaffold {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        PlayableItem(title = "Ugøy", sound = R.raw.ugoy)
                        PlayableItem(title = "Dagens", sound = R.raw.dagens)
                        PlayableItem(title = "Enig", sound = R.raw.enig)
                    }
                }
            }
        }
    }
}

@Composable
fun PlayableItem(
    modifier: Modifier = Modifier,
    title: String,
    @RawRes sound: Int
) {
    val context: Context = ContextAmbient.current
    val configuration = ConfigurationAmbient.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = screenWidth

    val isPlaying = remember { mutableStateOf(false) }
    val mediaPlayer = MediaPlayer.create(context, sound)
    mediaPlayer.setOnCompletionListener {
        isPlaying.value = false
    }
    Card(
        backgroundColor = if (isPlaying.value) rrSecondary else MaterialTheme.colors.surface,
        modifier = modifier
            .height(120.dp)
            .width(cardWidth)
            .padding(20.dp)
            .clickable(onClick = {
                isPlaying.value = true
                mediaPlayer.start()
            })
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                modifier = Modifier.gravity(Alignment.CenterVertically)
            )
            Button(
                onClick = {
                    isPlaying.value = true
                    mediaPlayer.start()
                },
                backgroundColor = if (isPlaying.value) rrSecondaryLight else rrSecondary,
                modifier = Modifier
                    .gravity(Alignment.CenterVertically)
                    .wrapContentSize(),
            ) {
                // TODO: Replace with play graphics
                Text(
                    text = if (isPlaying.value) "Playing..." else "Play"
                )
            }
        }
    }
}
