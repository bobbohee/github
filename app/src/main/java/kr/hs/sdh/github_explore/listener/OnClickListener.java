package kr.hs.sdh.github_explore.listener;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import kr.hs.sdh.github_explore.R;
import kr.hs.sdh.github_explore.thread.UserThread;

public class OnClickListener implements View.OnClickListener {

    private Context context;
    private Handler userHandler;
    private EditText editUser;
    private RelativeLayout relHeader;
    private LinearLayout linMenu;
    private LinearLayout linMain;
    private LinearLayout linUser;
    private LinearLayout linTrend;
    private String strUser;
    private InputMethodManager imm;

    private boolean isMenuVisible = false;

    public OnClickListener() { }

    public OnClickListener(LinearLayout linMenu, LinearLayout linMain, LinearLayout linUser, LinearLayout linTrend) {
        this.linMenu = linMenu;
        this.linMain = linMain;
        this.linUser = linUser;
        this.linTrend = linTrend;
    }

    public OnClickListener(Context context, Handler userHandler, EditText editUser, RelativeLayout relHeader, LinearLayout linMenu, LinearLayout linMain, LinearLayout linUser, LinearLayout linTrend) {
        this.context = context;
        this.userHandler = userHandler;
        this.editUser = editUser;
        this.relHeader = relHeader;
        this.linMenu = linMenu;
        this.linMain = linMain;
        this.linUser = linUser;
        this.linTrend = linTrend;
        this.strUser = editUser.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_user:
                imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editUser.getWindowToken(), 0);

                relHeader.setVisibility(View.VISIBLE);
                linMain.setVisibility(View.INVISIBLE);
                linUser.setVisibility(View.VISIBLE);
                linTrend.setVisibility(View.INVISIBLE);
                strUser = editUser.getText().toString();

                UserThread userThread = new UserThread(userHandler, strUser);
                Runnable runnable = userThread;
                Thread thread = new Thread(runnable);
                thread.start();
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
