package ru.netology.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$x;

public class FormPage {
    public SelenideElement cityField = $x("//*[@data-test-id='city']//input");
    public SelenideElement dateField = $x("//*[@data-test-id='date']//input");
    public SelenideElement firstLastNameField = $x("//*[@data-test-id='name']//input");
    public SelenideElement phoneNumberField = $x("//*[@data-test-id='phone']//input");

    private String generateDate(int daysToAdd, String pattern) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    public void fillForm(String city, int daysToAdd, String name, String phone) {
        cityField.setValue(city);
        dateField.press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        dateField.setValue(generateDate(daysToAdd, "dd.MM.yyyy"));
        firstLastNameField.setValue(name);
        phoneNumberField.setValue(phone);
    }
}
