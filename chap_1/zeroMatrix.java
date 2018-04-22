//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

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
        
        if (zeroMatrix_1(matrix) < 0) return;
        
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
    
    // Try to save info in the matrix itself, thus save some space
    static int zeroMatrix_1(int[][] m)
    {
        if (m.length == 0 || m[0].length == 0) return -1;
        
        int M = m.length, N = m[0].length;
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (m[i][j] == 0) {
                    m[i][0] = 0;    // set the first element (0th column) in ith row to zero as indicator 
                    m[0][j] = 0;    // set the first element (0th row) in jth column to zero as indicator
                }
            }
        }

        // check the in-matrix indicators (first row and first column) and set zeros accordingly
        boolean firstRowZero = false;
        for (int i = 0; i < M; i++) 
            if (m[i][0] == 0) {
                if (i == 0) { firstRowZero = true; continue; }
                
                for (int j = 0; j < N; j++) m[i][j] = 0;
            }
               
        for (int j = 0; j < N; j++)
            if (m[0][j] == 0)
                for (int i = 0; i < M; i++) m[i][j] = 0;        

        // delay the clearing of the first row to here
        if (firstRowZero)
            for (int j = 0; j < N; j++) m[0][j] = 0;
            
        return 0;
    }
    
}
