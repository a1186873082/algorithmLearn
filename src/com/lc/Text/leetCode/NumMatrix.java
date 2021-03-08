package com.lc.Text.leetCode;

public class NumMatrix {

    int[][] sumMatrix;


    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0){
            sumMatrix = new int[0][0];
            return;
        }
        sumMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int sumJ = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0) {
                    if (j > 0) {
                        sumMatrix[i][j] = sumMatrix[i][j - 1] + matrix[i][j];
                    } else {
                        sumMatrix[i][j] = matrix[i][j];
                    }
                } else if (j == 0) {
                    sumMatrix[i][j] = sumMatrix[i - 1][j] + matrix[i][j];
                    sumJ = matrix[i][j];
                } else {
                    sumJ += matrix[i][j];
                    sumMatrix[i][j] = sumMatrix[i - 1][j] + sumJ;
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (col1 > 0 && row1 == 0) {
            return sumMatrix[row2][col2] - sumMatrix[row2][col1 - 1];
        } else if (row1 > 0 && col1 == 0) {
            return sumMatrix[row2][col2] - sumMatrix[row1 - 1][col2];
        } else if (row1 == 0 && col1 == 0) {
            return sumMatrix[row2][col2];
        } else {
            return sumMatrix[row2][col2] - sumMatrix[row1 - 1][col2] - sumMatrix[row2][col1 - 1] + sumMatrix[row1 - 1][col1 - 1];
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        System.out.println(new NumMatrix(matrix).sumRegion(2,1,4,3));
    }
}
