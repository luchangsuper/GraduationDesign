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
		
		// ���ü���
		btn_Register.setOnTouchListener(btnRegisterTouch);
		btn_RegisterCancel.setOnTouchListener(btnRegisterCancelTouch);
        
    }
    
    Button.OnTouchListener btnRegisterTouch = new Button.OnTouchListener() 
    {
		@Override
		public boolean onTouch(View v, MotionEvent event) 
		{
			// ������Щ�����¼�����
			int iAction = event.getAction();
			if (iAction == MotionEvent.ACTION_CANCEL
					|| iAction == MotionEvent.ACTION_DOWN
					|| iAction == MotionEvent.ACTION_MOVE) 
			{
				return false;
			}
			// �ͻ����ж�
			if (checkUserInfo()) 
			{
				DisplayToast("��Ϣ��֤�ɹ���");
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
				
				//��������,���ѷ�������ӦתΪJSON����
				try 
				{
					String result=HttpUtil.postRequest(url,map);
					System.out.println(result);
					JSONObject jsonObject=new JSONObject(result);
					System.out.println(jsonObject);
					if(jsonObject.getInt("UserId")>0)
					{
						DisplayToast("���ѳɹ���������û���");
					}
					else
					{
						DisplayToast("�����ʧ�ܣ����Ժ����ԣ�");
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
	 * ����ȡ����¼�ķ���
	 */
	Button.OnTouchListener btnRegisterCancelTouch = new Button.OnTouchListener() 
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) 
		{
			// ������Щ�����¼�����
			int iAction = event.getAction();
			if (iAction == MotionEvent.ACTION_CANCEL
					|| iAction == MotionEvent.ACTION_DOWN
					|| iAction == MotionEvent.ACTION_MOVE) 
			{
				return false;
			}
			DisplayToast("���Ѿ�ȡ������ӣ�");
			return true;
		}
	};
	
	/**
	 * ��֤�û���Ϣ
	 */
	private boolean checkUserInfo() 
	{
		if (ed_userName.getText() != null && ed_userName.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("�û����Ʋ���Ϊ�գ�");
			return false;
		}
		if (ed_userName.getText().length() < 6) 
		{
			DisplayToast("�û�����̫�̣�");
			return false;
		}

		if (ed_userPwd.getText() != null && ed_userPwd.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("�û����벻��Ϊ�գ�");
			return false;
		}
		if (ed_userPwd.getText().length() < 6) 
		{
			DisplayToast("�û�����̫�̣�");
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
			DisplayToast("���������û����벻һ�£�");
			return false;
		}

		if (ed_userMobilePhone.getText() != null
				&& ed_userMobilePhone.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("����д�ֻ����룡");
			return false;
		}
		if (isNumeric(ed_userMobilePhone.getText().toString())) 
		{
		} 
		else 
		{
			DisplayToast("�ֻ�����ֻ��Ϊ���֣�");
			return false;

		}
		if ( ed_userMobilePhone.getText().length()==11)
		{
		} 
		else 
		{
			DisplayToast("�ֻ����������ʮһλ��");
			return false;
		}

		if (ed_userEmail.getText() != null
				&& ed_userEmail.getText().length() > 0)
		{
		} 
		else {
			DisplayToast("����д���䣡");
			return false;
		}

		if (getEmail(ed_userEmail.getText().toString())) 
		{
		} 
		else 
		{
			DisplayToast("�����ʽ����ȷ��");
			return false;
		}
		
		if(ed_userRealName.getText()!=null&&ed_userRealName.getText().length()>0)
		{
		}
		else
		{
			DisplayToast("��ʵ��������Ϊ�գ�");
			return false;
		}
		
		if(ed_userAddress.getText()!=null&&ed_userAddress.getText().length()>0)
		{
		}
		else
		{
			DisplayToast("����д�ջ��ַ��");
			return false;
		}
		
		if(ed_userPostNumber.getText()!=null&&ed_userPostNumber.getText().length()>0)
		{
		}
		else
		{
			DisplayToast("�������벻��Ϊ�գ�");
			return false;
		}
		
		if(isNumeric(ed_userPostNumber.getText().toString()))
		{
		}
		else
		{
			DisplayToast("��������ֻ��Ϊ���֣�");
			return false;
		}
		
		if(ed_userCredit.getText()!=null&&ed_userCredit.getText().length()>0)
		{
		}
		else
		{
			DisplayToast("���ö��ʸ���֤�Ǳ����");
			return false;
		}
		return true;
	}
	
	/**
	 * ������֤
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNumeric(String str) 
	{
		//����������ʽ
		String regNumeric="[0-9]*";
		Pattern pattern = Pattern.compile(regNumeric);
		return pattern.matcher(str).matches();
	}

	/**
	 * ������֤
	 * @param strEmail
	 * @return
	 */
	private boolean getEmail(String strEmail) 
	{
		/*����������ʽ*/
		String regEmail="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern
				.compile(regEmail);
		Matcher m = p.matcher(strEmail);
		return m.find();
	}
	
	
	/* ��ʾToast */
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
			intent.setClass(Tianjiapm.this, ManagerOperate.class);
			startActivity(intent);
			Tianjiapm.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
