package com.lele.simpleconfiglibrary;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
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
    public void init(Handler msgHandler) {
        if (sCLib != null) {
            Log.e("leleTest", "SimpleConfig init");
            sCLib.rtk_sc_init();
            sCLib.TreadMsgHandler = msgHandler;
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

    /**
     * 开始发包
     *
     * @param ssid     WiFi的SSID
     * @param pwd      WiFi密码
     * @param phoneMac mac地址
     */
    public void send(String ssid, String pwd, String phoneMac) {
        if (sCLib != null) {
            SCLibrary.PacketSendTimeIntervalMs = 0;
            if ((Build.MANUFACTURER.equalsIgnoreCase("Samsung")) &&
                    (Build.MODEL.equalsIgnoreCase("G9008"))) {
                SCLibrary.PacketSendTimeIntervalMs = 10; //10ms
            }
            sCLib.rtk_sc_reset();
            /**
             * 参数说明
             * ssid                 wifi的ssid
             * passwd               wfifi密码
             * pin
             * bssid
             * pkt_type
             * issoftap
             * total_time           Profile(SSID+PASSWORD, contain many packets)sending total time(ms).Default: 120000 ms (2 minutes)
             * old_mode_time        Configuring by using old mode(0~ TotalConfigTimeMs)before new mode(the remaining time)Default: 30000ms(30s)
             * profile_rounds       Profile continuous sending rounds.Default: 1
             * profile_interval     Time interval(ms) between sending two rounds of profiles.Default: 1000ms
             * packet_interval      Time interval(ms) between sending two packets.Default: 0ms
             * packet_counts        The count to send each packet of a profile .Default: 1, Bigger than 1 is used for transfer reliability.
             * hostip
             * wifi_interface
             * phoneMac
             */
            sCLib.rtk_sc_start(ssid, pwd, "57289961", "", true,
                    true, 120000, 0, (byte) 1, 1000,
                    SCLibrary.PacketSendTimeIntervalMs, (byte) 1, "", "", phoneMac);

        }
    }
}
