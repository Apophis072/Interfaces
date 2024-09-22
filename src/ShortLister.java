import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class ShortLister
{

    public static void main(String[] args)
    {

        JFileChooser chooser = new JFileChooser();
        Path target = new File(System.getProperty("user.dir")).toPath();
        chooser.setCurrentDirectory(target.toFile());

        ArrayList<Object> list = new ArrayList<Object>();
        ArrayList<Object> lines = new ArrayList<Object>();

        String line;

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            target = chooser.getSelectedFile().toPath();
            String fileName = String.valueOf(target.getFileName());

            try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
            {
                while ((line = br.readLine()) != null)
                {
                    lines.add(line);
                    System.out.println(line);
                }
                br.close();


                list = ShortWordFilter.shortWords(lines, new ShortWordFilter());


                for(Object object : list) System.out.println( "\t"+ ((String)object));

                for(int i=0;i<51;i++) System.out.print( (i<7 ? ' ' : '-' ));
                System.out.print("\n\n\n");




            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            }
                /*
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
                {
                    for (String recs : list)
                    {
                        bw.write(recs, 0, recs.length());
                        bw.newLine();
                    }
                    bw.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                 */


    }
}
