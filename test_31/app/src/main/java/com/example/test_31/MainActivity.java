package com.example.test_31;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(((EditText)findViewById(R.id.birthday)).getText().toString())){
                    Toast.makeText(MainActivity.this,"请输入您的阳历生日，否则不能计算！", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                String birthday = ((EditText)findViewById(R.id.birthday)).getText().toString();
                Info info = new Info();
                info.setBirthday(birthday);
                
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", info);
                
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}