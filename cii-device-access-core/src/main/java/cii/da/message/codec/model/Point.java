package cii.da.message.codec.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 存到时序数据中的结构
 * @author MFine
 * @date 2021/08/23
 */

@Data
public class Point extends PointBase implements Cloneable, Serializable {

    /**
     * 时间戳
     */
    private Long ts;

    /**
     * 测点id
     */
    private String pointId;

    /**
     * 测点值
     */
    private Float value;

    //地区码
    private Integer locationId;

    //子地区码
    private Integer subLocationId;

    //分组id
    private String groupId;

    //标签
    private String label;

    //区域码
    private Integer regionId;


    /**
     * 扩展属性
     */
    private String k1;
    private String k2;
    private String k3;

    public Point() {
    }

    public Point(Long ts, String pointId, Float value, Integer locationId, Integer subLocationId,
                 String groupId, String label, Integer regionId, String k1, String k2, String k3) {
        this.ts = ts;
        this.pointId = pointId;
        this.value = value;
        this.locationId = locationId;
        this.subLocationId = subLocationId;
        this.groupId = groupId;
        this.label = label;
        this.regionId = regionId;
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
    }


    @Override
    public Point clone() {
        try {
            return (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
