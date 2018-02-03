import org.junit.runner.RunWith;
import org.junit.runners.Suite;


    @RunWith(Suite.class)

    @Suite.SuiteClasses({
            CreditCardTests.class,
            LuhnCheckValidatorTests.class,
            ValidityCheckTests.class
    })


    public class TestSuite {

    }
