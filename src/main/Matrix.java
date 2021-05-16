package main;
import java.util.ArrayList;

public class Matrix {

    private Zelle[] content;
    private int grad;
    private int größe;

    public Matrix(int grad)
    {
        this.grad = grad;
        größe = grad * grad;
        content = new Zelle[größe];
    }

    public void resetContent()
    {
        for(int i = 0; i < größe; i++)
        {
            ArrayList<ArrayList<Integer>> aList = new ArrayList<>();
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(i);
            aList.add(temp);

            Zelle z = new Zelle();
            z.setContent(aList);

            content[i] = z;
        }
    }

    public Integer[][] getCellTable(int pos)
    {
        Zelle z = content[pos];
        ArrayList<ArrayList<Integer>> c = z.getContent();

        Integer[][] tableData = new Integer[c.size()][größe];

        for (int k = 0; k < tableData.length; k++)
        {
            ArrayList<Integer> src = c.get(k);

            for (int i = 0; i < größe;i++)
            {
                tableData[k][i] = 0;
            }

            for (int x : src)
            {
                tableData[k][x]++;
            }

        }

        return tableData;
    }

    public String[] getColumns()
    {
        String[] columns = new String[größe];
        for (int i = 0; i < grad; i++)
        {
            for (int j = 0; j < grad; j++)
            {
                String s = ((char) (97 + i)) + String.valueOf(j);
                columns[(i * grad) + j] = s;
            }
        }

        return columns;
    }

    

    public void printMatrixNum()
    {
        for (int i = 0; i < grad; i++)
        {
            for (int j = i; j < größe; j += grad)
            {
                System.out.print(content[j].getNumRepresentation() + "  ");
            }
            System.out.println();
        }

    }

    public void printMatrix()
    {
        for (int i = 0; i < grad; i++)
        {
            for (int j = i; j < größe; j += grad)
            {
                System.out.print(content[j].getStringRepresentation(grad) + "\t");
            }
            System.out.println();
        }

    }

    public void printCompactMatrix()
    {
        for (int i = 0; i < grad; i++)
        {
            for (int j = i; j < größe; j += grad)
            {
                System.out.print(content[j].getCompactRepresentation(grad) + "\t");
            }
            System.out.println();
        }

    }

    public void printCompactCell(int pos)
    {
        System.out.println(content[pos].getCompactRepresentation(grad));
    }



    public void setZelle(int pos, Zelle z)
    {
        content[pos] = z;
    }

    public Zelle getZelle(int pos)
    {
        return content[pos];
    }

    public Zelle[] getMatrix()
    {
        return content;
    }

    public int getGrad()
    {
        return grad;
    }


}