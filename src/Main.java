
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    //This is program from Computer Programming 1 that can be used to create files to test ShortWordFilter
    //universal Arraylist and Scanner
    public static ArrayList<String> list = new ArrayList<>();
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args)
    {
        String input = "";
        boolean done = false;

        /*
        "dirty flag". Anything that makes changes to the list will make saved false.
        Clear makes saved true because there isn't anything to save to begin with.
         */
        boolean saved = false;

        //Getting user input and determining what the user wants to do
        do {
            input = SafeInput.getRegExString(in, "Choose one of the follow: \nA: Add to list\tD: Delete from list\tI: Insert to list\tV: to view list\tQ: Quit program \tC: Clear list \tS: Save the list \tM: Move item", "[AaDdIiVvQqCcSsOoMm]");
            if (input.equalsIgnoreCase("A"))
            {
                add();
                saved = false;

            }
            else if (input.equalsIgnoreCase("D"))
            {
                if (list.size() != 0)
                {
                    delete();
                    saved = false;
                }
                else
                {
                    System.out.println("There is no element in the list yet.");
                }
            }
            else if (input.equalsIgnoreCase("I"))
            {
                if (list.size() != 0)
                {
                    insert();
                    saved = false;
                } else {
                    System.out.println("There isn't anything in the list. Please add to the list first.");
                }
            }
            else if (input.equalsIgnoreCase("V"))
            {
                view();
            }
            else if (input.equalsIgnoreCase("Q"))
            {
                quit();
            } else if (input.equalsIgnoreCase("C"))
            {
                clear();
                saved = true;
            }
            else if (input.equalsIgnoreCase("S"))
            {
                save();
                saved = true;
            }
            else if (input.equalsIgnoreCase("O"))
            {
                if (!saved)
                {
                    System.out.println("You have not saved the current list yet. Either clear the list or save the list to open another list.");
                }
                else
                {
                    String fileName = open(in, list);
                    view();
                }
            }
            else if (input.equalsIgnoreCase("M"))
            {
                move();
            }
        } while (!done);
    }

    //add to the list
    private static void add()
    {
        String addItem = "";
        Scanner in = new Scanner(System.in);

        addItem = SafeInput.getNonZeroLenString(in, "Input the item to add");
        list.addLast(addItem);

    }

    //delete a specific item in the list
    private static void delete()
    {
        int delete;

        view();
        delete = SafeInput.getRangedInt(in, "Enter the index to the element that you want to delete", 0, list.size() - 1);
        list.remove(delete);

    }

    //Insert an element in a specific index
    private static void insert()
    {
        int insertIndex;
        String insert = "";

        view();
        insertIndex = SafeInput.getRangedInt(in, "Enter the index that you want to insert an element", 0, list.size());
        insert = SafeInput.getNonZeroLenString(in, "Enter the element that you want to insert");


        list.add(insertIndex, insert);
    }

    //writes out the list that the user is currently working on
    private static void view()
    {
        for (int e = 0; e < list.size(); e++)
        {
            System.out.println("Index " + e + ":\t" + list.get(e));
        }
        if (list.isEmpty()) {
            System.out.println("[]");
        }
    }

    //Quits the program but the saved text (lists) remains
    private static void quit()
    {
        String quit = "";
        boolean done = false;
        boolean tryAgain = false;

        do {
            quit = SafeInput.getNonZeroLenString(in, "Just to double check. Type query to quit");
            if (quit.equals("query")) {
                System.exit(0);
            } else {
                System.out.println("\nYou typed " + quit + " instead of query so the program continues.");
                done = true;
            }

        } while (!done);
    }

    //clear the list the user is currently working on
    private static void clear()
    {
        list.clear();
    }

    //Saving the list into a text file named by the user
    private static void save()
    {
        String fileName = SafeInput.getNonZeroLenString(in, "Enter the name of the file you'll like to place this information in");

        fileName = fileName + ".txt";

        File file = new File(fileName);

        try (BufferedWriter br = new BufferedWriter(new FileWriter(file)))
        {

            for (String recs : list)
            {
                br.write(recs, 0, recs.length());
                br.newLine();
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
    }


    //opening and printing out an existing file on user.dir
    private static String open(Scanner in, ArrayList list)
    {

        clear();
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        chooser.setFileFilter(filter);
        String line;

        Path target = new File(System.getProperty("user.dir")).toPath();
        chooser.setCurrentDirectory(target.toFile());

        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();
                in = new Scanner(target);
                System.out.println("Opening File: " + target.getFileName());
                while (in.hasNextLine()) {
                    line = in.nextLine();
                    list.add(line);
                }
                in.close();
            } else {
                System.out.println("You must select a file, try again ");
            }
        } catch (IOException e) {
            System.out.println("IOException Error ");
        }
        return target.toFile().toString();
    }


    //Moving items by user input (index)
    private static void move ()
    {
        view();
        int moveItem = SafeInput.getRangedInt(in, "Enter the index of the element that you want to move", 0, list.size() - 1);
        int moveIndex = SafeInput.getRangedInt(in, "Enter the index of the location that you want to move the selected item", 0, list.size()-1);
        String item = list.get(moveItem);
        list.remove(moveItem);
        list.add(moveIndex,item);
    }
}