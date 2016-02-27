package com.example.mycoolweather.util;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.mycoolweather.bean.City;
import com.example.mycoolweather.bean.County;
import com.example.mycoolweather.bean.Province;
import com.example.mycoolweather.db.CoolWeatherDB;

public class Utility {

	
	
	/*
	 * 解析和处理服务器返回的省级数据
	 */

	public synchronized static boolean handleProvincesResponse(
			CoolWeatherDB coolWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}

	/*
	 * 解析和处理服务器返回的市级数据
	 */

	public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,
			String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}

	/*
	 * 解析和处理服务器返回的县级数据
	 */
	public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,
			String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
	

	/*
	 * 解析服务器返回的JSON数据，并将解析出来的数据存储到本地
	 */
	public static void handleWeatherResponse(Context context, String response, String countyCode) {
		SharedPreferences.Editor editor = PreferenceManager
				.getDefaultSharedPreferences(context).edit();
		try {
			JSONObject jsonObject = new JSONObject(response);
			// JSONObject weatherInfo = jsonObject.getJSONObject("data");
			String data = jsonObject.getString("data");
			// Log.d("MainActivity", "desc = " + desc);
			// Log.d("MainActivity", "status = " + status);
			 Log.d("MainActivity", "data = " + data);

			JSONObject jsonObject1 = new JSONObject(data);
			String wendu = jsonObject1.getString("wendu");
			String ganmao = jsonObject1.getString("ganmao");
			String forecast = jsonObject1.getString("forecast");
			String yesterday = jsonObject1.getString("yesterday");
			String city = jsonObject1.getString("city");
//			Log.d("MainActivity", "wendu = " + wendu);
//			Log.d("MainActivity", "ganmao = " + ganmao);
			// Log.d("MainActivity", "forecast = " + forecast);
			// Log.d("MainActivity", "yesterday = " + yesterday);
//			Log.d("MainActivity", "city = " + city);

			JSONObject jsonObject4 = new JSONObject(yesterday);
			String fl = jsonObject4.getString("fl");
			String fx = jsonObject4.getString("fx");
			String high = jsonObject4.getString("high");
			String type = jsonObject4.getString("type");
			String low = jsonObject4.getString("low");
			String date = jsonObject4.getString("date");
//			Log.d("MainActivity", "fl = " + fl);
//			Log.d("MainActivity", "fx = " + fx);
//			Log.d("MainActivity", "high = " + high);
//			Log.d("MainActivity", "type = " + type);
//			Log.d("MainActivity", "low = " + low);
//			Log.d("MainActivity", "date = " + date);
			saveWeatherInfo(context, wendu, ganmao, city, fl, fx, high, type, low, date, countyCode);

//			CoolWeatherDB coolWeatherDB = CoolWeatherDB.getInstance(context);
			// Gson gson = new Gson();
			// SharedPreferences.Editor editor =
			// PreferenceManager.getDefaultSharedPreferences(context).edit();
			// List<Dt> dtList = gson.fromJson(forecast, new
			// TypeToken<List<Dt>>() {}.getType());
			// for(Dt dt : dtList) {
			// Log.d("MainActivity1", "Ifengxiang = " + dt.getIfengxiang());
			// Log.d("MainActivity1", "fengli = " + dt.getFengli());
			// Log.d("MainActivity1", "high = " + dt.getHigh1());
			// Log.d("MainActivity1", "type = " + dt.getType1());
			// Log.d("MainActivity1", "low1 = " + dt.getLow1());
			// Log.d("MainActivity1", "date1 = " + dt.getDate1());
			// coolWeatherDB.saveDt(dt);
			// dt.get(i);
			// editor.putString("Ifengxiang", dt.getIfengxiang());
			// editor.putString("Fengli", dt.getFengli());
			// editor.putString("High1", dt.getHigh1());
			// editor.putString("Type1", dt.getType1());
			// editor.putString("Low1", dt.getLow1());
			// editor.putString("Date1", dt.getDate1());
			// editor.commit();
			// }

			JSONArray jsonArray = new JSONArray(forecast);
			editor = PreferenceManager
					.getDefaultSharedPreferences(context).edit();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject3 = jsonArray.getJSONObject(i);
				String ifengxiang = jsonObject3.getString("fengxiang");
				String fengli = jsonObject3.getString("fengli");
				String high1 = jsonObject3.getString("high");
				String type1 = jsonObject3.getString("type");
				String low1 = jsonObject3.getString("low");
				String date1 = jsonObject3.getString("date");
				editor.putString("ifengxiang" + i, ifengxiang);
				editor.putString("fengli" + i, fengli);
				editor.putString("high1" + i, high1);
				editor.putString("type1" + i, type1);
				editor.putString("low1" + i, low1);
				editor.putString("date1" + i, date1);
//				dt.setIfengxiang(ifengxiang);
//				dt.setFengli(fengli);
//				dt.setHigh1(high1);
//				dt.setType1(type1);
//				dt.setLow1(low1);
//				dt.setDate1(date1);
//				dt.setCountyId(countyId);
//				coolWeatherDB.saveDt(dt);
//				Log.d("MainActivity", "ifengxiang is " + dt.getIfengxiang());
//				Log.d("MainActivity", "fengli is " + dt.getFengli());
//				Log.d("MainActivity", "high is " + dt.getHigh1());
//				Log.d("MainActivity", "type is " + dt.getType1());
//				Log.d("MainActivity", "low is " + dt.getLow1());
//				Log.d("MainActivity", "date is " + dt.getDate1());
			}
//			editor.putBoolean("city_selected", true);
//			editor.putString("wendu", wendu);
//			editor.putString("ganmao", ganmao);
//			editor.putString("city", city);
//			editor.putString("fl", fl);
//			editor.putString("fx", fx);
//			editor.putString("high", high);
//			editor.putString("type", type);
//			editor.putString("low", low);
//			editor.putString("date", date);
			editor.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveWeatherInfo(Context context, String wendu,
			String ganmao, String city, String fl, String fx, String high,
			String type, String low, String date, String countyCode) {
		SharedPreferences.Editor editor = PreferenceManager
				.getDefaultSharedPreferences(context).edit();
		editor.putBoolean("city_selected", true);
		editor.putString("countyCode", countyCode);
		editor.putString("wendu", wendu);
		editor.putString("ganmao", ganmao);
		editor.putString("city", city);
		editor.putString("fl", fl);
		editor.putString("fx", fx);
		editor.putString("high", high);
		editor.putString("type", type);
		editor.putString("low", low);
		editor.putString("date", date);
		editor.commit();
	}

}
