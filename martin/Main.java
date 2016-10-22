package martin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Magnus C. Hyll <magnus@hyll.no>
 */
public class Main {
    
    private Memory mem;
    
    public Main() {
        mem = new Memory();
    }
    
    public void loadMemory() {
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
    
    public void start() {
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(System.in, "windows-1252"));
        } catch (UnsupportedEncodingException ex) {
            in = new BufferedReader(new InputStreamReader(System.in));
        }
        String line;
        System.out.println("Memories: " + mem.memories.size() + ", matrix size: " + mem.mat.getDim());
        System.out.println("\nI must learn more.");
        try {
            while (!"quit".equals(line = in.readLine())) {
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
                System.out.println("> " + ret);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void storeMemory() {
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

    public static void main(String[] args) {
        Main m = new Main();
        m.loadMemory();
        m.start();
        m.storeMemory();
        System.exit(0);
        
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "windows-1252"));
            String line;
            Memory mem = new Memory();
            mem.loadFromFile(new File("C:\\memory.dat"));
            mem.mat.loadFromFile(new File("C:\\matrix.dat"));
            /*System.out.println("My memories: ");
            for (MemoryNode node : mem.memories) {
                System.out.println("{" + node.id + "} " + node.value + " " + Arrays.toString(node.connections));
            }*/
            System.out.println("Memories: " + mem.memories.size() + ", matrix size: " + mem.mat.getDim());
            System.out.println("\nI must learn more.");
            while (!"quit".equals(line = in.readLine())) {
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
                System.out.println("> " + ret);
            }
            mem.saveToFile(new File("C:\\memory.dat"));
            mem.mat.saveToFile(new File("C:\\matrix.dat"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
