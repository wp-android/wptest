package com.example.wptest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Helper {
     List<String> stringList=new ArrayList<>();
    public  List<String> getLightTime(final String id, final int value,String ip){


                try {
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    OkHttpClient client=new OkHttpClient();
                    JSONObject json=new JSONObject();
                    json.put(id,value);
                    RequestBody requestBody=RequestBody.create(JSON,String.valueOf(json));
                    Request request=new Request.Builder()
                            .url("http://"+ip+"/transportservice/type/jason/action/GetTrafficLightConfigAction")
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                    String re=response.body().string();
                    JSONObject jsonObject=new JSONObject(re);
                    String res=jsonObject.getString("YellowTime");
                    String res1=jsonObject.getString("GreenTime");
                    String res2=jsonObject.getString("RedTime");
                    stringList.add(res);
                    stringList.add(res1);
                    stringList.add(res2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        return stringList;
    }
}
