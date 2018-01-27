package com.bysj.auction;

import com.bysj.function.Function;
import com.bysj.login.LoginApp;

import android.view.KeyEvent;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity 
{

	private int item = 0;
	private long exitTime = 0;
	private MainImageAdapter imageApter;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// 取得GridView对象
		GridView gridview = (GridView) findViewById(R.id.gridview);

		// 初始化图片容器对象
		imageApter = new MainImageAdapter(this);

		// 添加元素给gridview控件
		gridview.setAdapter(imageApter);

		// 设置系统的背景
		gridview.setBackgroundResource(R.drawable.bj8);

		// 单击事件监听
		gridview.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) 
			{
				item = position + 1;
				switch (item) 
				{
					/* 跳转到注册登录界面 */
					case 1:
						/* 新建一个Intent对象 */
						Intent intent1 = new Intent();
	
						/* 指定intent要启动的类，可以先建LoginApp.java文件 */
						intent1.setClass(MainActivity.this, LoginApp.class);
	
						/* 启动一个新的Activity */
						startActivity(intent1);
	
						/* 关闭当前的Activity */
						MainActivity.this.finish();
						break;
	
					/* 跳转到浏览查询主界面 */
					case 2:
						/* 新建一个Intent对象 */
						Intent intent2 = new Intent();
	
						/* 指定intent要启动的类 */
						intent2.setClass(MainActivity.this, Function.class);
	
						/* 启动一个新的Activity */
						startActivity(intent2);
	
						/* 关闭当前的Activity */
						MainActivity.this.finish();
						break;
					case 3:
						/* 新建一个Intent对象 */
						Intent intent3 = new Intent();
	
						/* 指定intent要启动的类，可以先建LoginApp.java文件 */
						intent3.setClass(MainActivity.this, Manager.class);
	
						/* 启动一个新的Activity */
						startActivity(intent3);
	
						/* 关闭当前的Activity */
						MainActivity.this.finish();
						break;
						
					case 4:
						/* 新建一个Intent对象 */
						Intent intent4 = new Intent();
	
						/* 指定intent要启动的类，可以先建LoginApp.java文件 */
						intent4.setClass(MainActivity.this, Tongzhi.class);
	
						/* 启动一个新的Activity */
						startActivity(intent4);
	
						/* 关闭当前的Activity */
						MainActivity.this.finish();
						break;
				}
			}
		});
	}

	/**
	 * Toast提示框
	 */
	public void DisplayToast(String str) 
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
		// 设置toast显示的位置
		toast.setGravity(Gravity.TOP, 0, 220);
		// 显示该Toast
		toast.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) 
		{
			
			// System.currentTimeMillis()无论何时调用，肯定大于2000
			if ((System.currentTimeMillis() - exitTime) > 2000) 
			{
				DisplayToast("再按一次退出程序");
				exitTime = System.currentTimeMillis();
			} 
			else 
			{
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
