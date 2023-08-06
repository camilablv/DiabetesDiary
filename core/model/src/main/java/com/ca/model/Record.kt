package com.ca.model

import java.time.LocalDateTime

interface Record : ListItem {
    val dateTime: LocalDateTime
}