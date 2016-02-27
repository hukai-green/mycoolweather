package com.example.mycoolweather.widget;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycoolweather.R;
import com.example.mycoolweather.bean.County;
import com.example.mycoolweather.bean.Dt;

public class OtherWeatherActivity extends Activity{
	
	private List<Dt> dtList = new ArrayList<Dt>();
	private TextView titleText;
//	private County selectedCounty = (County) getIntent().getSerializableExtra("selected_county");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.other_weather_layout);
		titleText = (TextView) findViewById(R.id.title_text);
		initDts();
		ListView listView = (ListView) findViewById(R.id.list_view);
		WeatherAdapter adapter = new WeatherAdapter(OtherWeatherActivity.this, R.layout.weather_item, dtList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Dt dt = dtList.get(position);
				Toast.makeText(OtherWeatherActivity.this, dt.getType1(), Toast.LENGTH_SHORT).show();
			}
			
		});
	}
	
	private void initDts() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		titleText.setText(prefs.getString("city", ""));
		
		Dt yesterday = new Dt();
		yesterday.setDate1(prefs.getString("date", ""));
		yesterday.setFengli(prefs.getString("fl", ""));
		yesterday.setIfengxiang(prefs.getString("fx", ""));
		yesterday.setLow1(prefs.getString("low", ""));
		yesterday.setHigh1(prefs.getString("high", ""));
		yesterday.setType1(prefs.getString("type", ""));
		
		Dt today = new Dt();
		today.setDate1(prefs.getString("date10", ""));
		today.setFengli(prefs.getString("fengli0", ""));
		today.setIfengxiang(prefs.getString("ifengxiang0", ""));
		today.setLow1(prefs.getString("low10", ""));
		today.setHigh1(prefs.getString("high10", ""));
		today.setType1(prefs.getString("type10", ""));
		
		Dt tomorrow = new Dt();
		tomorrow.setDate1(prefs.getString("date11", ""));
		tomorrow.setFengli(prefs.getString("fengli1", ""));
		tomorrow.setIfengxiang(prefs.getString("ifengxiang1", ""));
		tomorrow.setLow1(prefs.getString("low11", ""));
		tomorrow.setHigh1(prefs.getString("high11", ""));
		tomorrow.setType1(prefs.getString("type11", ""));
		
		Dt houtian = new Dt();
		houtian.setDate1(prefs.getString("date12", ""));
		houtian.setFengli(prefs.getString("fengli2", ""));
		houtian.setIfengxiang(prefs.getString("ifengxiang2", ""));
		houtian.setLow1(prefs.getString("low12", ""));
		houtian.setHigh1(prefs.getString("high12", ""));
		houtian.setType1(prefs.getString("type12", ""));
		
		Dt dahoutian = new Dt();
		dahoutian.setDate1(prefs.getString("date13", ""));
		dahoutian.setFengli(prefs.getString("fengli3", ""));
		dahoutian.setIfengxiang(prefs.getString("ifengxiang3", ""));
		dahoutian.setLow1(prefs.getString("low13", ""));
		dahoutian.setHigh1(prefs.getString("high13", ""));
		dahoutian.setType1(prefs.getString("type13", ""));
		
		Dt wanhoutian = new Dt();
		wanhoutian.setDate1(prefs.getString("date14", ""));
		wanhoutian.setFengli(prefs.getString("fengli4", ""));
		wanhoutian.setIfengxiang(prefs.getString("ifengxiang4", ""));
		wanhoutian.setLow1(prefs.getString("low14", ""));
		wanhoutian.setHigh1(prefs.getString("high14", ""));
		wanhoutian.setType1(prefs.getString("type14", ""));
		
		dtList.add(yesterday);
		dtList.add(today);
		dtList.add(tomorrow);
		dtList.add(houtian);
		dtList.add(dahoutian);
		dtList.add(wanhoutian);
	}
	
	public class WeatherAdapter extends ArrayAdapter<Dt>{
		
		private  int resourceId;

		public WeatherAdapter(Context context, int resource, List<Dt> objects) {
			super(context, resource, objects);
			// TODO Auto-generated constructor stub
			resourceId = resource;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Dt dt = getItem(position);
			View view;
			ViewHolder viewHolder;
			if(convertView == null) {
				view = LayoutInflater.from(getContext()).inflate(resourceId, null);
				viewHolder = new ViewHolder();
				viewHolder.otherWeather = (ImageView) view.findViewById(R.id.other_tianqi);
				viewHolder.otherWendu = (TextView) view.findViewById(R.id.other_wendu);
				viewHolder.otherDate = (TextView) view.findViewById(R.id.other_date);
				viewHolder.otherFengli = (TextView) view.findViewById(R.id.other_fengli);
				viewHolder.otherType = (TextView) view.findViewById(R.id.other_type);
				view.setTag(viewHolder);
			} else {
				view = convertView;
				viewHolder = (ViewHolder) view.getTag();
			}
			
			if("«Á".equals(dt.getType1())){
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_qing);
			}else if("∂‡‘∆".equals(dt.getType1())) {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_duoyun);
			}else if("“ı".equals(dt.getType1())) {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_yin);
			}else if("–°—©".equals(dt.getType1())) {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_xiaoxue);
			}else if("’Û—©".equals(dt.getType1())) {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_zhenxue);
			}else if("–°”Í".equals(dt.getType1())) {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_yu);
			}else if("”Í".equals(dt.getType1())) {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_yu);
			}else if("’Û”Í".equals(dt.getType1())) {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_zhenyu);
			}else if("¥Û—©".equals(dt.getType1())) {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_zhenxue);
			}else if("¿◊’Û”Í".equals(dt.getType1())) {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_leizhenyu);
			}else if("”Íº–—©".equals(dt.getType1())) {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_yujiaxue);
			}else if("÷–—©".equals(dt.getType1())) {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_zhongxue);
			}else if("÷–”Í".equals(dt.getType1())) {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_zhongyu);
			}else {
				viewHolder.otherWeather.setBackgroundResource(R.drawable.weathy_qita);
			}
			viewHolder.otherType.setText(dt.getType1());
			viewHolder.otherWendu.setText(dt.getLow1() + "~" + dt.getHigh1());
			viewHolder.otherDate.setText(dt.getDate1());
			viewHolder.otherFengli.setText(dt.getIfengxiang() + "£¨" + dt.getFengli());
			return view;
		}

	}
	
	class ViewHolder {
		ImageView otherWeather;
		TextView otherWendu;
		TextView otherDate;
		TextView otherFengli;
		TextView otherType;
	}
	

}
