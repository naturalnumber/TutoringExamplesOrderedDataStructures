public class TesterStack {
    public static void main(String[] args) {
        SinglyLinkedStack<String> goodStack = new SinglyLinkedStack<>();
        BadSinglyLinkedStack<String> badStack = new BadSinglyLinkedStack<>();

        testStack(goodStack);
        testStack(badStack);

        System.out.println("Emptying the stacks and repeating with tracing.");

        goodStack.clear();
        badStack.clear();

        goodStack.toPrint = true;
        badStack.toPrint = true;

        testStack(goodStack);
        testStack(badStack);
    }

    public static void testStack(Stack stack) {
        System.out.println("Filling: "+stack.getClass().getName());

        stack.push("Apple");
        stack.push("Banana");
        stack.push("Cherry");
        stack.push("Date");
        stack.push("Elderberry");
        stack.push("Fig");
        stack.push("Grape");

        System.out.println();
        stack.printAll();

        System.out.println("Trying some operations...");

        System.out.println("Pop returns: " + stack.pop());

        System.out.println("Pop returns: " + stack.pop());

        System.out.println("Peek returns: " + stack.peek());

        System.out.println("Pushing 'Pineapple' returns: " + stack.push("Pineapple"));

        System.out.println("Pushing 'Orange' returns: " + stack.push("Orange"));

        System.out.println("Peek returns: " + stack.peek());

        System.out.println("Pop returns: " + stack.pop());

        System.out.println("Peek returns: " + stack.peek());

        System.out.println();
        stack.printAll();
        System.out.println();
    }
}
