package com.example.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.format.Formatter;

public class ApplicationUtil {
	/**
	 * 得到应用名称
	 * 
	 * @param context
	 *            08 Context���� 09
	 * @param packageName
	 *            10 ���� 11
	 * @return ���ذ������Ӧ��Ӧ�ó������ơ� 12
	 */
	public static String getProgramNameByPackageName(Context context,
			String packageName) {
		PackageManager pm = context.getPackageManager();
		String name = null;
		try {
			name = pm.getApplicationLabel(
					pm.getApplicationInfo(packageName,
							PackageManager.GET_META_DATA)).toString();
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return name;
	}
	/**
	 * 获取手机的总内存大小 单位byte
	 * @return
	 */
	public static long getTotalMem(){
		try {
			FileInputStream fis = new FileInputStream(new File("/proc/meminfo"));
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String totalInfo  = br.readLine();
			//MemTotal:         513000 kB
			StringBuffer sb = new StringBuffer();
			for(char c : totalInfo.toCharArray()){
				if(c>='0'&&c<='9'){
					sb.append(c);
				}
			}
			long bytesize = Long.parseLong(sb.toString())*1024;
			return bytesize;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 获取可用的内存信息。
	 * @param context
	 * @return
	 */
	public static long getAvailMem(Context context){
		//获取内存大小
		MemoryInfo outInfo = new ActivityManager.MemoryInfo();
		getManager(context).getMemoryInfo(outInfo);
		long availMem = outInfo.availMem;
		return availMem;
	}
	/**
	 * 得到正在运行的进程的数量
	 * @param context
	 * @return
	 */
	public static int getRunningPocessCount(Context context){
		List<RunningAppProcessInfo> runningAppProcessInfos = getManager(context).getRunningAppProcesses();
		int count = runningAppProcessInfos.size();
		return count;
	}
	public static int getIntager(String str){
		str = str.substring(0,str.length()-2);
		int ab = Integer.parseInt(str);
		return ab;	
	}
	public static String getString(Context context,long aa){
		return Formatter.formatFileSize(context, aa);
	}
	// 更新可用内存信息
	public static String upDateMemInfo(Context context) {
			// 获得MemoryInfo对象
		ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
			// 获得系统可用内存，保存在MemoryInfo对象上
		 getManager(context).getMemoryInfo(memoryInfo);
		long memSize = memoryInfo.availMem;
			// 字符类型转换
		String leftMemSize = Formatter.formatFileSize(context, memSize);
			return leftMemSize;
		}
	
	
	public static List<RunningAppProcessInfo> getProgess(Context context){
		return getManager(context).getRunningAppProcesses();
	}
	
	
	public static ActivityManager getManager(Context context){
		return (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	}
}
