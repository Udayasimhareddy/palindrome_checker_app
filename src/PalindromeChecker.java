import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

interface PalindromeStrategy {
    boolean isPalindrome(String str);
    String getName();
}

// Stack-based palindrome check
class StackStrategy implements PalindromeStrategy {
    public boolean isPalindrome(String str) {
        str = str.replaceAll("\\s+", "").toLowerCase();
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            stack.push(ch);
        }
        for (char ch : str.toCharArray()) {
            if (stack.pop() != ch) return false;
        }
        return true;
    }

    public String getName() {
        return "Stack Strategy";
    }
}

// Deque-based palindrome check
class DequeStrategy implements PalindromeStrategy {
    public boolean isPalindrome(String str) {
        str = str.replaceAll("\\s+", "").toLowerCase();
        Deque<Character> deque = new LinkedList<>();
        for (char ch : str.toCharArray()) {
            deque.addLast(ch);
        }
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) return false;
        }
        return true;
    }

    public String getName() {
        return "Deque Strategy";
    }
}

// Two-pointer palindrome check
class TwoPointerStrategy implements PalindromeStrategy {
    public boolean isPalindrome(String str) {
        str = str.replaceAll("\\s+", "").toLowerCase();
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public String getName() {
        return "Two-Pointer Strategy";
    }
}

public class PalindromeChecker {

    // Helper method to run and time a strategy
    public static long measureExecutionTime(PalindromeStrategy strategy, String input) {
        long start = System.nanoTime();
        boolean result = strategy.isPalindrome(input);
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        String testString = "Never Odd Or Even";

        PalindromeStrategy[] strategies = {
                new StackStrategy(),
                new DequeStrategy(),
                new TwoPointerStrategy()
        };

        System.out.println("Comparing palindrome algorithm performance on: \"" + testString + "\"\n");

        for (PalindromeStrategy strategy : strategies) {
            long timeNano = measureExecutionTime(strategy, testString);
            System.out.printf("%-20s: %d nanoseconds\n", strategy.getName(), timeNano);
        }
    }
}