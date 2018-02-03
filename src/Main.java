import Card.CreditCard;
import Validator.CreditCardValidator;
import ValidityChecks.CardNumberWellFormedValidator;
import ValidityChecks.LuhnCheckValidator;

public class Main {

    public static void main(String[] args) {
        /*
            Demo how to use the Library.
         */

        // Make a new CreditCard
        CreditCard card = new CreditCard("222100345345");

        // What Brand is it?
        System.out.println( card.getCreditCardBrand() );

        // Is the Card Valid?
        CreditCardValidator validator = new CreditCardValidator(card);
        System.out.println("Is Card Valid? " + validator.isValid());

        // Granular Validity
        boolean numberWellFormed = CardNumberWellFormedValidator.validate(card);
        System.out.println("Is Number Well Formed? " + numberWellFormed);

        boolean luhnCheckSuccess = new LuhnCheckValidator(card).validate();
        System.out.println("Is Successful in LuhnCheck? " + luhnCheckSuccess);

    }

}
