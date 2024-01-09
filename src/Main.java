import Parallel.FirstParallel;
import Parallel.SecondParallel;
import Sequencial.FirstSequencial;
import Sequencial.SecondSequential;

public class Main {
    public static void main(String[] args) {

        int[] A = new int[10000];
        int[] B = new int[10000];

        for (int i = 0; i < 10000; i++) {
            A[i] = (int) (Math.random() * 10);
            B[i] = (int) (Math.random() * 10);
        }

        System.out.println("O(n^2) sequential method:");
        FirstSequencial firstSequentialClass = new FirstSequencial(A, B);
        System.out.print("A = ");
        //firstSequentialClass.printPoly(A);
        System.out.print("\nB = ");
        //firstSequentialClass.printPoly(B);
        long start = System.currentTimeMillis();
        int[] prodSeq = firstSequentialClass.multiply();
        long end = System.currentTimeMillis();
        System.out.print("\nA * B = ");
        //firstSequentialClass.printPoly(prodSeq);
        System.out.printf("\nO(n^2) Sequential Time: %.9f ms\n", (end - start) / 1000.0);

        System.out.println("\nO(n^2) parallel method:");
        FirstParallel firstParallelClass = new FirstParallel(A, B);
        System.out.print("A = ");
        //firstParallelClass.printPoly(A);
        System.out.print("\nB = ");
        //firstParallelClass.printPoly(B);
        start = System.currentTimeMillis();
        int[] prodPar = firstParallelClass.multiply();
        end = System.currentTimeMillis();
        System.out.print("\nA * B = ");
        //firstParallelClass.printPoly(prodPar);
        System.out.printf("\nO(n^2) Parallel Time: %.9f ms\n", (end - start) / 1000.0);

        System.out.println("\nKaratsuba sequential method:");
        SecondSequential secondSequentialClass = new SecondSequential(A, B);
        System.out.print("A = ");
        //secondSequentialClass.printPoly(A);
        System.out.print("\nB = ");
        //secondSequentialClass.printPoly(B);
        start = System.currentTimeMillis();
        int[] prodKaraSeq = secondSequentialClass.multiply();
        end = System.currentTimeMillis();
        System.out.print("\nA * B = ");
        //secondSequentialClass.printPoly(prodKaraSeq);
        System.out.printf("\nKaratsuba Sequential Time: %.9f ms\n", (end - start) / 1000.0);

        System.out.println("\nKaratsuba parallel method:");
        SecondParallel secondParallelClass = new SecondParallel(A, B);
        System.out.print("A = ");
        //secondParallelClass.printPoly(A);
        System.out.print("\nB = ");
        //secondParallelClass.printPoly(B);
        start = System.currentTimeMillis();
        int[] prodKaraPar = secondParallelClass.multiply();
        end = System.currentTimeMillis();
        System.out.print("\nA * B = ");
        //secondParallelClass.printPoly(prodKaraPar);
        System.out.printf("\nKaratsuba Parallel Time: %.9f ms\n", (end - start) / 1000.0);
    }
}
