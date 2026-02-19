package com.myapp.inspiredfaithmultiplatform.CalendarAPI

import co.touchlab.kermit.Logger
import com.myapp.inspiredfaithmultiplatform.CalendarAPI.CalendarDayObjectFiles.CalendarDay
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CalendarAPI {
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                // will ignore newly added fields if orthocal.info adds new things (they won't)
            })
        }
    }

    suspend fun orthoInfoToday(): HttpResponse {
        return client.get("https://orthocal.info/api/gregorian")
    }

    suspend fun orthoInfoChosen(year: Int, month: Int, day: Int): HttpResponse {
        return client.get("https://orthocal.info/api/gregorian/${year}/${month}/${day}/")
    }

    suspend fun convertToCalendarDay(response: HttpResponse): CalendarDay {
        try {
            Logger.d("CalendarAPI") { "Starting conversion..." }

            // Directly return the result of body<CalendarDay>()
            val parsed = response.body<CalendarDay>()

            Logger.d("CalendarAPI") { "Conversion Success: ${parsed.summary_title}" }
            return parsed
        } catch (e: Exception) {
            // This will tell you EXACTLY what went wrong (e.g., missing field, wrong type)
            Logger.e("CalendarAPI", e) { "Conversion failed!" }
            throw e // Re-throw so your App.kt catch block can handle it
        }
    }


}