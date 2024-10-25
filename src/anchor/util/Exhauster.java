package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Exhauster {

    public static String start(String input, int iteration){

        String[] charSeperator = input.split("");           //[A,B,C,D] [A,A,B,B]
        Map<String,Integer> UniqueCheckerList = new HashMap<>();

        for (int i = 0; i < charSeperator.length; i++){
            UniqueCheckerList.merge(charSeperator[i],1, Integer::sum);
        }

        List<Integer> staticIndexList = new ArrayList<>(); //Static - Snapshot 
        List<Integer> dynamicIndexList = new ArrayList<>(staticIndexList); //Dynamic - Updated to remove

        for (int i = 0 ; i < charSeperator.length; i++){
            staticIndexList.add(i);
        // System.out.println("Detected character " + charSeperator[i] + ". Adding index " + i + " into indexSizeDetector");
        }

        // System.out.println("Static - " + staticIndexList);
        // System.out.println("Dynamic - " + dynamicIndexList);

        Random rand = new Random();
        Set<String> set = new HashSet<>();

        for (int i = 0; i < iteration; i++){
            // System.out.println("Entering outer loop");
            if (dynamicIndexList.isEmpty()){
                dynamicIndexList = new ArrayList<>(staticIndexList);
                // System.out.println("Refresh");

            } else {
                String placeholder = "";
                String placeholder2 = "";
                for (int j = 0; j < charSeperator.length; j++){
                    // System.out.println("Inner loop entered: Current dynamic list " + dynamicIndexList + "Inner loop iteration ; " + j);

                    int randomNum = rand.nextInt(dynamicIndexList.size());       // Size will decrease as it loops. Pick a number from (size).
                    // System.out.println("Random number is " + randomNum);

                    int indexPicker = dynamicIndexList.get(randomNum);          // [0,1,2,3] 

                    placeholder2 = charSeperator[indexPicker];             
                    // System.out.println("Random index to access " + dynamicIndexList.get(randomNum));

                    dynamicIndexList.remove(randomNum);

                    // System.out.println("Current placeholder string " + placeholder);

                    placeholder = placeholder +  placeholder2;

                    // System.out.println("Adding " + placeholder2);

                    if (dynamicIndexList.isEmpty()){
                        break;
                    }
                }

                // System.out.println("End of Loop: Iteration: " + i);
                set.add(placeholder);
            }


        }

        return possiblePermutation(UniqueCheckerList.size(), charSeperator.length, set.size(), UniqueCheckerList, set);
    }
        
    public static int factorial(int n){
    if (n <= 1) {
        return 1;
    }
    return n * factorial(n-1);
    }

    public static String possiblePermutation(int unique, int size, int set, Map<String, Integer> map, Set<String> set2){
        
        StringBuilder result = new StringBuilder();
        if (unique == size){
            int possible = factorial(size);
            if (possible == set){
                result.append(set2.toString()).append("\n");
                result.append("Exhausted " + possible + " possible combinations.").append("\n");
            } else {
                result.append("Permutation have not yet been exhausted, you may want to increase your interation hehe");
            }
            
        } else {
            int value = 1;
            int value2 = factorial(size);
            for ( Map.Entry<String,Integer> entry : map.entrySet()){
                value = value * factorial(entry.getValue());
            }

            int value3 = value2 / value;

            if (set == value3){
                result.append(set2.toString()).append("\n");
                result.append("Exhausted " + value3 + " possible combinations.").append("\n");

            } else {
                result.append("Permutation have not yet been exhausted, you may want to increase your interation hehe");

            }
        }

        return result.toString();
    }



}
