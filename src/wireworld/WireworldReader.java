package wireworld;

import matrix.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
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
                    data.add(Integer.parseInt(element));
                }
            }
        }

        if (m == 0 || counter == 0) {
            throw new IOException("Wrong Wireworld format.");
        }

        return new Matrix(data, m, counter / m);
    }
}
