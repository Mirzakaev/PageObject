package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MoneyPage {
    private SelenideElement amount = $("[data-test-id='amount'] .input__control");
    private SelenideElement fromCard = $("[data-test-id='from'] .input__control");
    private SelenideElement topUpButton = $("[data-test-id='action-transfer']");
    private ElementsCollection rechargeButton = $$(".list__item div");


    public void cardSelection(String inCard) {
        rechargeButton.find(attribute("data-test-id", inCard)).find(".button__content").click();
    }

    public DashboardPage transferMoney(String transferAmount, String numberCardOut) {
        amount.setValue(transferAmount);
        fromCard.setValue(numberCardOut);
        topUpButton.click();
        return new DashboardPage();
    }

}