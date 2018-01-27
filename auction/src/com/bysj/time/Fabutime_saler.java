package com.bysj.time;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.bysj.auction.R;
import com.bysj.function.Function;
import com.bysj.sale.Sale;
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

public class Fabutime_saler extends Activity 
{
	private EditText ed_goodsno;
	private EditText ed_goodsname;
	private EditText ed_goods_start_time;
	private EditText ed_goods_end_time;
	private Button btn_addtime;
	private Button btn_addtimecancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fabutime_saler);
		
		ed_goodsno=(EditText) findViewById(R.id.time_edgoodsno);
		ed_goodsname=(EditText) findViewById(R.id.time_edgoodsname);
		ed_goods_start_time=(EditText) findViewById(R.id.time_edgoods_start);
		ed_goods_end_time=(EditText) findViewById(R.id.time_edgoods_end);
		
		btn_addtime=(Button) findViewById(R.id.btn_addtime);
		btn_addtimecancel=(Button) findViewById(R.id.btn_addtimeCancel);
		
		btn_addtime.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				if(checkTimeInfo()){
					DisplayToast("信息验证成功！");
					String no=ed_goodsno.getText().toString();
					String name=ed_goodsname.getText().toString();
					String start_time=ed_goods_start_time.getText().toString();
					String end_time=ed_goods_end_time.getText().toString();
					
					Map<String,String> map=new HashMap<String,String>();
					map.put("Goodsno", no);
					map.put("Goodsname", name);
					map.put("Goods_start_time", start_time);
					map.put("Goods_end_time", end_time);
					String url=HttpUtil.BASE_URL+"fabutime";
					
					//发送请求,并把服务器响应转为JSON对象
					try 
					{
						String result=HttpUtil.postRequest(url,map);
						System.out.println(result);
						JSONObject jsonObject=new JSONObject(result);
						System.out.println(jsonObject);
						if(jsonObject.getInt("time")>0)
						{
							DisplayToast("恭喜您，已成功发布时间信息！");
						}
						else
						{
							DisplayToast("发布时间信息失败，请稍后重试！");
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
		
		btn_addtimecancel.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				DisplayToast("您已经取消了发布时间信息！");
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
			intent.setClass(Fabutime_saler.this, Function.class);
			startActivity(intent);
			Fabutime_saler.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
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
	
	private boolean checkTimeInfo()
	{
		if (isNumeric(ed_goodsno.getText().toString())) 
		{
		} 
		else 
		{
			DisplayToast("商品编号只能为数字！");
			return false;
		}
		
		
		if (ed_goodsname.getText() != null && ed_goodsname.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("商品名称不能为空！");
			return false;
		}
		
		if (ed_goods_start_time.getText() != null && ed_goods_start_time.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("商品上货时间不能为空！");
			return false;
		}
		
		if (ed_goods_end_time.getText() != null && ed_goods_end_time.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("拍卖截止时间不能为空！");
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
}
