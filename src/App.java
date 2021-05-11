import java.util.*;

public class App {
    static int startSceneNumber = 1;
    static int endSceneNumber = 10;

    public static void main(String[] args) throws Exception {
        Random r = new Random();
        int sceneNumber = startSceneNumber + r.nextInt(endSceneNumber - startSceneNumber);
        int demandNumber = Integer.parseInt(args[0]);
        int satelliteNumber = Integer.parseInt(args[1]);
        boolean isChina = Boolean.parseBoolean(args[2]);
        double areaTargetRate = Double.parseDouble(args[3]);
        double urgentRate = Double.parseDouble(args[4]);
        // int density = Integer.parseInt(args[3]);

        DemandGenerator dg = new DemandGenerator(demandNumber, /* satelliteNumber, */ isChina, areaTargetRate,
                urgentRate /* , density */);
        List<Demand> demandList = dg.generate();
    }

}
