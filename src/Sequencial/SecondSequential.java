package Sequencial;

public class SecondSequential {
    private final int[] A;
    private final int[] B;

    public SecondSequential(int[] a, int[] b) {
        this.A = a;
        this.B = b;
    }

    public int[] multiply() {
        return karatsuba(A, B);
    }

    private int[] karatsuba(int[] A, int[] B) {
        int n = Math.max(A.length, B.length);

        if (n <= 1) {
            return new int[] { A[0] * B[0] };
        }

        A = adjustArrayLength(A, n);
        B = adjustArrayLength(B, n);

        int k = n / 2;

        int[] A1 = new int[k];
        int[] A0 = new int[n - k];
        int[] B1 = new int[k];
        int[] B0 = new int[n - k];

        splitArray(A, A1, A0, k);
        splitArray(B, B1, B0, k);

        int[] P0 = karatsuba(A0, B0);
        int[] P1 = karatsuba(A1, B1);
        int[] P2 = karatsuba(addArrays(A0, A1), addArrays(B0, B1));

        int[] result = new int[2 * n - 1];
        combineResults(result, P0, P1, P2, k);

        return result;
    }

    private void splitArray(int[] source, int[] dest1, int[] dest0, int splitIndex) {
        System.arraycopy(source, splitIndex, dest1, 0, dest1.length);
        System.arraycopy(source, 0, dest0, 0, splitIndex);
    }

    private int[] adjustArrayLength(int[] array, int newLength) {
        if (array.length < newLength) {
            int[] newArray = new int[newLength];
            System.arraycopy(array, 0, newArray, 0, array.length);
            return newArray;
        }
        return array;
    }

    private int[] addArrays(int[] A, int[] B) {
        int[] result = new int[Math.max(A.length, B.length)];
        for (int i = 0; i < result.length; i++) {
            result[i] = (i < A.length ? A[i] : 0) + (i < B.length ? B[i] : 0);
        }
        return result;
    }

    private void combineResults(int[] result, int[] P0, int[] P1, int[] P2, int k) {
        for (int i = 0; i < P1.length; i++) {
            result[i + 2 * k] += P1[i];
        }
        for (int i = 0; i < P2.length; i++) {
            result[i + k] += P2[i] - (i < P0.length ? P0[i] : 0) - (i < P1.length ? P1[i] : 0);
        }
        for (int i = 0; i < P0.length; i++) {
            result[i] += P0[i];
        }
    }

    public void printPoly(int[] poly) {
        for (int i = 0; i < poly.length; i++) {
            System.out.print(poly[i]);
            if (i != 0) {
                System.out.print("x^" + i);
            }
            if (i != poly.length - 1) {
                System.out.print(" + ");
            }
        }
    }
}

