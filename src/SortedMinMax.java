import java.util.ArrayList;

public class SortedMinMax {
    public ArrayList getMinMax(ArrayList<Integer> listToSort){
        ArrayList<Integer> sortedList = listToSort;
        //O = n
        for (int i = 0;i<sortedList.size();i++){
            int smallestPos = i;
            //O = n^2
            for (int j = i;j<sortedList.size();j++){
                if(sortedList.get(j)<sortedList.get(smallestPos)){
                    smallestPos = j;
                }
            }
            int numberToSwitch = sortedList.get(i);
            sortedList.set(i,sortedList.get(smallestPos));
            sortedList.set(smallestPos,numberToSwitch);
        }

        return sortedList;
    }
}
