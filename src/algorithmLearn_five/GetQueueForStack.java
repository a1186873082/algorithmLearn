package algorithmLearn.target.algorithmLearn_five;

import java.util.Stack;

public class GetQueueForStack {

    private Stack<String> str1 = new Stack<>();
    private Stack<String> str2 = new Stack<>();
    Integer size = 0;

    public void addQueue(String s) {
        str1.push(s);
        size++;
    }

    public String pop() {
        if(str2.isEmpty()){
            if(str1.isEmpty()){
                return null;
            }
            while (!str1.isEmpty()) {
                str2.push(str1.pop());
            }
        }
        size--;
        return str2.pop();
    }

    public static void main(String[] args) {
        GetQueueForStack getQueueForStack = new GetQueueForStack();
        getQueueForStack.addQueue("l");
        getQueueForStack.addQueue("i");
        getQueueForStack.addQueue("c");
        getQueueForStack.addQueue("h");
        getQueueForStack.addQueue("e");
        getQueueForStack.addQueue("n");
        while (getQueueForStack.size > 1) {
            System.out.printf(getQueueForStack.pop());
        }
        getQueueForStack.addQueue("l");
        getQueueForStack.addQueue("i");
        getQueueForStack.addQueue("c");
        getQueueForStack.addQueue("h");
        getQueueForStack.addQueue("e");
        getQueueForStack.addQueue("n");
        while (getQueueForStack.size > 1) {
            System.out.printf(getQueueForStack.pop());
        }

    }
}
