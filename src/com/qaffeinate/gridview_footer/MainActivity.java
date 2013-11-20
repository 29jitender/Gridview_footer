package com.qaffeinate.gridview_footer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	 CustomGridViewAdapter customGridAdapter;
	 ArrayList<Itemgrid> gridArray = new ArrayList<Itemgrid>();
	 ArrayList<Itemgrid> gridArray_update = new ArrayList<Itemgrid>();

	 GridView gridView;
	 Boolean checking=true;
	 RelativeLayout show_more;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid1, gridArray);
		show_more = (RelativeLayout) findViewById(R.id.loader);
		display();
	}

	public void display(){
		try {
			InputStream is = this.getAssets().open("Sample_JSON.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			String bufferString = new String(buffer);
 			
 			JSONObject jsonobject = new JSONObject(bufferString);
 			
 			JSONArray array1= jsonobject.getJSONArray("genericStreamEntry");
			for(int i=0;i<array1.length();i++){
	 			JSONObject obj1= array1.getJSONObject(i);
	 			JSONObject obj2 =obj1.getJSONObject("productReviewDetails");
	 			JSONObject obj3 =obj2.getJSONObject("productDetails");
	 			JSONObject obj4 =obj3.getJSONObject("imageDetails");
	 			String key =obj4.getString("cdnKeyWebList");
 					gridArray.add(new Itemgrid("http://www.wooplr.com/serve?blob-key="+key));


			}
 			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gridArray_update.addAll(gridArray);
  				 
				gridView = (GridView) findViewById(R.id.gridView1); 

				gridView.setAdapter(customGridAdapter);	
				gridView.setOnScrollListener(scrollListener);

             }
       
OnScrollListener scrollListener = new OnScrollListener() {
		
		@Override
		public void onScrollStateChanged(AbsListView v, int scrollState) {	
			Log.i("the check",gridArray.size()+"");

 		}
		
		@Override
		public void onScroll(final AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			if( (gridView.getLastVisiblePosition() == gridView.getAdapter().getCount() -1 &&
					gridView.getChildAt(gridView.getChildCount() - 1).getBottom() <= gridView.getHeight())
					 )
				{
				
				show_more.setVisibility(view.VISIBLE);
				if(checking){

					checking=false;

					final Handler handler = new Handler();
					
	                handler.postDelayed(new Runnable() {
	                  @Override
	                  public void run() {
	                         gridArray.addAll(gridArray_update);
	                        customGridAdapter.notifyDataSetChanged();
	                        checking=true;
	        				show_more.setVisibility(view.GONE);

	                        }
	                }, 3300);
				}
				
  					}
			
			else{
				show_more.setVisibility(view.GONE);
			}
				
			 
						 
		}
	};
    }


