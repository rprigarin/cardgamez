package cards.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ CardDeckTest.class, CardGameTest.class, OutputWritingTest.class, PlayerTest.class })
public class CardGameTestSuite {

}
