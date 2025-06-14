package dev.runo.core_ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.runo.core_ui.R

@Composable
fun TitleCard(
    image: Bitmap,
    name: String,
    rating: Float,
    onClickAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(115.dp)
            .aspectRatio(1.7f)
            .pointerInput(Unit) { detectTapGestures { onClickAction() } }
    ) {
        Box(
            modifier = Modifier.size(width = 115.dp, height = 165.dp)
        ) {
            Image(
                bitmap = image.asImageBitmap(),
                contentDescription = stringResource(R.string.title_poster)
            )
            // Rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "%1.1f".format(rating),
                    modifier = Modifier.padding(end = 2.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.star),
                    contentDescription = stringResource(R.string.title_rating_star)
                )
            }
        }
        // Title name
        Text(
            text = name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxSize().padding(5.dp)
        )
    }
}