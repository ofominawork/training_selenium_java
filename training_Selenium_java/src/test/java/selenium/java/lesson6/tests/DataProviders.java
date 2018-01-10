package selenium.java.lesson6.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import selenium.java.lesson6.model.Currency;
import selenium.java.lesson6.model.Product;

public class DataProviders {

    @DataProvider
    public static Object[][] validProducts() {
        return new Object[][]{
                {
                   Product.newEntity()
                        .setStatus(true).setName("Super Green Duck").setCode("yd001").setCategory("Green Ducks")
                           .setDefaultCategory("Green Ducks").setGroup("Age").setGroupValue("0-6").setQuantity("10")
                           .setQuantityUnit("pieces").setDeliveryStatus("5-7 days").setSoldOutStatus("Available")
                           .setDateFrom("09.01.2018").setDateTo("09.01.2019").setManufacturer("New Year corp")
                           .setSupplier("Father Frost").setKeywords("dog yellow 2018")
                           .setShortDescription("Buy now!").setHeadTitle("Rubber Toy")
                           .setMetaDescription("toy").setPrice("101").setCurrency("Rubles")
                           .setDescription("This toy is super! Buy it!")
                           .setImagePath("src/test/resources/greenduck.png")
                           .build(),
                        Currency.newEntity()
                        .setName("Rubles").setLetterCode("RUB").setNumberCode("643")
                                .setValue("0,6").setStatus(true)
                                .build()
                }
        };
    }
}
