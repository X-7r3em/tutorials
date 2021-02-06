package MVCFramework.view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.Constants.NEW_LINE;

public class ViewEngineImpl implements ViewEngine {

    @Override
    public String getHtml(String template, Object model) {
        if (model == null) {
            return template;
        }

        if (isPrimitive(model)) {
            throw new IllegalArgumentException("Improper object passed as argument!");
        }

        Method[] methods = Arrays.stream(model.getClass().getMethods())
                .filter(this::isGetter)
                .toArray(Method[]::new);

        for (Method method : methods) {
            String fieldName = getFieldName(method.getName());
            Object fieldValue = null;
            try {
                fieldValue = method.invoke(model);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

            template = parseVariable(template, fieldName, fieldValue);
            template = parseIf(template, fieldName, fieldValue);
            template = parseLoop(template, fieldName, fieldValue);
        }

        return template;
    }

    private String parseVariable(String template, String fieldName, Object fieldValue) {

        String fieldPattern = String.format("(?<!if|foreach)\\{\\{%s\\}\\}", fieldName);
        return template.replaceAll(fieldPattern, fieldValue != null ? fieldValue.toString() : "null");
    }

    private String parseIf(String template, String fieldName, Object fieldValue) {
        String ifPatternRegex = String.format("if\\{\\{(%s)\\}\\}(.*?)\\{\\{if\\}\\}", fieldName);
        Pattern ifPattern = Pattern.compile(ifPatternRegex, Pattern.DOTALL);
        Matcher ifMatcher = ifPattern.matcher(template);
        while (ifMatcher.find()) {
            String match = ifMatcher.group();
            String loopContent = ifMatcher.group(2);

            if ((boolean) fieldValue) {
                template = template.replace(match, loopContent);
            } else {
                template = template.replace(match, "");
            }
        }

        return template;
    }

    private String parseLoop(String template, String fieldName, Object fieldValue) {
        String loopRegexPattern = String.format("foreach\\{\\{([a-zA-Z][a-zA-Z0-9]*?) : %s\\}\\}(.*)\\{\\{foreach\\}\\}", fieldName);
        Pattern loopPattern = Pattern.compile(loopRegexPattern, Pattern.DOTALL);
        Matcher loopMatcher = loopPattern.matcher(template);
        while (loopMatcher.find()) {
            String match = loopMatcher.group();
            String loopContent = loopMatcher.group(2);
            String loopVariableName = loopMatcher.group(1);
            StringBuilder parsedLoop = new StringBuilder();
            Collection values = ((Collection) fieldValue);
            for (Object value : values) {
                parsedLoop.append(loopContent).append(NEW_LINE);
                if (isPrimitive(value)) {
                    parsedLoop = new StringBuilder(getHtml(parsedLoop.toString(), value, loopVariableName));
                } else {
                    //Can extend logic for nested objects
                }
            }

            template = template.replace(match, parsedLoop.toString());
        }

        return template;
    }

    private String getHtml(String template, Object value, String fieldName) {
        String fieldPattern = String.format("\\{\\{%s\\}\\}", fieldName);
        template = template.replaceAll(fieldPattern, value.toString());
        return template;
    }

    private boolean isGetter(Method method) {
        if (Modifier.isPublic(method.getModifiers()) &&
                method.getParameterTypes().length == 0) {
            return method.getName().matches("^(get|is|has)[A-Z].*") &&
                    !method.getReturnType().equals(void.class);
        }

        return false;
    }

    private String getFieldName(String getterName) {
        if (getterName.startsWith("get")) {
            getterName =  getterName.replace("get", "");
        }

        return getterName.substring(0, 1).toLowerCase() + getterName.substring(1);
    }

    private boolean isPrimitive(Object object) {
        String className = object.getClass().getName();
        boolean isPrimitive = false;
        if ("java.lang.Byte".equals(className)) {
            isPrimitive = true;
        } else if ("java.lang.Short".equals(className)) {
            isPrimitive = true;
        } else if ("java.lang.Integer".equals(className)) {
            isPrimitive = true;
        } else if ("java.lang.Long".equals(className)) {
            isPrimitive = true;
        } else if ("java.lang.Double".equals(className)) {
            isPrimitive = true;
        } else if ("java.lang.Float".equals(className)) {
            isPrimitive = true;
        } else if ("java.lang.Character".equals(className)) {
            isPrimitive = true;
        } else if ("java.lang.Boolean".equals(className)) {
            isPrimitive = true;
        }

        return isPrimitive;
    }
}
