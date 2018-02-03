import Card.CreditCard;
import ValidityChecks.LuhnCheckValidator;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Card Number's supplied that are known to be valid or invalid.
 */
public class LuhnCheckValidatorTests {

    @Test
    public void visaShouldPassTest() {
        CreditCard card = new CreditCard("4929804463622139");
        LuhnCheckValidator validator = new LuhnCheckValidator(card);
        Assert.assertTrue("Should be valid", validator.validate());
    }

    @Test
    public void visaShouldPassFailTest() {
        CreditCard card = new CreditCard("4929804463622138");
        LuhnCheckValidator validator = new LuhnCheckValidator(card);
        Assert.assertFalse("Should not be valid", validator.validate());
    }

    @Test
    public void MaestroShouldPassTest() {
        CreditCard card = new CreditCard("6762765696545485");
        LuhnCheckValidator validator = new LuhnCheckValidator(card);
        Assert.assertTrue("Should be valid", validator.validate());
    }

    @Test
    public void mastercardShouldFailTest() {
        CreditCard card = new CreditCard("5212132012291762");
        LuhnCheckValidator validator = new LuhnCheckValidator(card);
        Assert.assertFalse("Should not be valid", validator.validate());
    }

    @Test
    public void chinaUnionPayShouldPassTest() {
        CreditCard card = new CreditCard("6210948000000029");
        LuhnCheckValidator validator = new LuhnCheckValidator(card);
        Assert.assertTrue("Should Be Valid", validator.validate());
    }
}
