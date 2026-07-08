public class FindFrequencyArray {
    public static void main(String[] args) {
        int[] arr = {40, 25, 96, 48, 25, 37, 48, 25, 81, 25};
        int target = 25;
        int frequency = 0;
        for (int num : arr) {
            if (num == target) {
                frequency++;
            }
        }
        System.out.println("Frequency of " + target + " = " + frequency);
    }
}