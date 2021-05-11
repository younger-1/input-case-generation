import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Demand {
    private String id;
    private Date finishTime;
    private String target;
    private double[] targetPosition;
    private String targetType;
    private String demandType;
    private String priority;
    private String resolution;
    // @Depracated
    private String loadType;

    /**
     * @param id
     * @param finishTime
     * @param target
     * @param targetPosition
     * @param targetType
     * @param demandType
     * @param priority
     * @param resolution
     * @param loadType
     */
    public Demand(String id, Date finishTime, String target, double[] targetPosition, String targetType,
            String demandType, String priority, String resolution, String loadType) {
        this.id = id;
        this.finishTime = finishTime;
        this.target = target;
        this.targetPosition = targetPosition;
        this.targetType = targetType;
        this.demandType = demandType;
        this.priority = priority;
        this.resolution = resolution;
        this.loadType = loadType;
    }

    public Demand() {
    }

    public static void main(String[] args) {
        Demand d = new Demand();
        d.setDemandType("打击评估");
        d.setLoadType("光学");
        d.settarget("台湾");
        String s = JSON.toJSONString(d);
        System.out.println(s);
        JSONObject jb = JSON.parseObject(s);
        System.out.println(jb);

        Demand dd = new Demand();
        dd.setDemandType("打击评估");
        dd.setLoadType("光学");
        dd.settarget("日本");
        List<Demand> dl = new ArrayList<Demand>();
        dl.add(d);
        dl.add(dd);
        s = JSON.toJSONString(dl);
        System.out.println(s);
        JSONArray ja = JSON.parseArray(s);
        System.out.println(ja);
    }

    /**
     * @return the loadType
     */
    public String getLoadType() {
        return loadType;
    }

    /**
     * @param loadType the loadType to set
     */
    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    /**
     * @return the resolution
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * @param resolution the resolution to set
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the demandType
     */
    public String getDemandType() {
        return demandType;
    }

    /**
     * @param demandType the demandType to set
     */
    public void setDemandType(String demandType) {
        this.demandType = demandType;
    }

    /**
     * @return the targetType
     */
    public String gettargetType() {
        return targetType;
    }

    /**
     * @param targetType the targetType to set
     */
    public void settargetType(String targetType) {
        this.targetType = targetType;
    }

    /**
     * @return the targetPosition
     */
    public double[] gettargetPosition() {
        return targetPosition;
    }

    /**
     * @param targetPosition the targetPosition to set
     */
    public void settargetPosition(double[] targetPosition) {
        this.targetPosition = targetPosition;
    }

    /**
     * @return the target
     */
    public String gettarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void settarget(String target) {
        this.target = target;
    }

    /**
     * @return the finishTime
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime the finishTime to set
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
