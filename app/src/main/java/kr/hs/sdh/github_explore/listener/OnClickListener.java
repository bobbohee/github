package kr.hs.sdh.github_explore.listener;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import kr.hs.sdh.github_explore.R;

public class OnClickListener implements View.OnClickListener {

    private RelativeLayout relHeader;
    private LinearLayout linMenu;
    private LinearLayout linMain;
    private LinearLayout linUser;
    private LinearLayout linTrend;

    private boolean isMenuVisible = false;

    public OnClickListener() { }

    public OnClickListener(RelativeLayout relHeader, LinearLayout linMenu, LinearLayout linMain, LinearLayout linUser, LinearLayout linTrend) {
        this.relHeader = relHeader;
        this.linMenu = linMenu;
        this.linMain = linMain;
        this.linUser = linUser;
        this.linTrend = linTrend;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_user:
                relHeader.setVisibility(View.VISIBLE);
                linMain.setVisibility(View.INVISIBLE);
                linUser.setVisibility(View.VISIBLE);
                linTrend.setVisibility(View.INVISIBLE);
                break;
            case R.id.lin_hamburger:
                if (!isMenuVisible) {
                    linMenu.setVisibility(View.VISIBLE);
                    isMenuVisible = true;
                } else {
                    linMenu.setVisibility(View.INVISIBLE);
                    isMenuVisible = false;
                }
                break;
            case R.id.txt_menu_explore:
                linMain.setVisibility(View.INVISIBLE);
                linUser.setVisibility(View.INVISIBLE);
                linTrend.setVisibility(View.VISIBLE);
                linMenu.setVisibility(View.INVISIBLE);
                isMenuVisible = false;
                break;
            case R.id.txt_menu_user:
                linMain.setVisibility(View.INVISIBLE);
                linUser.setVisibility(View.VISIBLE);
                linTrend.setVisibility(View.INVISIBLE);
                linMenu.setVisibility(View.INVISIBLE);
                isMenuVisible = false;
                break;
        }
    }

}
