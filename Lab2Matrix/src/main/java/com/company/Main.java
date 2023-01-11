package com.company;

import java.util.Arrays;
import java.util.Scanner;
class Matrix{
    private int rows;
    private int columns;
    private float [][] matrix;
    //конструктор без параметрів
    public Matrix(){
        this.rows=0;
        this.columns=0;
        matrix =new float[0][0];

    }
    //конструктор із параметрами
    public Matrix(int rows, int columns){
        this.rows=rows;
        this.columns=columns;
        this.matrix=new float[rows][columns];
    }
    //конструктор копіювання
    public Matrix(Matrix matrix1){
        this.rows=matrix1.rows;
        this.columns=matrix1.columns;
        this.matrix=new float[rows][columns];
        for(int i=0;i<this.rows;i++){
            for(int j=0;j<columns;j++){
                this.matrix[i][j]=matrix1.matrix[i][j];
            }
        }
    }
    //заповнює значення рандомними значеннями
    public void autoFill(){
        for(int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                matrix[i][j]=(int) (-20+Math.random()*50);
            }
        }
    }
    //ручне заповмення матриці
    public void manualFill(){
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                System.out.println("Please, enter ["+i+"; "+j+"] element");
                matrix[i][j]=sc.nextInt();
            }
        }
    }
    // повертає певний елемент
    public float certainElement(int row, int column){
        return matrix[row][column];
    }
    // повертає масив з елементів певного рядка
    public float[] certainRow(int row){
        return matrix[row];
    }
    // повертає масив з елементів певного стовпця
    public float[] certainColumn(int column){
        float[] returnMatrix =new float[rows];
        for(int i=0;i<rows;i++){
            returnMatrix[i]=matrix[i][column];
        }
        return returnMatrix;
    }
    //друкує матрицю
    public void print(){
        for(int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                //System.out.print(Math.round(matrix[i][j])+"\t");
                System.out.print(String.format("%.2f",matrix[i][j])+"\t");

            }
            System.out.println();
        }
    }
    //статичний метод для друкування матриці
    public static void print(Matrix m){
        for(int i=0;i< m.rows;i++){
            for (int j=0;j<m.columns;j++){
                System.out.print(m.matrix[i][j]+"\t");

            }
            System.out.println();
        }
    }
    //повертає масив з двох чисел де 0-ий елемент - кількість рядків а 1-ий кількість стовпців
    public int[] matrixDimension(){
        int size[] =new int[2];
        size[0]=rows;
        size[1]=columns;
        return size;
    }
    //перевіряє рівність матриць
    public boolean equals(Matrix matrix1){
        for(int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                if(matrix[i][j]!= matrix1.matrix[i][j])
                    return false;

            }
        }
        return true;
    }
    //повертає хеш-код об’єкта
    public int hashCode(){
        return Arrays.hashCode(new Object[] { rows, columns, matrix });
    }
    //повертає діагональну матрицю, створену на основі вектора
    public static Matrix diagonalMatrix(int [] vector){
        Matrix returnMatrix =new Matrix(vector.length,vector.length);
        for(int i=0;i<vector.length;i++){
            returnMatrix.matrix[i][i]=vector[i];
        }
        return returnMatrix;
    }

    //зведення матриці до верхньої трикутної
    public void topTriangleMatrix (){
        int maxIndex =0;
        for (int i = 0; i < rows; i++) {
            maxIndex=i;
            float max =Math.abs(matrix[i][i]);
            for (int j = i+1; j < rows; j++) {
                float element = Math.abs(matrix[j][i]);
                if (element > max) {
                    max = element;
                    maxIndex = j;
                }
            }
            if (i != maxIndex) {
                float[]temp;
                temp=matrix[i];
                matrix[i]=matrix[maxIndex];
                matrix[maxIndex]=temp;
            }
            for (int j = i + 1; j < rows; j++) {
                float multi = -matrix[j][i]/matrix[i][i];
                for (int h = i; h < rows; h++) {
                    matrix[j][h] += matrix[i][h]*multi;
                }
            }
        }

    }
    //перетворює матрицю на нижнью трикутну
    public void lowerTriangleMatrix (){
        int maxIndex;
        for (int i = rows-1; i >=0; i--) {
            maxIndex=i;
            float max =Math.abs(matrix[i][i]);
            for (int j = i; j >= 0; j--) {
                float element = Math.abs(matrix[j][i]);
                if (element > max) {
                    max = element;
                    maxIndex = j;
                }
            }
            if (i != maxIndex) {
                float[]temp;
                temp=matrix[i];
                matrix[i]=matrix[maxIndex];
                matrix[maxIndex]=temp;
            }
            for (int j = i-1 ; j >= 0; j--) {
                float multi = -matrix[j][i]/matrix[i][i];
                for (int h = i; h >= 0; h--) {
                    matrix[j][h] += multi*matrix[i][h];
                }
            }
        }

    }


}


