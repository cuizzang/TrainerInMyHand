package mingun.trainerinmyhand.util;

/**
 * 입력받는 다이알로그
 */

import mingun.trainerinmyhand.R;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputDialog extends Dialog implements View.OnClickListener{
	private TextView txtInput;
	private Button	btnInput;
	private EditText edtInput;
	public static String msgInput;
	public static String strForInput;

	public InputDialog(Context context) {
		super(context);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.input_dialog);
		
		txtInput=(TextView)this.findViewById(R.id.txtInput);
		btnInput=(Button)this.findViewById(R.id.btnInput);
		edtInput=(EditText)this.findViewById(R.id.edtInput);
		btnInput.setOnClickListener(this);
		
		msgInput="";
		edtInput.setText("");
		txtInput.setText(strForInput);
	}

	@Override
	public void onClick(View v) {
		msgInput=edtInput.getText().toString();
		this.dismiss();
	}
}
