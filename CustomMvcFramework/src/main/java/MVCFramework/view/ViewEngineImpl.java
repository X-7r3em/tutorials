package MVCFramework.view;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.lang.reflect.Field;

public class ViewEngineImpl implements ViewEngine {
    @Override
    public String getHtml(String template, Object model) {
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String fieldValue = "";
            try {
                fieldValue = field.get(model).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            
            String pattern = String.format("\\{\\{%s\\}\\}", fieldName);
            template = template.replaceAll(pattern, fieldValue);
        }

        return template;
    }
}
