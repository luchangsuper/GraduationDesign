package com.bysj.sale;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.bysj.auction.MainActivity;
import com.bysj.auction.R;
import com.bysj.login.LoginApp;
import com.bysj.register.Register;
import com.bysj.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sale_delete extends Activity
{
	
	private EditText editText;
	private Button delete;
	private Button cancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deleteinfo);
		
		editText=(EditText) findViewById(R.id.delete_no);
		delete=(Button) findViewById(R.id.btn_del);
		cancel=(Button) findViewById(R.id.btn_delCancel);
		
		delete.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				String delno=editText.getText().toString().trim();
				Map<String, String> map=new HashMap<String, String>();
				map.put("delno", delno);
				String url=HttpUtil.BASE_URL+"delete";
				
				try {
					String result=HttpUtil.postRequest(url, map);
					System.out.println(result);
					JSONObject jsonObject=new JSONObject(result);
					System.out.println(jsonObject);
					if(jsonObject.getInt("flag")==1)
					{
						DisplayToast("��ϲ�����ѳɹ�ɾ����Ʒ��Ϣ��");
					}
					else
					{
						DisplayToast("ɾ����Ʒ��Ϣʧ�ܣ�������Ʒ����Ƿ�������ȷ�����Ժ����ԣ�");
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
				DisplayToast("���Ѿ�ѡ����ȡ��ɾ����Ʒ��Ϣ��");
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
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0)
		{
			Intent intent = new Intent();
			intent.setClass(Sale_delete.this, Sale.class);
			startActivity(intent);
			Sale_delete.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
