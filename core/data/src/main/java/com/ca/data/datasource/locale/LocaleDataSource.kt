package com.ca.data.datasource.locale

import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale

class LocaleDataSource {

    fun currentLocale(): Locale = LocaleList.getDefault()[0]

    fun setLocale(locale: Locale) {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(locale))
    }
}