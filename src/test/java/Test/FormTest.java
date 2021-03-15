package Test;

import Data.DataHelper;
import Pages.AuthPage;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {

    @BeforeEach
    public void openSource() {
        open("file:///C:/Users/Julia/ProteiTestTask/TestQA.html");
    }

    @Test
    @DisplayName("User data sent successfully")
    void userDataSentSuccessfully() {
        val authPage = new AuthPage();
        val userData = DataHelper.getUserWithValidData();
        val applicationFormPage = authPage.loginWithUserData(userData);
        applicationFormPage.sendUserData(userData);
        applicationFormPage.checkDataTable(userData);
    }

    @Test
    @DisplayName("Should show error message if empty authorization form was sent")
    void shouldShowErrorMessageIfEmptyAuthorizationFormWasSent() {
        val authPage = new AuthPage();
        authPage.sendEmptyForm();
        authPage.authEmailErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if only e-mail field was sent")
    void shouldShowErrorMessageIfOnlyEmailFieldWasSent() {
        val authPage = new AuthPage();
        val authData = DataHelper.getUserWithValidData();
        authPage.sendOnlyEmail(authData);
        authPage.invalidDataErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if only password field was sent")
    void shouldShowErrorMessageIfOnlyPasswordFieldWasSent() {
        val authPage = new AuthPage();
        val authData = DataHelper.getUserWithValidData();
        authPage.sendOnlyPassword(authData);
        authPage.authEmailErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if invalid authorization data was sent")
    void shouldShowErrorMessageIfInvalidAuthorizationDataWasSent() {
        val authPage = new AuthPage();
        val authData = DataHelper.getUserWithInvalidData();
        authPage.loginWithUserData(authData);
        authPage.invalidDataErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if e-mail is uppercase")
    void shouldShowErrorMessageIfEmailIsUppercase() {
        val authPage = new AuthPage();
        val authData = DataHelper.getUserWithUppercaseEmail();
        authPage.loginWithUserData(authData);
        authPage.invalidDataErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if password is uppercase")
    void shouldShowErrorMessageIfPasswordIsUppercase() {
        val authPage = new AuthPage();
        val authData = DataHelper.getUserWithUppercasePassword();
        authPage.loginWithUserData(authData);
        authPage.invalidDataErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if empty application form was sent")
    void shouldShowErrorMessageIfEmptyApplicationFormWasSent() {
        val authPage = new AuthPage();
        val userData = DataHelper.getUserWithValidData();
        val applicationFormPage = authPage.loginWithUserData(userData);
        applicationFormPage.sendEmptyApplication();
        applicationFormPage.appEmailErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if invalid e-mail was sent")
    void shouldShowErrorMessageIfInvalidEmailWasSent() {
        val authPage = new AuthPage();
        val userData = DataHelper.getUserWithValidData();
        val applicationFormPage = authPage.loginWithUserData(userData);
        applicationFormPage.sendUserDataWithInvalidEmail(userData);
        applicationFormPage.appEmailErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if invalid name was sent")
    void shouldShowErrorMessageIfInvalidNameWasSent() {
        val authPage = new AuthPage();
        val userData = DataHelper.getUserWithInvalidName();
        val applicationFormPage = authPage.loginWithUserData(userData);
        applicationFormPage.sendUserData(userData);
        applicationFormPage.appNameErrorCheck();
    }

    @Test
    @DisplayName("User data with two checkboxes sent successfully")
    void userDataWithTwoCheckboxesSentSuccessfully() {
        val authPage = new AuthPage();
        val userData = DataHelper.getUserWithValidData();
        val applicationFormPage = authPage.loginWithUserData(userData);
        applicationFormPage.sendUserDataTwoCheckboxes(userData);
        applicationFormPage.checkDataTableTwoCheckboxes(userData);
    }

    @Test
    @DisplayName("User data without choices sent successfully")
    void userDataWithoutChoicesSentSuccessfully() {
        val authPage = new AuthPage();
        val userData = DataHelper.getUserWithValidData();
        val applicationFormPage = authPage.loginWithUserData(userData);
        applicationFormPage.sendUserDataWithoutChoices(userData);
        applicationFormPage.checkDataTableWithoutChoices(userData);
    }

    @Test
    @DisplayName("Should show error message if form filled with spaces")
    void shouldShowErrorMessageIfFormFilledWithSpaces() {
        val authPage = new AuthPage();
        val userData = DataHelper.getUserWithValidData();
        val applicationFormPage = authPage.loginWithUserData(userData);
        applicationFormPage.sendSpacesInsteadOfUserData();
        applicationFormPage.appEmailErrorCheck();
    }
}