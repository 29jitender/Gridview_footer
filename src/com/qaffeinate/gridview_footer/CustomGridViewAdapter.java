package com.qaffeinate.gridview_footer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;
 
public class CustomGridViewAdapter extends ArrayAdapter<Itemgrid> {
	Context context;
	int layoutResourceId;
	View row;
	ArrayList<Itemgrid> data = new ArrayList<Itemgrid>();
	RecordHolder holder;
	 public static List<Integer> check = new ArrayList<Integer>();
	public CustomGridViewAdapter(Context context, int layoutResourceId,
			ArrayList<Itemgrid> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		  row = convertView;
		  holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();
 			holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		Itemgrid item = data.get(position);
 		if (holder.imageItem != null) {
			AQuery aq = new AQuery(row);
			ImageOptions options = new ImageOptions();
			options.round = 15;
			options.memCache=true;
			options.fileCache=true;
 			aq.id(holder.imageItem).image(item.getUrl(), options);
 
 			
			
 		}	
		
		return row;

	}
 
 
	static class RecordHolder {
 		ImageView imageItem;

	}
}