/**
 *
 */
package sandbox;


/**
 * @author Andy Tanoko
 *
 */
public class TestSherlock {

    int gcd(final int x, final int y) {
        System.out.println(x + "," + y);
        if(x % y == 0) {
            return y;
        } else {
            return gcd(y, x % y);
        }
    }

    public static void main(final String[] args) {
        System.out.println(new TestSherlock().gcd(105, 50));
    }

}
