package cn.rongcloud.live.util.NormalUtil;

import android.os.Handler;
import android.os.Message;

/**
 * Created by YSten on 2017/3/5.
 */

public class ThreadUtil {

    public static class TimerThread implements Runnable {
        private final Handler handler;
        private final  int time;

        public TimerThread(Handler handler, int time) {
            this.handler = handler;
            this.time = time;
        }

        // thread
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);     // sleep 1000ms
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    if (time == 0) {
                        break;
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}
