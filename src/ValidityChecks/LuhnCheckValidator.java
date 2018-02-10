package ValidityChecks;

import Card.CreditCard;

import java.util.Arrays;


/**
 *  The Luhn Algorithm works as follows (for determining validity in this context, not calculating the check-digit).
 *
 *  1. Determine whether the length (LEN) of the digits for the card number is odd or even.
 *      i.e. 1234 1234 is even
 *           1234 123  is odd
 *
 *  2. If LEN is even. Double every value at an even index (nb: index start with zero).
 *     If LEN is odd. Double every value at an odd index.
 *        Never double the last digit. Although the algorithm described in (2) won't let you.
 *
 *  3. If doubling a number yields a number GREATER THAN 9, subtract 9 from that digit.
 *          Note: LEN is odd, therefore we double indexes {1, 3}.
 *      card no:        1  2  3  6  5
 *      double :        1  4  3  12 5
 *      fix > 9:        2  4  3  3  5     << Val at index 3 is 3 (12 - 9).
 *
 *  4. Sum each number from your new result.
 *                      2  4  3  3  5
 *                             SUM: 17
 *
 *  5. If `SUM % 10 == 0` then a number is said to be valid.
 *              17 % 10 == 7, therefore not valid.
 */
public class LuhnCheckValidator {

    private CreditCard mCard;

    public LuhnCheckValidator(CreditCard card) {
        mCard = card;
    }

    /**
     * Performs the full LuhnCheck Validation on a CreditCard
     * @return Whether the card is valid under the LuhnCheck
     */
    public boolean validate() {

        // Set up
        String cardNumberAsString = mCard.getCreditCardNumber();
        int creditCardNumberLength = cardNumberAsString.length();

        // The Credit Card int array we will work with
        int[] cardNumberIntArray = new int[creditCardNumberLength];

        for (int i = 0; i < creditCardNumberLength; i++) {
            cardNumberIntArray[i] = Character.digit(cardNumberAsString.charAt(i), 10);
        }

        /*
            Now Stop, Double-Time.
            In this step we do the following:
                For EVEN length numbers, we double every even index value (nb. indexes start at zero).
                For ODD length numbers, we double every odd index value.
            The last digit is the check-digit and it never gets doubled.
         */
        boolean isEvenNumberCardNumber = isCardNumberEvenInLength(creditCardNumberLength);

        // Apply doubling formula.
        applyLuhnDouble(cardNumberIntArray, isEvenNumberCardNumber);

        // Valid if the sum off all numbers ends in zero.
        return isLuhnValid(cardNumberIntArray);
    }


    /**
     * Determines whether the card is even or odd in length
     * @param creditCardNumberLength The Length of the credit card
     * @return Whether the card is even in length
     */
    private boolean isCardNumberEvenInLength(int creditCardNumberLength) {
        boolean isEvenNumberCardNumber = false;
        if ((creditCardNumberLength % 2) == 0) {
            isEvenNumberCardNumber = true;
        }
        return isEvenNumberCardNumber;
    }


    /**
     * Doubles the second left most digit per the requirements of the Luhh Algorithm.
     * @param creditCardNumbers The credit card number as an int array
     * @param isEvenNumberCardNumber Whether the card is odd or even in length
     */
    private void applyLuhnDouble(int[] creditCardNumbers, boolean isEvenNumberCardNumber) {
        for (int i=0; i < creditCardNumbers.length; i++) {
            if ( shouldDoubleValueAtThisIndex(i, isEvenNumberCardNumber) ) {
                creditCardNumbers[i] *= 2;

                // Subtract Nine from product if > 9
                if (creditCardNumbers[i] > 9) {
                    creditCardNumbers[i] -= 9;
                }
            }
        }
    }


    /**
     * Determines whether the credit card passes the Luhn Check
     * @param creditCardNumbers The int array of credit card numbers
     * @return Whether the card passes the Luhn Check.
     */
    private boolean isLuhnValid(int[] creditCardNumbers) {
        int sum = Arrays.stream(creditCardNumbers).sum();
        return sum % 10 == 0;
    }


    /**
     * Given an index and whether the card's number length is odd/even, decides whether the algorithm
     * requires a doubling of the number.
     * @param index - The index of the value you are processing
     * @param isEven - Whether the card is even number in length or odd.
     * @return Whether to apply doubling.
     */
    private boolean shouldDoubleValueAtThisIndex(int index, boolean isEven) {
        if (isEven) {
            // Card number is even in length. Therefore double value at every even index.
            if (index % 2 == 0) return true;
            else return false;
        }
        else {
            // Card number is not even in length. Therefore double value at every odd index.
            if (index % 2 == 0) return false;
            else return true;
        }
    }
}
