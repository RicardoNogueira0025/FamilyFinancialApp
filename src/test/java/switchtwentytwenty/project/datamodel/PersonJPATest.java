package switchtwentytwenty.project.datamodel;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonJPA;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PersonJPATest {

    String id = "emaiil@email.com";
    PersonIDJPA personIDJPA = new PersonIDJPA(id);

    String name = "TonyZe";
    String birthdate = "23/12/1992";
    Integer vat = 999999999;

    FamilyIDJPA familyIDJPA = new FamilyIDJPA(UUID.randomUUID().toString());

    @Test
    @Tag("US010")
    void getNameTest() {
        PersonJPA personJPA = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);
        String expected = "TonyZe";

        String result = personJPA.getName();

        assertEquals(expected,result);
    }

    @Test
    @Tag("US010")
    void getBirthdateTest() {
        PersonJPA personJPA = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);
        String expected = "23/12/1992";

        String result = personJPA.getBirthdate();

        assertEquals(expected,result);
    }

    @Test
    @Tag("US010")
    void getVatTest() {
        PersonJPA personJPA = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);
        int expected = 999999999;

        int result = personJPA.getVat();

        assertEquals(expected,result);
    }

    @Test
    @Tag("US010")
    void getFamilyidTest() {
        PersonJPA personJPA = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);
        String expected = familyIDJPA.toString();

        String result = personJPA.getFamilyid().toString();

        assertEquals(expected,result);
    }

    @Test
    @Tag("US010")
    void getIdTest() {
        PersonJPA personJPA = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);
        String expected = personIDJPA.toString();

        String result = personJPA.getId().toString();

        assertEquals(expected,result);
    }

    @Test
    @Tag("US010")
    void testEqualsSameObject() {
        PersonJPA personJPAOne = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);
        PersonJPA personJPATwo = personJPAOne;

        assertEquals(personJPAOne,personJPATwo);
    }

    @Test
    @Tag("US010")
    void testEqualsEqualPersonJPA() {
        PersonJPA personJPAOne = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);
        PersonJPA personJPATwo = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);

        assertEquals(personJPAOne,personJPATwo);
        assertNotSame(personJPAOne,personJPATwo);
    }


    @Test
    @Tag("US010")
    void testEqualsDifferentObjectTypes() {
        PersonJPA personJPAOne = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);

        assertNotEquals(personJPAOne,personIDJPA);
    }

    @Test
    @Tag("US010")
    void testEqualsDifferentPersonJPA() {
        PersonJPA personJPAOne = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);
        PersonJPA personJPATwo = new PersonJPA(personIDJPA, "otherPerson", birthdate, vat, familyIDJPA);

        assertNotEquals(personJPAOne,personJPATwo);
    }

    @Test
    @Tag("US010")
    void testHashCodeEqualPersonJPA() {
        PersonJPA personJPAOne = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);
        PersonJPA personJPATwo = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);

        assertEquals(personJPAOne.hashCode(),personJPATwo.hashCode());
        assertNotSame(personJPAOne,personJPATwo);
    }

    @Test
    @Tag("US010")
    void testHashCodeDifferentPersonJPA() {
        PersonJPA personJPAOne = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);
        PersonJPA personJPATwo = new PersonJPA(personIDJPA, "otherPerson", birthdate, vat, familyIDJPA);

        assertNotEquals(personJPAOne.hashCode(),personJPATwo.hashCode());
    }

}