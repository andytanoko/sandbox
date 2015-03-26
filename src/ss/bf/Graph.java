/**
 *
 */
package ss.bf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph<T> {

    public static interface Visitor<T> {

        void visit(T vertex);
    }

    // Alternatively, use a Multimap:
    // http://google-collections.googlecode.com/svn/trunk/javadoc/com/google/common/collect/Multimap.html
    private final Map<T, List<T>> edges = new HashMap<T, List<T>>();

    public void addEdge(final T src, final T dest) {
        List<T> srcNeighbors = this.edges.get(src);
        if(srcNeighbors == null) {
            this.edges.put(src, srcNeighbors = new ArrayList<T>());
        }
        srcNeighbors.add(dest);
    }

    public Iterable<T> getNeighbors(final T vertex) {
        List<T> neighbors = this.edges.get(vertex);
        if(neighbors == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(neighbors);
        }
    }

    public void preOrderTraversal(final T vertex, final Visitor<T> visitor) {
        preOrderTraversal(vertex, visitor, new HashSet<T>());
    }

    private void preOrderTraversal(final T vertex, final Visitor<T> visitor, final Set<T> visited) {
        visitor.visit(vertex);
        visited.add(vertex);

        for(T neighbor : this.getNeighbors(vertex)) {
            // if neighbor has not been visited then recurse
            if(!visited.contains(neighbor)) {
                preOrderTraversal(neighbor, visitor, visited);
            }
        }
    }

    public void breadthFirstTraversal(T vertex, final Visitor<T> visitor) {
        Set<T> visited = new HashSet<T>();
        Queue<T> queue = new LinkedList<T>();

        queue.add(vertex); // Adds to end of queue
        visited.add(vertex);

        while(!queue.isEmpty()) {
            // removes from front of queue
            vertex = queue.remove();
            visitor.visit(vertex);

            // Visit child first before grandchild
            for(T neighbor : this.getNeighbors(vertex)) {
                if(!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

}