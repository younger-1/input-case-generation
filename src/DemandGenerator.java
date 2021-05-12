import java.util.*;

public class DemandGenerator {
    private static Random r = new Random();

    private static List<String> targetTypeList;
    private static List<String> demandTypeList;
    private static List<String> priorityList;
    private static List<String> resolutionList;
    private static List<String> loadTypeList;
    private static List<Date> dateList;

    private int demandNumber;
    // private int satelliteNumber;
    private boolean isChina;
    private double areaTargetRate;
    private double urgentRate;
    // private int density;

    public DemandGenerator(int demandNumber, /* int satelliteNumber, */ boolean isChina, double areaTargetRate,
            double urgentRate/* , int density */) {
        this.setDemandNumber(demandNumber);
        // this.setSatelliteNumber(satelliteNumber);
        this.setChina(isChina);
        this.setAreaTargetRate(areaTargetRate);
        this.setUrgentRate(urgentRate);
        // this.setDensity(density);
    }

    /**
     * @return the urgentRate
     */
    public double getUrgentRate() {
        return urgentRate;
    }

    /**
     * @param urgentRate the urgentRate to set
     */
    public void setUrgentRate(double urgentRate) {
        this.urgentRate = urgentRate;
    }

    /**
     * @return the areaTargetRate
     */
    public double getAreaTargetRate() {
        return areaTargetRate;
    }

    /**
     * @param areaTargetRate the areaTargetRate to set
     */
    public void setAreaTargetRate(double areaTargetRate) {
        this.areaTargetRate = areaTargetRate;
    }

    /**
     * @return the isChina
     */
    public boolean isChina() {
        return isChina;
    }

    /**
     * @param isChina the isChina to set
     */
    public void setChina(boolean isChina) {
        this.isChina = isChina;
    }

    /**
     * @return the demandNumber
     */
    public int getDemandNumber() {
        return demandNumber;
    }

    /**
     * @param demandNumber the demandNumber to set
     */
    public void setDemandNumber(int demandNumber) {
        this.demandNumber = demandNumber;
    }

    /* 
    public int getSatelliteNumber() {
        return satelliteNumber;
    }
    
    public void setSatelliteNumber(int satelliteNumber) {
        this.satelliteNumber = satelliteNumber;
    }
     */

    /* 
    public int getDensity() {
        return density;
    }
    
    public void setDensity(int density) {
        this.density = density;
    }
    */

    public List<Demand> generate() {
        ArrayList<Demand> allDemands = new ArrayList<>();

        for (int i = 0; i < this.demandNumber; i++) {
            Demand d = new Demand();
            d.setId(Integer.valueOf(i).toString());
            d.settarget("target-" + i);
            d.settargetPosition(pickRandomPosition(isChina ? true : false));
            populateDemand(d);
            allDemands.add(d);
        }
        return allDemands;
    }

    private Vector2d pickRandomPosition(boolean isChina) {
        return Tool.pickRandomPosition(isChina);
    }

    private void populateDemand(Demand d) {
        d.setDemandType(pickRandomValue(demandTypeList));
        d.setFinishTime(pickRandomValue(dateList));
        d.setLoadType(pickRandomValue(loadTypeList));
        d.setPriority(pickRandomValue(priorityList));
        d.setResolution(pickRandomValue(resolutionList));
    }

    private <T> T pickRandomValue(List<T> list) {
        return list.get(r.nextInt(list.size()));
    }
}
