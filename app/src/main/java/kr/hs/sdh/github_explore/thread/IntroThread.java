package kr.hs.sdh.github_explore.thread;

import android.os.Handler;
import android.os.Message;

public class IntroThread implements Runnable {

    private Handler handler;

    public IntroThread() { }

    public IntroThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        Message msg = new Message();
        try {
            Thread.sleep(3000);
            msg.what = 1;
            handler.sendEmptyMessage(msg.what);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
