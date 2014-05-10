package mingun.trainerinmyhand;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class FoodAdd extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_add);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Button btnFood;
		EditText edtFood;
		ListView lvFood;
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_add, menu);
		
		btnFood=(Button)findViewById(R.id.btnFoodAdding);
		edtFood=(EditText)findViewById(R.id.edtFoodFind);
		lvFood=(ListView)findViewById(R.id.lvFoodList);
		btnFood.setOnClickListener(this);
		ArrayAdapter aaFoodAdd=new ArrayAdapter(this,android.R.layout.simple_list_item_1,FoodInput.food);
		lvFood.setAdapter(aaFoodAdd);
		return true;
	}

	@Override
	public void onClick(View v) {
		
	}

	
}
