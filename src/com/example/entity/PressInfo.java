package com.example.entity;

import android.graphics.drawable.Drawable;

public class PressInfo {
	private int pid; // 进程id Android规定android.system.uid=1000
	private int uid; // 进程所在的用户id ，即该进程是有谁启动的 root/普通用户等
	private double memSize; // 进程占用的内存大小,单位为kb
	private String processName; // 进程名
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
