package main.java.switch2020.project.model;

import java.util.ArrayList;

public class FamilyMember {

    private int familyMemberID;
    private String name;
    private String birthDate;
    private PhoneNumber phoneNumber;
    private ArrayList<EmailAddress> emails = new ArrayList();
    private VatNumber vatNumber;
    private Relationship relationship;
    private boolean isAdmin;

    /* CONSTRUCTORS */

    // System Manager - add FamilyMember
    public FamilyMember(String name, String birthDate, PhoneNumber phone, EmailAddress email, VatNumber vat, Relationship relationship, boolean isAdmin){
        if(!validate(name))
            throw new IllegalArgumentException();
        this.name = name;

        if(!validate(birthDate))
            throw new IllegalArgumentException("Invalid Date");
        this.birthDate = birthDate;

        if(!validatePhone(phone))
            throw new IllegalArgumentException("Invalid Phone");
        this.phoneNumber = phone;

        if(!validateEmail(email))
            throw new IllegalArgumentException("Invalid Email");
        this.emails.add(email);

        if(!validateVat(vat))
            throw new IllegalArgumentException("Invalid VatNumber");
        this.vatNumber = vat;

        if(!validateRelation(relationship))
            throw new IllegalArgumentException("Invalid Relationship");
        this.relationship = relationship;

        this.isAdmin = isAdmin;
    }

    // Family Admin - add Family Member
    public FamilyMember(String name, String birthDate, PhoneNumber phone, EmailAddress email, VatNumber vat, Relationship relationship){
        if(!validate(name))
            throw new IllegalArgumentException();
        this.name = name;

        if(!validate(birthDate))
            throw new IllegalArgumentException("Invalid Date");
        this.birthDate = birthDate;

        if(!validatePhone(phone))
            throw new IllegalArgumentException("Invalid Phone");
        this.phoneNumber = phone;

        if(!validateEmail(email))
            throw new IllegalArgumentException("Invalid Email");
        this.emails.add(email);

        if(!validateVat(vat))
            throw new IllegalArgumentException("Invalid VatNumber");
        this.vatNumber = vat;

        if(!validateRelation(relationship))
            throw new IllegalArgumentException("Invalid Relationship");
        this.relationship = relationship;
    }

    // Add email to FamilyMember
    public FamilyMember(int iD) {
        this.familyMemberID = iD;
    }

    /* VALIDATORS */
    private boolean validate(String birthDate){
        if (birthDate == null);
            return false;
    }

    private boolean validatePhone(PhoneNumber phone){
        if (phone == null);
            return false;
    }

    private boolean validateEmail(EmailAddress email){
        if (email == null);
            return false;
    }

    private boolean validateVat(VatNumber vat){
        if (vat == null);
            return false;
    }

    private boolean validateRelation(Relationship relation) {
        if (relation == null);
            return false;
    }

    /* GETTERS & SETTERS */
    public ArrayList<EmailAddress> getEmails() {
        return emails;
    }

    /**
     * @return Int representing the FamilyMember's ID.
     */
    public int getID() {
        return this.familyMemberID;
    }

    /**
     * Method to create an EmailAddress object and add it to the ArrayList of EmailAddress objects
     *
     * @param emailToAdd String of the email address to add
     * @return True if the EmailAddress object is successfully created and added to the EmailAddress ArrayList
     */
    public boolean addEmail(String emailToAdd) {
        EmailAddress newEmail = new EmailAddress(emailToAdd);
        emails.add(newEmail);
        return true;
    }

}



