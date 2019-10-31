package com.mbobiosio.deviceinformation;

import static android.provider.Settings.Secure.ANDROID_ID;

import android.Manifest.permission;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mbobiosio.deviceinformation.util.InternetHelper;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.phone_key)
  TextView mPhoneKey;
  @BindView(R.id.phone_model)
  TextView mModel;
  @BindView(R.id.ip_address)
  TextView mIPAdd;
  @BindView(R.id.phone_name)
  TextView mPhoneName;
  @BindView(R.id.os_version)
  TextView mOSVersion;
  @BindView(R.id.os)
  TextView mOS;
  @BindView(R.id.network_ip)
  TextView mNetwork;

  private String android_id;
  private String str;
  private Field[] fields;
  private String osName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    TelephonyManager manager = (TelephonyManager) getApplicationContext()
        .getSystemService(Context.TELEPHONY_SERVICE);
    String carrierName = manager.getNetworkOperatorName();

    str = android.os.Build.MODEL;
    mModel.setText(str);
    mPhoneName.setText(" " + Build.MANUFACTURER);

    fields = Build.VERSION_CODES.class.getFields();
    osName = fields[Build.VERSION.SDK_INT + 1].getName();
    mOSVersion.setText(osName+"-"+VERSION.RELEASE);
    mOS.setText("Android");

    mIPAdd.setText(InternetHelper.getIPAddress());
    //mIPAdd.setText(mobileIp);
    mNetwork.setText(carrierName);

    android_id = Secure.getString(getApplicationContext().getContentResolver(),
        ANDROID_ID);
    mPhoneKey.setText(android_id);
  }

}
