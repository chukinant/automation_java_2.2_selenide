package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CardOrderTest {
    private FormPage formPage = new FormPage();

    @BeforeEach
    void openWebpage() {
        Selenide.open("http://localhost:9999/");
    }

    @Test
    void happyPathTest() {
        formPage.fillForm("Барнау", 3, "Артемий Иванов", "+71234567890");
        $x("//*[@data-test-id='agreement']").click();
        $x("//button[descendant::*[*[contains(text(),'Забронировать')]]]").click();

        $(Selectors.withText("Встреча успешно забронирована")).
                should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void emptyCityFieldTest() {
        formPage.fillForm("Барнаул", 3, "Артемий Иванов", "+71234567890");
        $x("//*[@data-test-id='agreement']").click();
        $x("//button[descendant::*[*[contains(text(),'Забронировать')]]]").click();

        $x("//*[contains(@class, 'input_invalid')][@data-test-id='city']//*[contains(@class, 'input__sub')]")
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void emptyDateFieldTest() {
        formPage.fillForm("Барнаул", 3, "Артемий Иванов", "+71234567890");
//        $x("//*[@data-test-id='date']//input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//*[@data-test-id='agreement']").click();
        $x("//button[descendant::*[*[contains(text(),'Забронировать')]]]").click();

        $x("//*[@data-test-id='date']//*[contains(@class, 'input_invalid')]//*[contains(@class, 'input__sub')]")
                .shouldHave(Condition.exactText("Неверно введена дата"));
    }

    @Test
    void emptyNameFieldTest() {
        formPage.fillForm("Барнаул", 3, "Артемий Иванов", "+71234567890");
        $x("//*[@data-test-id='agreement']").click();
        $x("//button[descendant::*[*[contains(text(),'Забронировать')]]]").click();

        $x("//*[contains(@class, 'input_invalid')][@data-test-id='name']//*[contains(@class, 'input__sub')]")
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }


    @Test
    void emptyPhoneFieldTest() {
        formPage.fillForm("Барнаул", 3, "Артемий Иванов", "+71234567890");
        $x("//*[@data-test-id='agreement']").click();
        $x("//button[descendant::*[*[contains(text(),'Забронировать')]]]").click();

        $x("//*[contains(@class, 'input_invalid')][@data-test-id='phone']//*[contains(@class, 'input__sub')]")
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }



    @Test
    void cityFieldInvalidInputTest() {
        formPage.fillForm("Барнаул", 3, "Артемий Иванов", "+71234567890");
        $x("//*[@data-test-id='agreement']").click();
        $x("//button[descendant::*[*[contains(text(),'Забронировать')]]]").click();

        $x("//*[contains(@class, 'input_invalid')][@data-test-id='city']//*[contains(@class, 'input__sub')]")
                .shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void nameFieldInvalidInputTest() {
        formPage.fillForm("Барнаул", 3, "Артемий Иванов", "+71234567890");
        $x("//*[@data-test-id='agreement']").click();
        $x("//button[descendant::*[*[contains(text(),'Забронировать')]]]").click();

        $x("//*[contains(@class, 'input_invalid')][@data-test-id='name']//*[contains(@class, 'input__sub')]")
                .shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void phoneFieldInvalidInputTest() {
        formPage.fillForm("Барнаул", 3, "Артемий Иванов", "+71234567890");
        $x("//*[@data-test-id='agreement']").click();
        $x("//button[descendant::*[*[contains(text(),'Забронировать')]]]").click();

        $x("//*[contains(@class, 'input_invalid')][@data-test-id='phone']//*[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Телефон указан неверно"));
    }

    @Test
    void uncheckedAgreementTest() {
        formPage.fillForm("Барнаул", 3, "Артемий Иванов", "+71234567890");
        $x("//*[@data-test-id='agreement']").click();
        $x("//button[descendant::*[*[contains(text(),'Забронировать')]]]").click();

        $x("//*[@data-test-id='agreement']").
                should(Condition.visible);
    }
}