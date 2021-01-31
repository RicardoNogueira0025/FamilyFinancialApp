package switchtwentytwenty.project.domain.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.DTOs.MoneyValue;
import switchtwentytwenty.project.domain.DTOs.input.AddCashAccountDTO;
import switchtwentytwenty.project.domain.DTOs.input.AddCreditCardAccountDTO;

import java.util.Currency;
import java.util.Date;
import switchtwentytwenty.project.controllers.AddFamilyAdministratorController;
import switchtwentytwenty.project.controllers.AddFamilyController;
import switchtwentytwenty.project.domain.model.FamilyMember;
import switchtwentytwenty.project.domain.model.Application;
import switchtwentytwenty.project.domain.model.Family;
import switchtwentytwenty.project.domain.model.accounts.*;
import switchtwentytwenty.project.domain.utils.CurrencyEnum;
import switchtwentytwenty.project.domain.model.categories.StandardCategory;
import switchtwentytwenty.project.domain.DTOs.input.FamilyCashTransferDTO;

import static org.junit.jupiter.api.Assertions.*;
import static switchtwentytwenty.project.domain.model.accounts.AccountTypeEnum.*;

class AccountServiceTest {

    String id = "000000000ZZ4";
    String name = "Diogo";
    Date date = new Date(1990,8,12);
    int numero = 919999999;
    String emailTwo = "abc@gmail.com";

    Application ffmApp = new Application();
    AddFamilyController addFamilyController = new AddFamilyController(ffmApp);
    AddFamilyAdministratorController addFamilyAdministratorController = new AddFamilyAdministratorController(ffmApp);
    AccountService accountService = new AccountService();
    FamilyService familyService = ffmApp.getFamilyService();

    String cc = "000000000ZZ4";
    String nameTwo = "Diogo";
    Date dateTwo = new Date(1990, 8, 26);
    Integer numeroTwo = 919999999;
    String email = "josediogoccbr@gmail.com";

    int nif = 212122233;
    String rua = "Rua Nossa";
    String codPostal = "4444-555";
    String local = "Zinde";
    String city = "Porto";
    String relacao = "filho";


    boolean admin = false;

    //Added 2nd FamilyMember to test
    String id2 = "137843828ZX3";
    String name2 = "Tony";
    Date date2 = new Date(1954,8, 12);
    int numero2 = 919999998;
    String email2 = "tony@gmail.com";
    int nif2 = 212122000;
    String rua2 = "Rua";
    String codPostal2 = "4444-556";
    String local2 = "Gaia";
    String city2 = "Porto";
    String relacao2 = "primo";
    boolean admin2 = false;

    //Added 3rd FamilyMember to test
    String id3 = "137476450ZX0";
    String name3 = "TonyZe";
    Date date3 = new Date(1955,8,1);
    int numero3 = 919939998;
    String email3 = "tonyze@gmail.com";
    int nif3 = 212122000;
    String rua3 = "Rua";
    String codPostal3 = "4444-556";
    String local3 = "Gaia";
    String city3 = "Porto";
    String relacao3 = "primo";
    boolean admin3 = true;

    //DTO Test Setup
    FamilyMember diogo = new FamilyMember(id, name, date, numero, email, nif, rua, codPostal, local, city, admin);
    FamilyMember jorge = new FamilyMember(id2, name2, date2, numero2, email2, nif2, rua2, codPostal2, local2, city2, admin2);
    FamilyMember manuelAdminMasterInTheHousesOfTheNightOfTheBicepsWithMyFamily = new FamilyMember(id3, name3, date3, numero3, email3, nif3, rua3, codPostal3, local3, city3, admin3);

    double balance = 0.6;
    double negativeBalance = -2;
    String accountName = "Current Account";
    String accountName2 = "Other Current Account";
    double interestRate = 2.00;

    int family1ID = 5;
    String family1Name = "Silva";
    Family silva = new Family(family1Name, family1ID);
    int generatedID = 1;

    //Account Types Test setup
    BankAccount bankAccount = new BankAccount("bank account", balance, generatedID, CurrencyEnum.EURO);
    BankSavingsAccount bankSavings = new BankSavingsAccount(generatedID, "Savings", balance, interestRate);
    AddCreditCardAccountDTO creditDTO = new AddCreditCardAccountDTO(diogo.getID(), silva.getFamilyID(), "card", 200.00 , 100.00, 50.00, CurrencyEnum.EURO);
    CreditCardAccount creditCardAccount = new CreditCardAccount(creditDTO, 12);
    CashAccount cashAccount = new CashAccount("Cash", 100.00, generatedID);
    BankAccount currentAccount = new BankAccount("Current", 100.00, generatedID, CurrencyEnum.EURO);


