package Validator;

import Card.CreditCard;
import ValidityChecks.CardNumberWellFormedValidator;
import ValidityChecks.LuhnCheckValidator;

/**
 *  Library to Handle CreditCard Validations.
 */
public class CreditCardValidator {

    private CreditCard mCard;

    private boolean mIsWellFormedNumber;
    private boolean mIsSuccessfulInLuhnCheck;

    public CreditCardValidator(CreditCard card) {
        mCard = card;
    }

    public boolean isValid() {
        mIsWellFormedNumber = CardNumberWellFormedValidator.validate(mCard);
        mIsSuccessfulInLuhnCheck = new LuhnCheckValidator(mCard).validate();

        return mIsWellFormedNumber && mIsSuccessfulInLuhnCheck;
    }

}
