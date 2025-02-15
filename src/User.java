import java.util.Arrays;

public class User {
    private static final int[] validRanks = { -8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8 };
    private int rank;
    private int progress;

    public User() {
        this.rank = -8;
        this.progress = 0;
    }

    public int getRank() {
        return rank;
    }

    public int getProgress() {
        return progress;
    }

    public void incProgress(int activityRank) {
        if (!isValidRank(activityRank)) {
            throw new IllegalArgumentException("The rank of an activity cannot be less than 8, 0, or greater than 8!");
        }
        if (rank == 8) {
            return;
        }

        int userIndex = getRankIndex(rank);
        int activityIndex = getRankIndex(activityRank);

        int delta = calculateProgressDelta(userIndex, activityIndex);
        progress += delta;

        processProgress();
    }

    private boolean isValidRank(int r) {
        for (int validRank : validRanks) {
            if (validRank == r) {
                return true;
            }
        }
        return false;
    }

    private int getRankIndex(int rank) {
        for (int i = 0; i < validRanks.length; i++) {
            if (validRanks[i] == rank) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid rank");
    }

    private int calculateProgressDelta(int userIndex, int activityIndex) {
        if (activityIndex < userIndex) {
            int diff = userIndex - activityIndex;
            return diff == 1 ? 1 : 0;
        } else if (activityIndex == userIndex) {
            return 3;
        } else {
            int d = activityIndex - userIndex;
            return 10 * d * d;
        }
    }

    private void processProgress() {
        if (rank == 8) {
            progress = 0;
            return;
        }

        int currentIndex = getRankIndex(rank);
        while (progress >= 100 && currentIndex < validRanks.length - 1) {
            progress -= 100;
            currentIndex += 1;
            if (currentIndex == validRanks.length - 1) {
                progress = 0;
                break;
            }
        }

        rank = validRanks[currentIndex];
        if (currentIndex == validRanks.length - 1) {
            progress = 0;
        }
    }

    public String toString() {
        return "User{" + "rank=" + rank + ", progress=" + progress + '}';
    }
}