import java.util.ArrayList;
import java.util.Iterator;

public class TesterIterator {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

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

        /* // This version won't work
        for(String s : list) {
            if(s.toLowerCase().charAt(0) != 'f') {
                list.remove(s);// inefficient an possibly error causing
                // In fact, array list won't let you do this
                // because of how potentially dangerous it can be
                // But our list wouldn't have this protection.
            }
        }
        //*/

        Iterator<String> iter = list.listIterator();
        for (String s; iter.hasNext();) { // This is usually better with a while loop, but I've used a for loop for consistency.
            s = iter.next();

            System.out.print("Considering: " + s);

            if(s.toLowerCase().charAt(0) != 'f') {
                System.out.print(", removing");
                iter.remove(); // Will remove the current object, and let us continue through the list without having to start again
            }
            System.out.println();
        }
        System.out.println();

        // This code prints the whole list, don't focus on this too much yet
        System.out.println("The list: ");
        list.forEach(x -> System.out.println("\t"+x));
        System.out.println("Number of elements: "+list.size());
        System.out.println();
    }
}
