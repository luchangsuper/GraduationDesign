package com.bysj.auction;

import com.bysj.bid.Bid;
import com.bysj.function.Function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class Tongzhi extends Activity 
{
	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tongzhi);
		textView=(TextView) findViewById(R.id.tv1);
		
		String mes="1����ϵͳ��֧�����ϻ���ʽ���ף�����ʵ�ʽ��׻�������½��С�\n"+
				   "2��ע���û����Խ�����Ʒ��Ϣ��ѯ����Ʒ����Ȳ�������ע���û����ܲ�ѯ��Ʒ��Ϣ��\n";
		textView.setText(mes);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0)
		{
			Intent intent = new Intent();
			intent.setClass(Tongzhi.this, MainActivity.class);
			startActivity(intent);
			Tongzhi.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
