import java.util.ArrayList;
import java.util.List;

public class RecMinMax {
    private int min;
    private int max;
    public void getMinMax(ArrayList<Integer> list){
        if(list.size()>2){
            splitList(list);
        }
        else{
            for(int i = 0;i<2;i++){
                if(list.get(i)>max){max = list.get(i);}
                if (list.get(i)<min){min = list.get(i);}
            }
        }
    }
    private void splitList(List<Integer> splittedList){
//        System.out.println("splittedList = [" + splittedList + "]");
        if(splittedList.size()>2){
//            System.out.println("List size = "+splittedList.size()+" .Too big, splitting");
            splitList( splittedList.subList(0,splittedList.size()/2));
            splitList( splittedList.subList((splittedList.size()/2),splittedList.size()));
        }
        else{
            for(int i = 0;i<splittedList.size();i++){
                if(splittedList.get(i)>max){max = splittedList.get(i);}
                if (splittedList.get(i)<min){min = splittedList.get(i);}
            }
        }
    }
}
