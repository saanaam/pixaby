package com.sanam.yavarpour.image_search.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.sanam.yavarpour.image_search.R
import com.sanam.yavarpour.ui.util.DefaultTopBarHeight

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClick: (String) -> Unit,
    onCloseClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(DefaultTopBarHeight),
        color = colorResource(id = R.color.green_700),
        elevation = AppBarDefaults.BottomAppBarElevation,
        shape = RectangleShape
    ) {
        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium),
                    text = stringResource(R.string.search_screen_search_view_search_here),
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = Color.White
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_screen_search_view_search_icon),
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier,
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClick()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.search_screen_search_view_close_icon),
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClick(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                cursorColor = colorResource(id = R.color.green_500)
            )
        )
    }
}