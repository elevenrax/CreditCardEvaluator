import Card.CreditCard;
import org.junit.Assert;
import org.junit.Test;

/**
 *  A credit card is branded as follows:
 *     VISA:            400000 - 499999
 *     MasterCard:      222100 - 272099
 *     ChinaUnionPay:   620000 - 629999
 *     Maestro          500000 - 509999
 *                      560000 - 629999
 *
 *     Where CUP and Maesto overlap, CUP takes priority
 */
public class CreditCardTests {

    // Brand not in range
    @Test(expected = RuntimeException.class)
    public void cardNumberNotAValidBrandShouldThrowException() {
        CreditCard card = new CreditCard("300000343455355");
    }

    @Test
    public void creditCardCanHandleWhitespace() {
        CreditCard card = new CreditCard("    4000 3453 5454 5420");
        Assert.assertEquals(card.getCreditCardNumber(), "4000345354545420");
    }

    @Test
    public void cardIsProperlyBrandedAsCUPLower() {
        CreditCard card = new CreditCard("620000 000000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.CHINA_UNION_PAY);
    }

    @Test
    public void cardIsProperlyBrandedAsCUPUpper() {
        CreditCard card = new CreditCard("629999 000000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.CHINA_UNION_PAY);
    }

    @Test
    public void cardIsProperlyBrandedAsMaestroLowerAtEdgeOfCUP() {
        CreditCard card = new CreditCard("619999 999999");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.MAESTRO);
    }

    @Test
    public void cardIsProperlyBrandedAsMaestroUpperAtEdgeOfCUP() {
        CreditCard card = new CreditCard("630000 00000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.MAESTRO);
    }

    @Test
    public void cardIsProperlyBrandedAsMaestroMinRangeOne() {
        CreditCard card = new CreditCard("500000 00000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.MAESTRO);
    }

    @Test
    public void cardIsProperlyBrandedAsMaestroMaxRangeOne() {
        CreditCard card = new CreditCard("509999 00000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.MAESTRO);
    }

    @Test
    public void cardIsProperlyBrandedAsMaestroMinRangeTwo() {
        CreditCard card = new CreditCard("560000 00000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.MAESTRO);
    }

    @Test
    public void cardIsProperlyBrandedAsMaestroMaxRangeTwo() {
        CreditCard card = new CreditCard("699999 00000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.MAESTRO);
    }

    @Test
    public void cardIsProperlyBrandedAsVisaMin() {
        CreditCard card = new CreditCard("400000 00000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.VISA);
    }

    @Test
    public void cardIsProperlyBrandedAsVisaMax() {
        CreditCard card = new CreditCard("499999 00000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.VISA);
    }

    @Test
    public void cardIsProperlyBrandedAsMasterCardMinOne() {
        CreditCard card = new CreditCard("222100 00000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.MASTERCARD);
    }

    @Test
    public void cardIsProperlyBrandedAsMasterCardMaxOne() {
        CreditCard card = new CreditCard("272099 00000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.MASTERCARD);
    }

    @Test
    public void cardIsProperlyBrandedAsMasterCardMinTwo() {
        CreditCard card = new CreditCard("510000 00000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.MASTERCARD);
    }

    @Test
    public void cardIsProperlyBrandedAsMasterCardMaxTwo() {
        CreditCard card = new CreditCard("559999 00000");
        Assert.assertEquals(card.getCreditCardBrand(), CreditCard.CardBrand.MASTERCARD);
    }

}
