package mingun.trainerinmyhand;

import java.util.ArrayList;
import java.util.Calendar;

import mingun.trainerinmyhand.util.DayInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class TrainerInMyHand extends Activity implements OnClickListener{
	
	public static int nowEat,planEat,nowExe,planExe,moreExe,weight,age,tall,base;
	private Button btnPlan,btnEat,btnExe,btnSet;
	private Button btnPrevMonth,btnNextMonth;
	private TextView txtEat,txtExe;
	private TextView txtCalTitle;
	
	private boolean exeOver,male;
	
	private Calendar prevMonthCal,thisMonthCal,nextMonthCal;
	private ArrayList<DayInfo> dayData;
	private CalendarAdapter calAdapter;
	private GridView gvCal;
	
	public static final int SUN = 1;
	public static final int MON = 2;
	public static final int TUE = 3;
	public static final int WED = 4;
	public static final int THU = 5;
	public static final int FRI = 6;
	public static final int SAT = 7;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		init();
		
		btnPlan=(Button)findViewById(R.id.btnPlan);
		btnPlan.setOnClickListener(this);
		btnEat=(Button)findViewById(R.id.btnEat);
		btnEat.setOnClickListener(this);
		btnExe=(Button)findViewById(R.id.btnExersice);
		btnExe.setOnClickListener(this);
		btnSet=(Button)findViewById(R.id.btnSetting);
		btnSet.setOnClickListener(this);
		btnPrevMonth=(Button)findViewById(R.id.btnPrevMonth);
		btnPrevMonth.setOnClickListener(this);
		btnNextMonth=(Button)findViewById(R.id.btnNextMonth);
		btnNextMonth.setOnClickListener(this);
		txtEat=(TextView)findViewById(R.id.txtEat);
		txtExe=(TextView)findViewById(R.id.txtExersice);
		txtCalTitle=(TextView)findViewById(R.id.txtCalTitle);
		
		gvCal=(GridView)findViewById(R.id.gvCalendar);
		dayData=new ArrayList<DayInfo>();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		thisMonthCal=Calendar.getInstance();
		thisMonthCal.set(Calendar.DAY_OF_MONTH, 1);
		getCal(thisMonthCal);
	}
	
	private void getCal(Calendar cal){
		int lastMonthLastDay;
		int thisMonthLastDay;
		int weekNum;
		
		//이번달 시작일의 요일
		weekNum = cal.get(Calendar.DAY_OF_WEEK);
		// 이번달 마지막날
		thisMonthLastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 지난달로 세팅
		cal.add(Calendar.MONTH, -1);
		//지난달 마지막날
		lastMonthLastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//다시 이번달로
		cal.add(Calendar.MONTH, 1);
		
		if(weekNum==SUN) weekNum += 7;
		lastMonthLastDay -= weekNum-2;
		
		//년월표시
		txtCalTitle.setText(thisMonthCal.get(Calendar.YEAR) + "년 " + (thisMonthCal.get(Calendar.MONTH)+1) + "월");
		
		// 달력에 날짜 세팅
		DayInfo day;
		

		for(int i=0;i<weekNum-1;i++){
			int date=lastMonthLastDay+i;
			day=new DayInfo();
			day.setDay(String.valueOf(date));
			day.setDayInMon(false);
			dayData.add(day);
		}
		
		for(int i=1;i<=thisMonthLastDay;i++){
			day=new DayInfo();
			day.setDay(String.valueOf(i));
			day.setDayInMon(true);
			dayData.add(day);
		}
		
		for(int i=1;i<=42-(thisMonthLastDay+weekNum-1)+1;i++){
			day=new DayInfo();
			day.setDay(String.valueOf(i));
			day.setDayInMon(false);
			dayData.add(day);
		}
		
		// 어댑터 설정
		//calAdapter=new CalendarAdapter(this, R.layout.day, dayData);
		//gvCal.setAdapter(calAdapter);
	}
	
	/**
	 * 초기화 작업
	 */
	private void init(){
		SharedPreferences pref=getSharedPreferences("TrainerInMyHand", MODE_PRIVATE);

		nowEat=pref.getInt("nowEat", 0);
		nowExe=pref.getInt("nowExe", 0);
		planEat=pref.getInt("planEat", 0);
		planExe=pref.getInt("planExe", 0);
		moreExe=nowExe-planExe;
		if(moreExe<0) moreExe=0;
		base=pref.getInt("base", 0);
		tall=pref.getInt("tall", 0);
		age=pref.getInt("age", 0);
		weight=pref.getInt("weight", 0);
		exeOver=pref.getBoolean("exeOver", false);
		male=pref.getBoolean("male", false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.trainer_in_my_hand, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Button btn=(Button)v;
		/*
		if(btn==btnPrevMonth){
			thisMonthCal.add(Calendar.MONTH, -1);
			getCal(thisMonthCal);
		}
		else if(btn==btnNextMonth){
			thisMonthCal.add(Calendar.MONTH, 1);
			getCal(thisMonthCal);
		}
		else*/
		if(btn==btnPlan){
			Intent intent=new Intent(TrainerInMyHand.this,Planning.class);
			startActivity(intent);
		}
		else if(btn==btnEat){
			Intent intent=new Intent(TrainerInMyHand.this,FoodInput.class);
			startActivity(intent);
		}
		
	}

}
