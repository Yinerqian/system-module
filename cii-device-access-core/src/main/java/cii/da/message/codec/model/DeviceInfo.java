package cii.da.message.codec.model;


import java.util.List;

/**
 * 设备信息
 *
 * @author MFine
 * @date 2021/08/24
 */
public class DeviceInfo {
    /**
     * 设备id
     */
    String deviceId;
    /**
     * 设备名字
     */
    String name;
    /**
     * 设备功能
     */
    List<DeviceFunction> deviceFunction;
    /**
     * 设备属性
     */
    List<Point> deviceProperties;
    /**
     * 设备事件
     */
    List<DeviceEvent> deviceEvent;

    public DeviceInfo() {
    }

    public DeviceInfo(String deviceId, String name, List<DeviceFunction> deviceFunction, List<Point> deviceProperties, List<DeviceEvent> deviceEvent) {
        this.deviceId = deviceId;
        this.name = name;
        this.deviceFunction = deviceFunction;
        this.deviceProperties = deviceProperties;
        this.deviceEvent = deviceEvent;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeviceFunction> getDeviceFunction() {
        return deviceFunction;
    }

    public void setDeviceFunction(List<DeviceFunction> deviceFunction) {
        this.deviceFunction = deviceFunction;
    }

    public List<Point> getDeviceProperties() {
        return deviceProperties;
    }

    public void setDeviceProperties(List<Point> deviceProperties) {
        this.deviceProperties = deviceProperties;
    }

    public List<DeviceEvent> getDeviceEvent() {
        return deviceEvent;
    }

    public void setDeviceEvent(List<DeviceEvent> deviceEvent) {
        this.deviceEvent = deviceEvent;
    }
}
