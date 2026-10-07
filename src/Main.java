//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //DO NOT FORGET EVERY ARRAY NEEDS TO START WITH A 0
        int[] array = {0,1,5,8,9,10,17,17,20,24,30};
        System.out.println(cutRod(array, array.length));

    }

    //finds the most profitable way to cut a rod of length n
    public static int cutRod(int[] prices, int n){
        //each index in maxprofits corresponds to the most profit you can make with a rod of the corresponding length
        int[] maxprofits = new int[n];
        //no rod = no profit
        maxprofits[0] = 0;

        //from smallest to largest, find the best way to cut up every possible length of rod within n
        for (int i = 1; i < n; i++){
            int max = -9999;
            //the best profit for a length is either its own price, or the price of a length lower than itself + the best price of its remainder
            //therefore, check every possible length lower than the current rod, and see which makes the best price
            for (int j = 1; j <= i; j++){
                //if the profit of a subrod + the most profitable way to divide its remainder is greater than max, change the max
                if (max < prices[j] + maxprofits[i-j]){
                    max = prices[j] + maxprofits[i-j];
                }
            }
            if (max >= 1) {maxprofits[i] = max;}
            else {maxprofits[i] = prices[i-1];}
        }

        return maxprofits[n-1];
    }

    //prints the series of cuts that maximizes a profit of a rod of length n
    public static void printCuts(int[] prices, int n){

    }






}