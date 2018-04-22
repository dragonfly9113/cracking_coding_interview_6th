//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

// 1.8: Zero Matrix

class Rextester
{  
    public static void main(String args[])
    {
        int[][] matrix = {{1,2,0,4},{0,3,4,5},{6,7,8,9}};
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
            
            System.out.println();
        }
        
        if (zeroMatrix(matrix) < 0) return;
        
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
            
            System.out.println();
        }
    }
    
    // Time complexity: O(M * N)
    // Space complexity: O(M + N)
    static int zeroMatrix(int[][] m)
    {
        if (m.length == 0 || m[0].length == 0) return -1;
        
        int M = m.length, N = m[0].length;
        
        int[] row = new int[M];
        int[] column = new int[N];
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (m[i][j] == 0) {
                    row[i] = 1; column[j] = 1;
                }
            }
        }

        for (int i = 0; i < M; i++) 
            if (row[i] == 1)
                for (int j = 0; j < N; j++) m[i][j] = 0;
        
        for (int j = 0; j < N; j++)
            if (column[j] == 1)
                for (int i = 0; i < M; i++) m[i][j] = 0;
            
        return 0;
    }
    
}
