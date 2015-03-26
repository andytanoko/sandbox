/**
 *
 */
package sandbox.ancestor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Andy Tanoko
 *
 */
public class TCA {

    private List<Employee> getBCList(final Employee ee) {
        LinkedList<Employee> listEe = new LinkedList<Employee>();
        Employee curEE = ee;
        while(curEE != null) {
            listEe.push(curEE);
            curEE = curEE.getBoss();
        }
        return listEe;
    }

    private Employee fc(final Employee eeA, final Employee eeB) {
        List<Employee> bcA = getBCList(eeA);
        List<Employee> bcB = getBCList(eeB);

        /*
         * for(Employee ea : bcA) { for(Employee eb : bcB) { if(ea.equals(eb)) { return ea; } } }
         */

        Employee lastMatching = new Employee("GOD", null);
        if(!bcA.get(0).equals(bcB.get(0))) {
            return lastMatching;
        }

        Employee eea = null;
        Employee eeb = null;
        int idxa = 0;
        int idxb = 0;

        while(idxa < bcA.size() || idxb < bcB.size()) {
            if(idxa < bcA.size()) {
                eea = bcA.get(idxa);
            }
            if(idxb < bcB.size()) {
                eeb = bcB.get(idxb);
            }
            if(!eea.equals(eeb)) {

                return lastMatching;
            } else {
                lastMatching = eea;
            }
            idxa++;
            idxb++;

        }
        return lastMatching;

    }

    public static void main(final String[] args) {

        Employee ceo = new Employee("CEO", null);
        Employee bob = new Employee("Bob", ceo);
        Employee sandy = new Employee("Sandy", bob);
        Employee plankton = new Employee("Plk", sandy);
        Employee patrick = new Employee("Patrick", bob);
        Employee mrcrab = new Employee("Crb", patrick);
        Employee garry = new Employee("Gary", patrick);
        Employee squidward = new Employee("Sqwr", garry);
        Employee joker = new Employee("JK", null);
        Employee superman = new Employee("SM", joker);
        Employee batman = new Employee("BM", joker);

        TCA tca = new TCA();

        System.out.println(tca.fc(plankton, squidward));
        System.out.println(tca.fc(plankton, bob));
        System.out.println(tca.fc(plankton, joker));
        System.out.println(tca.fc(mrcrab, squidward));

    }
}
