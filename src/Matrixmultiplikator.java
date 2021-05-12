import java.util.ArrayList;
import java.util.Collections;

/*
    Beispiel:

    ( 0  3  6 )
    ( 1  4  7 )
    ( 2  5  8 )

    ( a0  b0  c0 )
    ( a1  b1  c1 )
    ( a2  b2  c2 )

*/
public class Matrixmultiplikator
{
    public Matrixmultiplikator(int nGrad, int nExp, int nCell) {

        Matrix ergebnis = multiplizieren(nGrad, nExp);
        String[][] cellContent = ergebnis.getCellTable(nCell);
        int highest = getHighest(cellContent);
        String[] columns = ergebnis.getColumns();

        Table t = new Table();
        t.displayCellTable(cellContent, columns, highest);

    }

    public Matrix multiplizieren(int grad, int exponent)
    {
        Matrix matrix = new Matrix(grad);
        matrix.resetContent();

        Matrix basis = new Matrix(grad);
        basis.resetContent();

        for (int i = 0; i < (exponent - 1); i++)
        {
            Matrix tempMatrix = new Matrix(grad);
            
            for (int j = 0; j < (grad * grad); j++)
            {
                tempMatrix.setZelle(j, zelleBerechnen(matrix, basis, j));
            }

            matrix = tempMatrix;
        }

        // matrix.printMatrixNum();
        // matrix.printMatrix();
        return matrix;
    }

    public Zelle zelleBerechnen(Matrix faktor1, Matrix faktor2, int pos)
    {
        
        if (faktor1.getGrad() != faktor2.getGrad())
        {
            System.out.println("Matritzen nicht gleich groß!");
        }

        int horizontal = pos % faktor1.getGrad();
        //System.out.println(horizontal);
        int vertical = pos - horizontal;

        ArrayList<ArrayList<Integer>> newContent = new ArrayList<>();

        for (int i = 0; i < faktor1.getGrad();i++)
        {
            newContent.addAll(zellenMultiplizieren(faktor1.getZelle(horizontal), faktor2.getZelle(vertical)));

            horizontal += faktor1.getGrad();
            vertical++;
        }

        Zelle z = new Zelle();
        z.setContent(newContent);
        return z;
    }

    public ArrayList<ArrayList<Integer>> zellenMultiplizieren(Zelle z1, Zelle z2)
    {
        ArrayList<ArrayList<Integer>> content1 = z1.getContent();
        ArrayList<ArrayList<Integer>> content2 = z2.getContent();

        ArrayList<ArrayList<Integer>> produkt = new ArrayList<>();

        for (ArrayList<Integer> a : content1)
        {
            for (ArrayList<Integer> b : content2)
            {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.addAll(a);
                temp.addAll(b);
                Collections.sort(temp);
                produkt.add(temp);
            }
        }
        
        return produkt;
        
    }

    public int getHighest(String[][] s)
    {
        int highest = 0;
        for (String[] a : s)
        {
            for (String i : a)
            {
                if (Integer.parseInt(i) > highest)
                {
                    highest = Integer.parseInt(i);
                }
            }
        }

        return highest;
    }

    
}