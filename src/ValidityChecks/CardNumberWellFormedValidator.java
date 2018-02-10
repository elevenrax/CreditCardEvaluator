package ValidityChecks;

import Card.CreditCard;

/**
 *  Validates a CreditCard. Returns false at the first instance of a failed criteria,
 *  as the card itself can not be considered valid if it fails any criteria. Speed of the check was
 *  favoured over telling the operator all the reasons it failed. We assumed a high through-put of CreditCards
 *  and made this design choice.
 *
 *  ASSUMPTION: A user of the library does not care why the card failed, just that it failed
 *      and should not be processed. Need more information on the team using this library to get
 *      a better idea about requirements.
 */
public class CardNumberWellFormedValidator {

    /**
     * Validates a credit card whether it is well formed.
     * @param card The credit card to validate
     * @return Whether the card is valid
     */
    public static boolean validate(CreditCard card) {

        String cardNumber = card.getCreditCardNumber();

        // Assert- Numbers only
        if ( ! cardNumber.matches("[0-9]+") ) {
            return false;
        }

        // Assert- No Leading Zero
        String firstDigit = cardNumber.substring(0,1);
        if (firstDigit.equals("0")) {
            return false;
        }

        // Assert- 12 to 19 digits long
        if (cardNumber.length() < 12 || cardNumber.length() > 19) {
            return false;
        }

        return true;
    }

}
