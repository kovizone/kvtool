package com.kovizone.tool.spel;

import com.kovizone.tool.spel.exception.ExpressionParseException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:kovichen@163.com">KoviChen</a>
 * @version 1.0
 */
public class SpelUtil {

    public static void main(String[] args) {
        System.out.println(parse("test", new HashMap<String, Object>(1) {{
            put("test", "123");
        }}, String.class));
    }

    /**
     * 表达式解析器
     */
    private static ExpressionParser expressionParser = new SpelExpressionParser();

    /**
     * 解析表达式
     *
     * @param expressionString  表达式字符串
     * @param variableMap       变量集
     * @param desiredResultType 解析结果类
     * @return 解析结果
     * @throws ExpressionParseException 异常
     */
    public static <T> T parse(String expressionString, Map<String, Object> variableMap, Class<T> desiredResultType) {
        try {
            Expression expression = expressionParser.parseExpression(expressionString);
            return expression.getValue(evaluationContext(variableMap), desiredResultType);
        } catch (Exception e) {
            throw new ExpressionParseException(expressionString + ": " + e.getMessage());
        }
    }

    private static EvaluationContext evaluationContext(Map<String, Object> variableMap) {
        if (variableMap == null) {
            return new StandardEvaluationContext();
        }
        EvaluationContext evaluationContext;
        Object rootObject = variableMap.get("this");
        if (rootObject != null) {
            evaluationContext = new StandardEvaluationContext(rootObject);
            variableMap.remove("this");
        } else {
            evaluationContext = new StandardEvaluationContext();
        }
        variableMap.forEach(evaluationContext::setVariable);
        return evaluationContext;
    }
}
