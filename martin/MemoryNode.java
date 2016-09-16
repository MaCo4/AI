package martin;

/**
 *
 * @author Magnus C. Hyll <magnus@hyll.no>
 */
public class MemoryNode {
    public int id;
    public String value;
    public int[] connections;
    
    public MemoryNode(int id, String value, int[] connections) {
        this.id = id;
        this.value = value;
        this.connections = connections;
    }
}
