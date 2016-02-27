package com.example.mycoolweather.widget;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.mycoolweather.R;
import com.example.mycoolweather.bean.County;
import com.example.mycoolweather.db.CoolWeatherDB;
import com.example.mycoolweather.util.HttpCallbackListener;
import com.example.mycoolweather.util.HttpUtil;
import com.example.mycoolweather.util.Utility;

public class WeatherActivity extends Activity implements OnClickListener {

	private Button leftButton;
	private Button rightButton;
	private Button otherButton;
	private ImageView leftPicture;
	private ImageView rightPicture;
	private ImageView currentPicture1;
	private ImageView currentPicture2;
	private TextView wendu;
	private TextView ganmao;
	private TextView city;
	private TextView fengli;
	private TextView highlow1;
	private TextView highlow2;
	private TextView type;
	private TextView date;
	private TextView leftDay;
	private TextView rightDay;
	private County selectedcounty;
	private TextView tongbu;
	private RelativeLayout layoutMain;
	private CoolWeatherDB coolWeatherDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weather_layout);
		
		
		String countyCode = getIntent().getStringExtra("county_code");
		selectedcounty = (County) getIntent().getSerializableExtra(
				"selected_county");
		coolWeatherDB = CoolWeatherDB.getInstance(this);
		
		
		rightButton = (Button) findViewById(R.id.right_btn);
		leftButton = (Button) findViewById(R.id.left_btn);
		otherButton = (Button) findViewById(R.id.other);
		city = (TextView) findViewById(R.id.title_text);
		leftPicture = (ImageView) findViewById(R.id.left_picture);
		rightPicture = (ImageView) findViewById(R.id.right_picture);
		currentPicture1 = (ImageView) findViewById(R.id.current_picture1);
		currentPicture2 = (ImageView) findViewById(R.id.current_picture2);
		wendu = (TextView) findViewById(R.id.current_wendu);
		ganmao = (TextView) findViewById(R.id.current_tip);
		fengli = (TextView) findViewById(R.id.current_fengli);
		highlow1 = (TextView) findViewById(R.id.today_wendu);
		highlow2 = (TextView) findViewById(R.id.tomorrow_wendu);
		type = (TextView) findViewById(R.id.current_state);
		date = (TextView) findViewById(R.id.current_date);
		tongbu = (TextView) findViewById(R.id.tongbu);
		leftDay = (TextView) findViewById(R.id.left_day);
		rightDay = (TextView) findViewById(R.id.right_day);
		layoutMain = (RelativeLayout) findViewById(R.id.layoutmain);
		
		leftButton.setOnClickListener(this);
		rightButton.setOnClickListener(this);
		otherButton.setOnClickListener(this);

		if (!TextUtils.isEmpty(countyCode)) {
			tongbu.setText("Í¬²½ÖÐ...");
			leftPicture.setVisibility(View.INVISIBLE);
			rightPicture.setVisibility(View.INVISIBLE);
			currentPicture1.setVisibility(View.INVISIBLE);
			currentPicture2.setVisibility(View.INVISIBLE);
			leftDay.setVisibility(View.INVISIBLE);
			rightDay.setVisibility(View.INVISIBLE);
			queryWeatherCode(countyCode);
		} else {
			showWeather();
		}

	}

	private void queryWeatherCode(String countyCode) {
		String address = "http://www.weather.com.cn/data/list3/city"
				+ countyCode + ".xml";
		queryFromServer(address, "countyCode", countyCode);
	}

	private void queryWeatherInfo(String weatherCode, String countyCode) {
		String address = "http://wthrcdn.etouch.cn/weather_mini?citykey="
				+ weatherCode;
		queryFromServer(address, "weatherCode", countyCode);
	}

	private void queryFromServer(final String address, final String type,final String countyCode) {
		HttpUtil.sendHttpResponse(address, new HttpCallbackListener() {
			@Override
			public void onFinish(String response) {
				// TODO Auto-generated method stub
				if ("countyCode".equals(type)) {
					if (!TextUtils.isEmpty(response)) {
						String[] array = response.split("\\|");
						if (array != null && array.length == 2) {
							String weatherCode = array[1];
							queryWeatherInfo(weatherCode, countyCode);
						}
					}
				} else if ("weatherCode".equals(type)) {
					Utility.handleWeatherResponse(WeatherActivity.this,
							response, countyCode);
					Log.d("WeatherActivity", "1");
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Log.d("WeatherActivity", "2");
							showWeather();
							Log.d("WeatherActivity", "3");
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
						Log.d("WeatherActivity", "Í¬²½Ê§°Ü...");
					}

				});
			}

		});
	}

	public void showWeather() {
		SharedPreferences prefs =
		PreferenceManager.getDefaultSharedPreferences(this);
//		List<Dt> dtlist = new ArrayList<Dt>();
//		coolWeatherDB.loadDts(countyId);
//		Dt dts = new Dt();
//		List<String> list = new ArrayList<String>();
//		dtlist = coolWeatherDB.loadDts(countyId);
		city.setText(prefs.getString("city", ""));
		wendu.setText(prefs.getString("wendu", "") + "¡ã");
		ganmao.setText(prefs.getString("ganmao", ""));
		type.setText(prefs.getString("type10", ""));
		fengli.setText(prefs.getString("fengli0", ""));
		date.setText(prefs.getString("date10", ""));
		highlow1.setText(prefs.getString("low10", "") + "~" + prefs.getString("high10", ""));
		highlow2.setText(prefs.getString("low11", "") + "~" + prefs.getString("high11", ""));
		tongbu.setVisibility(View.INVISIBLE);
		leftPicture.setVisibility(View.VISIBLE);
		rightPicture.setVisibility(View.VISIBLE);
		currentPicture1.setVisibility(View.VISIBLE);
		currentPicture2.setVisibility(View.VISIBLE);
		leftDay.setVisibility(View.VISIBLE);
		rightDay.setVisibility(View.VISIBLE);
		leftDay.setVisibility(View.VISIBLE);
		rightDay.setVisibility(View.VISIBLE);
		city.setVisibility(View.VISIBLE);
		wendu.setVisibility(View.VISIBLE);
		ganmao.setVisibility(View.VISIBLE);
		type.setVisibility(View.VISIBLE);
		fengli.setVisibility(View.VISIBLE);
		date.setVisibility(View.VISIBLE);
		highlow1.setVisibility(View.VISIBLE);
		highlow2.setVisibility(View.VISIBLE);
		
		
		if("Çç".equals(prefs.getString("type10", ""))){
			leftPicture.setBackgroundResource(R.drawable.weathy_qing);
		}else if("¶àÔÆ".equals(prefs.getString("type10", ""))) {
			leftPicture.setBackgroundResource(R.drawable.weathy_duoyun);
		}else if("Òõ".equals(prefs.getString("type10", ""))) {
			leftPicture.setBackgroundResource(R.drawable.weathy_yin);
		}else if("Ð¡Ñ©".equals(prefs.getString("type10", ""))) {
			leftPicture.setBackgroundResource(R.drawable.weathy_xiaoxue);
		}else if("ÕóÑ©".equals(prefs.getString("type10", ""))) {
			leftPicture.setBackgroundResource(R.drawable.weathy_zhenxue);
		}else if("Ð¡Óê".equals(prefs.getString("type10", ""))) {
			leftPicture.setBackgroundResource(R.drawable.weathy_yu);
		}else if("Óê".equals(prefs.getString("type10", ""))) {
			leftPicture.setBackgroundResource(R.drawable.weathy_yu);
		}else if("ÕóÓê".equals(prefs.getString("type10", ""))) {
			leftPicture.setBackgroundResource(R.drawable.weathy_zhenyu);
		}else if("´óÑ©".equals(prefs.getString("type10", ""))) {
			leftPicture.setBackgroundResource(R.drawable.weathy_zhenxue);
		}else if("À×ÕóÓê".equals(prefs.getString("type10", ""))) {
			leftPicture.setBackgroundResource(R.drawable.weathy_leizhenyu);
		}else if("Óê¼ÐÑ©".equals(prefs.getString("type10", ""))){
			leftPicture.setBackgroundResource(R.drawable.weathy_yujiaxue);
		}else if("ÖÐÑ©".equals(prefs.getString("type10", ""))) {
			leftPicture.setBackgroundResource(R.drawable.weathy_zhongxue);
		}else if("ÖÐÓê".equals(prefs.getString("type10", ""))){
			leftPicture.setBackgroundResource(R.drawable.weathy_zhongyu);
		}else {
			leftPicture.setBackgroundResource(R.drawable.weathy_qita);
		}
		
		if("Çç".equals(prefs.getString("type11", ""))){
			rightPicture.setBackgroundResource(R.drawable.weathy_qing);
		}else if("¶àÔÆ".equals(prefs.getString("type11", ""))) {
			rightPicture.setBackgroundResource(R.drawable.weathy_duoyun);
		}else if("Òõ".equals(prefs.getString("type11", ""))) {
			rightPicture.setBackgroundResource(R.drawable.weathy_yin);
		}else if("Ð¡Ñ©".equals(prefs.getString("type11", ""))) {
			rightPicture.setBackgroundResource(R.drawable.weathy_xiaoxue);
		}else if("ÕóÑ©".equals(prefs.getString("type11", ""))) {
			rightPicture.setBackgroundResource(R.drawable.weathy_zhenxue);
		}else if("Ð¡Óê".equals(prefs.getString("type11", ""))) {
			rightPicture.setBackgroundResource(R.drawable.weathy_yu);
		}else if("Óê".equals(prefs.getString("type11", ""))) {
			rightPicture.setBackgroundResource(R.drawable.weathy_yu);
		}else if("ÕóÓê".equals(prefs.getString("type11", ""))) {
			rightPicture.setBackgroundResource(R.drawable.weathy_zhenyu);
		}else if("´óÑ©".equals(prefs.getString("type11", ""))) {
			rightPicture.setBackgroundResource(R.drawable.weathy_zhenxue);
		}else if("À×ÕóÓê".equals(prefs.getString("type11", ""))) {
			rightPicture.setBackgroundResource(R.drawable.weathy_leizhenyu);
		}else if("Óê¼ÐÑ©".equals(prefs.getString("type11", ""))){
			rightPicture.setBackgroundResource(R.drawable.weathy_yujiaxue);
		}else if("ÖÐÓê".equals(prefs.getString("type11", ""))){
			rightPicture.setBackgroundResource(R.drawable.weathy_zhongyu);
		}else if("ÖÐÑ©".equals(prefs.getString("type11", ""))) {
			rightPicture.setBackgroundResource(R.drawable.weathy_zhongxue);
		}else {
			rightPicture.setBackgroundResource(R.drawable.weathy_qita);
		}
		
		
		if("Çç".equals(prefs.getString("type10", ""))){
			layoutMain.setBackgroundResource(R.drawable.qing2);
		}else if("¶àÔÆ".equals(prefs.getString("type10", ""))) {
			layoutMain.setBackgroundResource(R.drawable.duoyun2);
		}else if("Òõ".equals(prefs.getString("type10", ""))) {
			layoutMain.setBackgroundResource(R.drawable.yin2);
		}else if("Ð¡Ñ©".equals(prefs.getString("type10", ""))) {
			layoutMain.setBackgroundResource(R.drawable.xiaoxue1);
		}else if("ÕóÑ©".equals(prefs.getString("type10", ""))) {
			layoutMain.setBackgroundResource(R.drawable.xue2);
		}else if("Ð¡Óê".equals(prefs.getString("type10", ""))) {
			layoutMain.setBackgroundResource(R.drawable.yu3);
		}else if("Óê".equals(prefs.getString("type10", ""))) {
			layoutMain.setBackgroundResource(R.drawable.xiaoxue1);
		}else if("ÕóÓê".equals(prefs.getString("type10", ""))) {
			layoutMain.setBackgroundResource(R.drawable.yu5);
		}else if("´óÑ©".equals(prefs.getString("type10", ""))) {
			layoutMain.setBackgroundResource(R.drawable.xue1);
		}else if("À×ÕóÓê".equals(prefs.getString("type10", ""))) {
			layoutMain.setBackgroundResource(R.drawable.leizhenyu1);
		}else if("Óê¼ÐÑ©".equals(prefs.getString("type10", ""))){
			layoutMain.setBackgroundResource(R.drawable.xue1);
		}else if("ÖÐÓê".equals(prefs.getString("type10", ""))){
			layoutMain.setBackgroundResource(R.drawable.xiaoyu1);
		}else if("ÖÐÑ©".equals(prefs.getString("type10", ""))){
			layoutMain.setBackgroundResource(R.drawable.xue2);
		}else {
			layoutMain.setBackgroundResource(R.drawable.qing3);
		}
		
//		Intent intent = new Intent(this, AutoUpdateService.class);
//		startService(intent);
		
		
		
//		layoutMain.setBackgroundResource(R.drawable.screen);
		
//		prefs.getString("type11", "");
//		if (dtlist.size() > 0) {
//			
//			Log.d("WeatherActivity", dtlist.get(0).getIfengxiang());
//			Log.d("WeatherActivity", dtlist.get(0).getFengli());
//			Log.d("WeatherActivity", dtlist.get(0).getHigh1());
//			Log.d("WeatherActivity", dtlist.get(0).getType1());
//			Log.d("WeatherActivity", dtlist.get(0).getLow1());
//			Log.d("WeatherActivity", dtlist.get(0).getDate1());
//			Log.d("WeatherActivity", dtlist.get(0).getLow1());
//		}
	}

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.left_btn:
			Intent intent = new Intent(this, ChooseAreaActivity.class);
			intent.putExtra("from_weather_activity", true);
			startActivity(intent);
			finish();
			break;
		case R.id.right_btn:
			tongbu.setVisibility(View.VISIBLE);
			tongbu.setText("Í¬²½ÖÐ...");
			leftPicture.setVisibility(View.INVISIBLE);
			rightPicture.setVisibility(View.INVISIBLE);
			currentPicture1.setVisibility(View.INVISIBLE);
			currentPicture2.setVisibility(View.INVISIBLE);
			leftDay.setVisibility(View.INVISIBLE);
			rightDay.setVisibility(View.INVISIBLE);
			city.setVisibility(View.INVISIBLE);
			wendu.setVisibility(View.INVISIBLE);
			ganmao.setVisibility(View.INVISIBLE);
			type.setVisibility(View.INVISIBLE);
			fengli.setVisibility(View.INVISIBLE);
			date.setVisibility(View.INVISIBLE);
			highlow1.setVisibility(View.INVISIBLE);
			highlow2.setVisibility(View.INVISIBLE);
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
			String countyCode = prefs.getString("countyCode", "");
			if(!TextUtils.isEmpty(countyCode)) {
				queryWeatherCode(countyCode);
			}
			break;
		case R.id.other:
			Intent intent1 = new Intent(WeatherActivity.this, OtherWeatherActivity.class);
//			intent1.putExtra("selected_county", selectedcounty);
			startActivity(intent1);
		default:
			break;
		}
	}
	
}
