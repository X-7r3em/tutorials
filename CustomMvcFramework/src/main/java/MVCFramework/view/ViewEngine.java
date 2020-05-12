package MVCFramework.view;

public interface ViewEngine {
    String getHtml(String template, Object model);
}
