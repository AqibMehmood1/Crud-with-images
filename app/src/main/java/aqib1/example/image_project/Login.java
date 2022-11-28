package aqib1.example.image_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    String name,store,ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SQLiteHelper db=new SQLiteHelper(this);

            Button button1=findViewById(R.id.Login);
             EditText editText1=findViewById(R.id.name);
             EditText editText2=findViewById(R.id.Logpas);
            TextView tv=findViewById(R.id.SignUp);


            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name2=editText1.getText().toString();
                    String pass2=editText2.getText().toString();
                    boolean res=db.checkLogin(name2,pass2);
                    if(res==true){
                        Toast.makeText(getApplicationContext(),name2+" Login successfully...",Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else {

                        Toast.makeText(getApplicationContext(),"Sorry...",Toast.LENGTH_SHORT).show();
                    }

                }
            });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });

    }
}