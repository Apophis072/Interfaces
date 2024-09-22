import java.util.ArrayList;
public class ShortWordFilter implements Filter
{
    private String shortWord;


    public ShortWordFilter(String shortWord)
    {
        this.shortWord = shortWord;
    }

    public ShortWordFilter() {

    }

    public static  ArrayList<Object> shortWords(ArrayList<Object> inputString, Filter filter)
    {
        ArrayList<Object> shortwords = new ArrayList<>();
        for (Object word : inputString)
        {
            if (filter.accept(word)) shortwords.add(word);
        }
        return shortwords;
    }

    public String getShortWord()
    {
        return shortWord;
    }

    public void setShortWord(String shortWord)
    {
        this.shortWord = shortWord;
    }

    @Override
    public boolean accept(Object x) {
        if (((String) x).length() < 5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
