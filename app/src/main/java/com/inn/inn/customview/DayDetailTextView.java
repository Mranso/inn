package com.inn.inn.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inn.inn.R;


public class DayDetailTextView extends RelativeLayout {

    private TextView contentTextView;

    public DayDetailTextView(Context context) {
        super(context);
        initView();
    }

    public DayDetailTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DayDetailTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        RelativeLayout relativeLayout = (RelativeLayout) View.inflate(getContext(), R.layout.day_detail_content_textview_layout, null);
        addView(relativeLayout);
        contentTextView = (TextView) relativeLayout.findViewById(R.id.day_detail_content_text_view);
    }

    public void setContentTextView(String content) {
        contentTextView.setText(content);
    }

}
