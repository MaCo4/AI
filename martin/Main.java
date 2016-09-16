
package martin;

import java.util.Scanner;

/**
 *
 * @author Magnus C. Hyll <magnus@hyll.no>
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        while ((line = in.nextLine()) != null) {
            System.out.println("Ret: " + line);
        }
    }
}
