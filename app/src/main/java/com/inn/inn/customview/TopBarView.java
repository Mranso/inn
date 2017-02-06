package com.inn.inn.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inn.inn.R;

public class TopBarView extends RelativeLayout {

    private TextView backText;
    private TextView titleText;
    private TextView moreText;
    private TopBarClickListener topBarClickListener;

    public TopBarView(Context context) {
        super(context);
        initView();
        initListener();
    }

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initListener();
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initListener();
    }

    private void initView() {
        RelativeLayout topBarView = (RelativeLayout) View.inflate(getContext(), R.layout.top_bar_view_layout, null);
        this.addView(topBarView);
        backText = (TextView) topBarView.findViewById(R.id.top_bar_left_text);
        titleText = (TextView) topBarView.findViewById(R.id.top_bar_title);
        moreText = (TextView) topBarView.findViewById(R.id.top_bar_right_text);
    }

    private void initListener() {
        backText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topBarClickListener != null) {
                    topBarClickListener.leftClickListener();
                }
            }
        });

        moreText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topBarClickListener != null) {
                    topBarClickListener.rightClickListener();
                }
            }
        });
    }

    public void setOnTopBarClickListener(TopBarClickListener topBarClickListener) {
        this.topBarClickListener = topBarClickListener;
    }

    public interface TopBarClickListener {
        void leftClickListener();

        void rightClickListener();
    }

    public void setTopBarTitle(String string) {
        titleText.setText(string);
    }

    public void setTopBarBackText(String string) {
        backText.setText(string);
    }

    public void setTopBarMoreText(String string) {
        moreText.setText(string);
    }

    public void setTopBarLeftVisibility(boolean visibility) {
        if (visibility) {
            backText.setVisibility(VISIBLE);
        } else {
            backText.setVisibility(GONE);
        }
    }

    public void setTopBarRightVisibility(boolean visibility) {
        if (visibility) {
            moreText.setVisibility(VISIBLE);
        } else {
            moreText.setVisibility(GONE);
        }
    }

    public TextView getTopBarRightView(){
        return moreText;
    }
}
