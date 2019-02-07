package com.tokeninfo.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import com.tokeninfo.R;
import com.tokeninfo.ui.bean.MessageEvent;
import com.tokeninfo.ui.bean.NotificationBean;
import com.tokeninfo.util.share.AppInfo;

import androidx.core.app.NotificationCompat;

public class TradeReceiveNotificationService extends NotificationListenerService {

    public static final String CHANNEL_NAME = "tokeninfo_name";
    public static final String CHANNEL_ID = "tokeninfo_id";
    public static final String CHANNEL_DESCRIPTION = "tokeninfo_description";

    private TradeReceiveNotificationService service;
    private PowerManager.WakeLock wakeLock;

    public static void service(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TradeReceiveNotificationService.class);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        service = this;

        notificationBar();
        powerLock();
    }

    protected void notificationBar() {
        NotificationManager mNM = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && mNM != null) {
            NotificationChannel mNotificationChannel = mNM.getNotificationChannel(CHANNEL_ID);
            if (mNotificationChannel == null) {
                mNotificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                mNotificationChannel.setDescription(CHANNEL_DESCRIPTION);
                mNM.createNotificationChannel(mNotificationChannel);
            }
        }

        String title = getString(R.string.name_tokeninfo);
        NotificationCompat.Builder nb = new NotificationCompat.Builder(service, CHANNEL_ID);
        nb.setContentTitle(title).setTicker(title).setSmallIcon(R.mipmap.icon);
        nb.setContentText("运行中.请保持此通知一直存在");
        nb.setWhen(System.currentTimeMillis());
        Notification notification = nb.build();
        startForeground(1, notification);
    }

    protected void powerLock() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //保持cpu一直运行，不管屏幕是否黑屏
        if (pm != null && wakeLock == null) {
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, this.getClass().getCanonicalName());
            wakeLock.acquire();
        }
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Bundle bundle = sbn.getNotification().extras;
        String packageName = sbn.getPackageName();
        String title = bundle.getString("android.title");
        String text = bundle.getString("android.text");

        if ("android".equals(packageName)) {//系统通知
            return;
        }

        if (!AppInfo.getAppInfo().getReceivePush() && "com.tokeninfo".equals(packageName)) {//是否接受自己的通知
            return;
        }

        if (!packageName.equals(getPackageName())) {
            NotificationBean notificationBean = new NotificationBean(packageName, title, text);
            MessageEvent.send(MessageEvent.MessageEnum.Notification, notificationBean);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wakeLock != null) {
            wakeLock.release();
            wakeLock = null;
        }

        TradeReceiveNotificationService.service(this);
    }
}
