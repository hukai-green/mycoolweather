package com.example.mycoolweather.widget;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.mycoolweather.R;
import com.example.mycoolweather.bean.County;
import com.example.mycoolweather.bean.Tab;
import com.example.mycoolweather.fragment.CategoryFragment;
import com.example.mycoolweather.fragment.HomeFragment;
import com.example.mycoolweather.fragment.HotFragment;
import com.example.mycoolweather.fragment.MineFragment;
import com.example.mycoolweather.util.HttpCallbackListener;
import com.example.mycoolweather.util.HttpUtil;


public class MainActivity extends FragmentActivity {
	
//	private Button leftButton;
//	private ImageView leftPicture;
//	private TextView wendu;
//	private TextView ganmao;
//	private TextView city;
//	private TextView fengli;
//	private TextView highlow1;
//	private TextView highlow2;
//	private TextView type;
//	private TextView date;
//	private View view;
	
	private County selectedcounty;
//	private CoolWeatherDB coolWeatherDB;
	

	private LayoutInflater mInflater;
	private FragmentTabHost mTabhost;
	private List<Tab> mTabs = new ArrayList<Tab>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initTab();
//		view = LayoutInflater.from(this).inflate(R.layout.fragment_home, null);
//		
//		leftButton = (Button) view.findViewById(R.id.left_btn);
//		city = (TextView) view.findViewById(R.id.title_text);
//		leftPicture = (ImageView) view.findViewById(R.id.left_picture);
//		wendu = (TextView) view.findViewById(R.id.current_wendu);
//		ganmao = (TextView) view.findViewById(R.id.current_tip);
//		fengli = (TextView) view.findViewById(R.id.current_fengli);
//		highlow1 = (TextView) view.findViewById(R.id.today_wendu);
//		highlow2 = (TextView) view.findViewById(R.id.tomorrow_wendu);
//		type = (TextView) view.findViewById(R.id.current_state);
//		date = (TextView) view.findViewById(R.id.current_date);
//		
//		leftButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent i = new Intent(MainActivity.this, WeatherActivity.class);
//				startActivity(i);
//			}
//			
//		});
		
		
		String countyCode = getIntent().getStringExtra("county_code");
		selectedcounty = (County) getIntent().getSerializableExtra(
				"selected_county");
//		coolWeatherDB = CoolWeatherDB.getInstance(this);

		if (!TextUtils.isEmpty(countyCode)) {
//			publishText.setText("同步中...");
//			weatherInfoLayout.setVisibility(View.INVISIBLE);
//			cityNameText.setVisibility(View.INVISIBLE);
			queryWeatherCode(countyCode);
		}
//		else {
//			showWeather(selectedcounty.getId());
//		}

	}

	private void queryWeatherCode(String countyCode) {
		String address = "http://www.weather.com.cn/data/list3/city"
				+ countyCode + ".xml";
		queryFromServer(address, "countyCode");
	}

	private void queryWeatherInfo(String weatherCode) {
		String address = "http://wthrcdn.etouch.cn/weather_mini?citykey="
				+ weatherCode;
		queryFromServer(address, "weatherCode");
	}

	private void queryFromServer(final String address, final String type) {
		HttpUtil.sendHttpResponse(address, new HttpCallbackListener() {
			@Override
			public void onFinish(String response) {
				// TODO Auto-generated method stub
				if ("countyCode".equals(type)) {
					if (!TextUtils.isEmpty(response)) {
						String[] array = response.split("\\|");
						if (array != null && array.length == 2) {
							String weatherCode = array[1];
							queryWeatherInfo(weatherCode);
						}
					}
				} else if ("weatherCode".equals(type)) {
//					Utility.handleWeatherResponse(MainActivity.this,
//							response, countyCode);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
//							showWeather(selectedcounty.getId());
						}

					});
				}
			}

			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Log.d("WeatherActivity", "同步失败...");
					}

				});
			}

		});
	}

//	public void showWeather(int countyId) {
//		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//		List<Dt> dtlist = new ArrayList<Dt>();
//		coolWeatherDB.loadDts(countyId);
//		dtlist = coolWeatherDB.loadDts(countyId);
		
