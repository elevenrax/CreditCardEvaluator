package Card;

/**
 *  A credit card is branded as follows:
 *     VISA:            400000 - 499999
 *     MasterCard:      222100 - 272099
 *                      510000 - 559999
 *     ChinaUnionPay:   620000 - 629999
 *     Maestro          500000 - 509999
 *                      560000 - 629999
 *
 *     Where CUP and Maesto overlap, CUP takes priority
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
