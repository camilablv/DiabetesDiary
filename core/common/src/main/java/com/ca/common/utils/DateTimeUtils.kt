package com.ca.common.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun currentDay(): String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd"))

fun currentMonth(): String = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM"))

fun currentTime(): String = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm"))

fun currentDate() = "${currentDay()} ${currentMonth()}"