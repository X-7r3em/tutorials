package app;

import MVCFramework.core.StartUp;
import MVCFramework.core.WebHost;

public class App {
    public static void main(String[] args) {
        WebHost.start(new StartUp());
    }
}
