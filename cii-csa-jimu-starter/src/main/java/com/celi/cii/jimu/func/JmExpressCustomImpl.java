package com.celi.cii.jimu.func;

import com.googlecode.aviator.AviatorEvaluatorInstance;
import org.jeecg.modules.jmreport.desreport.express.IJmExpressCustom;
import org.springframework.stereotype.Component;

/**
 * @author jiangshengjun
 * @date 2021/12/20
 * @desc 注册积木报表方法
 */
@Component
public class JmExpressCustomImpl implements IJmExpressCustom {

    @Override
    public void addFunction(AviatorEvaluatorInstance aviatorEvaluatorInstance) {
        aviatorEvaluatorInstance.addFunction(new JsonArrayFunction());
        aviatorEvaluatorInstance.addFunction(new StringFormatFunction());
    }
}
