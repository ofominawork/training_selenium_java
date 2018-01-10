package selenium.java.lesson6.tests;

import org.junit.Test;
import selenium.java.lesson6.model.Currency;
import selenium.java.lesson6.model.Product;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class TS12AddProductInAdminTests extends TestBase{

    /**
     * This test adds new product on page http://localhost/litecart/admin/?app=catalog&doc=catalog
     * The test first checks the presence of product parameters (category, group, manufacturer and etc) and then
     * adds them in case they are not present.
     * The test checks that the product is added by searching it.
     * @param product - product to add
     * @param currency - currency of product (made as a parameter because the application requires filling of
     *                 several parameters of currency which the product doesn't have)
     */
    @Test
    @UseDataProvider(value = "validProducts", location = DataProviders.class)
    public void addNewProduct(Product product, Currency currency) {
        app.loginToAdmin();
        int numberOfProductsExpected = app.numberOfProductsByName(product.getName())+1;
        app.addNewProductGroupValue(product.getGroup(),product.getGroupValue());
        app.addNewCategory(product.getCategory());
        app.addNewUnit(product.getQuantityUnit());
        app.addNewDeliveryStatus(product.getDeliveryStatus());
        app.addNewSoldOutStatus(product.getSoldOutStatus());
        app.addNewManufacturer(product.getManufacturer());
        app.addNewSupplier(product.getSupplier());
        app.addNewCurrency(currency);
        app.addNewProduct(product);
        int numberOfProductsCurrent=app.numberOfProductsByName(product.getName());
        assertEquals(numberOfProductsExpected, numberOfProductsCurrent);
    }

}
