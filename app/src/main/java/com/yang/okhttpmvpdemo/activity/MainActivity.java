package com.yang.okhttpmvpdemo.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.yang.okhttpmvpdemo.R;
import com.yang.okhttpmvpdemo.adapter.BussinessAdapter;
import com.yang.okhttpmvpdemo.databinding.ActivityMainBinding;
import com.yang.okhttpmvpdemo.mvp.contact.HomeContact;
import com.yang.okhttpmvpdemo.mvp.presenter.HomePresenter;
import com.yang.okhttpmvpdemo.util.BusinessBean;
import com.yang.okhttpmvpdemo.util.WrapContentLinearLayoutManager;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements HomeContact.MvpView{

    ActivityMainBinding mBinding;
    private HomeContact.Presenter presenter;
    private BussinessAdapter bussinessAdapter;
    private RecyclerView recyclerView;
    RecyclerView.OnScrollListener loadingMoreListener;
    LinearLayoutManager mLinearLayoutManager;
    int currentIndex;
    boolean loading;
    private static final String TAG ="zsj";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        presenter = new HomePresenter(this);
        bussinessAdapter = new BussinessAdapter(this);
        initUI();
//        initListener();
    }
    public void initUI(){
        mLinearLayoutManager = new WrapContentLinearLayoutManager(getContext());
        mBinding.recycleTopnews.setLayoutManager(mLinearLayoutManager);
        mBinding.recycleTopnews.setHasFixedSize(true);
        mBinding.recycleTopnews.setItemAnimator(new DefaultItemAnimator());
        mBinding.recycleTopnews.setAdapter(bussinessAdapter);
        //滑动监听
//        mBinding.recycleTopnews.addOnScrollListener(loadingMoreListener);
        loadDate();
    }

    private void loadDate() {
        if (bussinessAdapter.getItemCount() > 0) {
            bussinessAdapter.clearData();
        }
        currentIndex = 0;
        presenter.getBussinessfromvolloy();
    }

    @Override
    public void attachPresenter(HomeContact.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateBussiness(List<BusinessBean.Business> businesseslist) {
        Log.d(TAG, "updateBussiness: "+businesseslist.size());
        bussinessAdapter.addItems(businesseslist);
        bussinessAdapter.loadingStart();
    }
}
