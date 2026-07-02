package com.example.mywidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class MyWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {

            val remoteViews = RemoteViews(context.packageName, R.layout.widget_layout)

            // Set second radio button as checked by default
            remoteViews.setRadioGroupChecked(
                R.id.radio_group,
                R.id.radio_option_2
            )

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        // Handle future radio selection events here if needed
        if (intent.action == "com.example.mywidget.ACTION_RADIO_CHANGED") {
            val appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            )

            val selectedId = intent.getIntExtra("selected_radio", -1)

            if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                saveRadioState(context, appWidgetId, selectedId)
            }
        }
    }

    private fun saveRadioState(context: Context, widgetId: Int, selectedId: Int) {
        // Store selected radio button in SharedPreferences or database
    }
}