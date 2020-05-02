package com.lc.Text.TestForCata_02;

public class DoubleCola {
    public static String WhoIsNext(String[] names, int n)
    {
        if(n <= names.length){
            return names[n-1];
        }
        int splitTimes = 1;
        int sum = names.length;
        int nextSplitNum = names.length;
        for(;sum <= n ;splitTimes++){
            nextSplitNum = 2 * nextSplitNum;
            sum = sum + nextSplitNum;
        }

        int index = n - (sum - nextSplitNum);

        for (int i = 1; i <= names.length; i++) {
            if(index - nextSplitNum/names.length <= 0){
                return names[0];
            }else if(index - nextSplitNum/names.length * i > 0 && index - (i+1) * nextSplitNum/names.length <=0 ){
                return names[i];
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String[] name = {"A", "B", "C","D"};
        System.out.println(new DoubleCola().WhoIsNext(name,36));
    }
}
