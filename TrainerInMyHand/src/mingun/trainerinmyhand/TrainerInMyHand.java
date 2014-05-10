package mingun.trainerinmyhand;

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
	private GridView gvCal;
	private Button btnPlan,btnEat,btnExe,btnSet;
	private TextView txtEat,txtExe;
	
	boolean exeOver,male;
	
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
		txtEat=(TextView)findViewById(R.id.txtEat);
		txtExe=(TextView)findViewById(R.id.txtExersice);
		gvCal=(GridView)findViewById(R.id.gvCalendar);
	}
	
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trainer_in_my_hand, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Button btn=(Button)v;
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
