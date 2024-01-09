package Parallel;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class FirstParallel {
    private final int[] A;
    private final int[] B;
    int m;
    int n;

    public FirstParallel(int[] a, int[] b) {
        this.A = a;
        this.B = b;
        this.m = a.length;
        this.n = b.length;
    }

    private static class MultiplyTask extends RecursiveAction {
        private final int[] A;
        private final int[] B;
        private final int[] prod;
        private final int start;
        private final int end;

        MultiplyTask(int[] A, int[] B, int[] prod, int start, int end) {
            this.A = A;
            this.B = B;
            this.prod = prod;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= 1) {
                for (int j = 0; j < B.length; j++) {
                    prod[start + j] += A[start] * B[j];
                }
            } else {
                int mid = (start + end) / 2;
                MultiplyTask left = new MultiplyTask(A, B, prod, start, mid);
                MultiplyTask right = new MultiplyTask(A, B, prod, mid, end);
                left.fork();
                right.compute();
                left.join();
            }
        }
    }

    public int[] multiply() {
        int[] prod = new int[m + n - 1];
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new MultiplyTask(A, B, prod, 0, m));
        return prod;
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

