package jp.co.sss.crud.test07;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("07_社員変更機能")
public class UpdateEmployeeTest {

	private WebDriver webDriver;

	/**
	 * テストメソッドを実行する前に実行されるメソッド
	 */
	@BeforeEach
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		webDriver = new ChromeDriver(ops);
	}

	@AfterEach
	public void quitDriver() {
		webDriver.quit();
	}


	private void doLogin() {
		// 指定したURLに遷移する
//		webDriver.manage().window().setPosition(new Point(1680, 40));
//		webDriver.manage().window().setSize(new Dimension(1400, 900));
		webDriver.get("http://localhost:9999/spring_crud/");

		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		WebElement loginIdElement = webDriver.findElement(By.name("empId"));
		loginIdElement.clear();
		loginIdElement.sendKeys("2");

		WebElement password = webDriver.findElement(By.name("empPass"));
		password.clear();
		password.sendKeys("2222");

		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();

		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

	}

	@Test
	@Order(1)
	public void 正常系_社員変更操作_変更完了_管理者権限() {

		doLogin();

		/*****社員一覧から入力画面へ*****/
		List<WebElement> buttons = webDriver.findElements(By.tagName("button"));
		buttons.get(1).click();
//		WebElement buttonUpdate = webDriver
//				.findElement(By.cssSelector("table tr:nth-of-type(2) td:nth-of-type(8) input[type='submit']"));
//		buttonUpdate.submit();

		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// スクリーンショット
		File tempFileInput = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFileInput, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "社員変更入力画面.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement articleInputTitle = webDriver.findElement(By.cssSelector("article h3"));

		WebElement inputedEmpNameElement = webDriver.findElement(By.cssSelector(".update input[name='empName']"));
		WebElement inputedGenderElement = webDriver
				.findElement(By.cssSelector(".update input[name='gender'][value='1']"));
		WebElement inputedAddressElement = webDriver.findElement(By.cssSelector(".update input[name='address']"));
		WebElement inputedBirthdayElement = webDriver.findElement(By.cssSelector(".update input[name='birthday']"));
		WebElement inputedAuthorityElement = webDriver
				.findElement(By.cssSelector(".update input[name='authority'][value='1']"));
//		WebElement inputedDeptIdElement = webDriver
//				.findElement(By.cssSelector(".update select[name='deptId'] option[value='1']"));
		WebElement inputedDeptIdElement = webDriver
				.findElement(By.cssSelector(".update select[name='departmentForm.deptId'] option[value='1']"));
		//検証
		assertEquals("社員変更入力画面", articleInputTitle.getText());
		assertEquals("鈴木太郎", inputedEmpNameElement.getAttribute("value"));
		assertTrue(inputedGenderElement.isSelected(), "性別の選択がない、または間違っています");
		assertEquals("東京都", inputedAddressElement.getAttribute("value"));
		assertEquals("1986/10/12", inputedBirthdayElement.getAttribute("value"));
		assertTrue(inputedAuthorityElement.isSelected(), "権限の選択がない、または間違っています");
		assertTrue(inputedDeptIdElement.isSelected(), "部署名の選択がない、または間違っています");

		/*****社員入力から確認画面へ*****/
		WebElement empPassElement = webDriver.findElement(By.name("empPass"));
		empPassElement.sendKeys("1111");

		inputedEmpNameElement.clear();
		inputedEmpNameElement.sendKeys("山田太郎");

//		webDriver.findElement(By.cssSelector("input[value='変更']")).submit();
		List<WebElement> buttons2 = webDriver.findElements(By.tagName("button"));
		buttons2.get(1).click();


		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// スクリーンショット
		File tempFileCheck = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFileCheck, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "社員変更確認画面.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement articleCheckTitle = webDriver.findElement(By.cssSelector("article h3"));
		WebElement checkEmpNameElement = webDriver.findElement(By.cssSelector(".update .form:nth-of-type(2) .input"));

		// 検証
		assertEquals("社員変更確認画面", articleCheckTitle.getText());
		assertEquals("山田太郎", checkEmpNameElement.getText());

		/*****社員確認から完了画面へ*****/
//		webDriver.findElement(By.cssSelector(".update .input input[value='変更']")).submit();
		List<WebElement> buttons3 = webDriver.findElements(By.tagName("button"));
		buttons3.get(1).click();

		// スクリーンショット
		File tempFileComplete = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFileComplete, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "社員変更完了.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement articleCompleteTitle = webDriver.findElement(By.cssSelector("article h3"));
		//検証
		assertEquals("社員変更完了画面", articleCompleteTitle.getText());

		/*****社員完了から一覧画面へ*****/

		WebElement backToEmpListElement = webDriver.findElement(By.linkText("一覧表示に戻る"));

		backToEmpListElement.click();

		WebElement empId = webDriver.findElement(By.cssSelector("table tr:nth-of-type(2) td:nth-of-type(1)"));
		WebElement empName = webDriver.findElement(By.cssSelector("table tr:nth-of-type(2) td:nth-of-type(2)"));

		// 検証
		assertEquals("1", empId.getText());
		assertEquals("山田太郎", empName.getText());

	}

	@Test
	@Order(2)
	public void 正常系_社員変更操作_入力画面_戻るボタンを押下する() {

		doLogin();

		/*****社員一覧から入力画面へ*****/
//		WebElement buttonUpdate = webDriver
//				.findElement(By.cssSelector("table tr:nth-of-type(2) td:nth-of-type(8) input[type='submit']"));
//		buttonUpdate.submit();
		List<WebElement> buttons = webDriver.findElements(By.tagName("button"));
		buttons.get(1).click();

		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// スクリーンショット
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFile, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		webDriver.findElement(By.cssSelector(".update .input input[value='戻る']")).submit();

		WebElement articleCompleteTitle = webDriver.findElement(By.cssSelector("article h3"));
		//検証
		assertEquals("社員一覧画面", articleCompleteTitle.getText());

	}

	@Test
	@Order(3)
	public void 正常系_社員変更操作_確認画面_戻るボタンを押下する() {

		doLogin();
		/*****社員一覧から入力画面へ*****/
//		WebElement buttonUpdate = webDriver
//				.findElement(By.cssSelector("table tr:nth-of-type(2) td:nth-of-type(8) input[type='submit']"));
//		buttonUpdate.submit();
		List<WebElement> buttons = webDriver.findElements(By.tagName("button"));
		buttons.get(1).click();

		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// スクリーンショット
		File tempFileInput1 = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFileInput1, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "社員変更入力画面.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement empPassElement = webDriver.findElement(By.name("empPass"));
		WebElement inputedEmpNameElement = webDriver.findElement(By.cssSelector(".update input[name='empName']"));

		empPassElement.sendKeys("1111");

		inputedEmpNameElement.clear();
		inputedEmpNameElement.sendKeys("山田太郎");

//		webDriver.findElement(By.cssSelector("input[value='変更']")).submit();
		List<WebElement> buttons2 = webDriver.findElements(By.tagName("button"));
		buttons2.get(1).click();

		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// スクリーンショット
		File tempFileCheck = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFileCheck, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "社員変更入力画面.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*****社員確認から入力画面へ*****/
//		webDriver.findElement(By.cssSelector(".update .input input[value='戻る']")).submit();
		List<WebElement> buttons4 = webDriver.findElements(By.tagName("button"));
		buttons4.get(2).click();

		// スクリーンショット
		File tempFileInput2 = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFileInput2, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "社員変更入力画面.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement articleInputTitle = webDriver.findElement(By.cssSelector("article h3"));
		//検証
		assertEquals("社員変更入力画面", articleInputTitle.getText());

	}

}