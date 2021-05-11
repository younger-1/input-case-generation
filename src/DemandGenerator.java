import java.util.*;

public class DemandGenerator {
    private List<String> targetTypeList;
    private List<String> demandTypeList;
    private List<String> priorityList;
    private List<String> resolutionList;
    private List<String> loadTypeList;

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
            allDemands.add(d);
        }
        return allDemands;
    }
}
