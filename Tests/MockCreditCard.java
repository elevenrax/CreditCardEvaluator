import Card.CreditCard;

public class MockCreditCard extends CreditCard {

    public MockCreditCard(String cardNumber) {
        // Gets around RuntimeException thrown when cannot identify
        super("6000000");                   // We lie about who we are.
        super.setCreditCardBrand(CardBrand.VISA);      // We make ourselves a Visa.
        super.setCreditCardNumber(cardNumber);         // We set any old value as the card number.
    }

}
