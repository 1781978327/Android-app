package com.example.ui_designal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // 启用边缘到边缘布局
        setContentView(R.layout.activity_main);

        // 设置窗口插入监听器
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 初始化按钮和复选框
        final Button button = findViewById(R.id.button1);
        CheckBox checkbox = findViewById(R.id.checkBox1);
        
        // 设置按钮为禁用状态
        button.setEnabled(false);

        // 按钮点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "单击了按钮！", Toast.LENGTH_SHORT).show();
            }
        });

        // 添加复选框状态变化监听
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                button.setEnabled(isChecked);  // 根据复选框状态设置按钮可用性
            }
        });

        // 初始化ListView
        ListView listView = findViewById(R.id.listView1);
        
        // 定义图片资源
        int[] imageId = new int[] { 
            R.drawable.img01, R.drawable.img02,
            R.drawable.img03, R.drawable.img04,
            R.drawable.img05, R.drawable.img06,
            R.drawable.img07, R.drawable.img08
        };
        
        // 定义文本标题
        String[] title = new String[] { 
            "新建", "安全", "系统", "上网", 
            "邮件", "音乐", "视频", "游戏"
        };
        
        // 创建List集合
        List<Map<String, Object>> listItems = new ArrayList<>();
        
        // 向List集合中添加项
        for (int i = 0; i < imageId.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            listItems.add(map);
        }
        
        // 创建SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(
            this, 
            listItems,
            R.layout.itmes,
            new String[] { "title", "image" },
            new int[] { R.id.title, R.id.image }
        );
        
        // 为ListView设置适配器
        listView.setAdapter(adapter);
    }
}