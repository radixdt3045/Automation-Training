package Login;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Program7 {
	
	public static FirefoxDriver getFirefoxDriver() {
		FirefoxProfile fp = new FirefoxProfile();
		fp.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, application/vnd.ms-excel, application/zip, multipart/x-zip, application/x-compressed, application/x-zip-compressed ");
		fp.setPreference("browser.download.manager.showWhenStarting", false);
		fp.setPreference("pdfjs.disabled", true);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(fp);
        String osName = System.getProperty("os.name");
        String profileRoot = osName.contains("Linux") && new File("/snap/firefox").exists()? createProfileRootInUserHome(): null;
        return profileRoot != null? new FirefoxDriver(createGeckoDriverService(profileRoot), options): new FirefoxDriver(options);
    }
 
    private static String createProfileRootInUserHome() {
        String userHome = System.getProperty("user.home");
        File profileRoot = new File(userHome, "snap/firefox/common/.firefox-profile-root");
        if (!profileRoot.exists()) {
            if (!profileRoot.mkdirs()) {
                return null;
            }
        }
        return profileRoot.getAbsolutePath();
    }
 
    private static GeckoDriverService createGeckoDriverService(final String tempProfileDir) {
        return new GeckoDriverService.Builder() {
            @Override
            protected List<String> createArgs() {
                List<String> args = new ArrayList<>(super.createArgs());
                args.add(String.format("--profile-root=%s", tempProfileDir));
                return args;
            }
        }.build();
    }
	
	public static void main(String[] args) throws InterruptedException {
		

		WebDriverManager.firefoxdriver().clearDriverCache().setup();
		
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
		
		FirefoxDriver driver = getFirefoxDriver();

		String Main_Url = "https://ops-qa.4onprintshop.com/admin/";
		
		String Home_Page = "index.php";
		
		String Customer_Listingpage = "user_listing.php";
		
		String Add_Customer = "user_action.php";
		
		String Homepage = Main_Url.concat(Home_Page);
		
		String CustomerListingpage = Main_Url.concat(Customer_Listingpage);
		
		String AddCustomer = Main_Url.concat(Add_Customer);

		driver.get(Homepage);
		
		
		driver.manage().window().maximize();
		
		
		// Login Page------->

		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys("admin");

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("Admin095");

		WebElement login = driver.findElement(By.xpath("//button[@value = 'Login']"));
	
		login.click();
		
		
		// Xpath contains() function ------->
		
		driver.get(CustomerListingpage);
		WebElement Add = driver.findElement(By.xpath("(//i[contains(@class,'far fa-plus-circle')])[1]"));
		
		Add.click();
		
		
		// Xpath using input() function ------->
		
		driver.get(AddCustomer);
		WebElement fisrtname = driver.findElement(By.xpath("//input[@name = 'firstname' or @id = 'firstname']"));
		fisrtname.sendKeys("Darshan");

		WebElement lastname = driver.findElement(By.xpath("//input[@name = 'lastname' or @id = 'lastname']"));
		lastname.sendKeys("Joshi");
		
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("joshi@radixweb.com");
		
		WebElement password1 = driver.findElement(By.name("password"));
		password1.sendKeys("Admin@1234");
		
		WebElement phonenumber = driver.findElement(By.xpath("//input[@name = 'phone_number' and @id = 'phone_number']"));
		phonenumber.sendKeys("9595959595");
		
		WebElement SalesAgent = driver.findElement(By.xpath("//select[@id='sales_agent_id']//option[2]"));
		SalesAgent.click();
		
		WebElement UserGroup = driver.findElement(By.xpath("//select[@id='user_discount_group_id']//option[2]"));
		UserGroup.click();
		
		WebElement Country = driver.findElement(By.xpath("//button[@data-id = 'country']"));
		Country.click();
		
		WebElement Username = driver.findElement(By.xpath("//select[@name= 'user_discount_group_id']//preceding::input[@name='username']"));
		Username.sendKeys("darshan.joshi");
		
		WebElement Secondary_emails = driver.findElement(By.xpath("//input[@name='username']//following::input[@name='secondary_emails']"));
		Secondary_emails.sendKeys("joshi@radixweb.com");
		
		
		// Xpath using axes and following-sibling(), contains() function ------->
		
		WebElement CountryIndia = driver.findElement(By.xpath("//select[@id='country']//following-sibling::div[contains(@class, 'dropdown-menu')]//ul//li//a/span[text()='Egypt']"));
		CountryIndia.click();
		
		WebElement save = driver.findElement(By.xpath("//button[@type = 'submit']"));
		save.click();
		
		
		
		// Back to Customer Listing Page ---->
		
		driver.navigate().to("https://ops-qa.4onprintshop.com/admin/user_listing.php");
		
	
		// Checks "Website Customer" text at header at Website Customer Listing Page ---->
		
		@SuppressWarnings("unused")
		WebElement TextWebCus = driver.findElement(By.xpath("//h1[contains(text(),'Website Customers')]"));
		System.out.println("Website customer text is present in Header");
				
		System.out.println("Program Run Successfully and closed");
		
		Thread.sleep(5000);
		driver.close();
	}

   }
