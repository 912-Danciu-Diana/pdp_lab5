package Sequencial;

public class FirstSequencial{
    private final int[] A;
    private final int[] B;
    int m;
    int n;

    public FirstSequencial(int[] a, int[] b){
        this.A = a;
        this.B = b;
        this.m = a.length;
        this.n = b.length;
    }
    public int[] multiply()
    {
        int[] prod = new int[m + n - 1];

        for (int i = 0; i < m + n - 1; i++)
        {
            prod[i] = 0;
        }

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                prod[i + j] += A[i] * B[j];
            }
        }

        return prod;
    }

    public void printPoly(int poly[])
    {
        for (int i = 0; i < poly.length; i++)
        {
            System.out.print(poly[i]);
            if (i != 0)
            {
                System.out.print("x^" + i);
            }
            if (i != poly.length - 1)
            {
                System.out.print(" + ");
            }
        }
    }
}
