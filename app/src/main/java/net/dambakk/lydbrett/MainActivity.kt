package net.dambakk.lydbrett

import android.content.Context
import android.view.View
import android.media.MediaPlayer
import android.os.Bundle
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.ScrollableController
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import net.dambakk.lydbrett.ui.LydbrettTheme
import net.dambakk.lydbrett.ui.darkThemeSurface
import net.dambakk.lydbrett.ui.rrSecondary
import net.dambakk.lydbrett.ui.rrSecondaryLight

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Going edge-to-edge
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        setContent {
            LydbrettTheme {
//                    WithConstraints(modifier = Modifier.fillMaxSize()) {
//                        val width = constraints.maxWidth.toFloat()
//                        val height = constraints.maxHeight.toFloat()
//                        DynamicPointMesh(
//                            modifier = Modifier.fillMaxSize(),
//                            containerWidth = width,
//                            containerHeight = height
//                        )
//                    }
                    ScrollableColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(darkThemeSurface)
                    ) {
                        Image(
                            imageResource(id = R.drawable.rr),
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth().height(200.dp)
                        )
                        PlayableItem(title = "Ugøy", sound = R.raw.ugoy)
                        PlayableItem(title = "Dagens", sound = R.raw.dagens)
                        PlayableItem(title = "Enig", sound = R.raw.enig)
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
            .height(100.dp)
            .width(cardWidth)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
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
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Button(
                onClick = {
                    isPlaying.value = true
                    mediaPlayer.start()
                },
                backgroundColor = if (isPlaying.value) rrSecondaryLight else rrSecondary,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .wrapContentSize(),
            ) {
                // TODO: Replace with play graphics
                Text(
                    text = if (isPlaying.value) "Spiller av" else "Spill av"
                )
            }
        }
    }
}
