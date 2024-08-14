package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Leads {
	private WebDriver driver;
	
	@FindBy(linkText = "SIGN IN")
	 WebElement signInField;
	
	@FindBy(xpath="//input[@id='login_id']")
	 WebElement email;
	
	@FindBy(xpath="//input[@id='password']")
	 WebElement password;
	
	@FindBy(xpath="//a[@class='focusVisibleModule' and contains(@href, '/tab/Leads')]")
	 WebElement leadsHeading;
	
	
	@FindBy(xpath="//button[contains(text(),'Create Lead')]")
	 WebElement createLead;
	
	
	@FindBy(xpath="//span[contains(text(),'Next')]")
	 WebElement nextButton;
	
	@FindBy(xpath="//button[@id='nextbtn']")
	 WebElement signInButton;
	
	
	@FindBy(xpath="//input[@id='Crm_Leads_LASTNAME_LInput']")
	 WebElement lastNameField;
	
	@FindBy(xpath="//lyte-autocomplete[@id='Crm_Leads_COMPANY']")
	 WebElement companyNameField;
	
	
	
	@FindBy(xpath="//button[@id='crm_create_savebutn']")
	 WebElement saveButton;
	
	@FindBy(xpath="//span[@id='subvalue_LASTNAME']")
	WebElement createdLeadName;
	
	
	@FindBy(xpath="//header/div[3]/div[1]/div[2]/div[1]/a[1]")
	WebElement accessZohoCRM;
	
	@FindBy(xpath="//body[1]/div[17]/div[9]/div[1]/crm-menu[1]/div[2]/div[2]/crm-profile-menu[1]/div[1]/img[1]")
	WebElement userPhoto;
	
	
	@FindBy(xpath="//span[contains(text(),'Sign Out')]")
	WebElement signOutButton;
	
	
	@FindBy(xpath="//input[@id='inputId']")
	WebElement filterLeadSearchBox;
	
	
	@FindBy(xpath="//lyte-checkbox[@id='field_Full_Name']")
	WebElement lNameCheckbox;
	
	
	@FindBy(xpath="//lyte-input[@id='id_Full_Name']")
	WebElement lNameEnter;
	
	
	@FindBy(xpath="//lyte-yield[contains(text(),'Apply Filter')]")
	WebElement applyFilerBtn;
	
	
	@FindBy(xpath="//iframe[@id='pconnect']")
	public WebElement checkFilteredLead;
	
	
	@FindBy(xpath="//span[contains(text(),'Clear Filter')]")
	public WebElement clearFilterBtn;
	
	
	@FindBy(xpath="//lyte-button[@id='btn_edit']//button[@id='']")
	public WebElement editBtn;
	
	
	@FindBy(xpath="//input[@id='Crm_Leads_EMAIL_LInput']")
	public WebElement emailField;
	
	@FindBy(xpath="//lyte-button[@id='dv_moreBtn']//button[@id='']")
	public WebElement actionsBtn;
	
	@FindBy(xpath="/html[1]/body[1]/lyte-menu-box[4]/lyte-yield[1]/lyte-menu-body[1]/lyte-menu-group[1]/lyte-menu-item[2]")
	public WebElement deleteBtn;
	
	@FindBy(xpath="//lyte-yield[contains(text(),'Delete')]")
	public WebElement confirmDelete;
	
	
	public Leads(WebDriver driver) 
	{
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public String signOut()
	{
		userPhoto.click();
		signOutButton.click();
		String getCurrentTitle=driver.getTitle();
		System.out.println(getCurrentTitle);
		return getCurrentTitle;
	}
	
	
	public String Login(String emailId, String pw)
	{
		signInField.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		email.sendKeys(emailId);
		nextButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		password.sendKeys(pw);
		signInButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String getCurrentTitle=driver.getTitle();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return getCurrentTitle;
	}
	
	
	public void goToLeads() throws InterruptedException
	{
		leadsHeading.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		driver.manage().timeouts().wait(4000);
	}
	
	public String createNewLead(String lastName, String companyName)
	{
		createLead.click();
		lastNameField.sendKeys(lastName);
		companyNameField.sendKeys(companyName);
		saveButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return createdLeadName.getText();
	}
	
	public boolean filterLead(String lName)
	{
		try {
		filterLeadSearchBox.sendKeys("Lead Name");
		lNameCheckbox.click();
		lNameEnter.sendKeys(lName);
		applyFilerBtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String dynamicXPath = "//lyte-text[contains(text(),'" + lName + "')]";
		WebElement element = wait.until(ExpectedConditions.
				elementToBeClickable(By.xpath(dynamicXPath)));
		element.click();
		return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clearFilter()
	{
		clearFilterBtn.click();
		filterLeadSearchBox.sendKeys(Keys.chord(Keys.COMMAND, "a"), Keys.BACK_SPACE);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	public String editLead(String updatedLName) throws InterruptedException
	{
		editBtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		lastNameField.sendKeys(Keys.chord(Keys.COMMAND, "a"), Keys.BACK_SPACE);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//		to update lead name
		lastNameField.sendKeys(updatedLName);
//		to update any other field eg : email
		emailField.sendKeys("email123@ykmail.com");
		saveButton.click();
		Thread.sleep(5000);;
		String getCurrentTitle=driver.getTitle();
		System.out.println(getCurrentTitle); 
		return getCurrentTitle;
		
		
	}
	
	public void deleteLead()
	{
		actionsBtn.click(); 
		deleteBtn.click();
		confirmDelete.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	

}
