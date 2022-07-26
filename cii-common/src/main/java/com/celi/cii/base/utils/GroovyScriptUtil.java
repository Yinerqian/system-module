package com.celi.cii.base.utils;

import com.celi.cii.base.entity.function.CiiFunction;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;

public class GroovyScriptUtil {

    private final static ScriptEngineManager factory = new ScriptEngineManager();

    private static ScriptEngine engine = factory.getEngineByName("groovy");

    private static CiiFunction ciiFunction;

    /**
     * 执行 Groovy 脚本
     */
    public static Object runScript(String script) throws Throwable {
        try {
            // 绑定函数
            Bindings bindings = new SimpleBindings();
            bindings.put(CiiFunction.functionName, ciiFunction);

            return engineEval(script, bindings);
        } catch (Exception e) {
            if (e.getCause() != null && e.getCause().getCause() != null) {
                throw e.getCause().getCause();
            } else {
                throw e;
            }
        }

    }

    /**
     * 绑定自定义函数，执行 Groovy 脚本
     * 其中自定义函数可以调用 Java 对象
     */
    public static Object engineEval(String script, Bindings bindings) throws Throwable {
        try {
            return engine.eval(script, bindings);
        } catch (Exception e) {
            if (e.getCause() != null && e.getCause().getCause() != null) {
                throw e.getCause().getCause();
            } else {
                throw e;
            }
        }
    }
}
