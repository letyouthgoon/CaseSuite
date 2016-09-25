package net.asiasofti.android.ui.viewpager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

import net.asiasofti.android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ScheduledExecutorService;

public class ViewPagerDemoActivity extends Activity {
    private static final String TAG = "Activity";
    /**
     * 这里定义了相册的总数100条.
     */
    private static final int ALBUM_COUNT = 100;

    private int currentItem;
    private ScheduledExecutorService scheduledExecutorService;
    private int switchImageCount;
    /**
     * 相册的资源,实战开发中都是网络数据或者本地相册.
     */
    private static final int ALBUM_RES[] = {
            R.drawable.one, R.drawable.one, R.drawable.one,

    };

    private static final String picURL[] = {
            "http://tcyd.asiasofti.com/one.png",
            "http://tcyd.asiasofti.com/one.png",
            "http://tcyd.asiasofti.com/one.png"

    };

    private static final String picTitle[] = {
            "",
            "",
            "",
    };

    private ViewPager mViewPager;
    /**
     * 适配器.
     */
    private ViewPagerAdapter mViewPagerAdapter;

    /**
     * 数据源.
     */
    private JSONArray mJsonArray;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    public static double nlatitude;
    public static double nlontitude;
    public static String AddressName;
    public static String Address;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        Intent intent = new Intent(this, LocationService.class);
//		 startService(intent);
        setupViews();
        mLocationClient = new LocationClient(this.getApplicationContext());
        mLocationClient.start();
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
        option.setCoorType("bd09ll");//返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(0);//设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);//返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true);//返回的定位结果包含手机机头的方向
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(myListener);
    }

    public void setStop(Context context) {
        ViewPagerDemoActivity.this.finish();
    }

    private void setupViews() {
        //初始化JSonArray,给ViewPageAdapter提供数据源用.
        mJsonArray = new JSONArray();
        for (int i = 0; i < picURL.length; i++) {
            JSONObject object = new JSONObject();
            try {
                object.put("url", picURL[i]);
                object.put("title", picTitle[i]);
                mJsonArray.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPagerAdapter = new ViewPagerAdapter(this, mJsonArray, ViewPagerDemoActivity.this, ALBUM_RES);
        switchImageCount = mViewPagerAdapter.getCount();
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    //切换当前显示的图片
    private Handler switchHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPager.setCurrentItem(currentItem);// 切换当前显示的图片
        }
    };

    /**
     * 执行切换任务
     *
     * @author dyb
     */
    private class ScrollTask implements Runnable {
        public void run() {
            synchronized (mViewPager) {
                //System.out.println("currentItem: " + currentItem)
                Log.v(TAG, "currentItem" + currentItem);
                currentItem = (currentItem + 1) % switchImageCount;
                switchHandler.obtainMessage().sendToTarget();
            }
        }
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null)
                return;
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            nlatitude = location.getLatitude();
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            nlontitude = location.getLongitude();
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append(location.getProvince());
                Address = location.getProvince();
                AddressName = location.getAddrStr();
                String xiang = location.getStreetNumber();
                String di = location.getStreet();
                String shi = location.getCity();
                String q = location.getDistrict();
                System.out.println(Address + ":" + AddressName + ":" + shi + ":" + q + ":" + di + ":" + xiang);
            }

        }
    }
}