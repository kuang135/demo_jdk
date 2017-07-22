package com.demo.jdk8;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * java8新一代的javascript引擎--替代老旧，缓慢的Rhino，符合 ECMAScript-262 5.1 版语言规范
 *      jjs test.js
 */
public class J6_Nashorn {

    /**
     * http://docs.oracle.com/javase/8/docs/technotes/guides/scripting/nashorn/api.html
     */
    @Test
    public void test() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println(engine.getClass().getName());
        //java中调js
        String script = "(function sum(a,b){return a+b;})(12,23)";
        System.out.println( "12 + 23 = " + engine.eval(script));
        //js中调java
        String js = "var BigDecimal = Java.type('java.math.BigDecimal');" +
                    "function calculate(amount, percentage) {" +
                    "   var result = new BigDecimal(amount).multiply(" +
                    "      new BigDecimal(percentage)).divide(" +
                    "         new BigDecimal(\"100\"), 2, BigDecimal.ROUND_HALF_EVEN);" +
                    "   return result.toPlainString();" +
                    "}" +
                    "var result = calculate(568000000000000000023,13.9);" +
                    "print(result);";
        engine.eval(js);
    }

}
