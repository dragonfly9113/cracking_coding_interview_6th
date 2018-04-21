//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

// 1.7 Rotate Matrix

class Rextester
{  
    public static void main(String args[])
    {
        int N = 4, count = 0;
        int[][] m = new int[N][N];
        
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                m[i][j] = count++;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }
        
        rotateMatrix(m, N);

        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }
    }
    
    static void rotateMatrix(int[][] matrix, int N)
    {
        int tmp = 0;
        int l = 0; // layer number
        int n = N;
        
        for (int n1 = N; n1 >= 2; n1 = n1 - 2) {
            for (int i = 0; i < n1 - 1; i++) {
                tmp = matrix[l][i + l];
                
                matrix[l][i + l] = matrix[n - i - 1 - l][l];
                
                matrix[n - i - 1 - l][l] = matrix[n - 1 -l][n - i - 1 -l];
                
                matrix[n - 1 -l][n - i - 1 -l] = matrix[i + l][n - 1 - l];
                
                matrix[i + l][n - 1 - l] = tmp;
            }
            l++;
        }
    }
}
