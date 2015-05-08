package com.example.activity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.example.rammanage.R;
import com.example.util.ApplicationUtil;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.FileObserver;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private static final String TAG = "MainActivity----";
	Button btn;
	ImageView setting;
	private ActivityManager am;
	int num = 0;
	private static final String meminfoPath = "/proc/meminfo";
	private TextView mMemUesed;
	private Context mContext;

	int delaytime = 50;

	private float memUsed;
	private float memTotal;

	private MeminfoFileObserver mMeminfoFileObserver;
	private ImageView mIvMeminfo;

	private ClipDrawable drawable;
	private int mLevel;

	private Handler handler = new Handler();
	private Runnable task = new Runnable() {
		public void run() {
			// TODO Auto-generated method stub
			dataRefresh();
			handler.postDelayed(this, delaytime);
		}

	};

	private boolean animaling = false;
	private boolean downing;
	PopupWindow popupWindow;
	private View view;

	protected void dataRefresh() {
		// TODO Auto-generated method stub

		if (animaling) {
			if (mLevel > 0 && downing) {
				drawable.setLevel(mLevel);
				mLevel -= 100;
			} else {
				memUsed = 100
						* (ApplicationUtil.getTotalMem() - ApplicationUtil
								.getAvailMem(mContext)) / ApplicationUtil.getTotalMem();
				System.out.println(memUsed + ">>>>>>>>>>>>>>>>>>");
				downing = false;
				// mLevel=0;
				if (mLevel < 0)
					mLevel = 0;
			}

			if (mLevel < memUsed * 100 && !downing) {
				mLevel = mLevel + 100;
				drawable.setLevel(mLevel);

				if (mLevel > memUsed * 100) {
					animaling = false;
				}
			}

			mMemUesed.setText(((int) mLevel / 100) + "%");

			return;
		}

		else {
			memUsed = 100
					* (ApplicationUtil.getTotalMem() - ApplicationUtil
							.getAvailMem(mContext)) / ApplicationUtil.getTotalMem();
			System.out.println(">>>" + memUsed);
			drawable.setLevel((int) memUsed * 100);
			mMemUesed.setText(((int) memUsed) + "%");
			return;
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		btn = (Button) findViewById(R.id.clean_rightnow_btn);
		setting = (ImageView) findViewById(R.id.title_right);

		btn.setOnClickListener(this);
		setting.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.clean_rightnow_btn:
			Intent intent = new Intent(MainActivity.this,
					ShowprogressActivity.class);
			startActivity(intent);
			break;
		case R.id.clean_center_ball:
			init();
			break;
		case R.id.title_right:

			Intent sintent = new Intent(MainActivity.this,
					FloatWindowService.class);
			startService(sintent);
			finish();
			break;
		}
	}

	/**
	 * show popup window
	 */
	private void showPopupWindow() {
		// if popupWindow is null then initialize it
		if (popupWindow == null) {
			// get layout inflater from system service of
			// LAYOUT_INFLATER_SERVICE
			LayoutInflater layoutInflater = (LayoutInflater) this
					.getSystemService(LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.pop_window, null);
			popupWindow = new PopupWindow(view, LayoutParams.WRAP_CONTENT, 70,
					true);
			popupWindow.showAtLocation(this.findViewById(R.id.action_title),
					Gravity.RIGHT, 0, -480);

		}
		;
		popupWindow.update();
		TextView start_button = (TextView) view
				.findViewById(R.id.TextView_popupwindow);
		TextView close_button = (TextView) view
				.findViewById(R.id.TextView2_popupwindow);
		start_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						FloatWindowService.class);
				startService(intent);
				finish();
			}
		});
		close_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						FloatWindowService.class);
				stopService(intent);
				closePopupWindow();
			}
		});
	}

	/**
	 * clos popup window if popup window is not null
	 */
	private void closePopupWindow() {
		if (popupWindow != null) {
			popupWindow.dismiss();
		}
	}

	public void init() {
		// TODO Auto-generated method stub
		mMeminfoFileObserver = new MeminfoFileObserver(meminfoPath);
		mMeminfoFileObserver.startWatching();

		mContext = getApplicationContext();
		mIvMeminfo = (ImageView) findViewById(R.id.clean_schedule_img);
		// mBtnClean = (Button) view.findViewById(R.id.btn_clear);

		mMemUesed = (TextView) findViewById(R.id.clean_value_tip);

		mIvMeminfo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				freeMemory();
				mLevel = drawable.getLevel();
				downing = true;
				animaling = true;
			}
		});

		memTotal = ApplicationUtil.getAvailMem(mContext);
		Log.d(TAG, "memTotal = " + memTotal);
		drawable = (ClipDrawable) mIvMeminfo.getDrawable();

		animaling = false;

		dataRefresh();

		handler.postDelayed(task, delaytime);
	}

	private class MeminfoFileObserver extends FileObserver {

		private static final String TAG = "MeminfoFileObserver";

		public MeminfoFileObserver(String path) {
			super(path);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onEvent(int event, String path) {
			switch (event) {

			case android.os.FileObserver.ATTRIB:
				break;

			}
		}

	};

	void freeMemory() {
		List<ActivityManager.RunningAppProcessInfo> list = ApplicationUtil
				.getManager(mContext).getRunningAppProcesses();
		if (list != null)
			for (int i = 0; i < list.size(); i++) {
				ActivityManager.RunningAppProcessInfo apinfo = list.get(i);
				String[] pkgList = apinfo.pkgList;
				if (apinfo.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE) {
					// Process.killProcess(apinfo.pid);
					for (int j = 0; j < pkgList.length; j++) {
						ApplicationUtil.getManager(mContext)
								.killBackgroundProcesses(pkgList[j]);
						Log.i(TAG, "kill " + pkgList[j]);
					}
				}
			}
	}

}
