package com.jahu.playground.extensions

import android.os.Build
import android.text.Html
import android.widget.TextView

@Suppress("DEPRECATION")
fun TextView.setHtmlText(htmlText: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(htmlText)
    }
}
