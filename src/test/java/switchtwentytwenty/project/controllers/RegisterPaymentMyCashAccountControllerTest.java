package switchtwentytwenty.project.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.dtos.MoneyValue;
import switchtwentytwenty.project.domain.dtos.input.AddCashAccountDTO;
import switchtwentytwenty.project.domain.model.Application;
import switchtwentytwenty.project.domain.model.Family;
import switchtwentytwenty.project.domain.model.FamilyMember;
import switchtwentytwenty.project.domain.model.accounts.CashAccount;
import switchtwentytwenty.project.domain.services.FamilyService;
import switchtwentytwenty.project.domain.dtos.input.FamilyCashTransferDTO;
import switchtwentytwenty.project.domain.utils.CurrencyEnum;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RegisterPaymentMyCashAccountControllerTest {

    // FamilyMember
    int familyID = 1;
    String selfCC = "166699209ZY8";
    String name = "Ze Manel";
    Date date = new Date(1990,8,26);
    int numero = 919999998;
    String email = "zemanel@gmail.com";
    int nif = 212122000;
    String rua = "Rua";
    String codPostal = "4444-556";
    String local = "Gaia";
    String city = "Porto";

    // CashAccount
    String description = "Cash Account do Ze Manel";
    Double balance = 450.00;
    int accountID = 1;
    AddCashAccountDTO cashAccountDTO = new AddCashAccountDTO(balance,description,selfCC,familyID, CurrencyEnum.EURO);
    CashAccount contaCash = new CashAccount(cashAccountDTO,accountID);

    // CashTransaction
    double transferAmount = 200.0;
    CurrencyEnum currency = CurrencyEnum.EURO;
    int categoryID = 1;
    String transactionDesignation = "Luz Novembro";
    Date transactionDate = new Date(2021,1,21);
    FamilyCashTransferDTO transacaoDTO1 = new FamilyCashTransferDTO(familyID,selfCC,accountID,transferAmount,currency,categoryID,transactionDesignation,transactionDate);
    FamilyCashTransferDTO transacaoDTO2 = new FamilyCashTransferDTO(familyID,selfCC,accountID,-200.0,currency,categoryID,transactionDesignation,transactionDate);
    FamilyCashTransferDTO transacaoDTO3 = new FamilyCashTransferDTO(familyID,selfCC,accountID,700.0,currency,categoryID,transactionDesignation,transactionDate);

    @Test
    void registerPaymentMyCashAccount_SameBalance() {
        Application ffmApp = new Application();
        RegisterPaymentMyCashAccountController controller = new RegisterPaymentMyCashAccountController(ffmApp);
        // FamilyService
        FamilyService familyService = ffmApp.getFamilyService();
        Family family = new Family("Ribeiros",familyID);
        familyService.addFamily(family);
        FamilyMember zeManel = new FamilyMember(selfCC,name,date,numero,email,nif,rua,codPostal,local,city);
        family.addFamilyMember(zeManel);
        zeManel.addAccount(contaCash);
        controller.registerPaymentMyCashAccount(transacaoDTO1);
        double expected = 250.00;
        double result = contaCash.getMoneyBalance().getValue();
        assertEquals(expected,result);
    }

    @Test
    void registerPaymentMyCashAccount_SameMoneyValue() {
        Application ffmApp = new Application();
        RegisterPaymentMyCashAccountController controller = new RegisterPaymentMyCashAccountController(ffmApp);
        // FamilyService
        FamilyService familyService = ffmApp.getFamilyService();
        Family family = new Family("Ribeiros",familyID);
        familyService.addFamily(family);
        FamilyMember zeManel = new FamilyMember(selfCC,name,date,numero,email,nif,rua,codPostal,local,city);
        family.addFamilyMember(zeManel);
        zeManel.addAccount(contaCash);
        controller.registerPaymentMyCashAccount(transacaoDTO1);
        MoneyValue expected = new MoneyValue(250.00,CurrencyEnum.EURO);
        MoneyValue result = contaCash.getMoneyBalance();
        assertEquals(expected,result);
    }

    @Test
    void NotRegisterPaymentMyCashAccount_NegativeAmmount() {
        Application ffmApp = new Application();
        RegisterPaymentMyCashAccountController controller = new RegisterPaymentMyCashAccountController(ffmApp);
        // FamilyService
        FamilyService familyService = ffmApp.getFamilyService();
        Family family = new Family("Ribeiros",familyID);
        familyService.addFamily(family);
        FamilyMember zeManel = new FamilyMember(selfCC,name,date,numero,email,nif,rua,codPostal,local,city);
        family.addFamilyMember(zeManel);
        zeManel.addAccount(contaCash);
        assertFalse(controller.registerPaymentMyCashAccount(transacaoDTO2));
    }

    @Test
    void NotRegisterPaymentMyCashAccount_NotEnoughBalance() {
        Application ffmApp = new Application();
        RegisterPaymentMyCashAccountController controller = new RegisterPaymentMyCashAccountController(ffmApp);
        // FamilyService
        FamilyService familyService = ffmApp.getFamilyService();
        Family family = new Family("Ribeiros",familyID);
        familyService.addFamily(family);
        FamilyMember zeManel = new FamilyMember(selfCC,name,date,numero,email,nif,rua,codPostal,local,city);
        family.addFamilyMember(zeManel);
        zeManel.addAccount(contaCash);
        assertFalse(controller.registerPaymentMyCashAccount(transacaoDTO3));
    }

}