public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int choice=20;
        Matrix m=new Matrix();
        Matrix m1=new Matrix();
        while(choice!=0) {
            System.out.println("""
                    Обріть дію\s
                    0.Вихід
                    1.Створити пусту матрицю
                    2.Створити матрицю із заданими розмірами
                    3.Скопіювати матрицю
                    4.Заповнити матрицю випадковими числами
                    5.Заповнити матрицю вручну
                    6.Надрукувати матрицю
                    7.Надрукувати скопійовану матрицю
                    8.Повернути певний елемент
                    9.Повернути певний рядок
                    10.Повернути певний стовпчик
                    11.Повернути розмірність
                    12.Перевірка на рівність
                    13.Хеш-код
                    14.Діагональна матриця на основі вектора
                    15.Верхня трикутна матриця
                    16.Нижня трикутна матриця""");

            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    m = new Matrix();
                    break;
                case 2:
                    System.out.println("Введіть розміри матриці");
                    m = new Matrix(sc.nextInt(), sc.nextInt());
                    break;
                case 3:
                    m1 = new Matrix(m);
                    break;

                case 4:
                    m.autoFill();
                    m.print();
                    break;

                case 5:
                    m.manualFill();
                    m.print();
                    break;

                case 6:
                    m.print();
                    break;

                case 7:
                    m1.print();
                    break;

                case 8:
                    System.out.println("Введіть позицію елемента");
                    System.out.println(m.certainElement(sc.nextInt(), sc.nextInt()));
                    break;

                case 9:
                    System.out.println("Введіть рядок");
                    for (float el : m.certainRow(sc.nextInt())) {
                        System.out.print(el + "\t");}
                    System.out.println();
                    break;
                case 10:
                    System.out.println("Введіть стовпчик");
                    for (float el : m.certainColumn(sc.nextInt())) {
                        System.out.print(el + "\t");
                    }
                    System.out.println();
                    break;
                case 11:
                    System.out.println("Matrix dimension is "+ m.matrixDimension()[0]+"x"+m.matrixDimension()[1]);
                    break;
                case 12:
                    System.out.println(m.equals(m1));

                    break;
                case 13:
                    System.out.println(m.hashCode());
                    break;
                case 14:
                    System.out.println("Введіть розмір вектора");
                    int size=sc.nextInt();
                    System.out.println("Введіть вектор");
                    int vector[]=new int[size];
                    for(int i=0;i<size;i++){
                        vector[i]=sc.nextInt();
                    }
                    Matrix.print(Matrix.diagonalMatrix(vector));
                    break;
                case 15:
                    m.topTriangleMatrix();
                    m.print();

                    break;
                case 16:
                    m.lowerTriangleMatrix();
                    m.print();
                    break;


            }
        }
    }
}




final class MatrixImmutable{
    private final int rows;
    private final int columns;
    private final float [][] matrix;

