package org.michaelbel.movies.feed

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.michaelbel.movies.ui.MoviesTheme

@Composable
fun ErrorListItem(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .height(180.dp)
            .width(120.dp)
            .padding(2.dp)
            .clip(RoundedCornerShape(12F))
    ) {
        IconButton(
            onClick = onRetryClick
        ) {
            Image(
                painter = painterResource(R.drawable.ic_clear),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error)
            )
        }

        Text(
            text = "Error",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorListItemPreview() {
    MoviesTheme {
        ErrorListItem(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            onRetryClick = {}
        )
    }
}