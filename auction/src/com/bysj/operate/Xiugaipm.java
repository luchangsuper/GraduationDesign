package com.bysj.operate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
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

public class Xiugaipm extends Activity 
{
	int flag=0;
	private EditText editText;
	private Button btn_confirm;
	private Button bn_update;
	private Button bn_cancel;
	private EditText ed_salername;
	private EditText ed_salerpwd;
	private EditText ed_salerphone;
	private EditText ed_saleremail;
	private EditText ed_salerrealname;
	private EditText ed_saleraddress;
	private EditText ed_salerpost;
	private EditText ed_salercredit;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiugaipm);
		
		editText=(EditText) findViewById(R.id.xiugaipm_edno);
		btn_confirm=(Button) findViewById(R.id.btnxiugaipm);
		ed_salername=(EditText) findViewById(R.id.xiugaipm_edname);
		ed_salerpwd=(EditText) findViewById(R.id.xiugaipm_edpwd);
		ed_salerphone=(EditText) findViewById(R.id.xiugaipm_edphone);
		ed_saleremail=(EditText) findViewById(R.id.xiugaipm_edemail);
		ed_salerrealname=(EditText) findViewById(R.id.xiugaipm_edrealname);
		ed_saleraddress=(EditText) findViewById(R.id.xiugaipm_edaddress);
		ed_salerpost=(EditText) findViewById(R.id.xiugaipm_edpost);
		ed_salercredit=(EditText) findViewById(R.id.xiugaipm_edcredit);
		bn_update=(Button) findViewById(R.id.btn_xiugaipm);
		bn_cancel=(Button) findViewById(R.id.btn_xiugaipmcancel);
		
		btn_confirm.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				String Salerno=editText.getText().toString().trim();
				Map<String, String> map=new HashMap<String, String>();
				map.put("Salerno", Salerno);
				String url=HttpUtil.BASE_URL+"xiugaipm";
				String result;
				try 
				{
					result = HttpUtil.postRequest(url, map);
					System.out.println(result);
					JSONArray jsonArray=new JSONArray(result);
					JSONObject jsonObject=jsonArray.getJSONObject(0);
					String uName=jsonObject.getString("用户名");
					String uPwd=jsonObject.getString("密码");
					String uPhone=jsonObject.getString("电话");
					String uEmail=jsonObject.getString("邮箱");
					String uRealname=jsonObject.getString("姓名");
					String uAddress=jsonObject.getString("地址");
					String uPost=jsonObject.getString("邮编");
					String uCredit=jsonObject.getString("信用度");
					
					ed_salername.setText(uName);
					ed_salerpwd.setText(uPwd);
					ed_salerphone.setText(uPhone);
					ed_saleremail.setText(uEmail);
					ed_salerrealname.setText(uRealname);
					ed_saleraddress.setText(uAddress);
					ed_salerpost.setText(uPost);
					ed_salercredit.setText(uCredit);
				}
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		bn_update.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				if(checkSalerInfo()){
					DisplayToast("信息验证成功！");
					String salerno=editText.getText().toString();
					String salername=ed_salername.getText().toString();
					String salerpwd=ed_salerpwd.getText().toString();
					String salerphone=ed_salerphone.getText().toString();
					String saleremail=ed_saleremail.getText().toString();
					String salerrealname=ed_salerrealname.getText().toString();
					String saleraddress=ed_saleraddress.getText().toString();
					String salerpost=ed_salerpost.getText().toString();
					String salercredit=ed_salercredit.getText().toString();
					
					Map<String,String> map=new HashMap<String,String>();
					map.put("Salerno", salerno);
					map.put("Salername", salername);
					map.put("Salerpwd", salerpwd);
					map.put("Salerphone", salerphone);
					map.put("Saleremail", saleremail);
					map.put("Salerrealname", salerrealname);
					map.put("Saleraddress", saleraddress);
					map.put("Salerpost", salerpost);
					map.put("Salercredit", salercredit);
					String url=HttpUtil.BASE_URL+"xiugaipm_1";
					
					//发送请求,并把服务器响应转为JSON对象
					try 
					{
						String result=HttpUtil.postRequest(url,map);
						System.out.println(result);
						JSONObject jsonObject=new JSONObject(result);
						System.out.println(jsonObject);
						flag=1;
						if(jsonObject.getInt("SalerId")>0&&flag==1)
						{
							DisplayToast("恭喜您，已成功修改拍卖用户信息！");
						}
						else
						{
							DisplayToast("修改拍卖用户信息失败，请稍后重试！");
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
			}
		});
		
		bn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DisplayToast("您已经选择了取消修改拍卖用户信息！");
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
			intent.setClass(Xiugaipm.this, ManagerOperate.class);
			startActivity(intent);
			Xiugaipm.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * 输入检验方法
	 */
	private boolean checkSalerInfo()
	{
		if (ed_salername.getText() != null && ed_salername.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("拍卖者用户名不能为空！");
			return false;
		}
		
		if(isNumeric(ed_salerphone.getText().toString()))
		{
		}
		else
		{
			DisplayToast("拍卖用户电话只能为数字！");
			return false;
		}
		
		if(isNumeric(ed_salerpost.getText().toString()))
		{
		}
		else
		{
			DisplayToast("拍卖用户邮编只能为数字！");
			return false;
		}
		
		if (ed_salerpwd.getText() != null && ed_salerpwd.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("拍卖用户密码不能为空！");
			return false;
		}
		
		if (ed_saleremail.getText() != null && ed_saleremail.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("拍卖者邮箱不能为空！");
			return false;
		}
		
		if (ed_salerrealname.getText() != null && ed_salerrealname.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("拍卖者真实姓名不能为空！");
			return false;
		}
		
		if (ed_saleraddress.getText() != null && ed_saleraddress.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("拍卖用户地址不能为空！");
			return false;
		}
		
		if (ed_salercredit.getText() != null && ed_salercredit.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("拍卖用户信用度不能为空！");
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
	
	/* 显示Toast */
	public void DisplayToast(String str) 
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		// 设置toast显示的位置
		toast.setGravity(Gravity.TOP, 0, 220);
		// 显示该Toast
		toast.show();
	}
}
