package com.example.test_2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher.ViewFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    private int[] imageId = new int[] { 
        R.drawable.img01, R.drawable.img02,
        R.drawable.img03, R.drawable.img04, R.drawable.img05,
        R.drawable.img06, R.drawable.img07, R.drawable.img08,
        R.drawable.img09, R.drawable.img10, R.drawable.img11,
        R.drawable.img12 
    };

    final String[] filename = new String[] { 
        "img01.jpg", "img02.jpg", "img03.jpg", "img04.jpg", 
        "img05.jpg", "img06.jpg", "img07.jpg", "img08.jpg",
        "img09.jpg", "img10.jpg", "img11.jpg", "img12.jpg" 
    };

    private ImageSwitcher imageSwitcher;
    private ProgressBar progressBar;
    private Button button;
    private ProgressDialog progress;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (Button) findViewById(R.id.button);
        
        // 初始化 ImageSwitcher
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
            android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
            android.R.anim.fade_out));
        imageSwitcher.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                setupGridView();
            }
        });

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.advise);
                builder.setTitle("用户登录: ");
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.login, null);
                builder.setView(view);
                builder.setPositiveButton("登录", null);
                builder.setNegativeButton("退出", null);
                builder.create().show();
            }
        });
    }
    
    private void setupGridView() {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        
        for (int i = 0; i < imageId.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imageId[i]);
            map.put("title", filename[i]);
            listItems.add(map);
        }
        
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,
            R.layout.items, new String[] { "title", "image" }, 
            new int[] { R.id.title, R.id.image });
            
        GridView gridview = (GridView) findViewById(R.id.gridView1);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MainActivity", "Item clicked at position: " + position);
                imageSwitcher.setImageResource(imageId[position]);
                imageSwitcher.setVisibility(View.VISIBLE);
            }
        });
    }

    public void download(View view) {
        progress = new ProgressDialog(this);
        progress.setMessage("Downloading Music");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();
        
        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;
                while(jumpTime < totalProgressTime) {
                    try {
                        sleep(200);
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }
}