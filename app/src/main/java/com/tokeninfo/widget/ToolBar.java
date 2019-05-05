package com.tokeninfo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tokeninfo.R;

public class ToolBar extends RelativeLayout {

    private Context context;
    private TextView titleTxt;
    private TextView extTxt;

    public ToolBar(Context context) {
        super(context);
        init();
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        context = getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.widget_toolbar, this, true);
        titleTxt = view.findViewById(R.id.txt_title);
        extTxt = view.findViewById(R.id.txt_ext);
    }

    public void setTitle(String title) {
        titleTxt.setText(title);
    }

    public void setExt(String ext) {
        extTxt.setText(ext);
    }

    public void setTitleListener(OnClickListener listener){
        titleTxt.setOnClickListener(listener);
    }

    public void setExtListener(OnClickListener listener){
        extTxt.setOnClickListener(listener);
    }
}
