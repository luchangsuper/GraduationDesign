package com.bysj.operate;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.bysj.auction.R;
import com.bysj.manager.ManagerOperate;
import com.bysj.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Shanchupm extends Activity 
{
	private EditText editText;
	private Button delete;
	private Button cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shanchupm);

		editText = (EditText) findViewById(R.id.shanchupm_no);
		delete = (Button) findViewById(R.id.btn_shanchupm);
		cancel = (Button) findViewById(R.id.btn_shanchupmCancel);

		delete.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				String delno = editText.getText().toString().trim();
				Map<String, String> map = new HashMap<String, String>();
				map.put("delno", delno);
				String url = HttpUtil.BASE_URL + "shanchupm";

				try 
				{
					String result = HttpUtil.postRequest(url, map);
					System.out.println(result);
					JSONObject jsonObject = new JSONObject(result);
					System.out.println(jsonObject);
					if (jsonObject.getInt("flag") == 1) 
					{
						DisplayToast("��ϲ�����ѳɹ�ɾ�������û���Ϣ��");
					} 
					else 
					{
						DisplayToast("ɾ�������û���Ϣʧ�ܣ����������߱���Ƿ�������ȷ�����Ժ����ԣ�");
					}
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		cancel.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				DisplayToast("���Ѿ�ѡ����ȡ��ɾ�������û���Ϣ��");
			}
		});
	}

	public void DisplayToast(String str) 
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		// ����toast��ʾ��λ��
		toast.setGravity(Gravity.TOP, 0, 220);
		// ��ʾ��Toast
		toast.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
		{
			Intent intent = new Intent();
			intent.setClass(Shanchupm.this, ManagerOperate.class);
			startActivity(intent);
			Shanchupm.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
