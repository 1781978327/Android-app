package com.example.test_62;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class WebClient extends AppCompatActivity {
    private static final String SERVER_URL = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
    private static final String PACE = "http://WebXml.com.cn/";
    private static final String W_NAME = "getMobileCodeInfo";

    private EditText etPhone;
    private Button btnSearch;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_client);
        
        etPhone = findViewById(R.id.etphone);
        btnSearch = findViewById(R.id.btnsearch);
        tvInfo = findViewById(R.id.tvinfo);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = etPhone.getText().toString().trim();
                if (phoneNum.length() == 0) {
                    Toast.makeText(WebClient.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phoneNum.length() != 11) {
                    Toast.makeText(WebClient.this, "请输入11位手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                getMobileInfo(phoneNum);
            }
        });
    }

    private void getMobileInfo(String phoneNum) {
        btnSearch.setEnabled(false);
        tvInfo.setText("正在查询...");
        
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String result = "";
                HttpTransportSE httpSe = new HttpTransportSE(SERVER_URL);
                httpSe.debug = true;
                
                SoapObject soapObject = new SoapObject(PACE, W_NAME);
                soapObject.addProperty("mobileCode", params[0]);
                soapObject.addProperty("userID", "");
                
                SoapSerializationEnvelope serializa = new SoapSerializationEnvelope(SoapEnvelope.VER10);
                serializa.setOutputSoapObject(soapObject);
                serializa.dotNet = true;
                
                try {
                    httpSe.call(PACE + W_NAME, serializa);
                    if (serializa.getResponse() != null) {
                        SoapObject response = (SoapObject) serializa.bodyIn;
                        result = response.getProperty("getMobileCodeInfoResult").toString();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    result = "解析错误：" + e.getMessage();
                } catch (SoapFault e) {
                    e.printStackTrace();
                    result = "SOAP错误：" + e.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                    result = "网络错误：" + e.getMessage();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                btnSearch.setEnabled(true);
                if (result.isEmpty()) {
                    tvInfo.setText("查询失败，请重试");
                } else {
                    tvInfo.setText(result);
                }
            }
        }.execute(phoneNum);
    }
} 