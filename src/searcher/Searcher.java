package searcher;

import entity.SearchNode;

public interface Searcher {
    public String getGoalPattern(String pattern);
    public String getRoot();
    public void performSearch(String cur);
    public static boolean isGoal(String contents, String GOAL_PATTERN){
        return (contents != null && contents.indexOf(GOAL_PATTERN) >= 0);
    }
}
