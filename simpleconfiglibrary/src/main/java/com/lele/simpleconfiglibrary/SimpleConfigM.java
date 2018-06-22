package com.lele.simpleconfiglibrary;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.realtek.simpleconfiglib.SCLibrary;

/**
 * Created by lele on 2018/6/21.
 */

public class SimpleConfigM {
    public static SimpleConfigM manager = null;
    private Context context;
    public SCLibrary sCLib = new SCLibrary();

    public SimpleConfigM(Context context) {
        this.context = context;
    }

    public static SimpleConfigM getInstance(Context context) {
        if (manager == null) {
            manager = new SimpleConfigM(context);
        }
        return manager;
    }

    /**
     * 初始化
     */
    public void init() {
        if (sCLib != null) {
            Log.e("leleTest", "SimpleConfig init");
            sCLib.rtk_sc_init();
            sCLib.TreadMsgHandler = new MsgHandler();
            sCLib.WifiInit(context);
        }
    }

    /**
     * 退出
     */
    public void exit() {
        if (sCLib != null) {
            Log.e("leleTest", "SimpleConfig exit");
            sCLib.rtk_sc_exit();
        }
    }

    /**
     * 停止
     */
    public void stop() {
        if (sCLib != null) {
            Log.e("leleTest", "SimpleConfig stop");
            sCLib.rtk_sc_stop();
        }
    }

    public void send(String ssid, String pwd, String phoneMac) {
        if (sCLib != null) {
            SCLibrary.PacketSendTimeIntervalMs = 0;
            if ((Build.MANUFACTURER.equalsIgnoreCase("Samsung")) &&
                    (Build.MODEL.equalsIgnoreCase("G9008"))) {
                SCLibrary.PacketSendTimeIntervalMs = 10; //10ms
            }
            sCLib.rtk_sc_reset();
            sCLib.rtk_sc_start(ssid, pwd, "57289961", "", true,
                    true, 120000, 0, (byte) 1, 1000,
                    SCLibrary.PacketSendTimeIntervalMs, (byte) 1, "", "", phoneMac);

        }
    }

    /**
     * Handler class to receive send/receive message
     */
    private class MsgHandler extends Handler {
        byte ret;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ~SCParams.Flag.CfgSuccessACK:

                    break;
                case SCParams.Flag.CfgSuccessACK:

                    break;
                case SCParams.Flag.DiscoverACK:

                    break;
                case ~SCParams.Flag.DiscoverACK:

                    break;
                case SCParams.Flag.DelProfACK:

                    break;
                case SCParams.Flag.RenameDevACK:

                    break;
                case SCParams.Flag.PackFail:
                    break;

                case SCParams.Flag.CFGTimeSendBack:

                    break;

                default:
                    break;
            }
        }
    }
}
