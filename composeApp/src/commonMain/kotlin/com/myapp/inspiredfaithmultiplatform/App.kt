package com.myapp.inspiredfaithmultiplatform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.touchlab.kermit.Logger
import com.myapp.inspiredfaithmultiplatform.CalendarAPI.CalendarAPI

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var text by remember { mutableStateOf("Loading") }
            LaunchedEffect(true) {
                try {
                    val result = CalendarAPI().orthoInfoToday()

                    if (result.status.value.toString() == "200") {
                        val calendarData = CalendarAPI().convertToCalendarDay(result)

                        text = "Today is: ${calendarData.summary_title}"

                        Logger.d ("Calendar") { text }
                    } else { text = "Failed: ${result.status.value}" }
                } catch (e: Exception) {
                    e.message ?: "error"
                }
            }
            Text(text = text)
        }
    }
}