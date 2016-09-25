package net.asiasofti.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;


public class StartActivity extends Activity {

	public StartActivity() {

		super();

	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.start_view);
		(new Timer()).schedule(new TimerTask() {
			public void run() {

				try {
					Thread.sleep(2000L);
					Intent intent = new Intent();
					intent.setClass(StartActivity.this,
							net.asiasofti.android.MainActivity.class);
					startActivity(intent);
					finish();
				} catch (InterruptedException interruptedexception) {
					interruptedexception.printStackTrace();
				}

			}

		}, 1000L);
	}
}
