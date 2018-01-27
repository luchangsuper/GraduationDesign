package com.bysj.operate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.bysj.auction.MainActivity;
import com.bysj.auction.R;
import com.bysj.login.LoginApp;
import com.bysj.manager.ManagerOperate;
import com.bysj.register.Register;
import com.bysj.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tianjiapm extends Activity 
{
	private EditText ed_userName;
	private EditText ed_userPwd;
	private EditText ed_userRePwd;
	private EditText ed_userMobilePhone;
	private EditText ed_userEmail;
	private EditText ed_userRealName;
	private EditText ed_userAddress;
	private EditText ed_userPostNumber;
	private EditText ed_userCredit;
	private Button btn_Register;
	private Button btn_RegisterCancel;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tianjiapm);
        
        ed_userName = (EditText) findViewById(R.id.ed_registeruserName);
		ed_userPwd = (EditText) findViewById(R.id.ed_registerPwd);
		ed_userRePwd = (EditText) findViewById(R.id.ed_registerRePwd);
		ed_userMobilePhone = (EditText) findViewById(R.id.ed_registerUserMoblePhone);
		ed_userEmail = (EditText) findViewById(R.id.ed_registerUserEmail);
		ed_userRealName=(EditText) findViewById(R.id.ed_registerUserRealName);
		ed_userAddress=(EditText) findViewById(R.id.ed_registerUserAddress);
		ed_userPostNumber=(EditText) findViewById(R.id.ed_registerUserPostNumber);
		ed_userCredit=(EditText) findViewById(R.id.ed_registerUserCredit);
		btn_Register = (Button) findViewById(R.id.btn_Register);
		btn_RegisterCancel=(Button) findViewById(R.id.btn_RegisterCancel);
		
		// 设置监听
		btn_Register.setOnTouchListener(btnRegisterTouch);
		btn_RegisterCancel.setOnTouchListener(btnRegisterCancelTouch);
        
    }
    
    Button.OnTouchListener btnRegisterTouch = new Button.OnTouchListener() 
    {
		@Override
		public boolean onTouch(View v, MotionEvent event) 
		{
			// 设置哪些触屏事件可用
			int iAction = event.getAction();
			if (iAction == MotionEvent.ACTION_CANCEL
					|| iAction == MotionEvent.ACTION_DOWN
					|| iAction == MotionEvent.ACTION_MOVE) 
			{
				return false;
			}
			// 客户端判断
			if (checkUserInfo()) 
			{
				DisplayToast("信息验证成功！");
				String userName=ed_userName.getText().toString();
				String userPwd=ed_userPwd.getText().toString();
				String userMobliePhone=ed_userMobilePhone.getText().toString();
				String userEmail=ed_userEmail.getText().toString();
				String userRealName=ed_userRealName.getText().toString();
				String userAddress=ed_userAddress.getText().toString();
				String userPost=ed_userPostNumber.getText().toString();
				String userCredit=ed_userCredit.getText().toString();
				
				Map<String, String> map=new HashMap<String, String>();
				map.put("userName", userName);
				map.put("userPwd", userPwd);
				map.put("userMobilePhone", userMobliePhone);
				map.put("userEmail", userEmail);
				map.put("userRealName", userRealName);
				map.put("userAddress", userAddress);
				map.put("userPost", userPost);
				map.put("userCredit", userCredit);
				String url=HttpUtil.BASE_URL+"register";
				
				//发送请求,并把服务器响应转为JSON对象
				try 
				{
					String result=HttpUtil.postRequest(url,map);
					System.out.println(result);
					JSONObject jsonObject=new JSONObject(result);
					System.out.println(jsonObject);
					if(jsonObject.getInt("UserId")>0)
					{
						DisplayToast("您已成功添加拍卖用户！");
					}
					else
					{
						DisplayToast("您添加失败，请稍后重试！");
					}
				} 
				catch (JSONException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return true;
		}
	};
	
	/**
	 * 触屏取消登录的方法
	 */
	Button.OnTouchListener btnRegisterCancelTouch = new Button.OnTouchListener() 
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) 
		{
			// 设置哪些触屏事件可用
			int iAction = event.getAction();
			if (iAction == MotionEvent.ACTION_CANCEL
					|| iAction == MotionEvent.ACTION_DOWN
					|| iAction == MotionEvent.ACTION_MOVE) 
			{
				return false;
			}
			DisplayToast("您已经取消了添加！");
			return true;
		}
	};
	
	/**
	 * 验证用户信息
	 */
	private boolean checkUserInfo() 
	{
		if (ed_userName.getText() != null && ed_userName.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("用户名称不能为空！");
			return false;
		}
		if (ed_userName.getText().length() < 6) 
		{
			DisplayToast("用户名称太短！");
			return false;
		}

		if (ed_userPwd.getText() != null && ed_userPwd.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("用户密码不能为空！");
			return false;
		}
		if (ed_userPwd.getText().length() < 6) 
		{
			DisplayToast("用户密码太短！");
			return false;
		}

		if (ed_userRePwd.getText() != null
				&& ed_userRePwd.getText().length() > 0
				&& ed_userPwd.getText().toString()
						.equals(ed_userRePwd.getText().toString())) 
		{
		} 
		else 
		{
			DisplayToast("两次输入用户密码不一致！");
			return false;
		}

		if (ed_userMobilePhone.getText() != null
				&& ed_userMobilePhone.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("请填写手机号码！");
			return false;
		}
		if (isNumeric(ed_userMobilePhone.getText().toString())) 
		{
		} 
		else 
		{
			DisplayToast("手机号码只能为数字！");
			return false;

		}
		if ( ed_userMobilePhone.getText().length()==11)
		{
		} 
		else 
		{
			DisplayToast("手机号码必须是十一位！");
			return false;
		}

		if (ed_userEmail.getText() != null
				&& ed_userEmail.getText().length() > 0)
		{
		} 
		else {
			DisplayToast("请填写邮箱！");
			return false;
		}

		if (getEmail(ed_userEmail.getText().toString())) 
		{
		} 
		else 
		{
			DisplayToast("邮箱格式不正确！");
			return false;
		}
		
		if(ed_userRealName.getText()!=null&&ed_userRealName.getText().length()>0)
		{
		}
		else
		{
			DisplayToast("真实姓名不能为空！");
			return false;
		}
		
		if(ed_userAddress.getText()!=null&&ed_userAddress.getText().length()>0)
		{
		}
		else
		{
			DisplayToast("请填写收获地址！");
			return false;
		}
		
		if(ed_userPostNumber.getText()!=null&&ed_userPostNumber.getText().length()>0)
		{
		}
		else
		{
			DisplayToast("邮政编码不能为空！");
			return false;
		}
		
		if(isNumeric(ed_userPostNumber.getText().toString()))
		{
		}
		else
		{
			DisplayToast("邮政编码只能为数字！");
			return false;
		}
		
		if(ed_userCredit.getText()!=null&&ed_userCredit.getText().length()>0)
		{
		}
		else
		{
			DisplayToast("信用度资格认证是必填项！");
			return false;
		}
		return true;
	}
	
	/**
	 * 数字验证
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNumeric(String str) 
	{
		//定义正则表达式
		String regNumeric="[0-9]*";
		Pattern pattern = Pattern.compile(regNumeric);
		return pattern.matcher(str).matches();
	}

	/**
	 * 邮箱验证
	 * @param strEmail
	 * @return
	 */
	private boolean getEmail(String strEmail) 
	{
		/*定义正则表达式*/
		String regEmail="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern
				.compile(regEmail);
		Matcher m = p.matcher(strEmail);
		return m.find();
	}
	
	
	/* 显示Toast */
	public void DisplayToast(String str) 
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		// 设置toast显示的位置
		toast.setGravity(Gravity.TOP, 0, 220);
		// 显示该Toast
		toast.show();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0)
		{
			Intent intent = new Intent();
			intent.setClass(Tianjiapm.this, ManagerOperate.class);
			startActivity(intent);
			Tianjiapm.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
