package selenium.java.lesson11.tests;



import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddRemoveProductsInCartTests extends TestBase {

    /**
     * This test selects first product in "Most popular" section of HomePage and adds it to cart.
     * After adding three products this way the test removes all products from cart.
     */
    @Test
    public void addRemoveProductsInCart(){
        int quantityOfItemsInCart=3;
        for (int i=0; i<quantityOfItemsInCart; i++) {
            app.addFirstMostPopularProductToCart();
        }
        assertEquals(quantityOfItemsInCart, app.productPage.quantityOfItemsInCart());
        app.removeAllProductsFromCart();
        assertEquals(0, app.homePage.open().quantityOfItemsInCart());
    }
}

