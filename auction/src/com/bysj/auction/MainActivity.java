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

		// ȡ��GridView����
		GridView gridview = (GridView) findViewById(R.id.gridview);

		// ��ʼ��ͼƬ��������
		imageApter = new MainImageAdapter(this);

		// ���Ԫ�ظ�gridview�ؼ�
		gridview.setAdapter(imageApter);

		// ����ϵͳ�ı���
		gridview.setBackgroundResource(R.drawable.bj8);

		// �����¼�����
		gridview.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) 
			{
				item = position + 1;
				switch (item) 
				{
					/* ��ת��ע���¼���� */
					case 1:
						/* �½�һ��Intent���� */
						Intent intent1 = new Intent();
	
						/* ָ��intentҪ�������࣬�����Ƚ�LoginApp.java�ļ� */
						intent1.setClass(MainActivity.this, LoginApp.class);
	
						/* ����һ���µ�Activity */
						startActivity(intent1);
	
						/* �رյ�ǰ��Activity */
						MainActivity.this.finish();
						break;
	
					/* ��ת�������ѯ������ */
					case 2:
						/* �½�һ��Intent���� */
						Intent intent2 = new Intent();
	
						/* ָ��intentҪ�������� */
						intent2.setClass(MainActivity.this, Function.class);
	
						/* ����һ���µ�Activity */
						startActivity(intent2);
	
						/* �رյ�ǰ��Activity */
						MainActivity.this.finish();
						break;
					case 3:
						/* �½�һ��Intent���� */
						Intent intent3 = new Intent();
	
						/* ָ��intentҪ�������࣬�����Ƚ�LoginApp.java�ļ� */
						intent3.setClass(MainActivity.this, Manager.class);
	
						/* ����һ���µ�Activity */
						startActivity(intent3);
	
						/* �رյ�ǰ��Activity */
						MainActivity.this.finish();
						break;
						
					case 4:
						/* �½�һ��Intent���� */
						Intent intent4 = new Intent();
	
						/* ָ��intentҪ�������࣬�����Ƚ�LoginApp.java�ļ� */
						intent4.setClass(MainActivity.this, Tongzhi.class);
	
						/* ����һ���µ�Activity */
						startActivity(intent4);
	
						/* �رյ�ǰ��Activity */
						MainActivity.this.finish();
						break;
				}
			}
		});
	}

	/**
	 * Toast��ʾ��
	 */
	public void DisplayToast(String str) 
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
		// ����toast��ʾ��λ��
		toast.setGravity(Gravity.TOP, 0, 220);
		// ��ʾ��Toast
		toast.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) 
		{
			
			// System.currentTimeMillis()���ۺ�ʱ���ã��϶�����2000
			if ((System.currentTimeMillis() - exitTime) > 2000) 
			{
				DisplayToast("�ٰ�һ���˳�����");
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
