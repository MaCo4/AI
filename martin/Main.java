package martin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Magnus C. Hyll <magnus@hyll.no>
 */
public class Main {

    public static void main(String[] args) {
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
