package Test;

import Pages.Login;
import Pages.PageDesProduits;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

public class TestPages {
    WebDriver driver = null;
    Login PageLogin;
    PageDesProduits PageElement;
    String UserName = "standard_user";
    String Password = "secret_sauce";
    public String url = "https://www.saucedemo.com/";

    @BeforeMethod
    @Parameters("browser")
    public void setup(){
        String browser="edge";

            if (browser.equalsIgnoreCase("chrome")) {
                System.out.println("******Exécution avec:" + browser);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get(url);
            } else if (browser.equalsIgnoreCase("Edge")) {
                System.out.println("******Exécution avec:" + browser);
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                driver.get(url);
            }
        }
    @Test(testName = "Test D'exception avec des données non valide", priority = 2)
    public void Test_donnees_non_valide() throws InterruptedException {
        System.out.println(" Test Start");

        PageLogin = new Login(driver);
        PageLogin.loginPageAcceuille("12345", "12345");
        String expectedurl = "https://www.saucedemo.com/inventory.html";
        String actuelURL = driver.getCurrentUrl();
        System.out.println(actuelURL);
        if (!expectedurl.equals(actuelURL)) {
            System.out.println("la page est introuvable");
        }
    }

    @Test(testName = "Test avec données  valide", priority = 1)
    public void Test_donnees_valide() throws InterruptedException {
        System.out.println("Test Start");
        PageLogin = new Login(driver);
        PageLogin.loginPageAcceuille(UserName, Password);
        PageLogin.AssertLogin();
    }
    @Test(testName = "Recherche des Produits", priority = 3)
    public void Recherche_element() throws InterruptedException {
        System.out.println(" Test Start");
        PageLogin = new Login(driver);
        PageLogin.loginPageAcceuille(UserName, Password);
        PageLogin.AssertLogin();
        PageElement = new PageDesProduits(driver);
        PageElement.Recherche_Element();
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

