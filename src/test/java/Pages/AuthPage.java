package Pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import Data.DataHelper.UserData;

public class AuthPage {
    private SelenideElement loginEmail = $("[id='loginEmail']");
    private SelenideElement password = $("[id='loginPassword']");
    private SelenideElement authButton = $("[id='authButton']");
    private SelenideElement authWrongEmailMessage = $("[id='emailFormatError']");
    private SelenideElement invalidDataMessage = $("[id='invalidEmailPassword']");

    public ApplicationFormPage loginWithUserData (UserData userData) {
        loginEmail.setValue(userData.getUserEmail());
        password.setValue(userData.getUserPassword());
        authButton.click();
        return new ApplicationFormPage();
    }

    public void sendEmptyForm () {
        authButton.click();
    }

    public void sendOnlyEmail (UserData userData) {
        loginEmail.setValue(userData.getUserEmail());
        authButton.click();
    }

    public void sendOnlyPassword (UserData userData) {
        password.setValue(userData.getUserEmail());
        authButton.click();
    }

    public void authEmailErrorCheck () {
        authWrongEmailMessage.shouldBe(visible);
    }

    public void invalidDataErrorCheck () {
        invalidDataMessage.shouldBe(visible);
    }
}