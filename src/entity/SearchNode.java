package entity;

public class SearchNode implements Comparable<SearchNode>{
    public SearchNode parent;
    public int score;
    final String nodeName;
    public SearchNode(String name) {
        nodeName = name;
    }

    public void reportSolutionPath() {
        SearchNode cur;
        cur = this;
        int count = 0;
        System.out.println("The path is :");
        while (cur != null) {
            System.out.print(cur.getNodeName());
            System.out.print(" -> ");
            cur = cur.parent;
            count++;
        }
        System.out.println();
        System.out.print(count);
        System.out.println(" Node(s) on the path");
    }

    public String getNodeName() {
        return nodeName;
    }
    @Override
    public int compareTo(SearchNode s) {
        if (score == s.score) {
            return 0;
        }
        return score > s.score ? -1 : 1;
    }
    @Override
    public int hashCode(){
        return nodeName.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SearchNode)) {
            return false;
        }
        SearchNode s = (SearchNode) o;
        return this.nodeName.equals(s.getNodeName());
    }
}
