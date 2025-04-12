package com.ndewon.spendless.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Numpad(modifier: Modifier = Modifier, onClick: (String) -> Unit, pinLength: Int) {
    val buttons = listOf(
        "1", "2", "3",
        "4", "5", "6",
        "7", "8", "9",
        "", "0", "X"
    )

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        PinDots(pinLength = pinLength)

        Spacer(modifier = Modifier.height(32.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(buttons.size) {
                val key = buttons[it]
                if (key.isNotEmpty()) {
                    NumpadItem(
                        modifier = Modifier.then(if (key == "X") Modifier.alpha(0.5f) else Modifier),
                        text = key,
                        onClick = {
                            onClick(key)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NumpadPreview() {
    Numpad(onClick = {}, pinLength = 0)
}