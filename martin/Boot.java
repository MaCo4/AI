package martin;

/**
 *
 * @author Magnus C. Hyll <magnus@hyll.no>
 */
public class Boot {
    public static void main(String[] args) {
        Main main = new Main(new CliClient());
        main.start();
    }
}
