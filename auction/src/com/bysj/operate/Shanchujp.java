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

public class Shanchujp extends Activity 
{
	private EditText editText;
	private Button delete;
	private Button cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shanchujp);

		editText = (EditText) findViewById(R.id.shanchujp_no);
		delete = (Button) findViewById(R.id.btn_shanchujp);
		cancel = (Button) findViewById(R.id.btn_shanchujpCancel);

		delete.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				String delno = editText.getText().toString().trim();
				Map<String, String> map = new HashMap<String, String>();
				map.put("delno", delno);
				String url = HttpUtil.BASE_URL + "shanchujp";

				try 
				{
					String result = HttpUtil.postRequest(url, map);
					System.out.println(result);
					JSONObject jsonObject = new JSONObject(result);
					System.out.println(jsonObject);
					if (jsonObject.getInt("flag") == 1) 
					{
						DisplayToast("恭喜您，已成功删除竞拍用户信息！");
					} 
					else 
					{
						DisplayToast("删除竞拍用户信息失败，请检查拍卖者编号是否输入正确或请稍后重试！");
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
				DisplayToast("您已经选择了取消删除竞拍用户信息！");
			}
		});
	}

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
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
		{
			Intent intent = new Intent();
			intent.setClass(Shanchujp.this, ManagerOperate.class);
			startActivity(intent);
			Shanchujp.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
