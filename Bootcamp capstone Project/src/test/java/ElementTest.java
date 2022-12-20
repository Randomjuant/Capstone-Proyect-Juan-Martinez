import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;


import java.awt.*;
public class ElementTest{

    WebDriver driver;
    By Image = By.xpath("//div[@id='imgp']/div/img");//Page object Model To get image path


    @BeforeMethod(groups = "letsstart" )//i start the driver for chrome
    public void RunWebPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Juan\\Documents\\intelij\\Bootcamp capstone Project\\chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1, groups = "SR-12111")//this test is for compare categorys on demoblaze.com
    public void CompareCategory()throws InterruptedException {

        Thread.sleep(1000);
        driver.navigate().to("https://demoblaze.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        String ExpectedCategories = "CATEGORIES";
        String ExpectedPhone = "Phones";
        String ExpectedLaptops = "Laptops";
        String ExpectedMonitors = "Monitors";
        String ExpectedUrl = "https://demoblaze.com/";

            String RealCategories = driver.findElement(By.xpath("//*[@id=\"cat\"]")).getText();
            String RealPhone = driver.findElement(By.xpath("//a[contains(text(),'Phones')]")).getText();
            String RealLaptops = driver.findElement(By.xpath("//a[contains(text(),'Laptops')]")).getText();
            String RealMonitors = driver.findElement(By.xpath("//a[contains(text(),'Monitors')]")).getText();
            String RealUrl = driver.getCurrentUrl();

            Assert.assertEquals(ExpectedCategories, RealCategories);//asserts to comapre if they exist
            Assert.assertEquals(ExpectedPhone, RealPhone);
            Assert.assertEquals(ExpectedLaptops, RealLaptops);
            Assert.assertEquals(ExpectedMonitors, RealMonitors);
            Assert.assertEquals(ExpectedUrl, RealUrl);

        Thread.sleep(1000);
        driver.close();

    }

    @Test(priority = 2,groups = "SR-12120")//test to open a product and check name/price/description/button to add to cart/and image
    public void CompareProductPage() throws InterruptedException
    {
        Thread.sleep(1000);
        driver.navigate().to("https://demoblaze.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='tbodyid']/div/div/a/img")).click();

        Thread.sleep(1000);

        String ExpectedName = "Samsung galaxy s6";
        int ExpectedPrice = 360;
        String ExpectedImage=driver.findElement(Image).getText();//this is a Page object Model
        String ExpectedDescription ="The Samsung Galaxy S6 is powered by 1.5GHz octa-core Samsung Exynos 7420 processor and it comes with 3GB of RAM. The phone packs 32GB of internal storage cannot be expanded.";
        String ExpectedButton="Add to cart";

        String RealName = driver.findElement(By.xpath("//div[@id='tbodyid']/h2")).getText();
        String RealPrice = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h3")).getText();
        String RealPriceint=RealPrice.substring(1,4);////i cut the string with substring to get the correct price because i get $ and "iva" on a string
        int UltimateRealPrice=Integer.parseInt(RealPriceint);//i convert a string data, to int
        String RealImage=driver.findElement(By.xpath("//div[@id='imgp']/div/img")).getText();
        String RealDescription =driver.findElement(By.xpath("//div[@id='more-information']/p")).getText();
        String RealButton=driver.findElement(By.xpath("//div[@id='tbodyid']/div[2]/div/a")).getText();


        Thread.sleep(1000);

        Assert.assertEquals(ExpectedName,RealName);
        Assert.assertEquals(ExpectedPrice,UltimateRealPrice);
        Assert.assertEquals(ExpectedImage,RealImage);
        Assert.assertEquals(ExpectedDescription,RealDescription);
        Assert.assertEquals(ExpectedButton,RealButton);

        Thread.sleep(1000);
        driver.close();
    }
    @Test(priority = 3,groups = "SR-12121")//this test is to add to cart the product
    public void Addtocart() throws InterruptedException
    {
        Thread.sleep(1000);
        driver.navigate().to("https://demoblaze.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='tbodyid']/div/div/a/img")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
        Thread.sleep(500);
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
        driver.close();
    }
    @Test(priority = 4, groups = "SR-12130")//this test is to close and open the page again to clear cache, add to cart, open the cart, and compare the product on before page and actual page to confirm is the same page
    public void LetsBuy() throws InterruptedException
    {
        Thread.sleep(1000);
        driver.navigate().to("https://demoblaze.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='tbodyid']/div/div/a/img")).click();
        Thread.sleep(1000);

        String ProductName = driver.findElement(By.xpath("//div[@id='tbodyid']/h2")).getText();
        String ProductPrice = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h3")).getText();
        String RealPriceint = ProductPrice.substring(1,4);
        String RealImage=driver.findElement(Image).getText();
        String ExpectedDeletebttn = "Delete";

        driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
        Thread.sleep(500);
        driver.switchTo().alert().accept();
        Thread.sleep(1000);

        driver.findElement(By.id("cartur")).click();
        Thread.sleep(1000);

        String totalprice =driver.findElement(By.id("totalp")).getText();
        String CartImage = driver.findElement(By.xpath("//tbody[@id='tbodyid']/tr/td/img")).getText();
        String CartdName = driver.findElement(By.xpath("//td[2]")).getText();
        String CartPrice=driver.findElement(By.xpath("//td[3]")).getText();
        String CartDelete =driver.findElement(By.xpath("//tbody[@id='tbodyid']/tr/td[4]/a")).getText();

        Thread.sleep(1000);

        Assert.assertEquals(ProductName,CartdName);
        Assert.assertEquals(RealPriceint,CartPrice);//i compare the price of page of item, and i compare the cart after
        Assert.assertEquals(RealImage,CartImage);
        Assert.assertEquals(ExpectedDeletebttn,CartDelete);
        Assert.assertEquals(CartPrice,totalprice);//i compare the total price with cart price

        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div[2]/button")).click();
        Thread.sleep(1000);
        driver.close();
    }
    @Test(priority = 5, groups = "Final Buy")//i made this to add to cart a product, and confirm a purchase adding the final info and send the information to concluid the transaction
    public void Info() throws InterruptedException
    {
        Thread.sleep(1000);
        driver.navigate().to("https://demoblaze.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='tbodyid']/div/div/a/img")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        Thread.sleep(1000);

        driver.findElement(By.id("cartur")).click();
        Thread.sleep(1000);


        driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div[2]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Martinez Ramirez Juan Antonio");
        driver.findElement(By.id("country")).click();
        driver.findElement(By.id("country")).sendKeys("Mexico");
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("Tijuana");
        driver.findElement(By.id("card")).click();
        driver.findElement(By.id("card")).sendKeys("4219694213484685");
        Thread.sleep(1000);
        driver.findElement(By.id("month")).click();
        driver.findElement(By.id("month")).sendKeys("Agosto");
        driver.findElement(By.id("year")).click();
        driver.findElement(By.id("year")).sendKeys("Year");
        driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
        Thread.sleep(1000);
        driver.close();

    }


}


























































































































































































//Martinez Ramirez Juan Antonio