public class ShortWordFilter implements Filter
{

    //Checks whether the size of the word is under 5
    public static boolean shortWordFilter(String wordLength){
        if(wordLength.length() <= 5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //This  will save all the words that pass the shortWordFilter into a toString format
    public static String allShortWord(String items)
    {
        String result = "";
        result = items.toString();
        return result;
    }

    @Override
    public boolean accept(Object x)
    {
        return shortWordFilter(x.toString());
    }
}
