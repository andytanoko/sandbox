/**
 *
 */
package ss.bf;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

// javac -cp .:junit.jar Graph.java GraphTest.java
// java -cp .:junit.jar:hamcrest-core.jar org.junit.runner.JUnitCore GraphTest

@RunWith(JUnit4.class)
public class GraphTest {

    private static class CrumbtrailVisitor implements Graph.Visitor<String> {

        private final StringBuilder sb = new StringBuilder();

        @Override
        public void visit(final String vertex) {
            sb.append(' ').append(vertex);
        }

        @Override
        public String toString() {
            return sb.substring(1);
        }
    };

    public static Graph<String> graph1;

    @BeforeClass
    public static void makeGraphs() {
        Graph<String> g = graph1 = new Graph<String>();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("B", "D");
        // g.addEdge("B", "A");
        g.addEdge("B", "E");
        g.addEdge("B", "F");
        g.addEdge("C", "A");
        g.addEdge("D", "C");
        g.addEdge("E", "B");
        g.addEdge("F", "B");
    }

    @Test
    public void preOrderVisitorFromA() {
        Graph.Visitor<String> crumbtrailVisitor = new CrumbtrailVisitor();
        graph1.preOrderTraversal("A", crumbtrailVisitor);
        assertEquals("A B C D E F", crumbtrailVisitor.toString());
    }

    @Test
    public void breadthFirstVisitorFromB() {
        Graph.Visitor<String> crumbtrailVisitor = new CrumbtrailVisitor();
        graph1.breadthFirstTraversal("B", crumbtrailVisitor);
        assertEquals("B C D E F A", crumbtrailVisitor.toString());
    }
}