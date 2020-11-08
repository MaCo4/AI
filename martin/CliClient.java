package martin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Magnus C. Hyll <magnus@hyll.no>
 */
public class CliClient implements Client {
    
    private BufferedReader in;
    private boolean readonly;
    private boolean debug;
    
    public CliClient() {
        try {
            in = new BufferedReader(new InputStreamReader(System.in, "windows-1252"));
        } catch (UnsupportedEncodingException ex) {
            in = new BufferedReader(new InputStreamReader(System.in));
        }
        
        try {
            System.out.print("Debug? (y/N) ");
            debug = in.readLine().trim().equalsIgnoreCase("y");
            System.out.println(debug ? "Debugging is on" : "Not debugging");
            
            System.out.print("Readonly? (y/N) ");
            readonly = in.readLine().trim().equalsIgnoreCase("y");
            System.out.println(readonly ? "Readonly mode on" : "Readonly NOT active");
        } catch (IOException ex) {
            println("Error: " + ex.getMessage() + ", using default");
            debug = true;
            readonly = true;
        }
    }

    public void println(String line) {
        System.out.println(line);
    }

    public String readln() {
        System.out.print("> ");
        try {
            return in.readLine();
        } catch (IOException ex) {
            println("Error reading line: " + ex.getMessage());
        }
        
        return "";
    }
    
    public boolean isDebugging() {
        return debug;
    }
    
    public boolean isReadonly() {
        return readonly;
    }
    
    public void setStatText(String text) {
        println("[STATS]: " + text);
    }
    
}
