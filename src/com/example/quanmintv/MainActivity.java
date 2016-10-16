package com.example.quanmintv;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.entity.StringEntity;

public class MainActivity extends Activity implements OnClickListener {

	private ViewPager mViewPager;
	private PagerAdapter mAdapter;
	private List<View> mViews = new ArrayList<View>();
	//TAB
	private LinearLayout tuijiantab;
	private LinearLayout lanmutab;
	private LinearLayout zhibotab;
	private LinearLayout wodetab;
	
	private ImageButton tuijianimg;
	private ImageButton lanmuimg;
	private ImageButton zhiboimg;
	private ImageButton wodeimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //初始化界面
        initView();
        
        initEvent();

		//发送请求
		requestHomeAdvList();
    }
	private void initEvent() {
	  tuijiantab.setOnClickListener(this);
	  lanmutab.setOnClickListener(this);
	  zhibotab.setOnClickListener(this);
	  wodetab.setOnClickListener(this);
	  
	  mViewPager.setOnPageChangeListener(new OnPageChangeListener(){

		@Override
		public void onPageScrollStateChanged(int arg0) {
			int currentItem = mViewPager.getCurrentItem();
			resetImg();
			switch(currentItem){
			case 0:
				tuijianimg.setImageResource(R.drawable.recommened_press);
				break;
            case 1:
            	lanmuimg.setImageResource(R.drawable.column_press);
				break;
            case 2:
            	zhiboimg.setImageResource(R.drawable.living_press);
	          break;
            case 3:
            	wodeimg.setImageResource(R.drawable.mine_press);
	        break;
			}
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
		@Override
		public void onPageSelected(int arg0) {
		}});
	}
	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		
		tuijiantab = (LinearLayout) findViewById(R.id.id_tuijian);
		lanmutab = (LinearLayout) findViewById(R.id.id_lanmu);
		zhibotab = (LinearLayout) findViewById(R.id.id_zhibo);
		wodetab = (LinearLayout) findViewById(R.id.id_wode);
		
		tuijianimg = (ImageButton) findViewById(R.id.id_tuijian_img);
		lanmuimg = (ImageButton) findViewById(R.id.id_lanmu_img);
		zhiboimg = (ImageButton) findViewById(R.id.id_zhibo_img);
		wodeimg = (ImageButton) findViewById(R.id.id_wode_img);
		
	    LayoutInflater mInflater = LayoutInflater.from(this);
	    View tab_1 = mInflater.inflate(R.layout.tab_1, null);
	    View tab_2 = mInflater.inflate(R.layout.tab_2, null);
	    View tab_3 = mInflater.inflate(R.layout.tab_3, null);
	    View tab_4 = mInflater.inflate(R.layout.tab_4, null);
	    mViews.add(tab_1);
	    mViews.add(tab_2);
	    mViews.add(tab_3);
	    mViews.add(tab_4);
	    
	    mViewPager.setAdapter(new PagerAdapter(){

			@Override
			public int getCount() {
				return mViews.size();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(mViews.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = mViews.get(position);
				container.addView(view);
				return view;
			}
	    	
	    	
	    });
	
	}
	@Override
	public void onClick(View v) {
		resetImg();
		switch(v.getId()){
		case R.id.id_tuijian:
			tuijianimg.setImageResource(R.drawable.recommened_press);
			mViewPager.setCurrentItem(0);
			break;
		case R.id.id_lanmu:
			mViewPager.setCurrentItem(1);
			lanmuimg.setImageResource(R.drawable.column_press);
			break;
		case R.id.id_zhibo:
			mViewPager.setCurrentItem(2);
			zhiboimg.setImageResource(R.drawable.living_press);
			break;
		case R.id.id_wode:
			mViewPager.setCurrentItem(3);
			wodeimg.setImageResource(R.drawable.mine_press);
			break;
			default:
			break;
		
		}
	}
	//将所有的图片切换为灰色
	private void resetImg() {
		tuijianimg.setImageResource(R.drawable.recommended);
		lanmuimg.setImageResource(R.drawable.column);
		zhiboimg.setImageResource(R.drawable.living);
		wodeimg.setImageResource(R.drawable.mine);
		
	}


	private void  requestHomeAdvList()
	{
		HttpUtils httpUtils=new HttpUtils();

		httpUtils.send(HttpRequest.HttpMethod.GET, "http://www.quanmin.tv/json/page/app-data/info.json", new RequestCallBack<String>() {
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Log.d("requestHomeAdvList",""+responseInfo);
			}

			public void onFailure(HttpException e, String s) {
				Log.d("requestHomeAdvList",""+s);
			}
		});

	}

		
	}

    
    

