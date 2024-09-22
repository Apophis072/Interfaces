import java.util.ArrayList;
import java.util.Scanner;

public class BigRectLister
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        ArrayList<Double> perimeter = new ArrayList<>();
        ArrayList<Double> bigRectangle = new ArrayList<>();

        double length;
        double width;
        Boolean done = false;
        Boolean complete = false;

        do {

            length = SafeInput.getDouble(in, "Enter the length of the rectangle");
            width = SafeInput.getDouble(in, "Enter the width of the rectangle");
            double permimeter = length * width;
            perimeter.add(permimeter);

            done = SafeInput.getYNConfirm(in, "Is there anymore rectangles?");

        } while (!done);

        for(Object x : perimeter) {
            boolean Size = new BigRectangleFilter().accept(x);
            if (Size == true){
                bigRectangle.add((Double) x);
            }
        }
        System.out.println(bigRectangle);
    }
}
