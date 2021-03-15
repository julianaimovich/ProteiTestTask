package Pages;

import Data.DataHelper;
import Data.DataHelper.UserData;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ApplicationFormPage {
    private SelenideElement emailField = $("[id='dataEmail']");
    private SelenideElement nameField = $("[id='dataName']");
    private SelenideElement genderField = $("[id='dataGender']");
    private SelenideElement firstCheckbox = $("[id='dataCheck11']");
    private SelenideElement secondCheckbox = $("[id='dataCheck12']");
    private SelenideElement firstRadioButton = $("[id='dataSelect21']");
    private SelenideElement secondRadioButton = $("[id='dataSelect22']");
    private SelenideElement thirdRadioButton = $("[id='dataSelect23']");
    private SelenideElement sendDataButton = $("[id='dataSend']");
    private SelenideElement okButton = $(withText("Ok"));
    private SelenideElement userDataTable = $("[id='dataTable']");
    private SelenideElement appWrongEmailMessage = $("[id='emailFormatError']");
    private SelenideElement invalidNameMessage = $(withText("Неверный формат имени"));

    public void sendUserData(UserData userData) {
        emailField.setValue(userData.getUserEmail());
        nameField.setValue(userData.getUserName());
        genderField.click();
        genderField.selectOptionContainingText(userData.getUserGender());
        chooseCheckBox(userData);
        chooseRadioButton(userData);
        sendDataButton.click();
    }

    public void sendEmptyApplication () {
        sendDataButton.click();
    }

    public void sendSpacesInsteadOfUserData() {
        emailField.setValue(" ");
        nameField.setValue(" ");
        sendDataButton.click();
    }

    public void sendUserDataWithInvalidEmail(UserData userData) {
        emailField.setValue(DataHelper.symbolsNumbers);
        nameField.setValue(userData.getUserName());
        genderField.click();
        genderField.selectOptionContainingText(userData.getUserGender());
        chooseCheckBox(userData);
        chooseRadioButton(userData);
        sendDataButton.click();
        appEmailErrorCheck();
    }

    public void sendUserDataTwoCheckboxes(UserData userData) {
        emailField.setValue(userData.getUserEmail());
        nameField.setValue(userData.getUserName());
        genderField.click();
        genderField.selectOptionContainingText(userData.getUserGender());
        firstCheckbox.click();
        secondCheckbox.click();
        chooseRadioButton(userData);
        sendDataButton.click();
    }

    public void sendUserDataWithoutChoices(UserData userData) {
        emailField.setValue(userData.getUserEmail());
        nameField.setValue(userData.getUserName());
        genderField.click();
        genderField.selectOptionContainingText(userData.getUserGender());
        sendDataButton.click();
    }

    public void chooseCheckBox(UserData userData) {
        if (userData.getUserCheckbox() == 1) {
            firstCheckbox.click();
        } else {
            secondCheckbox.click();
        }
    }

    public void chooseRadioButton(UserData userData) {
        if (userData.getUserRadio() == 1) {
            firstRadioButton.click();
        } else if (userData.getUserRadio() == 2) {
            secondRadioButton.click();
        } else {
            thirdRadioButton.click();
        }
    }

    public void checkDataTable (UserData userData) {
        okButton.click();
        userDataTable.getText().equals(userData.getUserEmail());
        userDataTable.getText().equals(userData.getUserName());
        userDataTable.getText().equals(userData.getUserGender());
        userDataTable.getText().equals(userData.getUserCheckbox());
        userDataTable.getText().equals(userData.getUserRadio());
    }

    public void checkDataTableTwoCheckboxes (UserData userData) {
        okButton.click();
        userDataTable.getText().equals(userData.getUserEmail());
        userDataTable.getText().equals(userData.getUserName());
        userDataTable.getText().equals(userData.getUserGender());
        userDataTable.getText().equals("1.1, 1.2");
        userDataTable.getText().equals(userData.getUserRadio());
    }

    public void checkDataTableWithoutChoices (UserData userData) {
        okButton.click();
        userDataTable.getText().equals(userData.getUserEmail());
        userDataTable.getText().equals(userData.getUserName());
        userDataTable.getText().equals(userData.getUserGender());
        userDataTable.getText().equals("Нет");
        userDataTable.getText().equals("");
    }

    public void appEmailErrorCheck () {
        appWrongEmailMessage.shouldBe(visible);
    }

    public void appNameErrorCheck () {
        invalidNameMessage.shouldBe(visible);
    }
}