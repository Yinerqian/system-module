package com.celi.cii.jimu.func;

import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author jiangshengjun
 * @date 2021/12/23
 */
public class StringFormatFunction extends AbstractVariadicFunction {

    @Override
    public String getName() {
        return "stringFormat";
    }

    @Override
    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        String formatStr = args[0].stringValue(env);
        if (StringUtils.isBlank(formatStr)) {
            return null;
        }
        Object[] params = new Object[args.length -1];
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < args.length - 1; i++) {
            params[i] = args[i + 1].getValue(env);
        }
        return new AviatorString(String.format(formatStr, params));
    }
}
