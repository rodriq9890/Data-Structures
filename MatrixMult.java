import java.util.Arrays;
public class MatrixMult{
    public static double[][] Multiplication(double[][] A, double[][] B){
        int aRows = A.length;
        int aColumns = A[0].length;
        int bColumns = B[0].length;
        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0;
            }
        }
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < aColumns; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
    public static double[][] MatrixExponent(double[][] M, int k){
        if(k==0){
            return Identity(M);
        }
        else if(k%2==0){
            return Multiplication( MatrixExponent(M, k/2), MatrixExponent(M, k/2));
        }
        else{
            return Multiplication(M, Multiplication( MatrixExponent(M, k/2), MatrixExponent(M, k/2)));
        }
    }
    public static double[][] Identity(double[][] M){
        double[][] Identity = new double[M.length][M.length];
        for(int i =0; i<Identity.length;i++){
            Identity[i][i]=1;
        }
        return Identity;
    }
    public static void main(String[] args) {
        double[][] A = {{1,2,3},{1,2,3},{1,2,3}};
        System.out.println(Arrays.deepToString(MatrixExponent(A, 2)));
    }
}
