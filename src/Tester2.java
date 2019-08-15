import java.util.ListIterator;

public class Tester2 {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.toPrint = true;

        System.out.println("Creating list...");

        list.append("Apple");
        list.append("Banana");
        list.append("Cherry");
        list.append("Date");
        list.append("Elderberry");
        list.append("Fig");
        list.append("Grape");

        System.out.println();


        System.out.println("Current list:");

        list.printAll();


        // Task: Find and remove "Fig"

        // Step 1: Find Fig:
        System.out.println("Starting search for 'Fig'...");

        int i = 0;

        // loop through list
        //while (!list.get(n).equalsIgnoreCase("Fig") && n < list.getSize()) n++;

        for (; i < list.getSize(); i++) {
            if (list.get(i).equalsIgnoreCase("Fig")) {
                break;
            }
        }


        // Step 2: Remove Fig
        System.out.println("Removing element 'Fig'...");

        list.remove(i);





        System.out.println();

        list.printAll();



        System.out.println("Resetting to try with iterator.");

        list.add(5, "Fig");



        System.out.println();

        list.printAll();




        System.out.println("Beginning iteration...");

        ListIterator<String> iter = list.listIterator();

        while (iter.hasNext()) {
            if (iter.next().equalsIgnoreCase("Fig")) {
                iter.remove();

                break;
            }
        }



        System.out.println();

        list.printAll();
    }
}
