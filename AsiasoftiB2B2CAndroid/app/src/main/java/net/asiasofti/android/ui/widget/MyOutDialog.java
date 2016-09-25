package net.asiasofti.android.ui.widget;

import net.asiasofti.android.R;
import android.app.Dialog;
import android.content.Context;
import android.widget.*;

public class MyOutDialog extends Dialog
{

	public Button btu_off;
	public Button btu_on;
	public TextView edit_text_count;
	public ImageButton image_btu_jia;
	public ImageButton image_btu_jian;
	public TextView text_shopnum_price;

	public MyOutDialog(Context context)
	{
		super(context, R.style.MyProgressDialog);
		setContentView(R.layout.my_out);
		btu_on = (Button)findViewById(R.id.btu_on);
		btu_off = (Button)findViewById(R.id.btu_off);
		text_shopnum_price = (TextView)findViewById(R.id.text_shopnum_price);
		image_btu_jian = (ImageButton)findViewById(R.id.image_btu_jian);
		edit_text_count = (TextView)findViewById(R.id.edit_text_count);
		image_btu_jia = (ImageButton)findViewById(R.id.image_btu_jia);
	}
}
