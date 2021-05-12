import java.util.*;

public class DemandGenerator {
    private static Random r = new Random();
    private static final int MaxDayFromNow = 7;
    private static final int MaxHourFromNow = 12;

    private static List<String> targetTypeList;
    private static List<String> demandTypeList;
    private static List<String> priorityList;
    // ? Integer
    private static List<String> resolutionList;
    private static List<String> loadTypeList;

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
        initAllList();
    }

    private void initAllList() {
        targetTypeList = new ArrayList<>();
        targetTypeList.add("点目标");
        targetTypeList.add("区域目标");
        targetTypeList.add("移动目标");
        demandTypeList = new ArrayList<>();
        demandTypeList.add("态势普查");
        demandTypeList.add("战略详查");
        demandTypeList.add("搜索发现");
        priorityList = new ArrayList<>();
        priorityList.add("紧急");
        priorityList.add("战时");
        priorityList.add("普通");
        resolutionList = new ArrayList<>();
        resolutionList.add("5");
        resolutionList.add("10");
        resolutionList.add("15");
        loadTypeList = new ArrayList<>();
        loadTypeList.add("光学");
        loadTypeList.add("电子");
        loadTypeList.add("红外");
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
            d.setFinishTime(pickRandomFinishTime());
            populateDemand(d);
            allDemands.add(d);
        }
        return allDemands;
    }

    private Date pickRandomFinishTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + r.nextInt(MaxDayFromNow));
        c.set(Calendar.HOUR, c.get(Calendar.HOUR) + r.nextInt(MaxHourFromNow));
        return c.getTime();
    }

    private Vector2d pickRandomPosition(boolean isChina) {
        return Tool.pickRandomPosition(isChina);
    }

    private void populateDemand(Demand d) {
        d.setDemandType(pickRandomValue(demandTypeList));
        d.setLoadType(pickRandomValue(loadTypeList));
        d.setPriority(pickRandomValue(priorityList));
        d.setResolution(pickRandomValue(resolutionList));
        d.settargetType(pickRandomValue(targetTypeList));
    }

    private <T> T pickRandomValue(List<T> list) {
        return list.get(r.nextInt(list.size()));
    }
}
