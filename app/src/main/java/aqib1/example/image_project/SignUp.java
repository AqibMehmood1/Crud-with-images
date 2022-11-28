package aqib1.example.image_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    public static final String EXTRA_NAME="name";
    public static final String EXTRA_PH="ph";
    public static final String EXTRA_STORE="store";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SQLiteHelper db=new SQLiteHelper(this);

        Button btn =findViewById(R.id.SignUp);
        final EditText editText1=findViewById(R.id.name);
        final EditText editText2=findViewById(R.id.Logpas);
        final EditText editText4=findViewById(R.id.ph);
        final EditText editText5=findViewById(R.id.addr);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText1.getText().toString();
                String pass=editText2.getText().toString();
                String ph=editText4.getText().toString();
                String address=editText5.getText().toString();
                if(name.equals("")||pass.equals("")||ph.equals("")||address.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill field",Toast.LENGTH_LONG).show();
                }
                else {
                    boolean result = db.insertdata(name, ph, address, pass);
                    if (result == true) {
                        Toast.makeText(getApplicationContext(), name + " Registored successfully....", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SignUp.this, Login.class);
                        intent.putExtra(EXTRA_NAME,name);
                        intent.putExtra(EXTRA_PH,ph);
                        startActivity(intent);
                        editText1.setText("");
                        editText2.setText("");
                        editText4.setText("");
                        editText5.setText("");
                    }
                }
            }
        });
    }
}