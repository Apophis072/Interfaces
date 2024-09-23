public class BigRectangleFilter implements Filter
{
    public static boolean bigRectangleFilter(Double perimeter) {
        if(perimeter >= 10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    public boolean accept(Object x)
    {
        return bigRectangleFilter((Double) x);
    }
}
