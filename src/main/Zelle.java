package main;
import java.util.ArrayList;

public class Zelle {

    private ArrayList<ArrayList<Integer>> content;

    public Zelle()
    {
        
    }

    public String getNumRepresentation()
    {
        String string = "";

        for (ArrayList<Integer> a : content)
        {
            for (int i : a)
            {
                string = string + i + ",";
            }
            string = string.substring(0, string.length()-1);

            string += "|";
        }
        string = string.substring(0, string.length()-1);

        return string;
    }

    public String getStringRepresentation(int größe)
    {
        String string = "";

        for (ArrayList<Integer> a : content)
        {
            for (int i : a)
            {
                char var = (char) (97 + ((int) (i / größe)));
                string = string + var + ((i % größe) + 1) + "*";
            }
            string = string.substring(0, string.length()-1);

            string += " + ";
        }
        string = string.substring(0, string.length()-3);

        return string;
    }

    public String getCompactRepresentation(int größe)
    {
        String string = "";

        for (ArrayList<Integer> a : content)
        {
            int counter = 1;
            int current = -1;
            for (int i = 0; i < a.size();i++)
            {
                if (a.get(i) != current)
                {
                    char var = (char) (97 + ((int) (current / größe)));
                    if (counter > 1)
                    {
                        string = string + "(" + var + ((current % größe) + 1) + "^" + counter + ")*";
                    } else
                    {
                        string = string + var + ((current % größe) + 1) + "*";
                    }

                    current = a.get(i);
                    counter = 1;
                } else
                {
                    counter++;
                }
            }

            char var = (char) (97 + ((int) (current / größe)));
            if (counter > 1)
            {
                string = string + "(" + var + ((current % größe) + 1) + "^" + counter + ")*";
            } else
            {
                string = string + var + ((current % größe) + 1) + "*";
            }
            
            string = string.substring(0, string.length()-1);

            string += " + ";
        }
        string = string.substring(0, string.length()-3);

        return string;
    }

    

    public void setContent(ArrayList<ArrayList<Integer>> nContent)
    {
        content = nContent;
    }

    public ArrayList<ArrayList<Integer>> getContent()
    {
        return content;
    }
}