//package net.asiasofti.android.baidu;
//
//import net.asiasofti.android.ui.mystore.MyStoreActivity;
//import android.app.Service;
//import android.content.Intent;
//import android.os.IBinder;
//import android.widget.Toast;
//
//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.baidu.location.LocationClient;
//import com.baidu.location.LocationClientOption;
//
//public class LocationService extends Service {
//	static LocationClient mLocClient;
//	private MyLocationListener mListener = new MyLocationListener();
//	public static String Address=null;
//	public static double nlatitude;
//	public static double nlontitude;
//	public static String AddressName;
//	@Override
//	public IBinder onBind(Intent intent) {
//		return null;
//	}
//
//	@Override
//	public void onCreate() {
//		super.onCreate();
//		mLocClient = new LocationClient(getApplicationContext());
//		mLocClient.start();
//		mLocClient.setAK("xXwHeuZZrAb0pKPgGXWBeoZI");  
//		LocationClientOption option = new LocationClientOption();
//		option.setOpenGps(true); // 打开gps      shishi
//		option.setCoorType("bd09ll"); // 设置坐标类型为bd09ll
//		option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先
//		option.setScanSpan(0);  
//		option.setAddrType("all");
//		mLocClient.setLocOption(option);
//		mLocClient.registerLocationListener(mListener);
//		System.out.println("开始：" + mLocClient.isStarted());
//	}
//
//	/**
//	 * 定位SDK监听函数
//	 */
//	public class MyLocationListener implements BDLocationListener {
//		@Override
//		public void onReceiveLocation(BDLocation location) {
//			if (location == null)
//				return;
//			StringBuffer sb = new StringBuffer(256);
//			sb.append("time : ");
//			sb.append(location.getTime());
//			sb.append("\nerror code : ");
//			sb.append(location.getLocType());
//			sb.append("\nlatitude : ");
////			NearbyActivity.lat = location.getLatitude();
////			BaiDuDiTuActivity.lat =location.getLatitude();
//			nlatitude=location.getLatitude();
//			sb.append(location.getLatitude());
//			sb.append("\nlontitude : ");
////			NearbyActivity.lng = location.getLongitude();
////			BaiDuDiTuActivity.lng=location.getLongitude();
//			nlontitude=location.getLongitude();
//			sb.append(location.getLongitude());
//			sb.append("\nradius : ");
//			sb.append(location.getRadius());
//			if (location.getLocType() == BDLocation.TypeGpsLocation) {
//				sb.append("\nspeed : ");
//				sb.append(location.getSpeed());
//				sb.append("\nsatellite : ");
//				sb.append(location.getSatelliteNumber());
//			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//				sb.append("\naddr : ");
//				sb.append(location.getAddrStr());
//				String address = location.getProvince();
//				System.out.println("省份" + address+";"+location.getAddrStr());
//				Address=address;
//				AddressName=location.getAddrStr();
//			}
//			System.out.println("地理信息" + sb.toString());
//			// logMsg(sb.toString());
//		}
//
//		public void onReceivePoi(BDLocation poiLocation) {
//			if (poiLocation == null) {
//				return;
//			}
//			StringBuffer sb = new StringBuffer(256);
//			sb.append("Poi time : ");
//			sb.append(poiLocation.getTime());
//			sb.append("\nerror code : ");
//			sb.append(poiLocation.getLocType());
//			sb.append("\nlatitude : ");
//			sb.append(poiLocation.getLatitude()); // 纬度
//			sb.append("\nlontitude : ");
//			sb.append(poiLocation.getLongitude()); // 经度
//			sb.append("\nradius : ");
//			sb.append(poiLocation.getRadius());
//			if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
//				sb.append("\naddr : ");
//				sb.append(poiLocation.getAddrStr());
//			}
//			if (poiLocation.hasPoi()) {
//				sb.append("\nPoi:");
//				sb.append(poiLocation.getPoi());
//			} else {
//				sb.append("noPoi information");
//			}
//			System.out.println("位置信息" + sb.toString());
//			// logMsg(sb.toString());
//		}
//	}
//
//	// class MyLocationListenner implements BDLocationListener {
//	//
//	// @Override
//	// public void onReceiveLocation(BDLocation location) {
//	// System.out.println("-----------定位SDK监听------------");
//	// if (location == null){
//	// return ;
//	// }
//	// location.getAddrStr();
//	// System.out.println("-----------获取地址------------");
//	// location.getCity();
//	// System.out.println("address------------"+location.getAddrStr()+"\n"+"city----------"+location.getCity());
//	// }
//	//
//	// public void onReceivePoi(BDLocation poiLocation) {
//	// if (poiLocation == null)
//	// return ;
//	// }
//	// }
//
//	@Override
//	public int onStartCommand(Intent intent, int flags, int startId) {
//		return super.onStartCommand(intent, flags, startId);
//	}
//
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		if (mLocClient != null) {
//			mLocClient.stop();
//			mLocClient = null;
//		}
//	}
//	
//}
