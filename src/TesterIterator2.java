import java.util.ArrayList;
import java.util.Iterator;

public class TesterIterator2 {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        String first = "First";
        String second = "Second";
        String third = "Third";
        String fourth = "Fourth";
        String fifth = "Fifth";
        String sixth = "Sixth";
        String seventh = "Seventh";

        list.add(first);
        list.add(second);
        list.add(third);
        list.add(fourth);
        list.add(fifth);
        list.add(sixth);
        list.add(seventh);

        list.add(first);
        list.add(second);
        list.add(third);
        list.add(fourth);
        list.add(fifth);
        list.add(sixth);
        list.add(seventh);


        // This code prints the whole list, don't focus on this too much yet
        System.out.println("The list: ");
        list.forEach(x -> System.out.println("\t"+x));
        System.out.println("Number of elements: "+list.size());
        System.out.println();

        int skipped = 0;
        // Try the bad version, but try keeping the first half
        for(String s : list) {
            if (skipped < 7) {
                skipped++;
                continue; // skip the first half
            }

            if(s.toLowerCase().charAt(0) != 'f') {
                list.remove(s);// inefficient an possibly error causing
            }
        }


        // This code prints the whole list, don't focus on this too much yet
        System.out.println("The list: ");
        list.forEach(x -> System.out.println("\t"+x));
        System.out.println("Number of elements: "+list.size());
        System.out.println();
    }
}
