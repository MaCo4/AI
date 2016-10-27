package martin;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Magnus C. Hyll <magnus@hyll.no>
 */
public class WindowClient implements Client {
    
    private ClientFrame frame;
    
    public WindowClient() {
        frame = new ClientFrame();
    }
    
    public void println(String line) {
        frame.appendOutputLine(line);
    }

    public String readln() {
        // TODO Add code
        // Should block until line is available, using frame.wait() e.g.
        return null;
    }

    public void setStatText(String text) {
        frame.setStatText(text);
    }
    
    public boolean isDebugging() {
        return frame.isDebugging();
    }

    public boolean isReadonly() {
        return frame.isReadonly();
    }
}
