package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.*;
import pages.Leads;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LeadsTest {
	private WebDriver driver;
    private Leads lead;
    
    //new lead's last name
    public String lastName;
    //new lead's company name
	public String companyName;
	public String createdLeadName ;
	public String updatedLastName="Kate";
	
	
    @BeforeClass
    public void setUp() {
        // Set up WebDriver (e.g., ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "/Users/debsmitaroy/Library/CloudStorage/OneDrive-FICO/Documents/Debsmita_Personal/Zoho_Workspace/ZohoPom/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        
        
        
        driver.get("https://www.zoho.com/en-in/crm/");
        lead = new Leads(driver);
        
    }
    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
    
   
   @Test(priority=1)
   public void LoginToSite()
   {
    	String username="palletdebs@gmail.com";
    	String pw="password123@!";
    	String expectedTitle="Zoho Accounts";
    	Assert.assertEquals(lead.Login(username, pw), expectedTitle);
    	System.out.println("Login Successful");

   }
 
   
    @Test(priority=2, dependsOnMethods="LoginToSite")
    public void createNewLeads() throws InterruptedException
    {
   	lastName="Anthony";
   	companyName="Bridgerton";
    	lead.goToLeads();
    	createdLeadName = lead.createNewLead(lastName,companyName);
    	Assert.assertEquals(createdLeadName, lastName);
    	System.out.println("New Lead Creation Successful");
    }
    
    @Test(priority=3, dependsOnMethods={"LoginToSite","createNewLeads"})
    public void editLead() throws InterruptedException
    {
    	Assert.assertEquals(lead.editLead(updatedLastName), updatedLastName+ " (Lead) - Zoho CRM");
    	System.out.println("Edit Lead Successful");
    }
    
    @Test(priority=4, dependsOnMethods="LoginToSite")
    public void filterLeads() throws InterruptedException
    {
    	lead.goToLeads();
    	String leadName = "James Merced (Sample)";
    	Assert.assertEquals(lead.filterLead(leadName),true, "Filter Unsuccessful");
    	System.out.println("Filter Lead Successful");
    }
    
    
    @Test(priority=5, dependsOnMethods={"LoginToSite"})
    public void deleteLead() throws InterruptedException
    {
    	lead.goToLeads();
    	
    	lead.clearFilter();
    	Thread.sleep(4000);
    	Assert.assertEquals(lead.filterLead(updatedLastName),true);
    	lead.deleteLead();
    	lead.goToLeads();
    	lead.clearFilter();
    
    	Assert.assertEquals(lead.filterLead(updatedLastName),false);
    	lead.clearFilter();
    	System.out.println("Lead Deleted");    	
    }

    
    @Test(priority=6 , dependsOnMethods="LoginToSite")
    public void signOut()
    {
    	Assert.assertEquals(lead.signOut(),"Zoho CRM | Top-rated Sales CRM Software by Customers","Sign Out Unssuccessful" );
    	System.out.println("Sign Out Successful");
    }
    

}
