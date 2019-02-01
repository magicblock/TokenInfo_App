package com.tokeninfo.ui.bean;

import org.greenrobot.eventbus.EventBus;

public class MessageEvent {

    private String content;

    public MessageEvent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public static void send(String content) {
        MessageEvent messageEvent = new MessageEvent(content);
        EventBus.getDefault().post(messageEvent);
    }
}
