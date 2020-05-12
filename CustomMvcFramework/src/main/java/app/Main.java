package app;

import MVCFramework.core.StartUp;
import MVCFramework.core.WebHost;

public class Main {
    public static void main(String[] args) {
        WebHost.start(new StartUp());
    }
}
