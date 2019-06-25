package com.serenity.severconnect;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
网络数据处理类：
调用网易云api，获取网络歌曲的内容
 */
public class ServerConnect {
    private static final String TAG = "ServerConnect";
    private String path = "https://v1.itooi.cn/netease/";

    /**
     * 获得查找链接
     * @param keyword 查询关键字
     * @return提交的api链接
     */
    private String searchUrl(String keyword){
        return (path + "search?keyword=" + keyword + "&type=song&format=1");
    }

    /**
     * 根据关键字查询歌曲信息（目前默认30个）
     * @param keyword 关键字
     */
    public void search(final String keyword){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL(searchUrl(keyword));
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        sb.append(line);
                    }
                    parseJsonSong(sb.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (reader != null){
                        try {
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    /**
     * 解析查找的json字符串
     * @param jsonDataSong json串
     */
    private void parseJsonSong(String jsonDataSong){
        try {
            JSONObject jsonObject = new JSONObject(jsonDataSong);
            JSONArray data = jsonObject.getJSONArray("data");
            for (int i = 0; i < data.length(); i++){
                JSONObject song = data.getJSONObject(i);
                String singer = song.getString("singer");
                String name = song.getString("name");
                Log.d(TAG, "singer: " + singer);
                Log.d(TAG, "name: " + name);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
