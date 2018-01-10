package selenium.java.lesson6.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CatalogPage extends Page {

    public CatalogPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CatalogPage open(){
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        return this;
    }

    @FindBy(name = "category_id")
    public WebElement categorySelector;

    @FindBy(css = "[name='category_id'] option")
    private List<WebElement> categorySelectorOptionsList;

    @FindBy(css = "a.button")
    private List<WebElement> addButtons;

    @FindBy(name="query")
    private WebElement searchField;

    @FindBy(css = "tr.row")
    private List<WebElement> searchRows;

    public boolean isCategoryPresent(String categoryName){
        for (WebElement category : categorySelectorOptionsList ){
            if (categoryName.equals(category.getText().trim())){
                return true;
            }
        }
        return false;
    }

    public void clickAddCategoryButton(){
        addButtons.get(0).click();
        wait.until(titleIs("Add New Category | My Store"));
    }

    public void clickAddNewProductButton(){
        addButtons.get(1).click();
        wait.until(titleIs("Add New Product | My Store"));
    }

    public CatalogPage performSearch(String searchQuery){
        searchField.sendKeys(searchQuery + Keys.ENTER);
        return this;
    }

    public int numberOfExactSearchResults(String searchQuery){
        int i=0;
        for(WebElement searchRow: searchRows){
            if (searchQuery.equals(searchRow.getAttribute("outerText").trim())){
                i=i+1;
            }
        }
        return i;
    }

}
