package MVCFramework.view;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.Constants.NEW_LINE;

public class ViewEngineImpl implements ViewEngine {
    private static final String LOOP_REGEX_PATTERN = "foreach\\{\\{([a-zA-Z][a-zA-Z0-9]*) : %s\\}\\}(.*)\\{\\{foreach\\}\\}";

    @Override
    public String getHtml(String template, Object model) {
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = null;
            try {
                fieldValue = field.get(model);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            String loopRegexPattern = String.format(LOOP_REGEX_PATTERN, fieldName);
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
                    parsedLoop = new StringBuilder(getHtml(parsedLoop.toString(), value, loopVariableName));
                }
            }


            String ifPattern = String.format("if\\{\\{%s\\}\\}", fieldName);
            String fieldPattern = String.format("\\{\\{%s\\}\\}", fieldName);
            template = template.replaceAll(fieldPattern, fieldValue.toString());
        }

        return template;
    }

    private String getHtml(String template, Object model, String fieldName) {
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(model);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            String loopRegexPattern = String.format(LOOP_REGEX_PATTERN, fieldName);
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
                    parsedLoop = new StringBuilder(getHtml(parsedLoop.toString(), value, loopVariableName));
                }
            }


            String ifPattern = String.format("if\\{\\{%s\\}\\}", fieldName);
            String fieldPattern = String.format("\\{\\{%s\\}\\}", fieldName);
            template = template.replaceAll(fieldPattern, fieldName);
        }

        return template;
    }
}
