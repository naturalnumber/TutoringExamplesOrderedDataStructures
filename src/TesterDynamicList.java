import java.util.LinkedList;
import java.util.List;

public class TesterDynamicList {
    private static List<String> tasks = new LinkedList<>();

    //private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        //while (in.hasNextLine()) in.nextLine();


        // This is a person who looks at the task list every day
        // If there are things to do, they do the first thing on their list
        // If there is nothing to do, they take the day off
        Thread doer = new Doer();

        // This randomly adds new tasks to the list
        Thread assigner = new Assigner();

        // Start the simulation
        assigner.start();
        doer.start();
    }

    private static class Doer extends Thread {
        int day = 1;
        public void run () {

            // This is the once a day loop, where the doer checks what they should do
            while (true) {

                // Look at task list
                System.out.print("Day #" + (day++) + ", ");
                print(tasks);

                if (tasks.size() > 0) {
                    System.out.print("Doing daily task task: " + tasks.remove(0));
                } else {
                    System.out.print("Nothing to do, taking the day off");
                }

                passDay(); // This passes the day
                System.out.println();
            }
        }

        // This just wastes time
        private void passDay() {
            for (int i = 0; i < 10; i++) {
                try {
                    waste(150);
                } catch (Exception ignored) {}

                System.out.print(".");
            }
            System.out.println();
        }

        private void waste(long l) {
            long start = System.currentTimeMillis();

            while (System.currentTimeMillis() - start < l) {
                yield();
            }
        }
    }


    private static class Assigner extends Thread {
        public void run () {
            // Randomly assign tasks
            while (true) {
                double random = Math.random();
                if (random > .75) {
                    addTask();
                    addTask();
                    addTask();
                }
                else if (random > .5) {
                    addTask();
                    addTask();
                }
                else if (random > .25) {
                    addTask();
                }
                try {
                    this.waste(2500);

                } catch (Exception ignored) {}
            }
        }

        private void waste(long l) {
            long start = System.currentTimeMillis();

            while (System.currentTimeMillis() - start < l) {
                yield();
            }
        }

        public void addTask() {

            //System.err.println("Adding task");

            int task = (int) (Math.random() * 10);
            switch (task) {
                case 0:
                    tasks.add("Do laundry");
                    break;
                case 1:
                    tasks.add("Do dishes");
                    break;
                case 2:
                    tasks.add("Make food");
                    break;
                case 3:
                    tasks.add("Clean kitchen");
                    break;
                case 4:
                    tasks.add("Clean bathroom");
                    break;
                case 5:
                    tasks.add("Dust");
                    break;
                case 6:
                    tasks.add("Make bed");
                    break;
                case 7:
                    tasks.add("Do homework");
                    break;
                case 8:
                    tasks.add("Mow lawn");
                    break;
                case 9:
                    tasks.add("Take out trash");
                    break;
                default:
                    tasks.add("Take break");

            }
        }
    }


    private static void print(List c) {
        // This code prints the whole list, don't focus on this too much yet
        System.out.println("our task list: ");
        c.forEach(x -> System.out.println("\t"+x));
        System.out.println("Number of tasks: "+c.size());
        System.out.println();
    }
}


    /*
    // This is an example of a stub implementation for the example in the notes
    public boolean hasPrevious() {

        throw new UnsupportedOperationException("Singly linked list does not support previous operations.");

        //return false;
    }
    //*/



//        123456789
//        12346789
//        ___46789