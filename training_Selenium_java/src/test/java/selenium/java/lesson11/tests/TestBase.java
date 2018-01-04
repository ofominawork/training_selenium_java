package selenium.java.lesson11.tests;

import org.junit.Before;
import selenium.java.lesson11.app.Application;

public class TestBase {

    private static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    Application app;

    @Before
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }
        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    app.quit();
                    app = null;
                }));
    }
}
