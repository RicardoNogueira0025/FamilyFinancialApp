package switchtwentytwenty.project.domain.valueobject;

import switchtwentytwenty.project.exceptions.InvalidZipCodeException;

import java.util.Objects;
import java.util.regex.Pattern;

public class ZipCode implements ValueObject {

    private String zipCode;
    private final static String INVALIDZIPCODE = "Invalid Zip Code";


    public ZipCode(String zipCode) {
        this.zipCode = zipCode;
        validateData();
    }

    private void validateData() {
        checkZipCode();
    }

    private void checkZipCode() {
        if (!validateZipCode()) {
            throw new InvalidZipCodeException(INVALIDZIPCODE);
        }
    }

    private boolean validateZipCode() {
        String regex = "\\d{4}(-\\d{3})?";
        boolean result = Pattern.matches(regex, zipCode);
        if (zipCode == null || zipCode.trim().length() == 0 || zipCode.isEmpty()) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZipCode zipCode1 = (ZipCode) o;
        return zipCode.equals(zipCode1.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode);
    }
}