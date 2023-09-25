package com.celi.cii.jimu.func;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;
import com.googlecode.aviator.runtime.type.AviatorType;

import java.util.Map;

/**
 * @author jiangshengjun
 * @date 2021/12/20
 * @desc 用户获取jsonArray数据
 */
public class JsonArrayFunction extends AbstractVariadicFunction {

    /**
     * @param env 系统变量
     * @param args 列表对象 0 列表对象  1下表  2 分隔符，不需要传“” 3~... 需要获取的字段名称
     * @return
     */
    @Override
    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        // 数组的下表和索引，不可缺少
        AviatorObject arg1 = args[0];
        AviatorObject arg2 = args[1];
        String prefix = null;
        if (args[2].getValue(env) == null) {
            prefix = "";
        } else {
            prefix = String.valueOf(args[2].getValue(env));
        }
        String dataArray = (String) arg1.getValue(env);
        if (dataArray == null) {
            return null;
        }
        JSONArray jsonArray = JSON.parseArray(dataArray);
        if (arg2 != null) {
            // 不是数字类型
            if (!arg2.getAviatorType().name().equals(AviatorType.Long.name())) {
                return null;
            }
            Integer index = Integer.valueOf(String.valueOf(arg2.getValue(env)));
            if (jsonArray == null) {
                return null;
            }
            JSONObject jsonObject = jsonArray.getJSONObject(index);

            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < args.length - 3; i++) {
                AviatorObject arg = args[i + 3];
                String value = jsonObject.getString(arg.getValue(env).toString());
                if (value != null) {
                    stringBuffer.append(value);
                    if (i != args.length - 4 && stringBuffer.length() != 0) {
                        stringBuffer.append(prefix);
                    }
                }
            }
            return new AviatorString(stringBuffer.toString());
        }
        return null;
    }


    @Override
    public String getName() {
        return "jsonArray";
    }
}
