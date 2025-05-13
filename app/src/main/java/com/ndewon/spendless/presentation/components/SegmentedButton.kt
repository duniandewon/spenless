package com.ndewon.spendless.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ndewon.spendless.domain.models.ExpenseFormat

@Composable
fun <T> SegmentedButton(
    options: List<T>,
    selectedOption: T,
    onFormatSelected: (T) -> Unit,
    label: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            options.forEach { format ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (format == selectedOption)
                                Color.White
                            else
                                Color.Transparent
                        )
                        .clickable { onFormatSelected(format) }
                        .padding(vertical = 12.dp, horizontal = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        color = if (format == selectedOption)
                            Color.Black
                        else
                            MaterialTheme.colorScheme.primary,
                        text = label,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseFormatTogglePreview() {
    val options = ExpenseFormat.entries.toList()
    val selectedOption = ExpenseFormat.NEGATIVE_SIGN

    SegmentedButton(
        options = options,
        selectedOption = selectedOption,
        onFormatSelected = {},
        label = when (selectedOption) {
            ExpenseFormat.NEGATIVE_SIGN -> "-$10"
            ExpenseFormat.PARENTHESES -> "($10)"
        }
    )

}