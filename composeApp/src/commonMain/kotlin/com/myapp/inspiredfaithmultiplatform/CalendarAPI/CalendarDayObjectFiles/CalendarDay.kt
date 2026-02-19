package com.myapp.inspiredfaithmultiplatform.CalendarAPI.CalendarDayObjectFiles

import kotlinx.serialization.Serializable

@Serializable
data class CalendarDay(
    val abbreviated_reading_indices: List<Int>,
    val day: Int,
    val fast_exception: Int,
    val fast_exception_desc: String,
    val fast_level: Int,
    val fast_level_desc: String,
    val feast_level: Int,
    val feast_level_description: String,
    val feasts: List<String>?,
    val julian_day_number: Int,
    val month: Int,
    val pascha_distance: Int,
    val readings: List<Reading>,
    val saints: List<String>?,
    val service_notes: List<String>?,
    val stories: List<Story>,
    val summary_title: String,
    val titles: List<String>?,
    val tone: Int,
    val weekday: Int,
    val year: Int
) {
    override fun toString(): String {
        return "Abbreviated Reading Indices: $abbreviated_reading_indices\n" +
                "Day: $day\n" +
                "Fast Exception: $fast_exception\n" +
                "Fast Exception Description: $fast_exception_desc\n" +
                "Fast Level: $fast_level\n" +
                "Fast Level Description: $fast_level_desc\n" +
                "Feast Level: $feast_level\n" +
                "Feast Level Description: $feast_level_description\n" +
                "Feast(s): $feasts\n" +
                "Julian Day Number: $julian_day_number\n" +
                "Month: $month\n" +
                "Pascha Distance: $pascha_distance\n" +
                "Reading(s): $readings\n" +
                "Saint(s): $saints\n" +
                "Service Note(s): $service_notes\n" +
                "Story(s): $stories\n" +
                "Summary Title: $summary_title\n" +
                "Title(s): $titles\n" +
                "Tone: $tone\n" +
                "Weekday: $weekday\n" +
                "Year: $year"
    }
}