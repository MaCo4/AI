package martin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Magnus Hyll <magnus@hyll.no>
 */
public class MemoryMatrix {

    private int dim;
    private int[] elements;

    public MemoryMatrix() {
        dim = 0;
        elements = new int[0];
    }
    
    public int getDim() {
        return dim;
    }

    public boolean isWithin(int r, int c) {
        //System.out.println("Within? " + r + "x" + c + " " + (r <= dim && c <= dim));
        return r <= dim && c <= dim;
    }

    public int getElement(int r, int c) {
        if (r <= 0 || c <= 0 || !isWithin(r, c)) {
            return -1;
        }
        return elements[(r - 1) * dim + c - 1];
    }

    public void setElement(int r, int c, int value) {
        if (r <= 0 || c <= 0) {
            System.out.println("setfalse");
            return;
        }
        ensureSize(r, c);
        elements[(r - 1) * dim + c - 1] = value;
    }
    
    public void incElement(int r, int c) {
        setElement(r, c, getElement(r, c) + 1);
    }
    
    public int[] getHighestElement(int r) {
        System.out.println("Getting highest elem for r=" + r + " dim=" + dim);
        int[] res = new int[2];
        res[0] = 0;
        res[1] = -1;
        for (int i = 1; i <= dim; i++) {
            int current = getElement(r, i + 1);
            if (current > res[1]) {
                res[0] = i;
                res[1] = current;
            }
        }
        return res;
    }
    
    private void ensureSize(int r, int c) {
        if (!isWithin(r, c)) {
            resize(Math.max(r, c));
        }
    }
    
    private void resize(int newDim) {
        System.out.println("Gotta resize! old dim=" + dim + " newdim=" + newDim);
        int[] newArr = new int[newDim * newDim];
        for (int i = 0; i < dim; i++) {
            System.arraycopy(elements, i * dim, newArr, i * newDim, dim);
        }
        dim = newDim;
        elements = newArr;
    }
    
    public void loadFromFile(File file) throws FileNotFoundException, IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        dim = in.readInt();
        byte[] buf = new byte[dim * dim * 4];
        elements = new int[dim * dim];
        in.readFully(buf);
        ByteBuffer.wrap(buf).order(ByteOrder.BIG_ENDIAN).asIntBuffer().get(elements);
    }
    
    public void saveToFile(File file) throws FileNotFoundException, IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        out.writeInt(dim);
        ByteBuffer buf = ByteBuffer.allocate(dim * dim * 4);
        buf.asIntBuffer().put(elements);
        out.write(buf.array());
    }
}
