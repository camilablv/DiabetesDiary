package com.ca.model

import java.time.LocalDate

enum class DateFilterItem(val text: String, val range: DateRange) {
    ALL("All", DateRange(LocalDate.MIN, LocalDate.now())),
    TODAY("Today", DateRange(LocalDate.now().atStartOfDay().toLocalDate(), LocalDate.now())),
    WEEK("Week", DateRange(LocalDate.now().minusDays(7), LocalDate.now())),
    MONTH("Month", DateRange(LocalDate.now().withMonth(1), LocalDate.now())),
    YEAR("Year", DateRange(LocalDate.now().minusYears(1), LocalDate.now())),
}
