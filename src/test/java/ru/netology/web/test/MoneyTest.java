package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.MoneyPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void checkTransferToTheFirstCard() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var firstCardInfo = DataHelper.CardInfo.getFirstCardInfo();
        var secondCardInfo = DataHelper.CardInfo.getSecondCardInfo();
        int expectedBalanceFistCard = firstCardInfo.getBalance() + 8000;
        int expectedBalanceSecondCard = secondCardInfo.getBalance() - 8000;

        var moneyPage = new MoneyPage();
        moneyPage.cardSelection(firstCardInfo.getIdCard());
        moneyPage.transferMoney("8000", secondCardInfo.getNumberCard());

        var firstCardInfoAfter = DataHelper.CardInfo.getFirstCardInfo();
        var secondCardInfoAfter = DataHelper.CardInfo.getSecondCardInfo();
        int actualBalanceFistCard = firstCardInfoAfter.getBalance();
        int actualBalanceSecondCard = secondCardInfoAfter.getBalance();

        assertEquals(expectedBalanceFistCard, actualBalanceFistCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }

    @Test
    public void checkTransferToTheSecondCard() {
        open("http://localhost:9999/");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var firstCardInfo = DataHelper.CardInfo.getFirstCardInfo();
        var secondCardInfo = DataHelper.CardInfo.getSecondCardInfo();
        int expectedBalanceFistCard = firstCardInfo.getBalance() - 4000;
        int expectedBalanceSecondCard = secondCardInfo.getBalance() + 4000;


        var moneyTransferPage = new MoneyPage();
        moneyTransferPage.cardSelection(secondCardInfo.getIdCard());
        moneyTransferPage.transferMoney("4000", firstCardInfo.getNumberCard());

        var firstCardInfoAfter = DataHelper.CardInfo.getFirstCardInfo();
        var secondCardInfoAfter = DataHelper.CardInfo.getSecondCardInfo();
        int actualBalanceFistCard = firstCardInfoAfter.getBalance();
        int actualBalanceSecondCard = secondCardInfoAfter.getBalance();

        assertEquals(expectedBalanceFistCard, actualBalanceFistCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }

    @Test
    public void checkTransferWhenAmountMoreBalance() {
        open("http://localhost:9999/");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var firstCardInfo = DataHelper.CardInfo.getFirstCardInfo();
        var secondCardInfo = DataHelper.CardInfo.getSecondCardInfo();
        int expectedBalanceFistCard = firstCardInfo.getBalance();
        int expectedBalanceSecondCard = secondCardInfo.getBalance();

        var moneyTransferPage = new MoneyPage();
        moneyTransferPage.cardSelection(secondCardInfo.getIdCard());
        moneyTransferPage.transferMoney("20000", firstCardInfo.getNumberCard());

        var firstCardInfoAfter = DataHelper.CardInfo.getFirstCardInfo();
        var secondCardInfoAfter = DataHelper.CardInfo.getSecondCardInfo();
        int actualBalanceFistCard = firstCardInfoAfter.getBalance();
        int actualBalanceSecondCard = secondCardInfoAfter.getBalance();

        assertEquals(expectedBalanceFistCard, actualBalanceFistCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }

}

