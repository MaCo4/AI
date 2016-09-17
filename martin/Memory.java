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
    private int nextId;

    public Memory() {
        memories = new ArrayList<>();
        nextId = 1;
    }

    public MemoryNode addMemory(String value) {
        if (hasMemory(value)) {
            return recallMemory(value);
        }
        int[] i = {};
        MemoryNode node = new MemoryNode(nextId++, value, i);
        memories.add(node);
        return node;
    }
    
    public MemoryNode recallMemory(String value) {
        for (MemoryNode node : memories) {
            if (node.value.equals(value)) {
                return node;
            }
        }
        return addMemory(value);
    }
    
    public MemoryNode recallMemory(int id) {
        for (MemoryNode node : memories) {
            if (node.id == id) {
                return node;
            }
        }
        return null;
    }
    
    public boolean hasMemory(String value) {
        for (MemoryNode node : memories) {
            if (node.value.equals(value)) {
                return true;
            }
        }
        return false;
    }
    
    public String findClosestMemory(String value) {
        int len = recallMemory(value).connections.length;
        if (len == 0) {
            return null;
        }
        int closest = recallMemory(value).connections[0];
        if (closest == 0) {
            return null;
        }
        return recallMemory(closest).value;
    }

    public void loadFromFile(File file) throws FileNotFoundException, IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        memories = new ArrayList<>();
        while (in.available() > 0) {
            int nodeId = in.readInt();
            if (nodeId > nextId) {
                nextId = nodeId + 1;
            }
            int nodeValueLen = in.readInt();
            byte[] nodeValueBuf = new byte[nodeValueLen];
            in.read(nodeValueBuf, 0, nodeValueBuf.length);
            String nodeValue = new String(nodeValueBuf, "UTF-8");
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
            byte[] nodeValueBuf = node.value.getBytes("UTF-8");
            out.writeInt(nodeValueBuf.length);
            out.write(nodeValueBuf);
            out.writeInt(node.connections.length);
            for (int i = 0; i < node.connections.length; i++) {
                out.writeInt(node.connections[i]);
            }
        }
    }
}
