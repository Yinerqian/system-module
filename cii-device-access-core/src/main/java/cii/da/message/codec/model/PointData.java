package cii.da.message.codec.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.concurrent.Delayed;

/**
 * 二级发过来的数据结构
 */
@Data
public class PointData extends PointBase implements Cloneable,Comparable<PointData> {

    private String code;

    private String comment;

    private String convertNo;

    private String name;

    private Timestamp ts;

    private String pointId;

    private String valueType;

    private String value;

    private String unit;

    private String location;

    private String groupId;

    private String region;

    private String label;

    private String k1;

    private String k2;

    private String k3;

    private String k4;

    private String k5;

    private String uuid;

    public PointData() {
    }

    public PointData(String code, String comment, String convertNo, String name, Timestamp ts, String pointId, String valueType, String value, String unit, String location, String groupId, String region, String label, String k1, String k2, String k3, String k4, String k5) {
        this.code = code;
        this.comment = comment;
        this.convertNo = convertNo;
        this.name = name;
        this.ts = ts;
        this.pointId = pointId;
        this.valueType = valueType;
        this.value = value;
        this.unit = unit;
        this.location = location;
        this.groupId = groupId;
        this.region = region;
        this.label = label;
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
        this.k4 = k4;
        this.k5 = k5;
    }

    @Override
    public PointData clone() {
        try {
            PointData clone = (PointData) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();}
    }

    @Override
    public int compareTo(PointData o) {
        return (int)(o.getTs().getTime()-this.getTs().getTime());
    }
}
