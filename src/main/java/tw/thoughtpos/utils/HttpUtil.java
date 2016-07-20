package tw.thoughtpos.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tw.thoughtpos.domain.Receipt;
import tw.thoughtpos.domain.ShoppingItem;

public class HttpUtil {

    public static Receipt post(String postUrlString, List<String> input) throws Exception {
        JSONObject object = JSON.parseObject(getContent(input, postUrlString));
        JSONArray jsonArray = object.getJSONArray("shoppingItems");
        List<ShoppingItem> shoppingItems = JSON.parseArray(jsonArray.toJSONString(), ShoppingItem.class);
        return  new Receipt(shoppingItems);
    }

    private static String getContent(List<String> input, String postUrl) throws Exception {
        HttpURLConnection connection = getHttpURLConnection(postUrl);
        PrintWriter out = new PrintWriter(connection.getOutputStream());
        JSONArray jsonArray = new JSONArray();
        input.forEach(item -> jsonArray.add(item));
        out.print(jsonArray.toString());
        out.flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        String result = "";
        while ((line = in.readLine()) != null) {
            result += "\n" + line;
        }
        out.close();
        in.close();
        return result;
    }

    private static HttpURLConnection getHttpURLConnection(String postUrl) throws IOException {
        URL url = new URL(postUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.connect();
        return connection;
    }

}
