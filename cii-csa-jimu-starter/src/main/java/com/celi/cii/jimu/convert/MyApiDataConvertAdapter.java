package com.celi.cii.jimu.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.celi.cii.common.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.modules.jmreport.desreport.render.handler.convert.ApiDataConvertAdapter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangshengjun
 * @date 2021/12/20
 */
@Component
public class MyApiDataConvertAdapter implements ApiDataConvertAdapter {

    /**
     * 返回list数据集
     * @param jsonObject 接口数据原始对象
     * @return
     */
    @Override
    public String getData(JSONObject jsonObject) {
        if (jsonObject != null && !jsonObject.getBoolean("successflag")) {
            throw new ServiceException(jsonObject.getString("message").toString());
        }
        if (jsonObject.containsKey("content")) {
            Object result = jsonObject.get("content");
            if (result == null || StringUtils.isBlank(result.toString())) {
                return "";
            }
            if (StringUtils.startsWith(result.toString(), "[")) {
                return result.toString();
            } else {
                List response = new ArrayList();
                response.add(result);
                return JSON.toJSONString(response);
            }
        } else {
            return "";
        }
    }

    /**
     * 返回总页数
     * @param jsonObject 接口数据原始对象
     * @return
     */
    @Override
    public String getTotal(JSONObject jsonObject) {
        Object total = jsonObject.get("total");
        Object pageNum = jsonObject.get("pageNum");
        if (total != null && pageNum != null) {
            return String.valueOf(Long.valueOf(total.toString()) / Integer.valueOf(pageNum.toString()));
        }
        return null;
    }

    /**
     * 返回总条数
     * @param jsonObject 接口数据原始对象
     * @return
     */
    @Override
    public String getCount(JSONObject jsonObject) {
        Object total = jsonObject.get("total");
        if (total != null) {
            return String.valueOf(total);
        }
        return null;
    }
}
