package com.bysj.auction;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.bysj.function.Function;
import com.bysj.login.LoginApp;
import com.bysj.manager.ManagerOperate;
import com.bysj.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Manager extends Activity
{
	public static int flag=0;
	private EditText mu_name;
	private EditText mu_pwd;
	private CheckBox mcheckBox;
	private Button mbnLogin;
	private Button mbnCancle;
	private Editor editor;
	private SharedPreferences pref;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manager);
		
		mu_name=(EditText) findViewById(R.id.et_mUserName);
		mu_pwd=(EditText) findViewById(R.id.et_mUserPwd);
		mcheckBox=(CheckBox) findViewById(R.id.checkbox);
		mbnLogin=(Button) findViewById(R.id.mbtn_Ok);
		mbnCancle=(Button) findViewById(R.id.mbtn_Cancel);
		
		pref=getSharedPreferences("info", MODE_PRIVATE);
		editor = pref.edit();
		String name = pref.getString("userName", "");
		if (name==null) 
		{
			mcheckBox.setChecked(false);
		}
		else 
		{
			mcheckBox.setChecked(true);
			mu_name.setText(name);
		}
		mbnLogin.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				
				//ִ������У�鷽��
				if(CheckLogin()){
					//��¼�ɹ�
					if(loginSuccess()){
//						 �½�һ��Intent���� 
						Intent intent = new Intent();
						
//						 ָ��intentҪ�������� 
						intent.setClass(Manager.this, ManagerOperate.class);
						
//						 ����һ���µ�Activity 
						startActivity(intent);
						
//						 �رյ�ǰ��Activity 
						Manager.this.finish();
					}
				}
			}
		});
		
		 
		//ȡ����ťע�ᵥ���¼�������
		mbnCancle.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				DisplayToast("���Ѿ�ѡ����ȡ����¼ϵͳ��");
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0)
		{
			Intent intent = new Intent();
			intent.setClass(Manager.this, MainActivity.class);
			startActivity(intent);
			Manager.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	//��������û������������У��
	private boolean CheckLogin()
	{
		String username=mu_name.getText().toString().trim();
		if(username.equals(""))
		{
			DisplayToast("�û����Ǳ����");
			return false;
		}
		String pwd=mu_pwd.getText().toString().trim();
		if(pwd.equals(""))
		{
			DisplayToast("�����Ǳ����");
			return false;
		}
		return true;
	}
	
	public void DisplayToast(String str) 
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		// ����toast��ʾ��λ��
		toast.setGravity(Gravity.TOP, 0, 220);
		// ��ʾ��Toast
		toast.show();
	}
	
	public boolean loginSuccess() 
	{
		String name=mu_name.getText().toString();  
        String pass=mu_pwd.getText().toString();
        JSONObject jsonObject;
        try
        {
        	jsonObject=query(name,pass);
        	if(jsonObject.getInt("UserId")>0)
        	{
        		if (mcheckBox.isChecked()) 
    			{
    				editor.putString("userName", name);
    				editor.commit();
    			}
    			else 
    			{
    				editor.remove("userName");
    				editor.commit();
    			}
        		DisplayToast("�ѳɹ���¼ϵͳ��");
        		flag=1;
        		return true;
        	}
        	else
        	{
        		DisplayToast("�û�����������������µ�¼��");
        	}
        }
        catch(Exception e)
        {
        	DisplayToast("��������Ӧ�쳣�����Ժ����ԣ�");
        }
        return false;
	}
	
	//���巢������ķ���
	private JSONObject query(String name, String pass) throws Exception
	{
		Map<String,String> map=new HashMap<String, String>();
		System.out.println("�ͻ��ˣ�"+name+":"+pass);
		map.put("user", name);
		map.put("pass", pass);
		
		//���巢�͵�����URL
		String url=HttpUtil.BASE_URL+"loginmanager";
		
		//��������
		return new JSONObject(HttpUtil.postRequest(url,map));
	}
}
