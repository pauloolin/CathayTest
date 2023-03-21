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

		// �_Server
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability("appPackage", "com.android.chrome");
		dc.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		dc.setCapability("newCommandTimeout", 120);
		dc.setCapability("noReset", true);
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "6555e998");
		AndroidDriver driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);

		// �}����
		driver.get("https://www.cathaybk.com.tw/cathaybk");

		// ����
		Thread.sleep(2000);
		
		// �I��
		Screenshot(driver, "D:\\");

		// �I�索�W�����
		WaitForVisible(driver, By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]"));
		driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]"))
				.click();

		// ���~����
		WaitForVisible(driver, By.xpath("//*[@text='���~����']"));
		driver.findElement(By.xpath("//*[@text='���~����']")).click();

		// �H�Υd
		WaitForVisible(driver, By.xpath("//*[@text='�H�Υd']"));
		driver.findElement(By.xpath("//*[@text='�H�Υd']")).click();

		// �I��
		Screenshot(driver, "D:\\");

//		System.err.println(driver.getPageSource());

		// ���o�H�Υd�C��U���h�֤���
		List elements = driver.findElements(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View"));
		System.err.println("�H�Υd�C��: " + elements.size() + "��");

		// �d������
		WaitForVisible(driver, By.xpath("//*[@text='�d������']"));
		driver.findElement(By.xpath("//*[@text='�d������']")).click();

		// ����
		Thread.sleep(5000);

		// ���o�d
		Swip(driver, "500,1000/200,1000");
		Swip(driver, "500,1000/200,1000");
		WaitForVisible(driver, By.xpath("//*[@text='���o�d']"));
		driver.findElement(By.xpath("//*[@text='���o�d']")).click();

		// ��ܺ���
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
			System.out.println(contextName); // prints out something like NATIVE_APP \n WEBVIEW_1
		}
		driver.context(contextNames.toArray()[1].toString()); // set context to WEBVIEW_1

		List elements2 = driver.findElements(By.className("cubre-o-slide__item swiper-slide"));
		int count = elements2.size()+2;
		System.err.println("��: " + count + "��");

		// ��^app
		driver.context("NATIVE_APP");

		// �I��
		Screenshot(driver, "D:\\");
		for (int i = 0; i < count ; i++) {
			Swip(driver, "1000,1500/200,1500");
			Screenshot(driver, "D:\\");
		}

	}

	/*
	 * �I��
	 * 
	 */
	public static void Screenshot(AndroidDriver<MobileElement> driver, String Path) throws Exception {
		Thread.sleep(2000);
		Date date = new Date();
		SimpleDateFormat pathFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = pathFormat.format(date);

		// �I��
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(Path + fileName + ".jpg"));
	}

	/*
	 * ���ݤ������J
	 *
	 * @param driver driver
	 * 
	 * @param by �w��覡
	 * 
	 * @param waitTime ���ݮɶ�
	 */
	@SuppressWarnings("rawtypes")
	public static void WaitForVisible(AndroidDriver driver, final By by) throws InterruptedException {
		int waitTime = 3;
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		for (int attempt = 0; attempt < waitTime; attempt++) {
			try {
				// Thread.sleep(3000);
				System.err.println("����" + by);
				driver.findElement(by);
				break;
			} catch (Exception e) {
				System.err.println("�䤣�줸��" + by);
				// System.err.println(driver.getPageSource());
				Thread.sleep(5000);
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * <pre>
	 * �ư�
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void Swip(AndroidDriver driver, String value) throws Exception {
		String[] Array = value.split("/");
		// ��l��m
		String[] L1 = Array[0].split(",");
		int x = Integer.valueOf(L1[0]);
		int y = Integer.valueOf(L1[1]);
		// ���ʨ�
		String[] L2 = Array[1].split(",");
		int a = Integer.valueOf(L2[0]);
		int b = Integer.valueOf(L2[1]);
		System.err.println("�q" + x + "," + y + "��" + a + "," + b);
		Thread.sleep(2000);
		new TouchAction(driver).longPress(PointOption.point(x, y)).moveTo(PointOption.point(a, b)).release().perform();
	}
}
