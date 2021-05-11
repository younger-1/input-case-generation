import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.*;

public class Demo {
    public static final String KEY = "4d1af9309e20a6f520e8bb81c7bc17f4";
    public static final String URL = "https://restapi.amap.com/v3/geocode/geo?address=";

    public static void main(String[] args) throws Exception {
        getChinaBorder();
        // getLocation();
    }

    public static void getLocation() throws Exception {
        String url = URL + URLEncoder.encode("长沙", "utf-8") + "&output=JSON" + "&key=" + KEY;

        URL url2 = new URL(url); // 把字符串转换为URL请求地址
        HttpURLConnection connection = (HttpURLConnection) url2.openConnection();// 打开连接
        connection.connect();// 连接会话
        // 获取输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {// 循环读取流
            sb.append(line);
        }
        br.close();// 关闭流
        connection.disconnect();// 断开连接

        JSONObject a = JSON.parseObject(sb.toString());
        JSONArray sddressArr = JSON.parseArray(a.get("geocodes").toString());
        JSONObject c = JSON.parseObject(sddressArr.get(0).toString());
        String location = c.get("location").toString();
        System.out.println(location);

    }

    public static void bigJson() throws Exception {
        JSONReader reader = new JSONReader(new FileReader("test/demo.json"));
        reader.startObject();
        while (reader.hasNext()) {
            String key = reader.readString();
            String value = reader.readString();
            System.out.println(key);
            System.out.println(value);
            // VO vo = reader.readObject(VO.class);
            // handle vo ...
        }
        reader.endObject();
        reader.close();
    }

    // 清空json对象中的某个数组
    public static void clearJsonArray() throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(
                "{\"1\": {\"name\":\"maple\",\"sex\":\"man\",\"childrens\":[{\"name\":\"草根\",\"sex\":\"man\",\"date\":\"2018-01-01\"},{\"name\":\"merry\",\"sex\":\"woman\",\"date\":\"2017-01-01\"},{\"name\":\"liming\",\"sex\":\"woman\",\"date\":\"2016-01-01\"}]}}");
        jsonObject.forEach((key, val) -> {
            JSONObject obj = (JSONObject) val;
            JSONArray array = obj.getJSONArray("childrens");
            array.clear();
        });
        System.out.println(jsonObject);
    }

    // 收集对象属性重新成一个数组
    public static void collectJsonArray() throws Exception {
        JSONArray jsonArray = JSONArray.parseArray(
                "[{\"1\": {\"name\":\"maple\",\"sex\":\"man\",\"childrens\":[{\"name\":\"草根\",\"sex\":\"man\",\"date\":\"2018-01-01\"},{\"name\":\"merry\",\"sex\":\"woman\",\"date\":\"2017-01-01\"},{\"name\":\"liming\",\"sex\":\"woman\",\"date\":\"2016-01-01\"}]}}]");
        System.out.println(jsonArray);
        jsonArray = jsonArray.stream().map(obj -> {
            JSONObject returnObj = new JSONObject();
            JSONObject jsonObj = (JSONObject) obj;
            jsonObj.forEach((key, val) -> {
                returnObj.put(key, ((JSONObject) val).getJSONArray("childrens"));
            });
            return returnObj;
        }).collect(Collectors.toCollection(JSONArray::new));
        System.out.println(jsonArray);
    }

    public static void getCity() throws Exception {
        JSONReader reader = new JSONReader(new FileReader("test/cs.json"));
        reader.startObject();
        while (reader.hasNext()) {
            String key = reader.readString();
            String value = reader.readString();
            if (key.equals("geocodes")) {
                JSONArray ja = JSON.parseArray(value);
                String city = ja.getJSONObject(0).getString("city");
                System.out.println(ja);
                System.out.println(city);
            }
        }
        reader.endObject();
        reader.close();
    }

    public static void getChinaBorder() throws Exception {
        String[] nodes = null;
        JSONReader reader = new JSONReader(new FileReader("test/china.json"));
        reader.startObject();
        while (reader.hasNext()) {
            String key = reader.readString();
            String value = reader.readString();
            if (key.equals("districts")) {
                JSONArray ja = JSON.parseArray(value);
                String border = ja.getJSONObject(0).getString("polyline");
                // System.out.println(border.substring(0, 100));
                String[] borders = border.split("\\|");
                // System.out.println(borders.length); // 1920, 153773
                Queue<int[]> queue = new PriorityQueue<int[]>((a, b) -> b[1] - a[1]);
                for (int i = 0; i < borders.length; i += 1) {
                    nodes = borders[i].split(";");
                    queue.add(new int[] { i, nodes.length });
                }
                nodes = borders[queue.peek()[0]].split(";");
            }
        }
        reader.endObject();
        reader.close();

        // while (!queue.isEmpty()) {
        //     System.out.print(queue.poll()[1] + " ");
        // }

        FileWriter fw = new FileWriter("data/china-border.csv");
        for (int i = 0; i < nodes.length; i += 500) {
            fw.write(nodes[i] + "\n");
        }
        fw.close();
    }
}
