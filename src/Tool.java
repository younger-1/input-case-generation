import java.io.*;
import java.util.*;

public class Tool {

    private static Random r = new Random();
    /* 
     * 东经北纬为正，西经南纬为负
     * 73°33′E至135°05′E 纬度范围：3°51′N至53°33′N
     */
    private static final double ChinaNorth = 53.55;
    private static final double ChinaSouth = 3.86;
    private static final double ChinaEast = 135.08;
    private static final double ChinaWest = 73.55;

    private static final double North = 90;
    private static final double South = -90;
    private static final double East = 180;
    private static final double West = -180;

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

    public static boolean isInChina_(Vector2d targetPos) {
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

    public static boolean isInChina(Vector2d targetPos) {
        return ChinaWest <= targetPos.x && targetPos.x <= ChinaEast && ChinaSouth <= targetPos.y
                && targetPos.y <= ChinaNorth;
    }

    public static Vector2d pickRandomPosition(boolean isChina) {
        double n = North, s = South, e = East, w = West;
        if (isChina) {
            n = ChinaNorth;
            s = ChinaSouth;
            e = ChinaEast;
            w = ChinaWest;
        }
        Vector2d vec = new Vector2d(w + (e - w) * r.nextDouble(), s + (n - s) * r.nextDouble());
        return vec;
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