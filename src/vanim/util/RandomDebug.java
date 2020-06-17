package vanim.util;

import java.util.Random;

public class RandomDebug {
    public static int[] generateRandomIntArr(int n){
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++){
            arr[i] = rand.nextInt();
        }
        return arr;
    }

}
