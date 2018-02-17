import searcher.Searcher;
import searcher.SearcherFactory;

public class WebSearch {
    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("You must provide the directoryName and searchStrategyName.  Please try again.");
        } else {
            String directoryName = args[0]; // Read the search strategy to use.
            String searchStrategyName = args[1]; // Read the search strategy to use.
            Searcher mySearcher = SearcherFactory.getSearcher(
                    searchStrategyName,
                    "QUERY1 QUERY2 QUERY3 QUERY4",
                    directoryName);
            mySearcher.performSearch("page1.html");
        }
    }
}
