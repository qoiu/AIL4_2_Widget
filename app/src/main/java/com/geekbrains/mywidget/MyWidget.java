package com.geekbrains.mywidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyWidget extends AppWidgetProvider {

    private final String TAG="MyWidget: ";
    private static final String MyOnClick = "Button OnClick";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Toast.makeText(context,TAG+"onEnabled",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Toast.makeText(context, TAG + "onReceive", Toast.LENGTH_SHORT).show();
        if (MyOnClick.equals(intent.getAction())) {
            Toast.makeText(context, TAG + "BtnPressed", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent active = new Intent(context, MyWidget.class);
        active.setAction(MyOnClick);
        active.putExtra("msg", "This button can send extra");
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0);
        remoteViews.setOnClickPendingIntent(R.id.button, actionPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Toast.makeText(context,TAG+"onDeleted",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Toast.makeText(context,TAG+"onDisabled",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
        Toast.makeText(context,TAG+"onRestored",Toast.LENGTH_SHORT).show();
    }
}
