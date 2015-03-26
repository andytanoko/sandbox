/**
 *
 */
package ss.employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Andy Tanoko
 *
 */
public class Hierarchy {

    Map<String, Employee> allEEs = new LinkedHashMap<String, Employee>();

    public void addEE(final String name1, final String name2) {

        Employee e1 = genEe(name1);
        Employee e2 = genEe(name2);
        e1.subList.add(e2);
        e2.bossList.add(e1);
    }

    public Employee genEe(final String name) {
        if(allEEs.get(name) != null) {
            return allEEs.get(name);
        } else {
            Employee ee = new Employee(name);
            allEEs.put(name, ee);
            return ee;
        }
    }

    Collection<Employee> getSubFromMultiEes(final Collection<Employee> inputList) {
        Set<Employee> result = new HashSet<Employee>();
        for(Employee e : inputList) {

            result.addAll(e.subList);
        }
        return result;
    }

    void bfs(Employee e) {
        Set<Employee> visited = new HashSet<Employee>();
        Queue<Employee> que = new LinkedList<Employee>();

        que.add(e);
        visited.add(e);
        Employee tempE = null;
        while(!que.isEmpty()) {

            e = que.remove();

            System.out.print(e.toString() + " ");
            if(tempE == null) {
                System.out.println();
            } else {
                if(e.bossList.contains(tempE)) {
                    System.out.println();
                }
            }

            for(Employee sub : e.subList) {
                if(!visited.contains(sub)) {
                    que.add(sub);
                    visited.add(sub);

                }

            }
            tempE = e;
        }
    }
    public void printall() {
        for(String key : allEEs.keySet()) {
            Employee e = allEEs.get(key);
            if(e.bossList.size() == 0) {
                if(!e.displayed) {
                    System.out.println(e);
                    e.displayed = true;
                }
                Collection<Employee> subList = e.subList;
                do {

                    for(Employee sub : subList) {
                        if(!sub.displayed) {
                            System.out.print(sub + " ");
                            sub.displayed = true;
                        }
                    }
                    System.out.println();
                    subList = getSubFromMultiEes(subList);
                } while(subList.size() > 0);

                // bfs(e);
                // System.out.println();
            }

        }

    }

    public static void printHir() {

        Hierarchy hir = new Hierarchy();
        hir.addEE("Jon", "Steve");
        hir.addEE("Jon", "Mitt");
        hir.addEE("Steve", "Joe");
        hir.addEE("Steve", "Ivy");
        hir.addEE("Mitt", "Matt");

        hir.addEE("Munch", "Mich");
        hir.addEE("Munch", "Mutt");
        hir.printall();

    }

    public static void main(final String[] str) {
        printHir();
    }

    class Employee {

        Employee(final String nm) {
            this.name = nm;
        }
        String name;
        boolean displayed = false;
        List<Employee> bossList = new ArrayList<Employee>();
        List<Employee> subList = new ArrayList<Employee>();

        @Override
        public String toString() {
            return name;
        }
    }
}
