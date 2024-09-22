import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class ShortLister {

    public static void main(String[] args) {
        String rec;
        ArrayList<String> Words = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            chooser.setFileFilter(filter);
            Path target = new File(System.getProperty("user.dir")).toPath();
            chooser.setCurrentDirectory(target.toFile());

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                Path fileName = file.getFileName();

                String line;
                try (BufferedReader br = new BufferedReader(new FileReader(fileName.toFile()))) {
                    while (br.ready()) {
                        rec = br.readLine();
                        lines.add(rec);
                    }
                    for (Object x : lines) {
                        boolean shortWord = new ShortWordFilter().accept(x);
                        if (shortWord == true) {
                            Words.add(x.toString());
                        }
                    }
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Words.toString());
                ShortWordFilter.allShortWord(Words.toString());
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}