package searcher;

import entity.SearchNode;

import java.io.File;
import java.util.*;

public class BFSSearcher implements Searcher{
    private String pattern;
    private String root;
    private Deque<SearchNode> pending;
    private Set<SearchNode> finished;
    public BFSSearcher(String pattern, String root){
        this.pattern = pattern;
        this.root = root;
        pending = new ArrayDeque<>();
        finished = new HashSet<>();
    }
    @Override
    public String getGoalPattern(String pattern){
        return pattern;
    }

    @Override
    public String getRoot(){
        return root;
    }

    @Override
    public void performSearch(String startNode){
        pending.offerFirst(new SearchNode(startNode));
        while (!pending.isEmpty()) {
            SearchNode cur = pending.pollFirst();
            if (finished.add(cur)) {
                String currentURL = cur.getNodeName();
                String contents = Utilities.getFileContents(root + File.separator + currentURL);
                if (Searcher.isGoal(contents, pattern)) {
                    cur.reportSolutionPath();
                    System.out.print(finished.size());
                    System.out.println(" Node(s) visited");
                    return;
                }
                expandNode(cur, contents);
            }
        }
        System.out.println("Nothing found");
    }

    private void expandNode(SearchNode Parent, String contents) {
        StringTokenizer st = new StringTokenizer(contents);
        SearchNode temp;
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.equalsIgnoreCase("<A")) {
                String hyperlink; // The name of the child node.
                token = st.nextToken();
                if (!token.equalsIgnoreCase("HREF")) {
                    System.out.println("Expecting 'HREF' and got: " + token);
                }
                token = st.nextToken();
                if (!token.equalsIgnoreCase("=")) {
                    System.out.println("Expecting '=' and got: " + token);
                }
                hyperlink = st.nextToken();
                if (!hyperlink.startsWith("page")) {
                    System.out.println("Expecting 'page#.html' and got: " + hyperlink);
                }
                token = st.nextToken();
                if (!token.equalsIgnoreCase(">")) {
                    System.out.println("Expecting '>' and got: " + token);
                }
                temp = new SearchNode(hyperlink);
                temp.parent = Parent;
                pending.offerLast(temp);
            }
        }
    }
}
