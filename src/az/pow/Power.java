/**
 *
 */
package az.pow;


/**
 * @author Andy Tanoko
 *
 */
public class Power {

    public static int pow(final int num) {
        if(num - 1 > 0) {
            return 1 << (num - 1);
        } else {
            return 1;
        }
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        // TODO Auto-generated method stub
        System.out.println(pow(1));
        System.out.println(pow(2));
        System.out.println(pow(3));
        System.out.println(pow(4));
        System.out.println(pow(-3));
    }

}
