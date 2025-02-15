import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTester {

    private User user;

    public void setUp() {
        user = new User();
    }

    public void tearDown() {
        user = null;
    }

    @Test
    public void testConstructorRankStartsAtNegativeEight() {
        setUp();
        assertEquals(-8,user.getRank(), "Ensure that a new user starts at rank -8!");
        tearDown();
    }

    @Test
    public void testConstructorRankProgressStartsAtZero() {
        setUp();
        assertEquals(0,user.getProgress(), "Ensure that a new user starts weith a rank progress of 0!");
        tearDown();
    }

    public void test(int rankOfActivity, int expectedRank, int expectedProgress) {
        user.incProgress(rankOfActivity);
        assertEquals(expectedRank, user.getRank(), "After applying a rank of " + rankOfActivity);
        assertEquals(expectedProgress, user.getProgress(), "After applying a rank of " + rankOfActivity);

    }

    @Test
    public void testVariousRankUpActivity() {
        setUp();

        test(-8, -8, 3);

        user = new User();
        test(-7, -8, 10);

        user = new User();
        test(-6, -8, 40);

        user = new User();
        test(-5, -8, 90);

        user = new User();
        test(-4, -7, 60);

        user = new User();
        test(-8, -8, 3);

        tearDown();
    }

    @Test
    public void testRankUpActivity() {
        setUp();
        test(1, -2, 40);
        test(1, -2, 80);
        test(1, -1, 20);
        test(1, -1, 30);
        test(1, -1, 40);
        test(2, -1, 80);
        test(2, 1, 20);
        test(-1, 1, 21);
        tearDown();
    }

    @Test
    public void testActivityRankOutOfBoundsLessThanNegativeEight() {
        setUp();
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class, () -> user.incProgress(-9), "Expected incProgress to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contentEquals("The rank of an activity cannot be less than 8, 0, or greater than 8!"));
        tearDown();
    }

    @Test
    public void testActivityRankOutOfBoundsEqualToZero() {
        setUp();
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class, () -> user.incProgress(0), "Expected incProgress to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contentEquals("The rank of an activity cannot be less than 8, 0, or greater than 8!"));
        tearDown();
    }

    @Test
    public void testActivityRankOutOfBoundsGreaterThanEight() {
        setUp();
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class, () -> user.incProgress(9), "Expected incProgress to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contentEquals("The rank of an activity cannot be less than 8, 0, or greater than 8!"));
        tearDown();
    }

    @Test
    public void testToString() {
        setUp();
        assertEquals("User{" + "rank=" + -8 + ", progress=" + 0 + '}', user.toString(), "Ensure that you have implemented your toString method correctly!");
        tearDown();
    }
}

