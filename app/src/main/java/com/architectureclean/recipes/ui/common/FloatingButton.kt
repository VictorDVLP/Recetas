package com.architectureclean.recipes.ui.common

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FloatingButton( description: String, icon: ImageVector, onClick: () -> Unit) {
    FloatingActionButton(onClick = { onClick() }) {
        Icon(imageVector = icon, contentDescription = description)
    }
}