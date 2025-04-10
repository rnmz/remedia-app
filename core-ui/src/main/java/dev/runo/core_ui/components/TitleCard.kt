package dev.runo.core_ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleCard(
    image: Bitmap,
    name: String,
    rating: Float,
    modifier: Modifier = Modifier,
    onClickAction: () -> Unit
) {
    Card(
        modifier = modifier
            .width(110.dp)
            .aspectRatio(1.7f)
            .clip(RoundedCornerShape(5.dp)),
        onClick = onClickAction
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            Text(
                text = "%.1f".format(rating),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(5.dp)
                    .height(10.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.LightGray)
                    .padding(start = 5.dp, end = 5.dp)
                ,
                fontSize = 8.sp
            )
            Image(
                bitmap = image.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .border(1.dp, Color.Blue),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = name,
            modifier = Modifier.fillMaxSize().padding(5.dp),
            maxLines = 2,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis
        )
    }
}