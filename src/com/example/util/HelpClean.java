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
	 * һ������ķ���
	 */
	/*
	 * private static void clear(Context context) { ActivityManager am =
	 * (ActivityManager) getSystemService(context.ACTIVITY_SERVICE);
	 * List<RunningAppProcessInfo> infoList = am.getRunningAppProcesses();
	 * List<ActivityManager.RunningServiceInfo> serviceInfos = am
	 * .getRunningServices(100);// 100��ʾ���services��100��
	 * 
	 * long beforeMem = getAvailMemory(context); Log.d(TAG,
	 * "-----------before memory info : " + beforeMem); int count = 0; if
	 * (infoList != null) { for (int i = 0; i < infoList.size(); ++i) {
	 * RunningAppProcessInfo appProcessInfo = infoList.get(i); Log.d(TAG,
	 * "process name : " + appProcessInfo.processName); // importance �ý��̵���Ҫ�̶�
	 * ��Ϊ����������ֵԽ�;�Խ��Ҫ�� Log.d(TAG, "importance : " + appProcessInfo.importance);
	 * 
	 * // һ����ֵ����RunningAppProcessInfo.IMPORTANCE_SERVICE�Ľ��̶���ʱ��û�û��߿ս����� //
	 * һ����ֵ����RunningAppProcessInfo.IMPORTANCE_VISIBLE�Ľ��̶��Ƿǿɼ����̣�Ҳ�����ں�̨������ if
	 * (appProcessInfo.importance > RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
	 * String[] pkgList = appProcessInfo.pkgList; for (int j = 0; j <
	 * pkgList.length; ++j) {// pkgList // �õ��ý��������еİ��� Log.d(TAG,
	 * "It will be killed, package name : " + pkgList[j]);
	 * am.killBackgroundProcesses(pkgList[j]); count++; } }
	 * 
	 * } } long afterMem = getAvailMemory(context); Log.d(TAG,
	 * "----------- after memory info : " + afterMem); Toast.makeText(context,
	 * "clear " + count + " process, " + (afterMem - beforeMem) + "M",
	 * Toast.LENGTH_LONG).show(); }
	 *//**
	 * һ������İ��������������Ǹ�ʲô����Ҳ��֪��
	 * 
	 * @param mainActivity
	 * @return
	 */
	/*
	 * private long getAvailMemory(Context context) { return 0; }
	 */
}
