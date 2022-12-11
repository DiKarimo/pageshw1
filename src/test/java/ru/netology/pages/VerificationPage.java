package ru.netology.pages;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeInput = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeInput.shouldBe(visible);
    }

    public DashboardPage validVerify(DataGenerator.VerificationCode verificationCode) {
        codeInput.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
