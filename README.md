# Credit Card Number Evaluator

A simple Java project to determine if Credit Card Numbers are valid. 

## Definition

A credit card number is assumed valid if:

* it contains only numbers and no leading 0
* it is 12-19 digits long
* It passes the Luhn check (https://en.wikipedia.org/wiki/Luhn_algorithm). For credit card
numbers, the Luhn check digit is the last digit of the sequence.

## How To Use
```
        // Make a new CreditCard
        // Will throw a RuntimeException if the card is not a known brand.
        CreditCard card = new CreditCard("222100345345");

        // What Brand is the card?
        System.out.println( card.getCreditCardBrand() );

        // Access ranges for CreditCard Brands
        System.out.println("Visa range is : " + CreditCardBrandDeterminer.VISA_MIN +
                " to " + CreditCardBrandDeterminer.VISA_MAX);

        // Is the Card Valid? This validates the card against ALL criteria.
        // A card is only valid if it passes all the tests.
        CreditCardValidator validator = new CreditCardValidator(card);
        System.out.println("Is Card Valid? " + validator.isValid());

        // Granular Validity - Test if the card is well formed, this includes:
        // No letters, no numbers starting with a zero, and between 12 - 19 digits
        boolean numberWellFormed = CardNumberWellFormedValidator.validate(card);
        System.out.println("Is Number Well Formed? " + numberWellFormed);

        boolean luhnCheckSuccess = new LuhnCheckValidator(card).validate();
        System.out.println("Is Successful in LuhnCheck? " + luhnCheckSuccess);
```

## Design Choices and Assumptions

The biggest assumption made was in `CardNumberWellFormedValidator`. This will return `false` at the first instance
of invalid criteria. As I do not know the use-case of the team, I assumed that speed of processing cards was
preferred over an operator wanting to probe each of the Invalid criteria. Given the number of cards that can
be processed, the pass/fail is what is really important. 

## Next Features?

### Threading
Implement both: `LuhnCheckValidator` and `CardNumberWellFormedValidator` as Suppliers. 

The `CreditCardValidator` will then call them in a `CompletableFuture` to be managed by an 
`ExecutorService`. This will allow large queues of CreditCards to be processed faster on a single machine.

### CardNumberWellFormedValidator

Perhaps operators do need more granularity on why a card failed? Depending on feedback, will modify class if needed.

## Testing

To observe the behaviour of the library, run `Tests/TestSuite` which will run all the individual unit tests.