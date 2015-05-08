package com.example.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.PorterDuff.Mode;

public class SaveData {
	Context context;

	public SaveData(Context context) {
		super();
		this.context = context;
	}

	public void save(String filename, boolean b) {
		SharedPreferences sp = context.getSharedPreferences(filename,
				context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean("flag", b);
		editor.commit();
	}

	public boolean getData(String filename) {
		SharedPreferences sp = context.getSharedPreferences(filename,
				context.MODE_PRIVATE);
		boolean b = sp.getBoolean("flag", false);
		return b;
	}
}
