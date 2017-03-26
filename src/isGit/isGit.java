package isGit;

import java.io.IOException;
import java.nio.charset.Charset;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import com.csvreader.CsvReader;

public class isGit {
	public static void main(String[] args) throws IOException {
		CsvReader r = new CsvReader("D:\\inputgit.csv", ',', Charset.forName("GBK"));
		r.readHeaders();
		r.readRecord(); 	    	
		String number_csv = r.get("学号");	          
		String name_csv = r.get("姓名");
	    String address_csv = r.get("github地址");
	    String pwd_csv = number_csv.substring(number_csv.length()-6,number_csv.length());
		
	    System.setProperty("webdriver.firefox.bin", "D:\\Dapplo\\Download\\firefox\\firefox.exe"); 
	    WebDriver driver = new FirefoxDriver();
	   driver.get("http://121.193.130.195:8080/");			   
	   driver.manage().window().maximize();

       WebElement input_name = driver.findElement(By.id("name"));
	   input_name.clear();
	   input_name.sendKeys(number_csv);

	   WebElement input_pwd = driver.findElement(By.id("pwd"));
	   input_pwd.clear();
	   input_pwd.sendKeys(pwd_csv);

	   WebElement btn = driver.findElement(By.id("submit"));
	   btn.click();

	   String info_web = driver.findElement(By.xpath("//tbody[@id='table-main']")).getText();
	   String name_web = info_web.substring(info_web.indexOf("名") + 2, info_web.indexOf("学") - 1);
	   String number_web = info_web.substring(info_web.indexOf("号") + 2, info_web.indexOf("G") - 1);
	   String address_web = info_web.substring(info_web.indexOf("址") + 2);

	   if(name_csv.equals(name_web)&&number_csv.equals(number_web)&&address_csv.equals(address_web))
		{
			    System.out.println("coincidence ^w^");
		}
	  else
		{
			    System.out.println(name_web+"has an Error!");
		}
		driver.close();
        }
}
