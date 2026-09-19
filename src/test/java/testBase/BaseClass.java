package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger; // log4j
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Master", "Regression" })

	@Parameters({ "os", "browser" })
	public void setup(String os, String browser) throws IOException {
		// Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		logger = (Logger) LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equals("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			if (os.equalsIgnoreCase("windows"))
				capabilities.setPlatform(Platform.WIN11);
			else if (os.equalsIgnoreCase("mac"))
				capabilities.setPlatform(Platform.MAC);
			else if (os.equalsIgnoreCase("linux"))
				capabilities.setPlatform(Platform.LINUX);
			else {
				System.out.println("no matching OS");
				return;
			}

			switch (browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("no matching browser...");
				return;
			}
			driver = new RemoteWebDriver(new URL("http://192.168.178.26:4444/wd/hub"), capabilities);

		}

		if (p.getProperty("execution_env").equals("local")) {
			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name...");
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}

	public String randomeString() {
		@SuppressWarnings("deprecation")
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomeNumber() {
		@SuppressWarnings("deprecation")
		String generatedString = RandomStringUtils.randomNumeric(10);
		return generatedString;
	}

	public String randomeAlphaNumeric() {
		@SuppressWarnings("deprecation")
		String generatedString = RandomStringUtils.randomAlphanumeric(3);
		@SuppressWarnings("deprecation")
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + "@" + generatednumber);
	}

	public String captureScreen(String result) throws IOException {

		String timeshot = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File SourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + result + "_" + timeshot + ".jpeg";
		System.out.println("target File path" + targetFilePath);
		File targetFile = new File(targetFilePath);
		SourceFile.renameTo(targetFile);
		return targetFilePath;

	}

	@AfterClass(groups = { "Sanity", "Master", "Regression" })
	public void tearDown() {
		driver.quit();
	}
}
