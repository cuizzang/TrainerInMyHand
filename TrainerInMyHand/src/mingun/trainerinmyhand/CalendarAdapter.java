package mingun.trainerinmyhand;

import java.util.ArrayList;

import mingun.trainerinmyhand.util.DayInfo;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalendarAdapter extends BaseAdapter {
	private ArrayList<DayInfo> data;
	private Context context;
	private int res;
	private LayoutInflater li;
	
	public CalendarAdapter(Context context, int res, ArrayList<DayInfo> data){
		this.data = data;
		this.context = context;
		this.res = res;
		li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int pos) {
		return data.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View v, ViewGroup group) {
		DayInfo dinfo = data.get(pos);
		DayViewHolder holder;
		
		if(v==null){
			v = li.inflate(res, null);
			holder = new DayViewHolder();
			holder.ll = (LinearLayout)v.findViewById(R.id.llDay);
			holder.tv = (TextView)v.findViewById(R.id.tvDay);
			v.setTag(holder);
		}
		else{
			holder = (DayViewHolder)v.getTag();
		}
		
		if(dinfo!=null){
			if(dinfo.isDayInMon()){
				switch(pos%7){
				case 0: holder.tv.setTextColor(Color.RED); break;
				case 6: holder.tv.setTextColor(Color.BLUE); break;
				default: holder.tv.setText(Color.BLACK); break;
				}
			}
			else{
				holder.tv.setTextColor(Color.GRAY);
			}
		}
		
		return v;
	}
	
	class DayViewHolder{
		LinearLayout ll;
		TextView tv;
	}

}
