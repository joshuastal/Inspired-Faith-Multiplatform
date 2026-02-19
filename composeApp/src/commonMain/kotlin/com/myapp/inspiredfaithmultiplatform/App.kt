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
import com.myapp.inspiredfaithmultiplatform.QuoteStuff.Quote
import com.myapp.inspiredfaithmultiplatform.QuoteStuff.QuoteUtil

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
            var calendarTestText by remember { mutableStateOf("Loading") }
            var chosenCalendarTestText by remember { mutableStateOf("Loading") }
            LaunchedEffect(true) {
                try {
                    val result = CalendarAPI().orthoInfoToday()

                    if (result.status.value.toString() == "200") {
                        val calendarData = CalendarAPI().convertToCalendarDay(result)

                        calendarTestText = "Today is: ${calendarData.summary_title}"

                        Logger.d ("Calendar") { calendarData.toString() }
                    } else { calendarTestText = "Failed: ${result.status.value}" }

                    val chosenDay = CalendarAPI().orthoInfoChosen(2026, 12, 25)
                    if (chosenDay.status.value.toString() == "200") {
                        val calendarData = CalendarAPI().convertToCalendarDay(chosenDay)

                        chosenCalendarTestText = "Today is: ${calendarData.summary_title}"

                        Logger.d ("Calendar") { calendarData.toString() }
                    } else { calendarTestText = "Failed: ${chosenDay.status.value}" }
                } catch (e: Exception) {
                    e.message ?: "error"
                }
            }
            // LaunchedEffect blocks create a safe coroutine environment
            // true = run the block once and only once when the app opens
            Text(text = calendarTestText)
            Text(text = chosenCalendarTestText)


            var firestoreTextTest by remember { mutableStateOf("Attempting Firestore Test...") }
            LaunchedEffect(true) {
                try {
                    Logger.d("Firestore") { "Starting Firestore Test..." }

                    val results = QuoteUtil().getQuotesFromFirestore<Quote>() // specify the type for the generic T

                    if (results.isEmpty()){
                        Logger.d("Firestore") { "Firestore Test Failed..." }
                        firestoreTextTest = "Firestore Test Failed..."
                    } else {
                        Logger.d("Firestore") { "Firestore Test Success: ${results.size} results" }
                        firestoreTextTest = "Firestore Test Success: ${results.size} results"
                        results.forEach {
                            Logger.d("Firestore") { "Author: ${it.Author} | Quote: ${it.Quote}" }
                        }
                    }
                } catch (e: Exception) {
                    Logger.d("Firestore") { "Firestore Test Error: ${e.message}" }
                }
            }
            Text(text = firestoreTextTest)
        }
    }
}