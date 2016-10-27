package martin;

/**
 *
 * @author Magnus C. Hyll <magnus@hyll.no>
 */
public interface Client {
    
    public void println(String line);
    
    public String readln();
    
    public boolean isDebugging();
    
    public boolean isReadonly();
    
    public void setStatText(String text);
}
