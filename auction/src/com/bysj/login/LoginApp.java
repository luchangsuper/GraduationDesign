package com.bysj.login;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
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
import android.widget.RadioButton;
import android.widget.Toast;

import com.bysj.auction.MainActivity;
import com.bysj.auction.R;
import com.bysj.function.Function;
import com.bysj.register.Register;
import com.bysj.register.RegisterBid;
import com.bysj.util.HttpUtil;

/*����ע���¼���ܵ�ʵ��*/
public class LoginApp extends Activity
{
	public static String usernameinfo;
	public static int flag=0;
	private EditText u_name;
	private EditText u_pwd;
	private Button bnLogin;
	private Button bnCancle;
	private CheckBox checkBox;
	private Button bnRegister;
	private Button bnRegisterbid;
	private Editor editor;
	private SharedPreferences pref;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		//��ȡ�����е��ı��С���ť
		u_name=(EditText) findViewById(R.id.et_UserName);
		u_pwd=(EditText) findViewById(R.id.et_UserPwd);
		checkBox=(CheckBox) findViewById(R.id.checkbox);
		bnLogin=(Button) findViewById(R.id.btn_Ok);
		bnCancle=(Button) findViewById(R.id.btn_Cancel);
		bnRegister=(Button) findViewById(R.id.Register);
		bnRegisterbid=(Button) findViewById(R.id.Registerbid);
		
		pref=getSharedPreferences("info", MODE_PRIVATE);
		editor = pref.edit();
		String name = pref.getString("userName", "");
		if (name==null) 
		{
			checkBox.setChecked(false);
		}
		else 
		{
			checkBox.setChecked(true);
			u_name.setText(name);
		}
		
		//��¼��ťע�ᵥ���¼�������
		bnLogin.setOnClickListener(new OnClickListener() 
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
						intent.setClass(LoginApp.this, Function.class);
						
//						 ����һ���µ�Activity 
						startActivity(intent);
						
//						 �رյ�ǰ��Activity 
						LoginApp.this.finish();
					}
				}
			}
		});
		
		 
		//ȡ����ťע�ᵥ���¼�������
		bnCancle.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				DisplayToast("���Ѿ�ѡ����ȡ����¼ϵͳ��");
			}
		});
		
		//����ע�ᰴť�����¼�������
		bnRegister.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(LoginApp.this, Register.class);
				startActivity(intent);
				LoginApp.this.finish();
			}
		});
		
		//����ע�ᰴť�����¼�������
		bnRegisterbid.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(LoginApp.this, RegisterBid.class);
				startActivity(intent);
				LoginApp.this.finish();
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
			intent.setClass(LoginApp.this, MainActivity.class);
			startActivity(intent);
			LoginApp.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	//��������û������������У��
	private boolean CheckLogin()
	{
		String username=u_name.getText().toString().trim();
		if(username.equals(""))
		{
			DisplayToast("�û����Ǳ����");
			return false;
		}
		String pwd=u_pwd.getText().toString().trim();
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
		String name=u_name.getText().toString();  
        String pass=u_pwd.getText().toString();
        JSONObject jsonObject;
        try
        {
        	jsonObject=query(name,pass);
        	if(jsonObject.getInt("UserId")>0)
        	{
        		if (checkBox.isChecked()) 
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
        		usernameinfo=name;
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
		String url=HttpUtil.BASE_URL+"login";
		
		//��������
		return new JSONObject(HttpUtil.postRequest(url,map));
	}
}
