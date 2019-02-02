package com.tokeninfo.ui.bean;

import androidx.annotation.NonNull;

public class NotificationBean {

    private String packageName;
    private String title;
    private String content;

    public NotificationBean(String packageName, String title, String content) {
        this.packageName = packageName;
        this.title = title;
        this.content = content;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @NonNull
    @Override
    public String toString() {
        return packageName + title + content;
    }
}
