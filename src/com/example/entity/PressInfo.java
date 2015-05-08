package com.example.entity;

import android.graphics.drawable.Drawable;

public class PressInfo {
	private int pid; // ����id Android�涨android.system.uid=1000
	private int uid; // �������ڵ��û�id �����ý�������˭������ root/��ͨ�û���
	private double memSize; // ����ռ�õ��ڴ��С,��λΪkb
	private String processName; // ������
	private Drawable icon;

	public PressInfo() {
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public double getMemSize() {
		return memSize;
	}

	public void setMemSize(double memSize) {
		this.memSize = memSize;
	}

	public String getProcessName() {
		return processName;
	}

	public void setPocessName(String processName) {
		this.processName = processName;
	}
}
