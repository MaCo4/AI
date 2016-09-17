package martin;

import java.util.Arrays;

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
    
    public void incConnection(int id) {
        int index = Arrays.asList(connections).indexOf(id);
        if (index != -1) {
            if (index > 0) {
                int before = connections[index - 1];
                connections[index - 1] = connections[index];
                connections[index] = before;
            }
        }
        else {
            addConnection(id);
        }
    }
    
    private void addConnection(int id) {
        int[] newArr = new int[connections.length + 1];
        System.arraycopy(connections, 0, newArr, 0, connections.length);
        connections = newArr;
        connections[connections.length - 1] = id;
    }
}
