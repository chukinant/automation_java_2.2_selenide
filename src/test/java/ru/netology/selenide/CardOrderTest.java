package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CardOrderTest {

    @BeforeEach
    void openWebpage() {
        Selenide.open("http://localhost:9999/");
    }

    private String generateDate(int daysToAdd, String pattern) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void happyPathTest() {
        SelenideElement cityField = $x("//*[@data-test-id='city']//input");
        SelenideElement dateField = $x("//*[@data-test-id='date']//input");
        SelenideElement firstLastNameField = $x("//*[@data-test-id='name']//input");
        SelenideElement phoneNumberField = $x("//*[@data-test-id='phone']//input");

        cityField.setValue("Барнаул");
        dateField.press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        dateField.setValue(generateDate(3, "dd.MM.yyyy"));
        firstLastNameField.setValue("Артемий Иванов");
        phoneNumberField.setValue("+71234567890");
        $x("//*[@data-test-id='agreement']").click();
        $x("//button[descendant::*[*[contains(text(),'Забронировать')]]]").click();

        $(Selectors.withText("Встреча успешно забронирована")).
                should(Condition.visible, Duration.ofSeconds(15));
    }

//    @Test
//    void emptyNameFieldTest() {
//        WebElement phoneNumberField = driver.findElement(By.xpath("//*[@data-test-id='phone']//input"));
//
//        phoneNumberField.sendKeys("+71234567890");
//        driver.findElement(By.xpath("//*[@data-test-id='agreement']")).click();
//        driver.findElement(By.xpath("//button[@role='button']")).click();
//
//        WebElement result = driver.findElement(By.xpath("//*[contains(@class, 'input_invalid')][@data-test-id='name']//*[contains(@class, 'input__sub')]"));
//
//        assertEquals("Поле обязательно для заполнения", result.getText().trim());
//    }
//
//    @Test
//    void nameFieldInvalidInputTest() {
//        WebElement firstLastNameField = driver.findElement(By.xpath("//*[@data-test-id='name']//input"));
//        WebElement phoneNumberField = driver.findElement(By.xpath("//*[@data-test-id='phone']//input"));
//
//        firstLastNameField.sendKeys("Артемий7 Иванов");
//        phoneNumberField.sendKeys("+71234567890");
//        driver.findElement(By.xpath("//*[@data-test-id='agreement']")).click();
//        driver.findElement(By.xpath("//button[@role='button']")).click();
//
//        WebElement result = driver.findElement(By.xpath("//*[contains(@class, 'input_invalid')][@data-test-id='name']//*[contains(@class, 'input__sub')]"));
//
//        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", result.getText().trim());
//    }
//
//    @Test
//    void phoneFieldInvalidInputTest() {
//        WebElement firstLastNameField = driver.findElement(By.xpath("//*[@data-test-id='name']//input"));
//        WebElement phoneNumberField = driver.findElement(By.xpath("//*[@data-test-id='phone']//input"));
//
//        firstLastNameField.sendKeys("Артемий Иванов");
//        phoneNumberField.sendKeys("+7123456789");
//        driver.findElement(By.xpath("//*[@data-test-id='agreement']")).click();
//        driver.findElement(By.xpath("//button[@role='button']")).click();
//
//        WebElement result = driver.findElement(By.xpath("//*[contains(@class, 'input_invalid')][@data-test-id='phone']//*[contains(@class, 'input__sub')]"));
//
//        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", result.getText().trim());
//    }
//
//    @Test
//    void EmptyPhoneFieldTest() {
//        WebElement firstLastNameField = driver.findElement(By.xpath("//*[@data-test-id='name']//input"));
//
//        firstLastNameField.sendKeys("Артемий Иванов");
//        driver.findElement(By.xpath("//*[@data-test-id='agreement']")).click();
//        driver.findElement(By.xpath("//button[@role='button']")).click();
//
//        WebElement result = driver.findElement(By.xpath("//*[contains(@class, 'input_invalid')][@data-test-id='phone']//*[contains(@class, 'input__sub')]"));
//
//        assertEquals("Поле обязательно для заполнения", result.getText().trim());
//    }
//
//    @Test
//    void uncheckedAgreementTest() {
//        WebElement firstLastNameField = driver.findElement(By.xpath("//*[@data-test-id='name']//input"));
//        WebElement phoneNumberField = driver.findElement(By.xpath("//*[@data-test-id='phone']//input"));
//
//        firstLastNameField.sendKeys("Артемий Иванов");
//        phoneNumberField.sendKeys("+71234567890");
//        driver.findElement(By.xpath("//button[@role='button']")).click();
//
//        WebElement result = driver.findElement(By.xpath("//*[@data-test-id='agreement']"));
//
//        assertTrue(result.isDisplayed());
//    }
}