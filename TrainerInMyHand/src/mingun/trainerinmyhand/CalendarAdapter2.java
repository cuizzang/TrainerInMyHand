package mingun.trainerinmyhand;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class CalendarAdapter2 extends BaseAdapter {
	private GregorianCalendar mCalendar;
	private Calendar mCalendarToday;
	private Context mContext;
	private DisplayMetrics mDisplayMetrics;
	private List<String> mItems;
	private int mMonth;
	private int mYear;
	private int mDaysShown;
	private int mDaysLastMonth;
	private int mDaysNextMonth;
	private int mTitleHeight, mDayHeight;
	private final String[] mDays = { "일","월", "화", "수", "목", "금", "토" };
	private int[] mDaysInMonth  = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };;
	
	public CalendarAdapter2(Context c, int month, int year, DisplayMetrics metrics) {
		if(year%4==0 && (year%100!=0 || year%400==0)) mDaysInMonth[1] = 29;
		else mDaysInMonth[1]=28;
		mContext = c;
		mMonth = month;
		mYear = year;
		mCalendar = new GregorianCalendar(mYear, mMonth, 1);
		mCalendarToday = Calendar.getInstance();
		mDisplayMetrics = metrics;
		populateMonth();
	}
	
	private void populateMonth() {
		mItems = new ArrayList<String>();		
		for (String day : mDays) {
			mItems.add(day);
			mDaysShown++;
		}
		
		int firstDay = getDay(mCalendar.get(Calendar.DAY_OF_WEEK));
		int prevDay;
		if (mMonth == 0)
			prevDay = daysInMonth(mMonth) - firstDay+1;
		else
			prevDay = daysInMonth(mMonth - 1) - firstDay+1;
		for (int i = 0; i < firstDay+1; i++) {
			mItems.add(String.valueOf(prevDay + i-1));
			mDaysLastMonth++;
			mDaysShown++;
		} 
		
		int daysInMonth = daysInMonth(mMonth);	
		for (int i = 1; i <= daysInMonth; i++) {
			mItems.add(String.valueOf(i));
			mDaysShown++;
		}
		
		mDaysNextMonth = 1;
		while (mDaysShown % 7 != 0) {
			mItems.add(String.valueOf(mDaysNextMonth));
			mDaysShown++;
			mDaysNextMonth++;
		}
		
		mTitleHeight = 70;
		int rows = (mDaysShown / 7);
		mDayHeight = (mDisplayMetrics.heightPixels - mTitleHeight 
				- (rows * 3) - getBarHeight())/ (rows*2);
	}
	
	private int daysInMonth(int month) {
		int daysInMonth = mDaysInMonth[month];
		if (month == 1 && mCalendar.isLeapYear(mYear))
			daysInMonth++;
		return daysInMonth;
	}
	
	private int getBarHeight() {
		switch (mDisplayMetrics.densityDpi) {
		case DisplayMetrics.DENSITY_HIGH:
			return 48;
		case DisplayMetrics.DENSITY_MEDIUM:
			return 32;
		case DisplayMetrics.DENSITY_LOW:
			return 24;
		default:
			return 48;
		}
	}
	
	/**
	 * For week starting Monday
	 * @param day from Calendar.DAY_OF_WEEK
	 * @return day week starting Monday
	 */
	private int getDay(int day) {
		switch (day) {
		case Calendar.MONDAY:
			return 0;
		case Calendar.TUESDAY:
			return 1;
		case Calendar.WEDNESDAY:
			return 2;
		case Calendar.THURSDAY:
			return 3;
		case Calendar.FRIDAY:
			return 4;
		case Calendar.SATURDAY:
			return 5;
		case Calendar.SUNDAY:
			return 6;
		default:
			return 0;
		}
	}
	
	private boolean isToday(int day, int month, int year) {
		if (mCalendarToday.get(Calendar.MONTH) == month
				&& mCalendarToday.get(Calendar.YEAR) == year
				&& mCalendarToday.get(Calendar.DAY_OF_MONTH) == day) {
			return true;
		}
		return false;
	}
	
	/**
	 * 셀하나에 해당하는 View를 조정하는 부분으로 독립적인 View를 만들어 조정해도 된다.
	 * 
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final TextView view = new TextView(mContext);
		view.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		view.setText(mItems.get(position));
		view.setBackgroundColor(Color.rgb(234,234,234));
		view.setTextColor(Color.BLACK);

		if (position <= 6) {
			// names
			view.setBackgroundColor(Color.argb(100, 10, 80, 255));
			view.setHeight(mTitleHeight);
		} else if (position <= mDaysLastMonth + 6) {
			// previous month
			view.setBackgroundColor(Color.argb(100, 10, 80,245));
			view.setHeight(mDayHeight);
		} else if (position <= mDaysShown - mDaysNextMonth  ) {
			// current month
			view.setHeight(mDayHeight);
			int day = position - (mDaysLastMonth + 6);
			if (isToday(day, mMonth, mYear)) {
				view.setTextColor(Color.RED);
			}
			// check db and colour for shifts
			// ...
		} else {
			// next month
			view.setHeight(mDayHeight);
			view.setBackgroundColor(Color.argb(100, 10, 80,245));
		}
		return view;
	}
	
	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}