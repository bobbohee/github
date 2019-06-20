package kr.hs.sdh.github_explore.listener;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import kr.hs.sdh.github_explore.R;

public class OnClickListener implements View.OnClickListener {

    private Context context;
    private LinearLayout linearMenu;
    private boolean isMenuVisible = false;

    public OnClickListener() { }

    public OnClickListener(Context context, LinearLayout linearMenu) {
        this.context = context;
        this.linearMenu = linearMenu;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_hamburger:
                if (!isMenuVisible) {
                    linearMenu.setVisibility(View.VISIBLE);
                    isMenuVisible = true;
                } else {
                    linearMenu.setVisibility(View.INVISIBLE);
                    isMenuVisible = false;
                }
                break;
        }
    }

}
