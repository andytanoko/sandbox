/**
 *
 */
package az.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andy Tanoko
 *
 */
public class Chain {

    public static boolean isChain(final String[] arrStr) {
        List<String> leftList = new ArrayList<String>();
        List<String> rightList = new ArrayList<String>();

        // List <String> tempList = new ArrayList<String>();
        for(String str : arrStr) {

            leftList.add(String.valueOf(str.charAt(0)));
            rightList.add(String.valueOf(str.charAt(str.length() - 1)));

        }

        if(leftList.size() != rightList.size()) {
            return false;
        }
        for(int i = leftList.size() - 1; i >= 0; i--) {
            String chr = leftList.get(i);
            if(rightList.contains(chr)) {
                rightList.remove(chr);
                leftList.remove(i);
            }
        }

        if(leftList.size() == rightList.size() &&
                (leftList.size() + rightList.size() == 2 || leftList.size() + rightList.size() == 0)) {
            return true;
        }
        return false;

    }



    public static void main(final String[] str) {
        System.out.println(isChain(new String[] {"abcd", "defg", "abalabal"}));
        System.out.println(isChain(new String[] {"abc", "def", "ghi", "cod", "fig"}));

    }
}
