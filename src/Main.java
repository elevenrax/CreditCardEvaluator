import Card.CreditCard;
import Card.CreditCardBrandDeterminer;
import Validator.CreditCardValidator;
import ValidityChecks.CardNumberWellFormedValidator;
import ValidityChecks.LuhnCheckValidator;

public class Main {

    public static void main(String[] args) {
        /*
            Demo how to use the Library.
         */

        // Make a new CreditCard
        // Will throw a RuntimeException if the card is not a known brand.
        CreditCard card = new CreditCard("222100345345");

        // What Brand is the card?
        System.out.println( card.getCreditCardBrand() );

        // Access ranges for CreditCard Brands
        System.out.println("Visa range is : " + CreditCardBrandDeterminer.VISA_MIN +
                " to " + CreditCardBrandDeterminer.VISA_MAX);

        // Is the Card Valid? This validates the card against ALL criteria.
        // A card is only valid if it passes all the tests.
        CreditCardValidator validator = new CreditCardValidator(card);
        System.out.println("Is Card Valid? " + validator.isValid());

        // Granular Validity - Test if the card is well formed, this includes:
        // No letters, no numbers starting with a zero, and between 12 - 19 digits
        boolean numberWellFormed = CardNumberWellFormedValidator.validate(card);
        System.out.println("Is Number Well Formed? " + numberWellFormed);

        boolean luhnCheckSuccess = new LuhnCheckValidator(card).validate();
        System.out.println("Is Successful in LuhnCheck? " + luhnCheckSuccess);
    }

}
