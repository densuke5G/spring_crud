package jp.co.sss.crud.test09;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
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
@DisplayName("09_入力チェック機能_ログイン")
public class ValidLoginTest {

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


	@Test
	@Order(1)
	public void 異常系_ログイン操作_社員ID_空文字入力メッセージ出力() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:9999/spring_crud/");

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		WebElement empIdElement = webDriver.findElement(By.name("empId"));
		empIdElement.clear();

		WebElement password = webDriver.findElement(By.name("empPass"));
		password.clear();
		password.sendKeys("2222");

		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// スクリーンショット
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFile, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement errElement = webDriver.findElement(By.cssSelector("p"));

		String errMsgInputNUllOfEmpId = "社員IDを入力してください。";

		// 検証
		assertTrue(errElement.getText().contains(errMsgInputNUllOfEmpId), "エラーメッセージが違います：" + errElement.getText());

	}

	@Test
	@Order(2)
	public void 異常系_ログイン操作_社員ID_桁数オーバー入力メッセージ出力() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:9999/spring_crud/");

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		WebElement empIdElement = webDriver.findElement(By.name("empId"));
		empIdElement.clear();
		empIdElement.sendKeys("111111");

		WebElement password = webDriver.findElement(By.name("empPass"));
		password.clear();
		password.sendKeys("2222");

		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// スクリーンショット
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFile, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement errElement = webDriver.findElement(By.cssSelector("p"));

		String errMsgDigitsOverOfEmpId = "社員IDは99999までの整数値で入力してください。";

		// 検証
		assertTrue(errElement.getText().contains(errMsgDigitsOverOfEmpId), "エラーメッセージが違います：" + errElement.getText());

	}

	@Test
	@Order(3)
	public void 異常系_ログイン操作_社員ID_文字種エラー1入力メッセージ出力() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:9999/spring_crud/");

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		WebElement empIdElement = webDriver.findElement(By.name("empId"));
		empIdElement.clear();
		empIdElement.sendKeys("abc");

		WebElement password = webDriver.findElement(By.name("empPass"));
		password.clear();
		password.sendKeys("2222");

		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// スクリーンショット
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFile, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement errElement = webDriver.findElement(By.cssSelector("p"));

		String errMsgCharTypeOfEmpId = "社員IDは整数値で入力してください。";

		// 検証
		assertTrue(errElement.getText().contains(errMsgCharTypeOfEmpId), "エラーメッセージが違います：" + errElement.getText());

	}

	@Test
	@Order(4)
	public void 異常系_ログイン操作_社員ID_文字種エラー2入力メッセージ出力() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:9999/spring_crud/");

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		WebElement empIdElement = webDriver.findElement(By.name("empId"));
		empIdElement.clear();
		empIdElement.sendKeys("12.3");

		WebElement password = webDriver.findElement(By.name("empPass"));
		password.clear();
		password.sendKeys("2222");

		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// スクリーンショット
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFile, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement errElement = webDriver.findElement(By.cssSelector("p"));

		String errMsgCharTypeOfEmpId = "社員IDは整数値で入力してください。";

		// 検証
		assertTrue(errElement.getText().contains(errMsgCharTypeOfEmpId), "エラーメッセージが違います：" + errElement.getText());

	}

	@Test
	@Order(5)
	public void 異常系_ログイン操作_パスワード_空文字入力メッセージ出力() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:9999/spring_crud/");

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		WebElement empIdElement = webDriver.findElement(By.name("empId"));
		empIdElement.clear();
		empIdElement.sendKeys("1");

		WebElement password = webDriver.findElement(By.name("empPass"));
		password.clear();

		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// スクリーンショット
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFile, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement errElement = webDriver.findElement(By.cssSelector("p"));

		String errMsgNullOfEmpPass = "パスワードを入力してください。";

		// 検証
		assertTrue(errElement.getText().contains(errMsgNullOfEmpPass), "エラーメッセージが違います：" + errElement.getText());

	}

	@Test
	@Order(6)
	public void 異常系_ログイン操作_ログインエラーメッセージ出力() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:9999/spring_crud/");

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		WebElement empIdElement = webDriver.findElement(By.name("empId"));
		empIdElement.clear();
		empIdElement.sendKeys("5");

		WebElement password = webDriver.findElement(By.name("empPass"));
		password.clear();
		password.sendKeys("5");

		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// スクリーンショット
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFile, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// MOC要確認
		WebElement errElement = webDriver.findElement(By.cssSelector("p"));

		String errMsgIllegalEmpIdOrEmpPass = "社員ID、またはパスワードが間違っています。";

		// 検証
		assertTrue(errElement.getText().contains(errMsgIllegalEmpIdOrEmpPass), "エラーメッセージが違います：" + errElement.getText());

	}

}
