package switchtwentytwenty.project.domain.model.user_data;

import java.util.Objects;

public class CCNumber {

    private String ccNumber;

    public CCNumber(String ccNumber) {

        if (!validateNumber(ccNumber)) {
            throw new IllegalArgumentException("Invalid CC Number");
        }
        this.ccNumber = ccNumber.toUpperCase();

    }

    public String getCcNumber() {
        return ccNumber;
    }

    /**
     * Method to validate a ID Number of the Portuguese Cartão do Cidadão
     * Adaptado de https://www.autenticacao.gov.pt/documents/
     *
     * @param ccNumber
     * @return true if ID valid
     */

    private boolean validateNumber(String ccNumber) {
        int sum = 0;
        boolean secondDigit = false;
        if (ccNumber == null || ccNumber.trim().length() == 0 || ccNumber.isEmpty()) {
            throw new IllegalArgumentException("CC Number is blank or empty");
        }
        if (ccNumber.length() != 12)
            throw new IllegalArgumentException("Invalid CC Number size");
        String ccNumberUpper = ccNumber.toUpperCase();
        for (int i = ccNumberUpper.length() - 1; i >= 0; --i) {
            int value = GetNumberFromChar(ccNumberUpper.charAt(i));
            if (secondDigit) {
                value *= 2;
                if (value > 9)
                    value -= 9;
            }
            sum += value;
            secondDigit = !secondDigit;
        }
        return (sum % 10) == 0;
    }

    /**
     * Method to translate characters to its value
     * Adaptado de https://www.autenticacao.gov.pt/documents/
     *
     * @param letter
     * @return int representing the value of the character
     * Adaptado de https://www.autenticacao.gov.pt/documents/
     */
    private int GetNumberFromChar(char letter) {
        switch (letter) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            /*case 'A':
                return 10;*/
            case 'B':
                return 11;
            case 'C':
                return 12;
            /*case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            case 'G':
                return 16;
            case 'H':
                return 17;
            case 'I':
                return 18;
            case 'J':
                return 19;
            case 'K':
                return 20;
            case 'L':
                return 21;
            case 'M':
                return 22;
            case 'N':
                return 23;
            case 'O':
                return 24;
            case 'P':
                return 25;
            case 'Q':
                return 26;
            case 'R':
                return 27;
            case 'S':
                return 28;
            case 'T':
                return 29;
            case 'U':
                return 30;*/
            case 'V':
                return 31;
            case 'W':
                return 32;
            case 'X':
                return 33;
            case 'Y':
                return 34;
            case 'Z':
                return 35;
            default:
                throw new IllegalArgumentException("Invalid CC Number");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CCNumber)) return false;
        CCNumber that = (CCNumber) o;
        return Objects.equals(ccNumber, that.ccNumber);
    }
}
