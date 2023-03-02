package com.sanam.yavarpour.image_search.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sanam.yavarpour.core.model.Hit
import com.sanam.yavarpour.image_search.R
import com.sanam.yavarpour.ui.util.DefaultStandardPadding
import com.sanam.yavarpour.ui.util.SearchListItemImageHeight

@Composable
fun imageItem(hit: Hit, onItemClick: (Hit) -> Unit) {
    Box(modifier = Modifier) {
        AsyncImage(
            model = hit.webFormatURL,
            contentDescription = stringResource(R.string.search_screen_search_item_an_image_by) + hit.user + stringResource(
                R.string.search_screen_search_item_with
            ) + hit.tags + stringResource(R.string.search_screen_search_item_tags),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(SearchListItemImageHeight)
                .fillMaxWidth()
        )
        ImageInfo(hit, onItemClick)
    }
}

@Composable
fun ImageInfo(hit: Hit, onItemClick: (Hit) -> Unit) {
    Surface(
        modifier = Modifier
            .padding(DefaultStandardPadding)
            .fillMaxWidth(),
        shape = CircleShape,
        color = colorResource(R.color.semi_white),
        elevation = 1.dp
    ) {
        Row {
            Image(
                modifier = Modifier
                    .padding(start = DefaultStandardPadding)
                    .align(CenterVertically),
                colorFilter = tint(Red),
                imageVector = Icons.Default.Favorite,
                contentDescription = stringResource(R.string.like_count)
            )
            Spacer(modifier = Modifier.width(DefaultStandardPadding))
            Text(
                modifier = Modifier
                    .align(CenterVertically), text = stringResource(R.string.likes, hit.likes)
            )
            Spacer(Modifier.weight(1f))
            IconButton(
                modifier = Modifier.align(CenterVertically),
                onClick = { onItemClick.invoke(hit) }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.show_more)
                )
            }
        }
    }
}


