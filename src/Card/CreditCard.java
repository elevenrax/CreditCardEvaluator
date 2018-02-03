package Card;

/**
 *  Represents a Credit Card.
 */
public class CreditCard {

    public enum CardBrand { VISA, MASTERCARD, MAESTRO, CHINA_UNION_PAY }

    private String mCreditCardNumber;
    private CardBrand mCreditCardBrand;

    public CreditCard(String cardNumber) {
        mCreditCardNumber = cardNumber.replaceAll("\\s+","");
        mCreditCardBrand = CreditCardBrandDeterminer.determineBrand(this);
    }

    public void setCreditCardNumber(String cardNumber) {
        mCreditCardNumber = cardNumber;
    }

    public String getCreditCardNumber() {
        return mCreditCardNumber;
    }

    public void setCreditCardBrand(CardBrand brand) {
        mCreditCardBrand = brand;
    }

    public CardBrand getCreditCardBrand() {
        return mCreditCardBrand;
    }

    /**
     * A credit card's brand is identified by the first six digits.
     * @return the brand-identifying digits.
     */
    public int getFirstSixDigitsForBrandIdentification() {
        int brandNumber = -1;
        try {
            String brandNumberAsString = this.getCreditCardNumber().substring(0,6);
            brandNumber = Integer.parseInt(brandNumberAsString);
        }
        catch (NumberFormatException ex) {
            ex.toString();
        }

        return brandNumber;
    }

}