    //конструктор без параметрів
    public MatrixImmutable(){
        this.rows=0;
        this.columns=0;
        matrix =new float[0][0];

    }
    //конструктор із параметрами
    public MatrixImmutable(int rows, int columns){
        this.rows=rows;
        this.columns=columns;
        this.matrix=new float[rows][columns];
    }
    //конструктор копіювання
    public MatrixImmutable(MatrixImmutable matrix1){
        this.rows=matrix1.rows;
        this.columns=matrix1.columns;
        this.matrix=new float[rows][columns];
        for(int i=0;i<this.rows;i++){
            for(int j=0;j<columns;j++){
                this.matrix[i][j]=matrix1.matrix[i][j];
            }
        }
    }
    //заповнює значення рандомними значеннями
    public void autoFill(){
        for(int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                matrix[i][j]=(int) (-20+Math.random()*50);
            }
        }
    }
    //ручне заповмення матриці
    public void manualFill(){
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                System.out.println("Please, enter ["+i+"; "+j+"] element");
                matrix[i][j]=sc.nextInt();
            }
        }
    }
    // повертає певний елемент
    public float certainElement(int row, int column){
        return matrix[row][column];
    }
    // повертає масив з елементів певного рядка
    public float[] certainRow(int row){
        return matrix[row];
    }
    // повертає масив з елементів певного стовпця
    public float[] certainColumn(int column){
        float[] returnMatrix =new float[rows];
        for(int i=0;i<rows;i++){
            returnMatrix[i]=matrix[i][column];
        }
        return returnMatrix;
    }
    //друкує матрицю
    public void print(){
        for(int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                //System.out.print(Math.round(matrix[i][j])+"\t");
                System.out.print(String.format("%.2f",matrix[i][j])+"\t");

            }
            System.out.println();
        }
    }
    //статичний метод для друкування матриці
    public static void print(MatrixImmutable m){
        for(int i=0;i< m.rows;i++){
            for (int j=0;j<m.columns;j++){
                System.out.print(m.matrix[i][j]+"\t");

            }
            System.out.println();
        }
    }
    //повертає масив з двох чисел де 0-ий елемент - кількість рядків а 1-ий кількість стовпців
    public int[] matrixDimension(){
        int size[] =new int[2];
        size[0]=rows;
        size[1]=columns;
        return size;
    }
    //перевіряє рівність матриць
    public boolean equals(MatrixImmutable matrix1){
        for(int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                if(matrix[i][j]!= matrix1.matrix[i][j])
                    return false;

            }
        }
        return true;
    }
    //повертає хеш-код об’єкта
    public int hashCode(){
        return Arrays.hashCode(new Object[] { rows, columns, matrix });
    }
    //повертає діагональну матрицю, створену на основі вектора
    public static MatrixImmutable diagonalMatrix(int [] vector){
        MatrixImmutable returnMatrix =new MatrixImmutable(vector.length,vector.length);
        for(int i=0;i<vector.length;i++){
            returnMatrix.matrix[i][i]=vector[i];
        }
        return returnMatrix;
    }

    //зведення матриці до верхньої трикутної
    public void topTriangleMatrix (){
        int maxPos =0;
        for (int i = 0; i < rows; i++) {
            maxPos=i;
            float max =Math.abs(matrix[i][i]);
            for (int j = i+1; j < rows; j++) {
                float element = Math.abs(matrix[j][i]);
                if (element > max) {
                    max = element;
                    maxPos = j;
                }
            }
            if (i != maxPos) {
                float[]temp;
                temp=matrix[i];
                matrix[i]=matrix[maxPos];
                matrix[maxPos]=temp;
            }
            for (int j = i + 1; j < rows; j++) {
                float mul = -matrix[j][i]/matrix[i][i];
                for (int k = i; k < rows; k++) {
                    matrix[j][k] += matrix[i][k]*mul;
                }
            }
        }

    }
    //перетворює матрицю на нижнью трикутну
    public void lowerTriangleMatrix (){
        int maxPos;
        for (int i = rows-1; i >=0; i--) {
            maxPos=i;
            float max =Math.abs(matrix[i][i]);
            for (int j = i; j >= 0; j--) {
                float element = Math.abs(matrix[j][i]);
                if (element > max) {
                    max = element;
                    maxPos = j;
                }
            }
            if (i != maxPos) {
                float[]temp;
                temp=matrix[i];
                matrix[i]=matrix[maxPos];
                matrix[maxPos]=temp;
            }
            for (int j = i-1 ; j >= 0; j--) {
                float mul = -matrix[j][i]/matrix[i][i];
                for (int k = i; k >= 0; k--) {
                    matrix[j][k] += matrix[i][k]*mul;
                }
            }
        }

    }
}
