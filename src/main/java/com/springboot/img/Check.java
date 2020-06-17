package com.springboot.img;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;


public class Check {
    private static final String POST_URL = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic?access_token=" + AuthService.getAuth();

    public static String checkFile(String path) throws URISyntaxException, IOException {
        System.out.println("1");
        File file = new File(path);
        if (!file.exists()) {
            throw new NullPointerException("图片不存在");
        }
        String image = BaseImg64.getImageStrFromPath(path);
        String param = "image=" + image;
        return post(param);
    }

    /**
     * @param url 图片url
     * @return 识别结果，为json格式
     */
    public static String checkUrl(String url) throws IOException, URISyntaxException {
        String param = "url=" + url;
        System.out.println("2");
        return post(param);

    }

    /**
     * 通过传递参数：url和image进行文字识别
     *
     * @param param 区分是url还是image识别
     * @return 识别结果
     * @throws URISyntaxException URI打开异常
     * @throws IOException        IO流异常
     */
    private static String post(String param) throws URISyntaxException, IOException {
        //开始搭建post请求
        System.out.println("3");
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost();
        URI url = new URI(POST_URL);
        post.setURI(url);
        //设置请求头，请求头必须为application/x-www-form-urlencoded，因为是传递一个很长的字符串，不能分段发送
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        StringEntity entity = new StringEntity(param);
        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);
        //System.out.println(response.toString());
        if (response.getStatusLine().getStatusCode() == 200) {
            String str;
            try {
                /*读取服务器返回过来的json字符串数据*/
                str = EntityUtils.toString(response.getEntity());
                JSONObject obj = new JSONObject(str);
                JSONArray a= (JSONArray) obj.get("words_result");
                String content="";
                for(int i=0;i<a.length();i++){
                    JSONObject jsonobj= (JSONObject) a.get(i);
                    content+=jsonobj.get("words");
                }
                System.out.println(content);
                return content;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public  String test(String path) {
       // String path = "D:\\timg.jpg";
        try {
            long now = System.currentTimeMillis();
            String ans= checkFile(path);
            System.out.println("-----------------");
           // String ans=checkUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=08c05c0e8444ebf8797c6c6db890bc4f/fc1f4134970a304e46bfc5f7d2c8a786c9175c19.jpg");
            System.out.println("耗时：" + (System.currentTimeMillis() - now) / 1000 + "s");
            return ans;
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}