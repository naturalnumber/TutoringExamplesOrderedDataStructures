import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TesterForEach {
    public static void main(String[] args) {

        //List<String> list = new ArrayList<>();
        List<String> list = new LinkedList<>();

        list.add("First");
        list.add("Second");
        list.add("Third");
        list.add("Fourth");
        list.add("Fifth");
        list.add("Sixth");
        list.add("Seventh");

        // This code prints the whole list, don't focus on this too much yet
        System.out.println("The list: ");
        list.forEach(x -> System.out.println("\t"+x));
        System.out.println("Number of elements: "+list.size());
        System.out.println();

         // This is what we would write for a for each loop to print all strings not starting with f
        for(String s : list) {
            if(s.toLowerCase().charAt(0) != 'f') {
                System.out.println(s);
            }
        }
        //*/

        /*
        // This is what java actually does
        // Code from here
        Iterator<String> iter = list.listIterator(); // Java can only do this is there is an iterator
        String s;
        while (iter.hasNext()) {
            s = iter.next();
            // to here

            if(s.toLowerCase().charAt(0) != 'f') {
                System.out.println(s);
            }
        }
        System.out.println();
        //*/
    }
}
