package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;


import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransactionPage {
    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement fromInput = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    public static SelenideElement refreshButton = $("[data-test-id=action-reload]");
    private SelenideElement canselButton = $("[data-test-id=action-cancel]");
    private SelenideElement errorMessage = $("[data-test-id=error-message]");

    public TransactionPage () {
        transferHead.shouldBe(visible);
    }

    public DashboardPage validTransfer(String amountOfTransfer, DataGenerator.CardInfo cardInfo) {
        makeTransaction(amountOfTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeTransaction(String amountOfTransfer, DataGenerator.CardInfo cardInfo) {
        amountInput.setValue(amountOfTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void shouldFindErrorMessage(String expectedText) {
        errorMessage.shouldBe(exactText(expectedText)).shouldBe(visible);
    }

}