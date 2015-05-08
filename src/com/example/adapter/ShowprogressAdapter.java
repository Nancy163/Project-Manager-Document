package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.PressInfo;
import com.example.rammanage.R;
import com.example.util.ApplicationUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ShowprogressAdapter extends BaseAdapter {
	private List<PressInfo> list;
	private Context context;

	public ShowprogressAdapter(List<PressInfo> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.listview_item, null);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.app_icon);
			holder.name = (TextView) convertView.findViewById(R.id.app_name);
			holder.size = (TextView) convertView.findViewById(R.id.app_size);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String ProcessName = ApplicationUtil.getProgramNameByPackageName(
				context, list.get(position).getProcessName());
		if (ProcessName == null) {
			holder.name.setText(list.get(position).getProcessName());
		} else {
			holder.name.setText(ProcessName);
		}
		holder.size.setText(list.get(position).getMemSize() + "MB");
//		ImageLoader.getInstance().displayImage(uri, imageAware);
		return convertView;
	}

	private class ViewHolder {
		TextView name, size;
		ImageView image;
	}
}
