package martin;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Magnus C. Hyll <magnus@hyll.no>
 */
public class Main {
    
    private Client client;
    private Memory mem;
    
    private static String debugText;
    
    public Main(Client client) {
        this.client = client;
        mem = new Memory();
    }
    
    public void start() {
        loadMemory();
        
        String line;
        client.setStatText("Memories: " + mem.memories.size() + ", matrix size: " + mem.mat.getDim());
        client.println("\nI must learn more.");
        try {
            while (!"quit".equals(line = client.readln())) {
                String prevWord = null;
                String ret = "";
                for (String word : line.trim().split(" ")) {
                    if (word.equals("")) {
                        continue;
                    }
                    if (prevWord != null) {
                        MemoryNode curr = mem.recallMemory(word);
                        MemoryNode closest = mem.findClosestMemory(word);
                        if (closest == null) {
                            continue;
                        }
                        curr.incConnection(mem.recallMemory(prevWord).id);
                        mem.mat.incElement(curr.id, closest.id);
                        ret += closest.value + " ";
                    }
                    prevWord = word;
                }
                
                client.setStatText("Memories: " + mem.memories.size() + ", matrix size: " + mem.mat.getDim());
                
                if (client.isDebugging()) {
                    client.println(debugText);
                }
                debugText = "";
                
                client.println("> " + ret);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        if (!client.isReadonly()) {
            storeMemory();
        }
    }
    
    public static void debug(String line) {
        debugText += line + "\n";
    }
    
    private void loadMemory() {
        try {
            File memFile = new File("C:\\memory.dat");
            File matFile = new File("C:\\matrix.dat");
            if (memFile.exists()) {
                mem.loadFromFile(memFile);
            }
            if (matFile.exists()) {
                mem.mat.loadFromFile(matFile);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void storeMemory() {
        try {
            File memFile = new File("C:\\memory.dat");
            File matFile = new File("C:\\matrix.dat");
            memFile.createNewFile();
            matFile.createNewFile();
            mem.saveToFile(new File("C:\\memory.dat"));
            mem.mat.saveToFile(new File("C:\\matrix.dat"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
