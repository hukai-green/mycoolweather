package com.example.mycoolweather.fragment;


import com.example.mycoolweather.R;
import com.example.mycoolweather.bean.County;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment{
	
	private Button leftButton;
	private ImageView leftPicture;
	private TextView wendu;
	private TextView ganmao;
	private TextView city;
	private TextView fengli;
	private TextView highlow1;
	private TextView highlow2;
	private TextView type;
	private TextView date;
	private View view;
	
	private County selectedcounty;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_home, container, false);
		selectedcounty = (County) getActivity().getIntent().getSerializableExtra(
				"selected_county");
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		leftButton = (Button) view.findViewById(R.id.left_btn);
		city = (TextView) view.findViewById(R.id.title_text);
		leftPicture = (ImageView) view.findViewById(R.id.left_picture);
		wendu = (TextView) view.findViewById(R.id.current_wendu);
		ganmao = (TextView) view.findViewById(R.id.current_tip);
		fengli = (TextView) view.findViewById(R.id.current_fengli);
		highlow1 = (TextView) view.findViewById(R.id.today_wendu);
		highlow2 = (TextView) view.findViewById(R.id.tomorrow_wendu);
		type = (TextView) view.findViewById(R.id.current_state);
		date = (TextView) view.findViewById(R.id.current_date);
		
		city.setText(prefs.getString("city", ""));
		wendu.setText(prefs.getString("wendu", "") + "бу");
		ganmao.setText(prefs.getString("ganmao", ""));
//		type.setText(prefs.getString("type10", ""));
//		fengli.setText(prefs.getString("fengli0", ""));
//		date.setText(prefs.getString("date10", ""));
//		highlow1.setText(prefs.getString("low10", "") + "~" + prefs.getString("high10", ""));
//		highlow2.setText(prefs.getString("low11", "") + "~" + prefs.getString("high11", ""));
//		showWeather(selectedcounty.getId());
		
		return view;
	}
//	public void showWeather(int countyId) {
////		coolWeatherDB = CoolWeatherDB.getInstance(getActivity());
//		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
////		List<Dt> dtlist = new ArrayList<Dt>();
////		coolWeatherDB.loadDts(countyId);
////		dtlist = coolWeatherDB.loadDts(countyId);
//		
//		city.setText(prefs.getString("city", ""));
//		wendu.setText(prefs.getString("wendu", "") + "бу");
//		ganmao.setText(prefs.getString("ganmao", ""));
//		type.setText(prefs.getString("type10", ""));
//		fengli.setText(prefs.getString("fengli0", ""));
//		date.setText(prefs.getString("date10", ""));
//		highlow1.setText(prefs.getString("low10", "") + "~" + prefs.getString("high10", ""));
//		highlow2.setText(prefs.getString("low11", "") + "~" + prefs.getString("high11", ""));
		
//		Log.d("WeatherActivity", prefs.getString("city", ""));
//		Log.d("WeatherActivity", prefs.getString("wendu", ""));
//		Log.d("WeatherActivity", prefs.getString("ganmao", ""));
//		Log.d("WeatherActivity", dtlist.toString());
//		Log.d("WeatherActivity", dtlist.get(0).getFengli());
//		Log.d("WeatherActivity", dtlist.get(0).getHigh1());
//		Log.d("WeatherActivity", dtlist.get(0).getType1());
//		Log.d("WeatherActivity", dtlist.get(0).getLow1());
//		Log.d("WeatherActivity", dtlist.get(0).getDate1());
//		Log.d("WeatherActivity", dtlist.get(1).getLow1());
//		Log.d("WeatherActivity", dtlist.get(1).getHigh1());
//		Log.d("WeatherActivity", dtlist.get(0).getLow1() + "~" + dtlist.get(0).getHigh1());
//		Log.d("WeatherActivity", dtlist.get(1).getLow1() + "~" + dtlist.get(1).getHigh1());
	
	
	
	
	
	
//	}
	
	
	

}
