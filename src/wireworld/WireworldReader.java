package wireworld;

import matrix.Matrix;
import wireworld.cell.Cell;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WireworldReader {
    public WireworldReader() {

    }

    public Matrix readWireworldFromFile(String filePath) throws IOException {
        List<Object> data = new ArrayList<>();

        int m = 0;
        int counter = 0;

        if (!(new File(filePath)).isFile()) {
            throw new FileNotFoundException("File doesn't exist.");
        }

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                m++;

                String line = scanner.nextLine();
                String[] elements = line.split("\\s+");

                for (String element : elements) {
                    counter++;
                    if (Integer.parseInt(element) < 0 || Integer.parseInt(element) >= Const.CELLS_STATES_NUM) {
                        throw new IOException("Wireworld contains unsupported cells.");
                    }
                    data.add(Integer.parseInt(element));
                }
            }
        }

        if (m != Const.CELLS_Y || (counter / m) != Const.CELLS_X) {
            throw new IOException("Wrong Wireworld format.");
        }

        return new Matrix(data, m, counter / m);
    }

    public void writeWireworldToFile(Matrix wireworld ,String filePath) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (int m = 0; m < wireworld.getM(); m++) {
                for (int n = 0; n < wireworld.getN(); n++) {
                    fileWriter.write(String.valueOf(((Cell)wireworld.getElement(m, n)).getState()) + " ");
                }
                fileWriter.write("\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new IOException("Couldn't write matrix to file.");
        }
    }
}
