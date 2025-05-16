package com.example.test_32;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class HeadActivity extends Activity {
    public String[] city = new String[] { 
        "北京", "上海", "广州", "长春", "沈阳", "天津", 
        "西安", "杭州", "深圳", "南京", "洛阳"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head);
        
        GridView gridview = (GridView) findViewById(R.id.gridView1);
        
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv;
                if(convertView == null) {
                    tv = new TextView(HeadActivity.this);
                    tv.setPadding(5, 5, 5, 5);
                } else {
                    tv = (TextView)convertView;
                }
                tv.setText(city[position]);
                return tv;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public int getCount() {
                return city.length;
            }
        };

        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("city", city[position]);
                intent.putExtras(bundle);
                setResult(0x11, intent);
                finish();
            }
        });
    }
} 