package set;

/**
 * Created by dat13ebj on 1/29/14.
 */
public class SetHelpers {

    public static int[] uniqueElements(int[] ints) {
        MaxSet<Integer> set = new MaxSet<Integer>();
        for(int item : ints) {
            set.add(item);
        }
        int[] output = new int[set.size()];
        for(int i=0; i<output.length; i++) {
            int max = set.getMax();
            output[i] = max;
            set.remove(max);
        }
        return output;
    }
}
