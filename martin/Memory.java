package martin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Magnus C. Hyll <magnus@hyll.no>
 */
public class Memory {
    
    public List<MemoryNode> memories;
    
    public Memory() {
        memories = new ArrayList<>();
    }
    
    public void loadFromFile(File file) throws FileNotFoundException, IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        memories = new ArrayList<>();
        while (in.available() > 0) {
            int nodeId = in.readInt();
            int nodeValueLen = in.readInt();
            byte[] nodeValueBuf = new byte[nodeValueLen];
            in.read(nodeValueBuf, 0, nodeValueBuf.length);
            String nodeValue = new String(nodeValueBuf);
            int nodeConnectionsNum = in.readInt();
            int[] nodeConnections = new int[nodeConnectionsNum];
            for (int i = 0; i < nodeConnectionsNum; i++) {
                nodeConnections[i] = in.readInt();
            }
            memories.add(new MemoryNode(nodeId, nodeValue, nodeConnections));
        }
    }
    
    public void saveToFile(File file) throws FileNotFoundException, IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        for (MemoryNode node : memories) {
            out.writeInt(node.id);
            int nodeValueLen = node.value.length();
            node.value.toCharArray();
            in.read(nodeValueBuf, 0, nodeValueBuf.length);
            String nodeValue = new String(nodeValueBuf);
            int nodeConnectionsNum = in.readInt();
            int[] nodeConnections = new int[nodeConnectionsNum];
            for (int i = 0; i < nodeConnectionsNum; i++) {
                nodeConnections[i] = in.readInt();
            }
        }
    }
}
