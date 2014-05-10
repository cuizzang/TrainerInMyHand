package mingun.trainerinmyhand;

import mingun.trainerinmyhand.util.InputDialog;
import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

public class FoodAdd extends Activity implements OnClickListener, AdapterView.OnItemClickListener{
	Button btnFood;
	EditText edtFood;
	ListView lvFood;
	InputDialog inputDial;
	String strInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_add);
		
		btnFood=(Button)findViewById(R.id.btnFoodAdding);
		edtFood=(EditText)findViewById(R.id.edtFoodFind);
		lvFood=(ListView)findViewById(R.id.lvFoodList);
		btnFood.setOnClickListener(this);
		
		ArrayAdapter aaFoodAdd=new ArrayAdapter(this, android.R.layout.simple_list_item_1, FoodInput.food);
		lvFood.setAdapter(aaFoodAdd);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.food_add, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		InputDialog.strForInput="음식의 칼로리를 입력하세요.";
		inputDial=new InputDialog(FoodAdd.this);
		
		inputDial.setOnDismissListener(new OnDismissListener(){
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
				
				FoodInput.food.add(edtFood.getText().toString());
				FoodInput.cal.add(numInput);
			}
		});
		
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		//
	}

}
