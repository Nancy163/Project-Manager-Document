package com.example.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.BrowseApplicationInfoAdapter;
import com.example.entity.AppInfo;
import com.example.rammanage.R;
import com.example.util.ApplicationUtil;

public class ShowprogressActivity extends Activity implements
		OnItemClickListener, OnClickListener {
	private static final String TAG = null;
	private ActivityManager am;
	private ListView lv;
	ImageView finish;
	Button clean_all;
	private ListView listview = null;
	private List<AppInfo> mlistAppInfo = null;
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cleanprogress_listview);
		btn = (Button) findViewById(R.id.clean_all_button1);
		listview = (ListView) findViewById(R.id.listviewApp);
		mlistAppInfo = new ArrayList<AppInfo>();
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		finish = (ImageView) findViewById(R.id.clean_progress_image);
		finish.setOnClickListener(this);
		queryAppInfo(); // ��ѯ����Ӧ�ó�����Ϣ
		browseAppAdapter = new BrowseApplicationInfoAdapter(this, mlistAppInfo);
		listview.setAdapter(browseAppAdapter);
		listview.setOnItemClickListener(this);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// startService(new
				// Intent(ShowprogressActivity.this,FloatWindowService.class));
		       List<RunningAppProcessInfo> pro = ApplicationUtil.getProgess(ShowprogressActivity.this);
		       for (RunningAppProcessInfo Info : pro) {
				 String classNmame = Info.processName;
				 ApplicationUtil.getManager(ShowprogressActivity.this).killBackgroundProcesses(classNmame);
			}
		       Toast.makeText(ShowprogressActivity.this, "一键清理结束，当前可用内存为："+ApplicationUtil.upDateMemInfo(ShowprogressActivity.this), Toast.LENGTH_SHORT).show();
			}
		});
	}

	// �����������Activity����Ϣ��������Launch����
	public void queryAppInfo() {
		PackageManager pm = this.getPackageManager(); // ���PackageManager����
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		// ͨ���ѯ���������ResolveInfo����.
		List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent,
				PackageManager.MATCH_DEFAULT_ONLY);
		// ����ϵͳ���� �� ���name����
		// ���������Ҫ������ֻ����ʾϵͳӦ�ã������г�����Ӧ�ó���
		Collections.sort(resolveInfos,
				new ResolveInfo.DisplayNameComparator(pm));
		if (mlistAppInfo != null) {
			mlistAppInfo.clear();
			for (ResolveInfo reInfo : resolveInfos) {
				String activityName = reInfo.activityInfo.name; // ��ø�Ӧ�ó��������Activity��name
				String pkgName = reInfo.activityInfo.packageName; // ���Ӧ�ó���İ���
				String appLabel = (String) reInfo.loadLabel(pm); // ���Ӧ�ó����Label
				Drawable icon = reInfo.loadIcon(pm); // ���Ӧ�ó���ͼ��
				// ΪӦ�ó��������Activity ׼��Intent
				Intent launchIntent = new Intent();
				launchIntent.setComponent(new ComponentName(pkgName,
						activityName));
				// ����һ��AppInfo���󣬲���ֵ
				AppInfo appInfo = new AppInfo();
				appInfo.setAppLabel(appLabel);
				appInfo.setPkgName(pkgName);
				appInfo.setAppIcon(icon);
				appInfo.setIntent(launchIntent);
				mlistAppInfo.add(appInfo); // 追加到进程集合
			}
		}
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			final int position, long id) {
		if (mlistAppInfo.get(position).getPkgName().equals(getPackageName())) {
			int pid = android.os.Process.myPid();
			android.os.Process.killProcess(pid);
		} else {
			new AlertDialog.Builder(ShowprogressActivity.this)
					.setTitle("进程提示")
					.setMessage("是否杀死进程")
					.setNegativeButton("No", null)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								@SuppressWarnings("deprecation")
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// am.killBackgroundProcesses(list
									// .get(position).processName);
									System.out.println("�����˽��"
											+ mlistAppInfo.get(position)
													.getPkgName());
									am.restartPackage(mlistAppInfo
											.get(position).getPkgName());
									// Process.killProcess(mlistAppInfo.get(position)
									// .getPid());
									mlistAppInfo.remove(position);
									browseAppAdapter.notifyDataSetChanged();
									browseAppAdapter = new BrowseApplicationInfoAdapter(
											ShowprogressActivity.this,
											mlistAppInfo);
									listview.setAdapter(browseAppAdapter);
								}
							}).show();
		}
	}

	int count = 0;
	private BrowseApplicationInfoAdapter browseAppAdapter;

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.clean_progress_image:
			finish();
			break;

		case R.id.clean_all_button1:
			while (count < mlistAppInfo.size()) {
				for (int i = 0; i < mlistAppInfo.size(); i++) {
					if (getPackageName().equals(
							mlistAppInfo.get(i).getPkgName())) {
						continue;
					}
					am.restartPackage(mlistAppInfo.get(i).getPkgName());
					mlistAppInfo.remove(mlistAppInfo.get(i));
					browseAppAdapter.notifyDataSetChanged();
					count++;
				}
			}
			break;
		}
	}

}
