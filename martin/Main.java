package martin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

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
            System.out.println("My memories: ");
            for (MemoryNode node : mem.memories) {
                System.out.print(node.value + ";");
            }
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
                        String closest = mem.findClosestMemory(word);
                        curr.incConnection(mem.recallMemory(prevWord).id);
                        if (closest == null) {
                            continue;
                        }
                        ret += closest + " ";
                    }
                    prevWord = word;
                }
                System.out.println("> " + ret);
            }
            mem.saveToFile(new File("C:\\memory.dat"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
