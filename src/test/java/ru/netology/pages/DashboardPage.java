package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransactionPage selectCardToTransfer(DataGenerator.CardInfo cardInfo) {
        cards.findBy(attribute("data-test-id", cardInfo.getCardTestId())).$("button").click();
        return new TransactionPage();
    }

    public int getCardBalance(DataGenerator.CardInfo cardInfo) {
        val balance = cards.findBy(text(cardInfo.getCardNumber().substring(12, 16))).getText();
        return extractBalance(balance);
    }

    private int extractBalance(String balance) {
        val start = balance.indexOf(balanceStart);
        val finish = balance.indexOf(balanceFinish);
        val value = balance.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
