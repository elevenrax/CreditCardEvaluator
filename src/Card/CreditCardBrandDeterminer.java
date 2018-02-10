package Card;

/**
 *  Determines the Brand of a Credit Card
 *  The CreditCard itself handles its own brand.
 *  This class is invoked in the Constructor of CreditCard.
 */
public class CreditCardBrandDeterminer {

    // Constants for card range
    public static final int VISA_MIN = 400000;
    public static final int VISA_MAX = 499999;

    public static final int MASTERCARD_MIN_RANGE_ONE = 222100;
    public static final int MASTERCARD_MAX_RANGE_ONE = 272099;

    public static final int MASTERCARD_MIN_RANGE_TWO = 510000;
    public static final int MASTERCARD_MAX_RANGE_TWO = 559999;

    public static final int CUP_MIN = 620000;
    public static final int CUP_MAX = 629999;

    public static final int MAESTRO_MIN_RANGE_ONE = 500000;
    public static final int MAESTRO_MAX_RANGE_ONE = 509999;

    public static final int MAESTRO_MIN_RANGE_TWO = 560000;
    public static final int MAESTRO_MAX_RANGE_TWO = 699999;


    /**
     * Determines the brand of a credit card based on its first six digits
     * @param card - The Credit Card to get the brand
     * @return A value from the Enum Type CardBrand, corresponding to the brand of the card.
     */
    public static CreditCard.CardBrand determineBrand(CreditCard card) {
        int brandNumber = card.getFirstSixDigitsForBrandIdentification();
        CreditCard.CardBrand brand;

        // VISA
        if ( numberInRange(brandNumber, VISA_MIN, VISA_MAX) ) {
            brand = CreditCard.CardBrand.VISA;
        }

        // MASTERCARD
        else if ( numberInRange(brandNumber, MASTERCARD_MIN_RANGE_ONE, MASTERCARD_MAX_RANGE_ONE)
                  || numberInRange(brandNumber, MASTERCARD_MIN_RANGE_TWO, MASTERCARD_MAX_RANGE_TWO) ) {
            brand = CreditCard.CardBrand.MASTERCARD;
        }

        // CHINA UNION PAY (CUP) NB. overlaps with Maestro, but takes priority.
        else if ( numberInRange(brandNumber, CUP_MIN, CUP_MAX) ) {
            brand = CreditCard.CardBrand.CHINA_UNION_PAY;
        }

        // MAESTRO
        else if ( numberInRange(brandNumber, MAESTRO_MIN_RANGE_ONE, MAESTRO_MAX_RANGE_ONE)
                || numberInRange(brandNumber, MAESTRO_MIN_RANGE_TWO, MAESTRO_MAX_RANGE_TWO) ) {
            brand = CreditCard.CardBrand.MAESTRO;
        }

        // NOT A KNOWN BRAND
        else {
            // Could add enum `Unidentified`, but decided that an exception was preferable.
            throw new RuntimeException("Credit Card Brand Could Not Be Determined");
        }

        return brand;
    }

    private static boolean numberInRange(int number, int min, int max) {
        return ((number >= min) && (number <= max));
    }

}
