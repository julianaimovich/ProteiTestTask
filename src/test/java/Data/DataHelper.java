package Data;

import com.github.javafaker.Faker;
import lombok.Value;
import java.util.Locale;

public class DataHelper {
    static Faker faker = new Faker(new Locale("ru"));
    public static String validEmail = "test@protei.ru";
    public static String validPassword = "test";
    public static String symbolsNumbers = "&*!182";

    @Value
    public static class UserData {
        private String userEmail;
        private String userPassword;
        private String userName;
        private String userGender;
        private int userCheckbox;
        private int userRadio;
    }

    public static UserData getUserWithValidData () {
        return new UserData(validEmail, validPassword, faker.name().firstName(), "Женский", 2, 3);
    }

    public static UserData getUserWithInvalidData () {
        return new UserData("protei@test.ru", "тест", faker.name().firstName(), "Женский", 2, 3);
    }

    public static UserData getUserWithUppercaseEmail () {
        return new UserData("TEST@PROTEI.RU", validPassword, faker.name().firstName(), "Женский",1, 2);
    }

    public static UserData getUserWithUppercasePassword () {
        return new UserData(validEmail, "TEST", faker.name().firstName(), "Мужской",2, 3);
    }

    public static UserData getUserWithInvalidName () {
        return new UserData(validEmail, validPassword, symbolsNumbers, "Женский", 2, 3);
    }
}