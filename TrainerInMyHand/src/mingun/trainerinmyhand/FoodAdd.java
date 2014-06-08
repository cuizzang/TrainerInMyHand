package mingun.trainerinmyhand;

import mingun.trainerinmyhand.util.InputDialog;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnDismissListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.content.DialogInterface;

public class FoodAdd extends Activity implements OnClickListener, AdapterView.OnItemClickListener{
	Button btnFood,btnBack;
	EditText edtFood;
	ListView lvFood;
	InputDialog inputDial;
	String strInput;
	ArrayAdapter<String> aaFoodAdd;
	SharedPreferences pref;
	SharedPreferences.Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_add);
		
		btnFood=(Button)findViewById(R.id.btnFoodAdding);
		edtFood=(EditText)findViewById(R.id.edtFoodFind);
		lvFood=(ListView)findViewById(R.id.lvFoodList);
		btnBack=(Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		btnFood.setOnClickListener(this);
		
		aaFoodAdd=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FoodInput.listForShow);
		lvFood.setAdapter(aaFoodAdd);
		lvFood.setOnItemClickListener(this);
		pref = getSharedPreferences("TrainerInMyHand", MODE_PRIVATE);;
		editor = pref.edit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.food_add, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Button b=(Button)v;
		if(b==btnBack){
			editor.putInt("foodCount", FoodInput.foodcount);
			editor.commit();
			Intent intent=new Intent(FoodAdd.this,TrainerInMyHand.class);
			startActivity(intent);
		}
		else{
			InputDialog.strForInput="음식의 칼로리를 입력하세요.";
			inputDial=new InputDialog(FoodAdd.this);
			
			Context mContext = getApplicationContext();
	        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
	        View layout = inflater.inflate(R.layout.input_dialog,(ViewGroup) findViewById(R.id.llInput));
			
			inputDial.setOnDismissListener(new OnDismissListener(){
				@SuppressLint("CommitPrefEdits")
				@Override
				public void onDismiss(DialogInterface dialog) {
					strInput=InputDialog.msgInput;
					if(strInput==""){
						Toast.makeText(FoodAdd.this, "입력내용이 없습니다.", Toast.LENGTH_SHORT).show();
						return;
					}
					int numInput;
					try{
						numInput=Integer.valueOf(strInput);
					}
					catch(Exception e){
						Toast.makeText(FoodAdd.this, "숫자만 입력해 주세요!", Toast.LENGTH_SHORT).show();
						return;
					}
					
					FoodInput.foodcount++;
					Toast.makeText(FoodAdd.this, "추가 완료", Toast.LENGTH_SHORT).show();
					FoodInput.food.add(edtFood.getText().toString());
					FoodInput.cal.add(numInput);
					FoodInput.listForShow.add(FoodInput.food.get(FoodInput.food.size()-1) + " " + String.valueOf(FoodInput.cal.get(FoodInput.cal.size()-1)));
					
					
					editor.putString("food"+(FoodInput.food.size()-1), FoodInput.food.get(FoodInput.food.size()-1));
					editor.putInt("cal"+(FoodInput.cal.size()-1), FoodInput.cal.get(FoodInput.cal.size()-1));
					aaFoodAdd.notifyDataSetChanged();
				}
			});
			inputDial.show();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		final int position = pos;
		new AlertDialog.Builder(FoodAdd.this).setTitle("")
				.setMessage(FoodInput.food.get(pos)+"의 칼로리는 "+FoodInput.cal.get(pos)+"칼로리입니다.")
				.setPositiveButton("삭제", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){
						FoodInput.food.remove(position);
						FoodInput.cal.remove(position);
						FoodInput.listForShow.remove(position);
						aaFoodAdd.notifyDataSetChanged();
						FoodInput.foodcount--;
					}})
				.setNegativeButton("취소", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){}})
				.show();
		aaFoodAdd.notifyDataSetChanged();
	}
	
	public boolean onKeyDown( int KeyCode, KeyEvent event ){
		if( event.getAction() == KeyEvent.ACTION_DOWN ){
			if( KeyCode == KeyEvent.KEYCODE_BACK )return false;
		}
	 return super.onKeyDown( KeyCode, event );
	}
}
