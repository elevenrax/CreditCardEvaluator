import Card.CreditCard;
import ValidityChecks.CardNumberWellFormedValidator;
import org.junit.Test;
import org.junit.Assert;


/**
 *  Test CardNumberWellFormedValidator.
 *  NB. Mock CreditCard used to bypass RuntimeException thrown when a card's number is not in the known range.
 */
public class ValidityCheckTests {

    @Test
    public void cardWithLettersShouldBeInvalid() {
        MockCreditCard card = new MockCreditCard("HanShotFirst");
        boolean result = CardNumberWellFormedValidator.validate(card);
        Assert.assertFalse("Expected false: CreditCard number is made of text values", result);
    }

    @Test
    public void cardStartingWithZeroShouldBeInvalid() {
        MockCreditCard card = new MockCreditCard("01234356643414");
        boolean result = CardNumberWellFormedValidator.validate(card);
        Assert.assertFalse("Expected false: CreditCard number starts with zero", result);
    }

    @Test
    public void cardNumberLessThanTwelveInLengthShouldBeInvalid() {
        String number = "45634523434";
        System.out.println("Testing Length: " + number.length());
        MockCreditCard card = new MockCreditCard(number);
        boolean result = CardNumberWellFormedValidator.validate(card);
        Assert.assertFalse("Expected false: CreditCard number < 12 in length", result);
    }

    @Test
    public void cardNumberEqualToTwelveInLengthShouldBeValid() {
        String number = "456345234344";
        System.out.println("Testing Length: " + number.length());
        MockCreditCard card = new MockCreditCard(number);
        boolean result = CardNumberWellFormedValidator.validate(card);
        Assert.assertTrue("Expected true: CreditCard number == 12 in length and is valid", result);
    }

    @Test
    public void cardNumberGreaterThanNineteenInLengthShouldBeInvalid() {
        String number = "34524465437643256465";
        System.out.println("Testing Length: " + number.length());
        MockCreditCard card = new MockCreditCard(number);
        boolean result = CardNumberWellFormedValidator.validate(card);
        Assert.assertFalse("Expected false: CreditCard number > 19 in length", result);
    }

    @Test
    public void cardNumberEqualToNineteenInLengthShouldBeValid() {
        String number = "3452446543764325465";
        System.out.println("Testing Length: " + number.length());
        MockCreditCard card = new MockCreditCard(number);
        boolean result = CardNumberWellFormedValidator.validate(card);
        Assert.assertTrue("Expected true: CreditCard number == 19 in length and is valid", result);
    }

    @Test
    public void cardPassesCardWellFormedValidatorShouldBeValid() {
        CreditCard card = new CreditCard("4000345354545420");
        boolean result = CardNumberWellFormedValidator.validate(card);
        Assert.assertTrue("Expect true: Card meeets proper critiera", result);
    }
}