//		city.setText(prefs.getString("city", ""));
//		wendu.setText(prefs.getString("wendu", ""));
//		ganmao.setText(prefs.getString("ganmao", ""));
//		type.setText(dtlist.get(0).getType1());
//		fengli.setText(dtlist.get(0).getFengli());
//		date.setText(dtlist.get(0).getDate1());
//		highlow1.setText(dtlist.get(0).getLow1() + "~" + dtlist.get(0).getHigh1());
//		highlow2.setText(dtlist.get(1).getLow1() + "~" + dtlist.get(1).getHigh1());
//		Log.d("WeatherActivity", prefs.getString("city", ""));
//		Log.d("WeatherActivity", prefs.getString("wendu", ""));
//		Log.d("WeatherActivity", prefs.getString("ganmao", ""));
//		Log.d("WeatherActivity", dtlist.get(0).getIfengxiang());
//		Log.d("WeatherActivity", dtlist.get(0).getFengli());
//		Log.d("WeatherActivity", dtlist.get(0).getHigh1());
//		Log.d("WeatherActivity", dtlist.get(0).getType1());
//		Log.d("WeatherActivity", dtlist.get(0).getLow1());
//		Log.d("WeatherActivity", dtlist.get(0).getDate1());
//		Log.d("WeatherActivity", dtlist.get(1).getLow1());
//		Log.d("WeatherActivity", dtlist.get(1).getHigh1());
//		Log.d("WeatherActivity", dtlist.get(0).getLow1() + "~" + dtlist.get(0).getHigh1());
//		Log.d("WeatherActivity", dtlist.get(1).getLow1() + "~" + dtlist.get(1).getHigh1());
		
//		highlow1.setText(dtlist.get(0).getLow1() + "~" + dtlist.get(0).getHigh1());
//		highlow2.setText(dtlist.get(1).getLow1() + "~" + dtlist.get(1).getHigh1());
//			for (int i = 0; i < dtlist.size(); i++) {
//				Log.d("WeatherActivity", dtlist.get(0).getLow1());
//			}

		// dts = (Dt) dtlist;
//	}

	// Log.d("WeatherActivity", dts.getIfengxiang());
	// Log.d("WeatherActivity", dts.getFengli());
	// Log.d("WeatherActivity", dts.getHigh1());
	// Log.d("WeatherActivity", dts.getType1());
	// Log.d("WeatherActivity", dts.getLow1());
	// Log.d("WeatherActivity", dts.getDate1());

	// for(Dt dt : dtlist) {
	// list.add(dt.getIfengxiang());
	// list.add(dt.getFengli());
	// list.add(dt.getHigh1());
	// list.add(dt.getType1());
	// list.add(dt.getLow1());
	// list.add(dt.getDate1());
	//
	// Log.d("WeatherActivity", dt.getIfengxiang() + "\n");
	// Log.d("WeatherActivity", dt.getFengli() + "\n");
	// Log.d("WeatherActivity", dt.getHigh1() + "\n");
	// Log.d("WeatherActivity", dt.getType1() + "\n");
	// Log.d("WeatherActivity", dt.getLow1() + "\n");
	// Log.d("WeatherActivity", dt.getDate1() + "\n");
	// }
	// }
	// }

		
	
	
	
	
	
	
	
	
	
	
	private void initTab() {
		
		Tab tab_home = new Tab(R.string.home, R.drawable.selector_icon_home, HomeFragment.class);
		Tab tab_category = new Tab(R.string.category, R.drawable.selector_icon_discover, CategoryFragment.class);
		Tab tab_hot = new Tab(R.string.hot, R.drawable.selector_icon_hot, HotFragment.class);
		Tab tab_mine = new Tab(R.string.mine, R.drawable.selector_icon_mine, MineFragment.class);
		mTabs.add(tab_home);
		mTabs.add(tab_category);
		mTabs.add(tab_hot);
		mTabs.add(tab_mine);
		
		mInflater = LayoutInflater.from(this);
		mTabhost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
		mTabhost.setup(this, getSupportFragmentManager(), R.id.realtablecontent);
		
		for(Tab tab : mTabs) {
			TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
			 View view = buildIndicator(tab);
			 tabSpec.setIndicator(view);
			 mTabhost.addTab(tabSpec, tab.getFragment(), null);
			
		}
		//取到底部菜单的分隔线
		mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
		mTabhost.setCurrentTab(0);
	}
	
	private View buildIndicator(Tab tab) {
		View view = mInflater.inflate(R.layout.tab_indicator, null);
		ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
		TextView text = (TextView) view.findViewById(R.id.text_indicator);
		img.setBackgroundResource(tab.getIcon());
		text.setText(tab.getTitle());
		return view;
	}


}
