import time
from telnetlib import EC

from appium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait

# 起Server
desired_caps = {
    "platformName": "Android",
    "deviceName": "6555e998",
    "platformVersion": "10",
    "appPackage": "com.android.chrome",
    "appActivity": "com.google.android.apps.chrome.Main",
    'noReset': True
}
driver = webdriver.Remote('http://127.0.0.1:4723/wd/hub', desired_caps)

# 開網頁
driver.get("https://www.cathaybk.com.tw/cathaybk");

# 等待
time.sleep(5)

# 截圖
screen_save_path = 'D://開網頁.png'
driver.save_screenshot(screen_save_path)

# 點選左上角選單
driver.find_element(By.XPATH,
                    "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]").click()

# 產品介紹
time.sleep(5)
driver.find_element(By.XPATH, "//*[@text='產品介紹']").click();

# 信用卡
time.sleep(5)
driver.find_element(By.XPATH, "//*[@text='信用卡']").click();

# 截圖
time.sleep(5)
screen_save_path = 'D://信用卡.png'
driver.save_screenshot(screen_save_path)

# 取得信用卡列表下有多少元素
el = driver.find_elements(By.XPATH,
                     "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View")

print("信用卡列表有: ",len(el),"筆")

# 卡片介紹
time.sleep(5)
driver.find_element(By.XPATH, "//*[@text='卡片介紹']").click();

# 等待
time.sleep(5)

# 停發卡
driver.swipe(500,1000,200,1000,3000)
time.sleep(5)
driver.swipe(500,1000,200,1000,3000)
time.sleep(5)
driver.find_element(By.XPATH, "//*[@text='停發卡']").click();

# 轉至網頁
webview = driver.contexts[1]
driver.switch_to.context(webview)
print("切換至:",driver.current_context)

list = driver.find_elements(By.CLASS_NAME,"cubre-o-slide__item swiper-slide")
i = len(list)+2

# 轉回app
webview = driver.contexts[0]
driver.switch_to.context(webview)
print("切換至:",driver.current_context)

# 截圖
screen_save_path = 'D://停發卡.',1,'png'
driver.save_screenshot(screen_save_path)
for n in range(i):
    driver.swipe(1000, 1500, 200, 1500, 3000)
    screen_save_path = 'D://停發卡.', n+2, 'png'
    driver.save_screenshot(screen_save_path)


