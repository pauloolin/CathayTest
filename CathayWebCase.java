package Main;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class CathayWebCase {

	public static void main(String[] args) throws Exception {

		// 起Server
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability("appPackage", "com.android.chrome");
		dc.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		dc.setCapability("newCommandTimeout", 120);
		dc.setCapability("noReset", true);
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "6555e998");
		AndroidDriver driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);

		// 開網頁
		driver.get("https://www.cathaybk.com.tw/cathaybk");

		// 等待
		Thread.sleep(2000);
		
		// 截圖
		Screenshot(driver, "D:\\");

		// 點選左上角選單
		WaitForVisible(driver, By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]"));
		driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]"))
				.click();

		// 產品介紹
		WaitForVisible(driver, By.xpath("//*[@text='產品介紹']"));
		driver.findElement(By.xpath("//*[@text='產品介紹']")).click();

		// 信用卡
		WaitForVisible(driver, By.xpath("//*[@text='信用卡']"));
		driver.findElement(By.xpath("//*[@text='信用卡']")).click();

		// 截圖
		Screenshot(driver, "D:\\");

//		System.err.println(driver.getPageSource());

		// 取得信用卡列表下有多少元素
		List elements = driver.findElements(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View"));
		System.err.println("信用卡列表有: " + elements.size() + "筆");

		// 卡片介紹
		WaitForVisible(driver, By.xpath("//*[@text='卡片介紹']"));
		driver.findElement(By.xpath("//*[@text='卡片介紹']")).click();

		// 等待
		Thread.sleep(5000);

		// 停發卡
		Swip(driver, "500,1000/200,1000");
		Swip(driver, "500,1000/200,1000");
		WaitForVisible(driver, By.xpath("//*[@text='停發卡']"));
		driver.findElement(By.xpath("//*[@text='停發卡']")).click();

		// 轉至網頁
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
			System.out.println(contextName); // prints out something like NATIVE_APP \n WEBVIEW_1
		}
		driver.context(contextNames.toArray()[1].toString()); // set context to WEBVIEW_1

		List elements2 = driver.findElements(By.className("cubre-o-slide__item swiper-slide"));
		int count = elements2.size()+2;
		System.err.println("有: " + count + "筆");

		// 轉回app
		driver.context("NATIVE_APP");

		// 截圖
		Screenshot(driver, "D:\\");
		for (int i = 0; i < count ; i++) {
			Swip(driver, "1000,1500/200,1500");
			Screenshot(driver, "D:\\");
		}

	}

	/*
	 * 截圖
	 * 
	 */
	public static void Screenshot(AndroidDriver<MobileElement> driver, String Path) throws Exception {
		Thread.sleep(2000);
		Date date = new Date();
		SimpleDateFormat pathFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = pathFormat.format(date);

		// 截圖
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(Path + fileName + ".jpg"));
	}

	/*
	 * 等待元素載入
	 *
	 * @param driver driver
	 * 
	 * @param by 定位方式
	 * 
	 * @param waitTime 等待時間
	 */
	@SuppressWarnings("rawtypes")
	public static void WaitForVisible(AndroidDriver driver, final By by) throws InterruptedException {
		int waitTime = 3;
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		for (int attempt = 0; attempt < waitTime; attempt++) {
			try {
				// Thread.sleep(3000);
				System.err.println("元素" + by);
				driver.findElement(by);
				break;
			} catch (Exception e) {
				System.err.println("找不到元素" + by);
				// System.err.println(driver.getPageSource());
				Thread.sleep(5000);
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * <pre>
	 * 滑動
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void Swip(AndroidDriver driver, String value) throws Exception {
		String[] Array = value.split("/");
		// 初始位置
		String[] L1 = Array[0].split(",");
		int x = Integer.valueOf(L1[0]);
		int y = Integer.valueOf(L1[1]);
		// 移動到
		String[] L2 = Array[1].split(",");
		int a = Integer.valueOf(L2[0]);
		int b = Integer.valueOf(L2[1]);
		System.err.println("從" + x + "," + y + "到" + a + "," + b);
		Thread.sleep(2000);
		new TouchAction(driver).longPress(PointOption.point(x, y)).moveTo(PointOption.point(a, b)).release().perform();
	}
}
