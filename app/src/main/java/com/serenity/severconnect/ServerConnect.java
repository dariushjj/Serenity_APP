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
    public static final int SEARCH = 1;
    public static final int SONG = 2;
    public static final int LRC = 3;
    public static final int PIC = 4;
    public static final int URL = 5;
    public String usefulInfo;

    /**
     * 获得查找链接
     * @param keyword 查询关键字
     * @param id 歌曲id
     * @param type 返回的url类型
     *             SEARCH：返回查找链接，要求输入keyword
     *             SONG：返回歌曲信息，要求输入id
     *             LRC：返回歌曲歌词，要求输入id
     *             ALBUM：返回200*200歌曲图片，要求输入id
     *             URL：返回播放音乐链接，要求输入id
     * @return 提交的api链接
     */
    private String backUrl(String keyword, String id, int type){
        String url = path;
        switch (type){
            case SEARCH: url += "search?keyword=" + keyword + "&type=song&format=1";break;
            case SONG: url += "song?id=" + id + "&format=1";break;
            case LRC: url += "lrc?id=" + id;break;
            case PIC: url += "pic?id=" + id;break;
            case URL: url += "url?id=" + id + "&quality=192";break;
            default: break;
        }
        return url;
    }

    /**
     * 根据关键字查询歌曲信息（目前默认30个）
     * @param keyword 参数关键字，可根据关键字查找获得歌曲的信息
     * @param id 参数id，每个id对应一首歌曲
     * @param type 返回的url类型
     *             SEARCH：返回查找查找结果，要求输入keyword
     *             SONG：返回歌曲信息，要求输入id
     *             LRC：返回歌曲歌词，要求输入id
     *             ALBUM：返回200*200歌曲图片，要求输入id
     *             URL：返回播放音乐链接，要求输入id
     */
    public void search(final String keyword,final String id, final int type){
        switch (type){
            case SEARCH:
                getInfoFromWeb(keyword,id,type);
                break;
            case SONG:
                getInfoFromWeb(keyword,id,type);
                break;
            case LRC:
                break;
            case PIC:
                break;
            case URL:
                usefulInfo = backUrl("",id,type);
                break;
            default:
                break;
        }
    }

    /**
     * 解析查找的json字符串
     * @param jsonDataSong json串
     */
    private void parseJsonSong(String jsonDataSong){
        try {
            JSONObject jsonObject = new JSONObject(jsonDataSong);
            JSONArray data = jsonObject.getJSONArray("data");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.length(); i++){
                JSONObject song = data.getJSONObject(i);
                String singer = song.getString("singer");
                String name = song.getString("name");
                String id = song.getString("id");
                String time = song.getString("time");
                String pic = song.getString("pic");
                String lrc = song.getString("lrc");
                String url = song.getString("url");
                sb.append("name:").append(name).append( "singer:").append(singer)
                        .append(" id:").append(id).append("\r\n");
                Log.d(TAG, "singer: " + singer);
                Log.d(TAG, "id: " + id);
                Log.d(TAG, "time: " + time);
                Log.d(TAG, "name: " + name);
                Log.d(TAG, "pic: " + pic);
                Log.d(TAG, "lrc: " + lrc);
                Log.d(TAG, "url: " + url);
            }
            usefulInfo = sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 查找歌曲信息的解析
     * @param keyword 由上层传入
     * @param id 由上层传入
     * @param type 由上层传入
     */
    private void getInfoFromWeb(final String keyword, final String id, final int type){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL(backUrl(keyword,id,type));
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
        });
    }
}
