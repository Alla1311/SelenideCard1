import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideCardTest {

   public String localDate (int day) {
        LocalDate date = LocalDate.now();
        String plusDays = String.valueOf(LocalDate.now());
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldDeliveryCard(){
        open("http://localhost:9999/");
        $("[data-test-id='city' ] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(localDate(7));
        $("[data-test-id='name'] input").setValue("Иванов-Сидоров Иван");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text(localDate(7)));

    }

}
