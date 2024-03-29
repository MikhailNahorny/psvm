package com.nahorny.automationqa.webdriver;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

    public void method() {
        WebDriver driver = new ChromeDriver(); // = WebDriverRunner.getWebDriver();//получаем инстанцию вебдрайвера. в данном случае - через селениде
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//неявное ожидание для всех элементов
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);//для загрузки стр
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);//для executeAsyncScript

        WebElement explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("bar")));//явное ожидание всегда "одноразовое"
        //потому что привязано к элементу
        explicitWait.click();//взаимодействие с элементом выполняется от имени объекта ожидания

        //свободное ожидание позволяет установить не только таймаут, но и частоту проверок
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)//таймаут (устаревший)
                .pollingEvery(5, TimeUnit.SECONDS)//периодичность проверок (устаревший)
                .ignoring(NoSuchElementException.class);//игнорирование исключения

        //пример использования свободного ожидания
        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("foo"));
            }
        });

        //еще одно ожидание
//        await().atMost(5, SECONDS).until(customerStatusIsUpdated());
        //https://github.com/awaitility/awaitility
    }
}