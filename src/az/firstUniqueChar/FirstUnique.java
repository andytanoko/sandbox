/**
 *
 */
package az.firstUniqueChar;

import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * @author Andy Tanoko
 *
 */
public class FirstUnique {

    String findFirstUnique(final String str) {
        HashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
        for(int i = 0; i < str.length(); i++) {
            if(map.get(String.valueOf(str.charAt(i))) == null) {
                map.put(String.valueOf(str.charAt(i)), 1);
            } else {
                Integer newVal = map.get(String.valueOf(str.charAt(i))) + 1;
                map.put(String.valueOf(str.charAt(i)), newVal);
            }
        }

        for(String key : map.keySet()) {
            if(map.get(key) == 1) {
                return key;
            }
        }

        return "<NO UNIQUE CHARACTER>";
    }

    public static void main(final String[] args) {
        FirstUnique fu = new FirstUnique();
        System.out.println(fu.findFirstUnique("abacadabra"));
        System.out.println(fu.findFirstUnique("swissbell hotel"));
        System.out.println(fu.findFirstUnique("aabbccdd"));
    }
}
