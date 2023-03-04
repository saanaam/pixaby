package com.sanam.yavarpour.image_search.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.sanam.yavarpour.image_search.R
import com.sanam.yavarpour.ui.util.DefaultStandardPadding

@Composable
fun EmptyView(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(DefaultStandardPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = com.sanam.yavarpour.ui.R.drawable.empty_view),
            contentDescription = stringResource(R.string.empty_view),
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            text = stringResource(R.string.empty_view),
            style = TextStyle(colorResource(id = R.color.green_700)),
            textAlign = TextAlign.Center
        )
    }
}