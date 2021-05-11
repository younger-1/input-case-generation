import java.io.*;
import java.util.*;

public class Tool {

    public static List<Vector2d> chinaBorder = null;

    public static void main(String[] args) throws IOException {
        System.out.println(isInChina(new Vector2d(116.3683244, 39.915085)));
    }

    public static void initChinaBorder() {
        chinaBorder = new ArrayList<>();
        // https://blog.csdn.net/weixin_43108539/article/details/89885376
        try {
            FileReader fr = new FileReader("data/china-border.csv");
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] lonAndLat = line.split(",");
                try {
                    double lon = Double.parseDouble(lonAndLat[0]);
                    double lat = Double.parseDouble(lonAndLat[1]);
                    chinaBorder.add(new Vector2d(lon, lat));
                } catch (NumberFormatException e) {
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean isInChina(Vector2d targetPos) {
        if (chinaBorder == null) {
            initChinaBorder();
        }
        List<Vector2d> vectors = new ArrayList<Vector2d>();
        int size = chinaBorder.size();
        for (int i = 0; i < size; i++) {
            Vector2d borderPosition = chinaBorder.get(i);
            vectors.add(borderPosition.minues(targetPos));
        }
        int first = 0;
        int second = 0;
        for (int i = 0; i < size - 1; i++) {
            first = second;
            second = vectors.get(i).crossProduct(vectors.get(i + 1)) > 0 ? 1 : -1;
            if (i > 0 && first * second < 0) {
                System.out.println(first);
                System.out.println(second);
                System.out.println(i);
                return false;
            }
        }
        return true;
    }
}

class Vector2d {
    double x;
    double y;

    Vector2d() {
        x = 0;
        y = 0;
    }

    Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d minues(Vector2d targetPos) {
        return new Vector2d(this.x - targetPos.x, this.y - targetPos.y);
    }

    public double crossProduct(Vector2d vec) {
        return x * vec.y - y * vec.x;
    }
}