package com.example.user.simpleconfigdemo;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lele.simpleconfiglibrary.SimpleConfigM;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tx_ssid;
    private EditText et_pwd;
    private Button bt_start, bt_stop;
    String ssid, mac, bssid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        currentWifi();
        SimpleConfigM.getInstance(MyApp.app).init();
    }

    private void initUI() {
        tx_ssid = (TextView) findViewById(R.id.tx_ssid);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bt_start = (Button) findViewById(R.id.bt_start);
        bt_stop = (Button) findViewById(R.id.bt_stop);
        bt_start.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_start) {
//            if (phoneMac == null) {
//                Toast.makeText(this, "mac is null", Toast.LENGTH_SHORT).show();
//                return;
//            }
            String s = "";
            Log.e("leleTest", "" + Arrays.toString(s.getBytes()));
            String pwd = et_pwd.getText().toString().trim();
            if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(bssid) || TextUtils.isEmpty(mac)) {
                Toast.makeText(this, " is not null", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.e("leleTest", "ssid=" + ssid + "--pwd=" + pwd + "--" + "phoneMac=" + mac + "--bssid" + bssid);
            SimpleConfigM.getInstance(MyApp.app).send(ssid, pwd, mac);
        } else if (v.getId() == R.id.bt_stop) {
            SimpleConfigM.getInstance(MyApp.app).stop();
            SimpleConfigM.getInstance(MyApp.app).exit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SimpleConfigM.getInstance(MyApp.app).exit();
    }

    /**
     * 通过WiFiManager获取mac地址
     *
     * @param context
     * @return
     */
    private static String tryGetWifiMac(Context context) {
        WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wi = wm.getConnectionInfo();
        if (wi == null || wi.getMacAddress() == null) {
            return null;
        }
        if ("02:00:00:00:00:00".equals(wi.getMacAddress().trim())) {
            return null;
        } else {
            return wi.getMacAddress().trim();
        }
    }

    public void currentWifi() {
        WifiInfo wifiInfo = WifiUtils.getInstance().getConnectWifiInfo();
        if (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getSSID())) {
            return;
        }
        ssid = wifiInfo.getSSID();
        mac = wifiInfo.getMacAddress();
        bssid = wifiInfo.getBSSID();
        int a = ssid.charAt(0);
        if (a == 34 && ssid.length() >= 3) {
            ssid = ssid.substring(1, ssid.length() - 1);
        }
        if (ssid.equals("<unknown ssid>") || ssid.equals("0x")) {
            return;
        }
        tx_ssid.setText(ssid);
        List<ScanResult> wifiList = WifiUtils.getInstance().getLists();
        if (wifiList == null) {
            return;
        }
        for (int i = 0; i < wifiList.size(); i++) {
            ScanResult result = wifiList.get(i);
            if (!result.SSID.equals(ssid)) {
                continue;
            }
        }
    }

}
