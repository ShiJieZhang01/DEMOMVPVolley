package com.yang.okhttpmvpdemo.mvp.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.yang.okhttpmvpdemo.mvp.contact.HomeContact;
import com.yang.okhttpmvpdemo.okhttp.CommonHttpClient;
import com.yang.okhttpmvpdemo.okhttp.listener.DisposeDataHandle;
import com.yang.okhttpmvpdemo.okhttp.listener.DisposeDownloadListener;
import com.yang.okhttpmvpdemo.okhttp.reponse.CommonRequest;
import com.yang.okhttpmvpdemo.util.BusinessBean;
import com.yang.okhttpmvpdemo.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 17/4/21.
 */

public class HomePresenter implements HomeContact.Presenter {

    private HomeContact.MvpView mvpView;
    private static final String TAG ="zsj";
    String url = "https://publicobject.com/helloword.txt";
    public HomePresenter(HomeContact.MvpView mvpView) {
        this.mvpView = mvpView;
        this.mvpView.attachPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void showname() {
        Log.d(TAG, "showname: ");
    }

    @Override
    public void okhttpget() {
        Log.d(TAG, "okhttpget: ");
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call  = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: "+response.body().string());
            }
        });
//        CommonHttpClient.get(CommonRequest.createGetRequest(url,null),new DisposeDataHandle(new DisposeDownloadListener() {
//            @Override
//            public void onProgress(int progrss) {
//            }
//            @Override
//            public void onSuccess(Object responseObj) {
//                Log.d(TAG, "onSuccess: "+responseObj.toString());
//            }
//
//            @Override
//            public void onFailure(Object reasonObj) {
//                Log.d(TAG, "onFailure: "+reasonObj.toString());
//            }
//        }));

    }

    @Override
    public void okhttpPost() {
        OkHttpClient okhttoClient = new OkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();

        formBody.add("mb","1234455");
        formBody.add("pwd","00000");
        Request request = new Request.Builder().url("").post(formBody.build()).build();

        Call call = okhttoClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    @Override
    public void getBussinessfromvolloy(int i) {
        HttpUtil.getFoods("北京", i,null,new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                BusinessBean businessBean = new Gson().fromJson(s,BusinessBean.class);
                List<BusinessBean.Business> businessesList = businessBean.getBusinesses();
                Log.d(TAG, "onResponse: "+ businessesList.get(0).toString());
                mvpView.updateBussiness(businessesList);
            }
        });
    }
}
