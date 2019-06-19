package kr.hs.sdh.github_explore.listener;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import kr.hs.sdh.github_explore.R;

public class OnClickListener implements View.OnClickListener {

    private Context context;
    private LinearLayout linearHamburger;

    public OnClickListener() { }

    public OnClickListener(Context context, LinearLayout linearHamburger) {
        this.context = context;
        this.linearHamburger = linearHamburger;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_hamburger:
                break;
        }
    }

}
