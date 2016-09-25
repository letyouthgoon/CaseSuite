/**
 * @copyright Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license http://www.asiasofti.com
 * @link http://www.asiasofti.com
 * @since File available since Release v1.1
 */
package net.asiasofti.android.ui.mystore;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.HomeAdapter;
import net.asiasofti.android.adapter.SubmenuListViewAdapter;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.common.MySrcAsynaTask;
import net.asiasofti.android.en.Classify;
import net.asiasofti.android.en.HttpInputStream;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.HistoryBrowse;
import net.asiasofti.android.model.MyStore;
import net.asiasofti.android.model.ResponseData;
import net.asiasofti.android.ui.cart.CartActivity;
import net.asiasofti.android.ui.custom.MyListView;
import net.asiasofti.android.ui.mr.CityActivity;
import net.asiasofti.android.ui.type.GoodsTabActivity;
import net.asiasofti.android.ui.viewpager.SlideHolder;
import net.asiasofti.android.ui.viewpager.ViewPagerDemoActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

// Referenced classes of package net.asiasofti.android.ui.mystore:
//			LoginActivity, OrderListActivity, FavoritesListActivity, AddressListActivity, 
//			HistoryBrowseListActivity, HelpActivity, RegisteredActivity

public class MyStoreActivity extends Activity implements OnClickListener {
    public static String a;
    private EditText edittext;
    private TextView city_text;
    private ScrollView SView;
    private SubmenuListViewAdapter adapter;
    private Button buttonLoginSubmit;
    private Button buttonLoingOut;
    private Button buttonRegistered;
    private ImageView imageMyAvator;
    private LinearLayout linearLayoutNOLogin;
    private LinearLayout linearLayoutYesLogin;
    private BroadcastReceiver mBroadcastReceiver;
    private MyApp myApp;
    private MyListView mystoreListView;
    private TextView textMyPoint;
    private TextView textMyPredepoit;
    private TextView textMyUserName;
    private ListView home_list;
    private DisplayMetrics dm;
    private ImageView imageViewYu, imageViewMe, imageViewJu, imageViewGo;
    private int width;
    private SlideHolder mSlideHolder;
    private ScrollView scrollView;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                HomeAdapter adapter = new HomeAdapter(MyStoreActivity.this,
                        classify);
                home_list.setAdapter(adapter);
                setListViewHeight(home_list);
            }

        }

        ;
    };

    public MyStoreActivity() {
        mBroadcastReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Constants.APP_BORADCASTRECEIVER))
                    loadingMyStoreData();
            }

        };
    }

    public void delAllFile(String s) {
        File file;
        file = new File(s);
        if (file.exists() && file.isDirectory()) {
            String as[] = file.list();
            int i = 0;
            while (i < as.length) {
                File file1;
                if (s.endsWith(File.separator))
                    file1 = new File((new StringBuilder(String.valueOf(s)))
                            .append(as[i]).toString());
                else
                    file1 = new File((new StringBuilder(String.valueOf(s)))
                            .append(File.separator).append(as[i]).toString());
                if (file1.isFile())
                    file1.delete();
                if (file1.isDirectory())
                    delAllFile((new StringBuilder(String.valueOf(s)))
                            .append("/").append(as[i]).toString());
                i++;
            }
        }
        return;
    }

    public void loadingMyStoreData() {
        HashMap hashmap = new HashMap();
        hashmap.put("key", myApp.getLoginKey());
        RemoteDataHandler.asyncPost2(Constants.URL_MYSTOIRE, hashmap,
                new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

                    public void dataLoaded(ResponseData responsedata) {
                        String s;
                        if (responsedata.getCode() == 200) {
                            s = responsedata.getJson();
                            if (responsedata.getLogin() == 0) {
                                myApp.setLoginKey("");
                                linearLayoutNOLogin.setVisibility(0);
                                linearLayoutYesLogin.setVisibility(8);
                                buttonLoingOut.setVisibility(8);
                            }

                            try {
                                String s1 = (new JSONObject(s))
                                        .getString("error");
                                if (s1 != null) {
                                    Toast.makeText(MyStoreActivity.this, s1, 0)
                                            .show();
                                }
                                return;
                            } catch (JSONException jsonexception) {
                                jsonexception.printStackTrace();
                            }
                            try {
                                MyStore mystore = MyStore
                                        .newInstanceList((new JSONObject(s))
                                                .getString("member_info"));
                                textMyUserName.setText(mystore.getUsername());
                                textMyPoint.setText(mystore.getPoint());
                                textMyPredepoit
                                        .setText((new StringBuilder("￥"))
                                                .append(mystore.getPredepoit())
                                                .toString());
                                (new MySrcAsynaTask(mystore.getAvator(),
                                        imageMyAvator)).execute(new String[0]);
                                return;
                            } catch (JSONException jsonexception1) {
                                jsonexception1.printStackTrace();
                            }
                            return;
                        }
                        Toast.makeText(MyStoreActivity.this, "数据加载失败，请稍后重试", 0)
                                .show();

                    }

                });
    }

    public void loginOut() {
        HashMap hashmap = new HashMap();
        hashmap.put("key", myApp.getLoginKey());
        hashmap.put("username", myApp.getLoginName());
        hashmap.put("client", "android");
        myApp.setLoginKey("");
        myApp.setLoginName("");
        HistoryBrowse.deleteAll();
        linearLayoutNOLogin.setVisibility(0);
        linearLayoutYesLogin.setVisibility(8);
        buttonLoingOut.setVisibility(8);
        RemoteDataHandler.asyncPost2(Constants.URL_LOGIN_OUT, hashmap,
                new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

                    public void dataLoaded(ResponseData responsedata) {
                        String s;
                        if (responsedata.getCode() == 200) {
                            s = responsedata.getJson();
                            try {
                                if (s != null) {
                                    if (s.equals("1")) {

                                    }
                                    String error = (new JSONObject(s))
                                            .getString("error");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            return;
                        }
                        return;
                    }

                });
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        // String pm = "手机：" + dm.widthPixels + "*" + dm.heightPixels;
        width = (dm.widthPixels) / 5;
        width = (dm.widthPixels) - width;
        setContentView(R.layout.act_main);

        new Thread(runnable).start();
        scrollView = (ScrollView) findViewById(R.id.scrollview);
        LayoutParams params = (LayoutParams) scrollView.getLayoutParams();
        params.width = width;
        scrollView.setLayoutParams(params);

        home_list = (ListView) findViewById(R.id.home_listview);
        edittext = (EditText) findViewById(R.id.edittext);
        TextView textView = (TextView) findViewById(R.id.home_text);
        TextPaint paint = textView.getPaint();
        paint.setFakeBoldText(true);
        edittext.setOnClickListener(this);
        city_text = (TextView) findViewById(R.id.city_texth);
        city_text.setText(ViewPagerDemoActivity.Address);
        city_text.setOnClickListener(this);
        myApp = (MyApp) getApplication();
        mystoreListView = (MyListView) findViewById(R.id.mystoreListView);
        imageMyAvator = (ImageView) findViewById(R.id.imageMyAvator);
        textMyUserName = (TextView) findViewById(R.id.textMyUserName);
        textMyPoint = (TextView) findViewById(R.id.textMyPoint);
        textMyPredepoit = (TextView) findViewById(R.id.textMyPredepoit);
        buttonLoingOut = (Button) findViewById(R.id.buttonLoingOut);
        linearLayoutYesLogin = (LinearLayout) findViewById(R.id.linearLayoutYesLogin);
        linearLayoutNOLogin = (LinearLayout) findViewById(R.id.linearLayoutNOLogin);
        buttonLoginSubmit = (Button) findViewById(R.id.buttonLoginSubmit);
        SView = (ScrollView) findViewById(R.id.SView);
        mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);

		/*
         * toggleView can actually be any view you want. Here, for simplicity,
		 * we're using TextView, but you can easily replace it with button.
		 * 
		 * Note, when menu opens our textView will become invisible, so it quite
		 * pointless to assign toggle-event to it. In real app consider using UP
		 * button instead. In our case toggle() can be replaced with open().
		 */
        View toggleView = findViewById(R.id.home_image);
        toggleView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mSlideHolder.toggle();
            }
        });

        mystoreListView
                .setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view,
                                            int i, long l) {
                        int j = (int) l;
                        Intent intent = null;
                        switch (j) {
                            case R.drawable.huancun:
                                intent = new Intent(
                                        MyStoreActivity.this,
                                        net.asiasofti.android.ui.more.AboutActivity.class);
                                break;
                            case R.drawable.gou:
                                if (myApp.getLoginKey() == null
                                        || myApp.getLoginKey().equals("")
                                        || myApp.getLoginKey().equals("null")) {
                                    Intent intent2 = new Intent(
                                            MyStoreActivity.this,
                                            net.asiasofti.android.ui.mystore.LoginActivity.class);
                                    startActivity(intent2);
                                    return;
                                }
                                intent = new Intent(MyStoreActivity.this,
                                        CartActivity.class);
                                break;
                            case R.drawable.qingchu:
                                HistoryBrowse.deleteAll();
                                Toast.makeText(MyStoreActivity.this, "删除浏览历史成功", 0)
                                        .show();
                                intent = null;
                                break;
                            case R.drawable.zaixian:
                                intent = new Intent(
                                        MyStoreActivity.this,
                                        net.asiasofti.android.ui.mystore.HelpActivity.class);
                                break; /* Loop/switch isn't completed */
                            case R.drawable.lishijilu:
                                delAllFile(Constants.CACHE_DIR_IMAGE);
                                Toast.makeText(MyStoreActivity.this, "删除缓存图片成功", 0)
                                        .show();
                                intent = null;
                                break; /* Loop/switch isn't completed */
                            case R.drawable.dizhi:
                                if (myApp.getLoginKey() == null
                                        || myApp.getLoginKey().equals("")
                                        || myApp.getLoginKey().equals("null")) {
                                    Intent intent2 = new Intent(

                                            MyStoreActivity.this,
                                            net.asiasofti.android.ui.mystore.LoginActivity.class);
                                    startActivity(intent2);
                                    return;
                                }
                                intent = new Intent(
                                        MyStoreActivity.this,
                                        net.asiasofti.android.ui.mystore.AddressListActivity.class);
                                break; /* Loop/switch isn't completed */
                            case R.drawable.shoucang:
                                if (myApp.getLoginKey() == null
                                        || myApp.getLoginKey().equals("")
                                        || myApp.getLoginKey().equals("null")) {
                                    Intent intent3 = new Intent(
                                            MyStoreActivity.this,
                                            net.asiasofti.android.ui.mystore.LoginActivity.class);
                                    startActivity(intent3);
                                    return;
                                }
                                intent = new Intent(
                                        MyStoreActivity.this,
                                        net.asiasofti.android.ui.mystore.FavoritesListActivity.class);
                                break; /* Loop/switch isn't completed */
                            case R.drawable.lishi:
                                if (myApp.getLoginKey() == null
                                        || myApp.getLoginKey().equals("")
                                        || myApp.getLoginKey().equals("null")) {
                                    Intent intent1 = new Intent(
                                            MyStoreActivity.this,
                                            net.asiasofti.android.ui.mystore.LoginActivity.class);
                                    startActivity(intent1);
                                    return;
                                }
                                intent = new Intent(
                                        MyStoreActivity.this,
                                        net.asiasofti.android.ui.mystore.HistoryBrowseListActivity.class);
                                break; /* Loop/switch isn't completed */
                            case R.drawable.dingdan:
                                if (myApp.getLoginKey() == null
                                        || myApp.getLoginKey().equals("")
                                        || myApp.getLoginKey().equals("null")) {
                                    Intent intent4 = new Intent(
                                            MyStoreActivity.this,
                                            net.asiasofti.android.ui.mystore.LoginActivity.class);
                                    startActivity(intent4);
                                    return;
                                }
                                intent = new Intent(
                                        MyStoreActivity.this,
                                        net.asiasofti.android.ui.mystore.OrderListActivity.class);
                                break; /* Loop/switch isn't completed */

                        }

                        if (intent != null)
                            startActivity(intent);
                        return;

                    }

                });
        buttonLoingOut
                .setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view) {
                        loginOut();
                    }

                });
        buttonLoginSubmit
                .setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view) {
                        Intent intent = new Intent(
                                MyStoreActivity.this,
                                net.asiasofti.android.ui.mystore.LoginActivity.class);
                        startActivity(intent);
                    }

                });
        // buttonRegistered
        // .setOnClickListener(new android.view.View.OnClickListener() {
        //
        // public void onClick(View view) {
        // Intent intent = new Intent(
        // MyStoreActivity.this,
        // net.asiasofti.android.ui.mystore.RegisteredActivity.class);
        // startActivity(intent);
        // }
        //
        // });
        imageViewYu = (ImageView) findViewById(R.id.imageview);
        imageViewMe = (ImageView) findViewById(R.id.imageview1);
        imageViewJu = (ImageView) findViewById(R.id.imageview2);
        imageViewGo = (ImageView) findViewById(R.id.imageview3);
        imageViewYu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(MyStoreActivity.this, GoodsTabActivity.class);
                intent.putExtra("gc_id", "1073");
                intent.putExtra("gc_name", "所有商品");
                intent.putExtra("keyword", "");
                startActivity(intent);
            }
        });
        imageViewMe.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(MyStoreActivity.this, GoodsTabActivity.class);
                intent.putExtra("gc_id", "1073");
                intent.putExtra("gc_name", "所有商品");
                intent.putExtra("keyword", "");
                startActivity(intent);
            }
        });
        imageViewJu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(MyStoreActivity.this, GoodsTabActivity.class);
                intent.putExtra("gc_id", "1073");
                intent.putExtra("gc_name", "所有商品");
                intent.putExtra("keyword", "");
                startActivity(intent);
            }
        });
        imageViewGo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(MyStoreActivity.this, GoodsTabActivity.class);
                intent.putExtra("gc_id", "1073");
                intent.putExtra("gc_name", "所有商品");
                intent.putExtra("keyword", "");
                startActivity(intent);
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent) {
        if (i == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("退出")
                    .setMessage("是否退出软件")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0,
                                                    int arg1) {
                                    finish();
                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0,
                                                    int arg1) {

                                }
                            });
            builder.create().show();
        }
        return true;
    }

    protected void onResume() {
        super.onResume();
        if (a != null) {
            city_text.setText(a);
        }
        ArrayList arraylist;
        HashMap hashmap;
        HashMap hashmap1;
        HashMap hashmap2;
        HashMap hashmap3;
        HashMap hashmap4;
        HashMap hashmap5;
        HashMap hashmap6;
        HashMap hashmap7;
        HashMap hashmap8;
        if (myApp.getLoginKey() == null || myApp.getLoginKey().equals("")
                || myApp.getLoginKey().equals("null")) {
            linearLayoutNOLogin.setVisibility(0);
            linearLayoutYesLogin.setVisibility(8);
            buttonLoingOut.setVisibility(8);
        } else {
            linearLayoutYesLogin.setVisibility(0);
            linearLayoutNOLogin.setVisibility(8);
            buttonLoingOut.setVisibility(0);
        }
        arraylist = new ArrayList();
        hashmap = new HashMap();
        hashmap.put("text", getString(R.string.mystore_order_text));
        hashmap.put("icon", Integer.valueOf(R.drawable.dingdan));
        arraylist.add(hashmap);
        hashmap1 = new HashMap();
        hashmap1.put("text", getString(R.string.mystore_collection_text));
        hashmap1.put("icon", Integer.valueOf(R.drawable.shoucang));
        arraylist.add(hashmap1);
        hashmap2 = new HashMap();
        hashmap2.put("text", getString(R.string.mystore_address_text));
        hashmap2.put("icon", Integer.valueOf(R.drawable.dizhi));
        arraylist.add(hashmap2);
        hashmap3 = new HashMap();
        hashmap3.put("text", getString(R.string.mystore_history_text));
        hashmap3.put("icon", Integer.valueOf(R.drawable.lishi));
        arraylist.add(hashmap3);
        hashmap4 = new HashMap();
        hashmap4.put("text", getString(R.string.mystore_huancun_text));
        hashmap4.put("icon", Integer.valueOf(R.drawable.qingchu));
        arraylist.add(hashmap4);
        hashmap5 = new HashMap();
        hashmap5.put("text", getString(R.string.mystore_pic_huancan_text));
        hashmap5.put("icon", Integer.valueOf(R.drawable.lishijilu));
        arraylist.add(hashmap5);
        hashmap6 = new HashMap();
        hashmap6.put("text", getString(R.string.mystore_help_text));
        hashmap6.put("icon", Integer.valueOf(R.drawable.zaixian));
        arraylist.add(hashmap6);
        hashmap7 = new HashMap();
        hashmap7.put("text", getString(R.string.mystore_about_text));
        hashmap7.put("icon", Integer.valueOf(R.drawable.huancun));
        arraylist.add(hashmap7);
        hashmap8 = new HashMap();
        hashmap8.put("text", getString(R.string.mystore_shopping_text));
        hashmap8.put("icon", Integer.valueOf(R.drawable.gou));
        arraylist.add(hashmap8);
        adapter = new SubmenuListViewAdapter(this, arraylist);
        mystoreListView.setAdapter(adapter);
    }

    protected void onStart() {
        super.onStart();
        registerBoradcastReceiver();
    }

    public void registerBoradcastReceiver() {
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(Constants.APP_BORADCASTRECEIVER);
        registerReceiver(mBroadcastReceiver, intentfilter);
    }

    /**
     * 侧滑菜单的数据解析
     */
    List<Classify> classify = new ArrayList<Classify>();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                // 这里
                HttpInputStream stream = new HttpInputStream();
                String url = stream.InputStream(Constants.URL_GOODSCLASS);
                if (!TextUtils.isEmpty(url)) {
                    classify.clear();
                    JSONObject jsonObject = new JSONObject(url);
                    if (jsonObject.getString("code").equals("200")) {
                        jsonObject = jsonObject.optJSONObject("datas");
                        JSONArray array = jsonObject.optJSONArray("class_list");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.optJSONObject(i);
                            classify.add(new Classify().deserialize(object));
                        }
                        handler.sendEmptyMessage(0);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    // ScrollView防止嵌套listview的冲突
    public void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight
                    + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);

        }
    }

    private int num = 0;

    public void setSort(int i) {
        // int width = children[0].getMeasuredWidth();
        // moveScrollList(width);
        this.num = i;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.edittext:
                // TODO: 16/9/25 yy
                intent.setClass(MyStoreActivity.this, GoodsTabActivity.class);
                intent.putExtra("gc_id", "4");
                intent.putExtra("gc_name", "所有商品");
                intent.putExtra("keyword", "");
                startActivity(intent);
                break;
            case R.id.city_texth:
                intent.setClass(MyStoreActivity.this, CityActivity.class);
                startActivity(intent);
                break;

        }
    }

}
