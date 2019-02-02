package com.tokeninfo.ui.bean;

import org.greenrobot.eventbus.EventBus;

public class MessageEvent {

    private MessageEnum messageEnum;
    private Object obj;

    public MessageEvent(MessageEnum e, Object obj) {
        this.messageEnum = e;
        this.obj = obj;
    }

    public static void send(MessageEnum e, Object obj) {
        MessageEvent messageEvent = new MessageEvent(e, obj);
        EventBus.getDefault().post(messageEvent);
    }

    public MessageEnum getMessageEnum() {
        return messageEnum;
    }

    public Object getObj() {
        return obj;
    }

    public enum MessageEnum {
        Notice,
        Notification,
    }
}
