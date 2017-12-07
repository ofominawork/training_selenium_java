package selenium.java.lesson2;

import org.junit.Test;

public class Ts1OpenBrowser extends BaseSeleniumTest{

    @Test
    public void MyTest1(){
        openLink("http://www.jetbrains.com/");
    }

}
