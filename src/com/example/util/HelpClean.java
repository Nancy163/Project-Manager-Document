package com.example.util;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.activity.ShowprogressActivity;

public class HelpClean {
	/*
	 * private static final String TAG = "ShowprogressActivity";
	 *//**
	 * 一件清理的方法
	 */
	/*
	 * private static void clear(Context context) { ActivityManager am =
	 * (ActivityManager) getSystemService(context.ACTIVITY_SERVICE);
	 * List<RunningAppProcessInfo> infoList = am.getRunningAppProcesses();
	 * List<ActivityManager.RunningServiceInfo> serviceInfos = am
	 * .getRunningServices(100);// 100表示最大services是100个
	 * 
	 * long beforeMem = getAvailMemory(context); Log.d(TAG,
	 * "-----------before memory info : " + beforeMem); int count = 0; if
	 * (infoList != null) { for (int i = 0; i < infoList.size(); ++i) {
	 * RunningAppProcessInfo appProcessInfo = infoList.get(i); Log.d(TAG,
	 * "process name : " + appProcessInfo.processName); // importance 该进程的重要程度
	 * 分为几个级别，数值越低就越重要。 Log.d(TAG, "importance : " + appProcessInfo.importance);
	 * 
	 * // 一般数值大于RunningAppProcessInfo.IMPORTANCE_SERVICE的进程都长时间没用或者空进程了 //
	 * 一般数值大于RunningAppProcessInfo.IMPORTANCE_VISIBLE的进程都是非可见进程，也就是在后台运行着 if
	 * (appProcessInfo.importance > RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
	 * String[] pkgList = appProcessInfo.pkgList; for (int j = 0; j <
	 * pkgList.length; ++j) {// pkgList // 得到该进程下运行的包名 Log.d(TAG,
	 * "It will be killed, package name : " + pkgList[j]);
	 * am.killBackgroundProcesses(pkgList[j]); count++; } }
	 * 
	 * } } long afterMem = getAvailMemory(context); Log.d(TAG,
	 * "----------- after memory info : " + afterMem); Toast.makeText(context,
	 * "clear " + count + " process, " + (afterMem - beforeMem) + "M",
	 * Toast.LENGTH_LONG).show(); }
	 *//**
	 * 一件清理的帮助方法，具体是干什么的我也不知道
	 * 
	 * @param mainActivity
	 * @return
	 */
	/*
	 * private long getAvailMemory(Context context) { return 0; }
	 */
}
