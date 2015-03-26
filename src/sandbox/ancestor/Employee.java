/**
 *
 */
package sandbox.ancestor;


/**
 * @author Andy Tanoko
 *
 */
public class Employee {

    private String name;
    private Employee boss;

    public Employee(final String name, final Employee boss) {
        this.name = name;
        this.boss = boss;
    }
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Employee getBoss() {
        return boss;
    }

    public void setBoss(final Employee boss) {
        this.boss = boss;
    }

    @Override
    public String toString() {
        return name;
    }
}
