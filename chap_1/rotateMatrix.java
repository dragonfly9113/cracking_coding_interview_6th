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
        
        rotateMatrix(m);

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

    // another version with easier-to-understand way
    static void rotateMatrix(int[][] m)
    {
        if (m.length == 0) return;
        
        if (m.length != m[0].length) return;

        for (int layer = 0; layer < m.length/2; layer++) {

            int first = layer;
            int last = m.length - layer - 1;
        
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int tmp = m[first][i];
                
                m[first][i] = m[last - offset][first];
                
                m[last - offset][first] = m[last][last - offset];
                
                m[last][last - offset] = m[i][last];
                
                m[i][last] = tmp;
            }
        }
    }
}