    @BeforeEach
    void setup() {
        addFamilyController.addFamily("Ribeiro");
        addFamilyAdministratorController.addFamilyAdministrator( id, name, date, numero, email, nif, rua, codPostal, local, city,1);
    }

    @Test
    void addBankSavingsAccountAddedSuccessfullyWithNullInput() {
        String accountName = "Savings Account";
        Double balance = null;
        Double interestRate = null;

        assertTrue(accountService.addBankSavingsAccount(diogo, accountName, balance, interestRate));
    }

    @Test
    void addBankSavingsAccountAddedSuccessfullyWithRegularInput() {
        String accountName = "Savings Account";
        Double balance = 1.01;
        Double interestRate = -4.21;

        assertTrue(accountService.addBankSavingsAccount(diogo, accountName, balance, interestRate));
    }


    @Test
    void addBankAccountTest1_Success() {
        FamilyMember diogo = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city);
        assertTrue(accountService.addBankAccount(diogo, accountName, balance));
    }
    @Test
    void addBankAccountTest2_NullBalanceSuccess() {
        FamilyMember diogo = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city);
        Assertions.assertTrue(accountService.addBankAccount(diogo, accountName, null));
    }
    @Test
    void addBankAccountTest3_AddTwoBankAccountsSuccess() {
        FamilyMember diogo = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city);
        accountService.addBankAccount(diogo, accountName, balance);
        assertTrue(accountService.addBankAccount(diogo, accountName, negativeBalance));
    }

    @Test
    void createPersonalCreditCardAccountTrue() {
        FamilyMember familyMember = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city);
        AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(id2, family1ID, "Visa do Diogo", 5000.00 , 100.00, 50.00, CurrencyEnum.EURO);
        assertTrue(accountService.createPersonalCreditCardAccount(addCreditCardAccountDTO, familyMember));
        }

    @Test
    void createPersonalCreditCardAccountAssertThrowInvalidWithrawalLimit() {
        FamilyMember familyMember = new FamilyMember(cc, name, date, numero, email, nif, rua, codPostal, local, city);
        AddCreditCardAccountDTO addCreditCardAccountDTO = new AddCreditCardAccountDTO(id2, family1ID, "Conta da Maria", -100.00 , 100.00, 50.00, CurrencyEnum.EURO);
        assertThrows(IllegalArgumentException.class, () -> accountService.createPersonalCreditCardAccount(addCreditCardAccountDTO, familyMember));
    }

    @Test
    void verifyAccountType_BankSavings_ExpectingTrue() {
        //Arrange
        AccountTypeEnum expectedType = BANKSAVINGSACCOUNT;
        //Act
        boolean result = bankSavings.checkAccountType(expectedType);
        //Assert
        assertTrue(result);
    }

    @Test
    void verifyAccountType_BankSavings_ExpectingFalse() {
        //Arrange
        AccountTypeEnum expectedType = CREDITCARDACCOUNT;
        //Act
        boolean result = bankSavings.checkAccountType(expectedType);
        //Assert
        assertFalse(result);
    }

    @Test
    void verifyAccountType_CreditCardAccount_ExpectingTrue() {
        //Arrange
        AccountTypeEnum expectedType = CREDITCARDACCOUNT;
        //Act
        boolean result = creditCardAccount.checkAccountType(expectedType);
        //Assert
        assertTrue(result);
    }

    @Test
    void verifyAccountType_CreditCardAccount_ExpectingFalse() {
        //Arrange
        AccountTypeEnum expectedType = BANKSAVINGSACCOUNT;
        //Act
        boolean result = creditCardAccount.checkAccountType(expectedType);
        //Assert
        assertFalse(result);
    }

    @Test
    void verifyAccountType_BankAccount_ExpectingTrue() {
        //Arrange
        AccountTypeEnum expectedType = BANKACCOUNT;
        //Act
        boolean result = bankAccount.checkAccountType(expectedType);
        //Assert
        assertTrue(result);
    }

    @Test
    void verifyAccountType_BankAccount_ExpectingFalse() {
        //Arrange
        AccountTypeEnum expectedType = CREDITCARDACCOUNT;
        //Act
        boolean result = bankAccount.checkAccountType(expectedType);
        //Assert
        assertFalse(result);
    }

    @Test
    void verifyAccountType_CashAccount_ExpectingTrue() {
        //Arrange
        AccountTypeEnum expectedType = CASHACCOUNT;
        //Act
        boolean result = cashAccount.checkAccountType(expectedType);
        //Assert
        assertTrue(result);
    }

    @Test
    void verifyAccountType_CashAccount_ExpectingFalse() {
        //Arrange
        AccountTypeEnum expectedType = CREDITCARDACCOUNT;
        //Act
        boolean result = cashAccount.checkAccountType(expectedType);
        //Assert
        assertFalse(result);
    }


    @Test
    void getAccountNoAccountWithGivenIDResultNull() {
        int accountID = 1;
        Account expected = null;

        Account result = accountService.getAccount(diogo, accountID);

        assertEquals(result, expected);
    }

    @Test
    void getAccountSuccessTypeBankAccount() {
        String accountName = "Bank Account";
        Double balance = null;
        int accountID = 1;
        Account expected = new BankAccount(accountName, balance, accountID, CurrencyEnum.EURO);

        accountService.addBankAccount(diogo, accountName, balance);
        Account result = accountService.getAccount(diogo, accountID);

        assertEquals(result, expected);
    }

    @Test
    void getAccountSuccessTypeBankSavingsAccount() {
        String accountName = "Savings Account";
        Double balance = null;
        Double interestRate = null;
        int accountID = 1;
        Account expected = new BankSavingsAccount(accountID, accountName, balance, interestRate);

        accountService.addBankSavingsAccount(diogo, accountName, balance, interestRate);
        Account result = accountService.getAccount(diogo, accountID);

        assertEquals(result, expected);
    }

    //Teste escrito antes do method estar definido
    @Test
    void checkCashAccountBalance_ExpectingCorrectValue() {
        MoneyValue expected = new MoneyValue(100.00, CurrencyEnum.EURO);

        diogo.addAccount(cashAccount);
        Account expectedAccount = diogo.getAccount(cashAccount.getAccountID());

        MoneyValue result = accountService.checkCashAccountBalance(expectedAccount.getAccountID(), diogo);

        assertEquals(expected, result);

    }

    @Test
    void checkCashAccountBalance_ExpectingCorrectValueWithNegativeBalance() {
    }

    @Test
    void checkCashAccountBalance_AssertThrowsTrue() {
    }

    @Test
    void checkCashAccountBalance_AssertNotThrows() {
    }



    @Test
    void createPersonalCashAccount_validInputValues() {
        String cashAccount = "Wallet";
        double balance = 0.23;
        AddCashAccountDTO cashAccountDTO = new AddCashAccountDTO(balance,cashAccount,id,family1ID);

        boolean result = accountService.createPersonalCashAccount(diogo,cashAccountDTO);

        Assertions.assertTrue(result);
    }

    @Test
    void createPersonalCashAccount_invalidInputValues() {
        String cashAccount = "Wallet";
        double balance = -1000;
        AddCashAccountDTO cashAccountDTO = new AddCashAccountDTO(balance,cashAccount,id,family1ID);

        boolean result = accountService.createPersonalCashAccount(diogo,cashAccountDTO);

        Assertions.assertFalse(result);
    }

    @Test
    void createFamilyCashAccount_validInputValues() {
        String designation = "Mealheiro familia Silva";
        double initialBalance = 100;
        boolean result = accountService.createFamilyCashAccount(silva,designation,initialBalance);

        Assertions.assertTrue(result);
    }

    @Test
    void createFamilyCashAccount_invalidInputValues() {
        String designation = "Mealheiro familia Silva";
        double initialBalance = -100;

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            accountService.createFamilyCashAccount(silva,designation,initialBalance);
        });
    }



    @Test
    void transferCashFromFamilyToFamilyMember_ValidData() {
        //Common Data
        int familyID = 1;
        int accountID = 1;
        int categoryID = 1;
        StandardCategory transactionCategory = new StandardCategory("Apostas",null,100);
        String transactionDesignation = "Lost Bet";
        Date transactionDate = new Date();
        //Account Data
        double initialBalance = 1000.00;
        AddCashAccountDTO cashAccountDTO = new AddCashAccountDTO(initialBalance,"Diogo's Wallet",id,1);
        Family ribeiro = familyService.getFamily(familyID);
        FamilyMember diogo = ribeiro.getFamilyMember(id);
        accountService.createFamilyCashAccount(ribeiro, "Familia Ribeiro's Wallet", 525);
        accountService.createPersonalCashAccount(diogo,cashAccountDTO);
        //Transference Data
        double transferAmount = 200;
        CurrencyEnum currency = CurrencyEnum.EURO;
        FamilyCashTransferDTO familyCashTransferDTO = new FamilyCashTransferDTO(familyID,id,accountID,transferAmount,currency,categoryID,transactionDesignation,transactionDate);

        boolean result = accountService.transferCashFromFamilyToFamilyMember(ribeiro,diogo,transactionCategory, familyCashTransferDTO);

        Assertions.assertTrue(result);
    }

    @Test
    void transferCashFromFamilyToFamilyMember_NotEnoughMoneyInvalid() {
        //Common Data
        int familyID = 1;
        int accountID = 1;
        int categoryID = 1;
        StandardCategory transactionCategory = new StandardCategory("Apostas",null,100);
        String transactionDesignation = "Lost Bet";
        Date transactionDate = new Date();
        //Account Data
        double initialBalance = 100.0;
        AddCashAccountDTO cashAccountDTO = new AddCashAccountDTO(initialBalance,"Diogo's Wallet",id,1);
        Family ribeiro = familyService.getFamily(familyID);
        FamilyMember diogo = ribeiro.getFamilyMember(id);
        accountService.createFamilyCashAccount(ribeiro, "Familia Ribeiro's Wallet", 100);
        accountService.createPersonalCashAccount(diogo,cashAccountDTO);
        //Transference Data
        double transferAmount = 200;
        CurrencyEnum currency = CurrencyEnum.EURO;
        FamilyCashTransferDTO familyCashTransferDTO = new FamilyCashTransferDTO(familyID,id,accountID,transferAmount,currency,categoryID,transactionDesignation,transactionDate);

        boolean result = accountService.transferCashFromFamilyToFamilyMember(ribeiro,diogo,transactionCategory, familyCashTransferDTO);

        Assertions.assertFalse(result);
    }

    @Test
    void transferCashFromFamilyToFamilyMember_FamilyDoesNotHaveCashAccount() {
        //Common Data
        int familyID = 1;
        int accountID = 1;
        int categoryID = 1;
        StandardCategory transactionCategory = new StandardCategory("Apostas",null,100);
        String transactionDesignation = "Lost Bet";
        Date transactionDate = new Date();
        //Account Data
        double initialBalance = 100.0;
        AddCashAccountDTO cashAccountDTO = new AddCashAccountDTO(initialBalance,"Diogo's Wallet",id,1);
        Family ribeiro = familyService.getFamily(familyID);
        FamilyMember diogo = ribeiro.getFamilyMember(id);
        accountService.createPersonalCashAccount(diogo,cashAccountDTO);
        //Transference Data
        double transferAmount = 200;
        CurrencyEnum currency = CurrencyEnum.EURO;
        FamilyCashTransferDTO familyCashTransferDTO = new FamilyCashTransferDTO(familyID,id,accountID,transferAmount,currency,categoryID,transactionDesignation,transactionDate);

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            accountService.transferCashFromFamilyToFamilyMember(ribeiro,diogo,transactionCategory, familyCashTransferDTO);
        });
    }


    @Test
    void transferCashFromFamilyToFamilyMember_FamilyMemberDoesNotHaveCashAccount() {
        //Common Data
        int familyID = 1;
        int accountID = 2;
        int categoryID = 1;
        StandardCategory transactionCategory = new StandardCategory("Apostas",null,100);
        String transactionDesignation = "Lost Bet";
        Date transactionDate = new Date();
        //Account Data
        double initialBalance = 1000.00;
        AddCashAccountDTO cashAccountDTO = new AddCashAccountDTO(initialBalance,"Diogo's Wallet",id,1);
        Family ribeiro = familyService.getFamily(familyID);
        FamilyMember diogo = ribeiro.getFamilyMember(id);
        accountService.createFamilyCashAccount(ribeiro, "Familia Ribeiro's Wallet", 525);
        accountService.createPersonalCashAccount(diogo,cashAccountDTO);
        //Transference Data
        double transferAmount = 200;
        CurrencyEnum currency = CurrencyEnum.EURO;
        FamilyCashTransferDTO familyCashTransferDTO = new FamilyCashTransferDTO(familyID,id,accountID,transferAmount,currency,categoryID,transactionDesignation,transactionDate);

        boolean result = accountService.transferCashFromFamilyToFamilyMember(ribeiro,diogo,transactionCategory, familyCashTransferDTO);

        Assertions.assertTrue(result);
    }
}
