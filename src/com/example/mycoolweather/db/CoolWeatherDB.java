package com.example.mycoolweather.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mycoolweather.bean.City;
import com.example.mycoolweather.bean.County;
import com.example.mycoolweather.bean.Dt;
import com.example.mycoolweather.bean.Province;

public class CoolWeatherDB {
	
	//数据库名
	public static final String DB_NAME = "cool_weather";
	//数据库版本
	private static final int VERSION = 1;
	
	private static CoolWeatherDB coolWeather;
	
	private SQLiteDatabase db;
	
	//构造方法私有化，单例设计模式
	private CoolWeatherDB(Context context) {
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}
	
	//获取CoolWeatherDB的实例
	public synchronized static CoolWeatherDB getInstance(Context context) {
		if(coolWeather == null) {
			coolWeather = new CoolWeatherDB(context);
		}
		return coolWeather;
	}
	
	public void saveProvince(Province province) {
		if(province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	//从数据库中读取所有的province信息
	public List<Province> loadProvinces() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()) {
			do{
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
			} while (cursor.moveToNext());
		}
		if(cursor != null) {
			cursor.close();
		}
		return list;
	}
	
	//将City实例存储到数据库
	public void saveCity(City city) {
		if(city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);
		}
	}
	//将County实例存储到数据库
	public List<City> loadCities(int provinceId) {
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, "province_id = ?", new String[] {String.valueOf(provinceId)}, null, null, null);
		if(cursor.moveToFirst()) {
			do{
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvinceId(provinceId);
				list.add(city);
			}while (cursor.moveToNext());
		}
		if(cursor != null) {
			cursor.close();
		}
		return list;
	}
	
	//将County实例存储到数据库
	public void saveCounty(County county) {
		if(county != null) {
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);
		}
	}
	public void saveDt(Dt dt) {
		if(dt != null) {
			ContentValues values = new ContentValues();
			values.put("ifengxiang", dt.getIfengxiang());
			values.put("fengli", dt.getFengli());
			values.put("high1", dt.getHigh1());
			values.put("type1", dt.getType1());
			values.put("low1", dt.getLow1());
			values.put("date1", dt.getDate1());
			values.put("county_id", dt.getCountyId());
			db.insert("Dt", null, values);
		}
	}
	
	//从数据库中读取某城市下所有县的信息
	public List<County> loadCounties(int cityId) {
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query("County", null, "city_id = ?", new String[] {String.valueOf(cityId)}, null, null, null);
		if(cursor.moveToFirst()) {
			do{
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCityId(cityId);
				list.add(county);
			}while(cursor.moveToNext());
		}
		if(cursor != null) {
			cursor.close();
		}
		return list;
	}
	
	public List<Dt> loadDts(int countyId) {
		List<Dt> list = new ArrayList<Dt>();
		Cursor cursor = db.query("Dt", null, "county_id = ?", new String[] {String.valueOf(countyId)}, null, null, null);
		if(cursor.moveToFirst()) {
			do{
				Dt dt = new Dt();
				dt.setId(cursor.getInt(cursor.getColumnIndex("id")));
				dt.setIfengxiang(cursor.getString(cursor.getColumnIndex("ifengxiang")));
				dt.setFengli(cursor.getString(cursor.getColumnIndex("fengli")));
				dt.setHigh1(cursor.getString(cursor.getColumnIndex("high1")));
				dt.setType1(cursor.getString(cursor.getColumnIndex("type1")));
				dt.setLow1(cursor.getString(cursor.getColumnIndex("low1")));
				dt.setDate1(cursor.getString(cursor.getColumnIndex("date1")));
				dt.setCountyId(countyId);
				list.add(dt);
			}while(cursor.moveToNext());
		}
		if(cursor != null) {
			cursor.close();
		}
		return list;
	}

}
