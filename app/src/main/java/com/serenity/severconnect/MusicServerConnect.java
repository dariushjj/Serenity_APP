package com.serenity.severconnect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*
网络数据处理类：
调用网易云api，获取网络歌曲的内容
 */
public class MusicServerConnect {
    private static final String TAG = "MusicServerConnect";
    private String path = "https://v1.itooi.cn/netease/";
    public static final int SEARCH = 1;
    public static final int SONG = 2;
    public static final int LRC = 3;
    public static final int PIC = 4;
    public static final int URL = 5;
    public String usefulInfo;
    public Bitmap picture;
    public List<String> lrcTime = new ArrayList<>();
    public List<String> lrcSentence = new ArrayList<>();

    /**
     * 获得url
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
            case URL: url += "url?id=" + id;break;
            default: break;
        }
        return url;
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
     * 生成信息
     * @param keyword 参数关键字，可根据关键字查找获得歌曲的信息
     * @param id 参数id，每个id对应一首歌曲
     * @param type 返回的url类型
     *             SEARCH：返回查找查找结果，要求输入keyword
     *             SONG：返回歌曲信息，要求输入id
     *             LRC：返回歌曲歌词，要求输入id
     *             ALBUM：返回200*200歌曲图片，要求输入id
     *             URL：返回播放音乐链接，要求输入id
     */
    public void init(final String keyword, final String id, final int type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                StringBuilder sb;
                String line;
                try {
                    URL url = new URL(backUrl(keyword, id, type));
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    switch (type) {
                        case SEARCH:
                        case SONG:
                            reader = new BufferedReader(new InputStreamReader(in));
                            sb = new StringBuilder();
                            while ((line = reader.readLine()) != null) {
                                sb.append(line).append("\n\r");
                            }
                            parseJsonSong(sb.toString());
                            break;
                        case LRC:
                            reader = new BufferedReader(new InputStreamReader(in));
                            sb = new StringBuilder();
                            while ((line = reader.readLine()) != null) {
                                sb.append(line).append("\n\r");
                            }
                            setLrc(sb.toString());
                            usefulInfo = sb.toString();
                            break;
                        case PIC:
                            picture = BitmapFactory.decodeStream(in);
                            in.close();
                            break;
                        case URL:
                            usefulInfo = backUrl(keyword, id, type);
                        default:
                            break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        try {
                            connection.getInputStream().close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    /**
     * 将歌词文件分为时间和内容
     * @param lrc
     */
    private void setLrc(String lrc){
        String[] allLrc = lrc.split("\n\r");
        for (String s: allLrc){
            String time = s.substring(0,s.indexOf("]") + 1);
            String sentence = s.substring(s.indexOf("]") + 1, s.length());
            lrcTime.add(time);
            lrcSentence.add(sentence);
        }
    }
}
