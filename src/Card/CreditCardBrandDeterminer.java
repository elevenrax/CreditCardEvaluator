package Card;

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
public class CreditCardBrandDeterminer {

    public static CreditCard.CardBrand determineBrand(CreditCard card) {
        int brandNumber = card.getFirstSixDigitsForBrandIdentification();
        CreditCard.CardBrand brand;

        // VISA
        if ( numberInRange(brandNumber, 400000, 499999) ) {
            brand = CreditCard.CardBrand.VISA;
        }

        // MASTERCARD
        else if ( numberInRange(brandNumber, 222100, 272099)
                  || numberInRange(brandNumber, 510000, 559999) ) {
            brand = CreditCard.CardBrand.MASTERCARD;
        }

        // CHINA UNION PAY (CUP) NB. overlaps with Maestro, but takes priority.
        else if ( numberInRange(brandNumber, 620000, 629999) ) {
            brand = CreditCard.CardBrand.CHINA_UNION_PAY;
        }

        // MAESTRO
        else if ( numberInRange(brandNumber, 500000, 509999)
                || numberInRange(brandNumber, 560000, 699999) ) {
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
