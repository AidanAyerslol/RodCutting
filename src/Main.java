import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //DO NOT FORGET EVERY ARRAY NEEDS TO START WITH A 0

        //hardcoded test array
        int[] hardcodedarray = {0,1,5,8,9,10,17,17,20,24,24};
        printCutSolution(hardcodedarray, hardcodedarray.length);

        int[] array1 = readFile("input-4.2.txt");
        int[] array2 = readFile("input-4.3.txt");
        printCutSolution(array1, array1.length);
        printCutSolution(array2, array2.length);

    }

    //finds the most profitable way to cut a rod of length n
    public static int[][] cutRod(int[] prices, int n){
        //cutmatrix is a table with 2 rows
        //cutmatrix[i][maxprofits] is the most profit you can make by cutting a rod of length i
        //cutmatrix[i][bestcut] will show the location of the best cut you can make to reproduce the max profit at length i
        //We are implementing this as a matrix, as java only allows you to return one item per function
        int maxprofits = 0; int bestcut = 1;
        int[][] cutmatrix = new int[n][2];
        //no rod = no profit and no cuts
        cutmatrix[0][maxprofits] = 0;
        cutmatrix[0][bestcut] = -1;

        //from smallest to largest, find the best way to cut up every possible length of rod within n
        for (int i = 1; i < n; i++){
            int max = -9999;
            //the best profit for a length is either its own price, or the price of a length lower than itself + the best price of its remainder
            //therefore, check every possible length lower than the current rod, and see which makes the best price
            for (int j = 1; j <= i; j++){
                //if the profit of a subrod + the most profitable way to divide its remainder is greater than max, change the max
                if (max < prices[j] + cutmatrix[i-j][maxprofits]){
                    max = prices[j] + cutmatrix[i-j][maxprofits];
                    cutmatrix[i][bestcut] = j;
                }
            }
            if (max >= 1) {
                cutmatrix[i][maxprofits] = max;}
            else {
                cutmatrix[i][maxprofits] = prices[i-1];}
        }

        return cutmatrix;
    }

    //prints the series of cuts that maximizes a profit of a rod of length n
    public static void printCutSolution(int[] prices, int n){

        //we need the initial length so we can return the maxprofit in the final line
        int initialLength = n-1;
        //to make code more readable we show what row of the matrix we access without encoding
        int maxprofits = 0; int bestcut = 1;

        //call the cutRod function in order to create our matrix with the best cuts and their locations
        int[][] cutmatrix = cutRod(prices, n);
        System.out.println("To Maximize Profit, Cut Rod of Length " + (n-1) + ", into Lengths as Follows: ");

        while (n-1 > 0){
            //find and print the location of the best cut for the current remaining length (initially the length of the rod)
            int cutlocation = cutmatrix[n-1][bestcut];
            System.out.println(cutlocation+" - $" + prices[cutlocation]);
            //change the length of the rod to the remaining length after the last cut, and then do it again and again until no length remains
            n = n -  cutlocation;
        }
        //finally, print our total profit from the maxprofits row of the table
        System.out.println("This Will Earn You $" + cutmatrix[initialLength][maxprofits]);

    }

    public static int[] readFile(String fileName) throws IOException {

        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt() +1;
        int[] array = new int[n];
        array[0] = 0;
        for (int i = 1; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        return array;

    }
}

