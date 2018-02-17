package searcher;

public class SearcherFactory {
    public static Searcher getSearcher(String strategy, String pattern, String root) {
        switch (strategy) {
            case "depth" : return new DFSSearcher(pattern, root);
            case "breadth" : return new BFSSearcher(pattern, root);
            default : return new BFSSearcher(pattern, root);
        }
    }
}
