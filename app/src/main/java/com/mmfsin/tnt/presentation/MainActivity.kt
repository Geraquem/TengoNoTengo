package com.mmfsin.tnt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mmfsin.tnt.presentation.core.navigation.NavigationWrapper
import com.mmfsin.tnt.presentation.core.theme.TNTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { TNTTheme { NavigationWrapper() } }
    }
}