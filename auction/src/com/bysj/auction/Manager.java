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
				
				//执行输入校验方法
				if(CheckLogin()){
					//登录成功
					if(loginSuccess()){
//						 新建一个Intent对象 
						Intent intent = new Intent();
						
//						 指定intent要启动的类 
						intent.setClass(Manager.this, ManagerOperate.class);
						
//						 启动一个新的Activity 
						startActivity(intent);
						
//						 关闭当前的Activity 
						Manager.this.finish();
					}
				}
			}
		});
		
		 
		//取消按钮注册单击事件监听器
		mbnCancle.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				DisplayToast("您已经选择了取消登录系统！");
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

	//对输入的用户名和密码进行校验
	private boolean CheckLogin()
	{
		String username=mu_name.getText().toString().trim();
		if(username.equals(""))
		{
			DisplayToast("用户名是必填项！");
			return false;
		}
		String pwd=mu_pwd.getText().toString().trim();
		if(pwd.equals(""))
		{
			DisplayToast("密码是必填项！");
			return false;
		}
		return true;
	}
	
	public void DisplayToast(String str) 
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		// 设置toast显示的位置
		toast.setGravity(Gravity.TOP, 0, 220);
		// 显示该Toast
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
        		DisplayToast("已成功登录系统！");
        		flag=1;
        		return true;
        	}
        	else
        	{
        		DisplayToast("用户名或密码错误，请重新登录！");
        	}
        }
        catch(Exception e)
        {
        	DisplayToast("服务器响应异常，请稍后再试！");
        }
        return false;
	}
	
	//定义发送请求的方法
	private JSONObject query(String name, String pass) throws Exception
	{
		Map<String,String> map=new HashMap<String, String>();
		System.out.println("客户端："+name+":"+pass);
		map.put("user", name);
		map.put("pass", pass);
		
		//定义发送的请求URL
		String url=HttpUtil.BASE_URL+"loginmanager";
		
		//发送请求
		return new JSONObject(HttpUtil.postRequest(url,map));
	}
}
