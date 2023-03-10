package com.sanam.yavarpour.image_search.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.sanam.yavarpour.image_search.R
import com.sanam.yavarpour.ui.util.DefaultStandardDoublePadding
import com.sanam.yavarpour.ui.util.DefaultStandardPadding

@Composable
fun ErrorAndRetry(modifier: Modifier, errorMessage: String, onRetryClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(DefaultStandardPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = modifier.fillMaxWidth(), textAlign = TextAlign.Center, text = errorMessage)
        Spacer(modifier = modifier.height(DefaultStandardDoublePadding))
        Text(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onRetryClick() },
            textAlign = TextAlign.Center,
            text = stringResource(R.string.search_screen_retry),
            style = TextStyle(color = Color.Red)
        )
    }
}