package com.yang.okhttpmvpdemo.util;

import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yang.okhttpmvpdemo.app.FoodSearchApplication;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 17/4/21.
 */

public class HttpUtil {
    private static final String APPKEY = "49814079";
    private static final String APPSECRET = "90e3438a41d646848033b6b9d461ed54";
    private static RequestQueue queue = Volley.newRequestQueue(FoodSearchApplication.getInstance());

    public static String getUrl(String url,Map<String,String> params ){
        String result="";
        String sign = getSign(APPKEY,APPSECRET,params);
        String query = getQuery(APPKEY,sign,params);
        result = url + "?" + query;
        return result;
    }

    /**
     * ªÒµ√∑˚∫œ¥Û÷⁄µ„∆¿∑˛ŒÒ∆˜“™«Ûµƒ«©√˚
     */
    private static String getSign(String appkey, String appsecret, Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        // ∂‘≤Œ ˝√˚Ω¯––◊÷µ‰≈≈–Ú
        String[] keyArray = params.keySet().toArray(new String[0]);
        Arrays.sort(keyArray);
        // ∆¥Ω””––Úµƒ≤Œ ˝√˚-÷µ¥Æ
        stringBuilder.append(appkey);
        for (String key : keyArray){

            stringBuilder.append(key).append(params.get(key));
        }
        String codes = stringBuilder.append(appsecret).toString();
        //String sign = org.apache.commons.codec.digest.DigestUtils.shaHex(codes).toUpperCase();
        String sign = new String(Hex.encodeHex(DigestUtils.sha(codes))).toUpperCase();
        return sign;
    }

    /**
     * ªÒµ√∑√Œ ≤Œ ˝
     */
    private static String getQuery(String appkey, String sign, Map<String, String> params) {

        try {
            // ÃÌº”«©√˚
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("appkey=").append(appkey).append("&sign=").append(sign);
            for (Map.Entry<String, String> entry : params.entrySet()){

                stringBuilder.append('&').append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(),"utf8"));
            }
            String queryString = stringBuilder.toString();

            return queryString;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("httputil exception");
        }
    }
    public static void getFoods(String city,String region,Response.Listener<String> listener){

        Map<String, String> params = new HashMap<String, String>();
        params.put("city", city);
        params.put("category", "美食");
        if(!TextUtils.isEmpty(region)){
            params.put("region", region);
        }
        String url = "http://api.dianping.com/v1/business/find_businesses";
        StringRequest req = new StringRequest(getUrl(url , params ), listener, null);
        queue.add(req);
    }
}
