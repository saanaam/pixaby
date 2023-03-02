package com.sanam.yavarpour.pixaby
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixabaySearchApp()
        }
    }
}

