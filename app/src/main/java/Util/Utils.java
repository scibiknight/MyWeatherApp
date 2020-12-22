package Util;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {


    public static final String BASE_URL= "https://api.openweathermap.org/data/2.5/weather?q=";
    public static final String ICON_URL = "https://openweathermap.org/img/w/";
    public static final String API_KEY = "952e74f23fa499e5e7f1223ddcf7cdc7";

    public static JSONObject getObject(String tagName,JSONObject jsonObject) throws JSONException{
        JSONObject jsonObject1=jsonObject.getJSONObject(tagName);
        return jsonObject1;
    }
    public static String  getString(String tagName,JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(tagName);
    }
    public static float getFloat(String tagName,JSONObject jsonObject) throws JSONException {
        return(float) jsonObject.getDouble(tagName);
    }
    public static double getDouble(String tagName, JSONObject jsonObject) throws JSONException {
        return(float) jsonObject.getDouble(tagName);
    }

    public static int getInt(String tagName,JSONObject jsonObject) throws JSONException {
        return jsonObject.getInt(tagName);
    }



}
