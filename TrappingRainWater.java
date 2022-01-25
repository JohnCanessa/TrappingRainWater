import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * LeetCode 42. Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingRainWater {

    
    /**
     * Given n non-negative integers representing an 
     * elevation map where the width of each bar is 1, 
     * compute how much water it can trap after raining.
     * 
     * Runtime: O(n) - Space: O(1)
     * 
     * Runtime: 9 ms, faster than 9.07% of Java online submissions.
     * Memory Usage: 42.2 MB, less than 17.46% of Java online submissions.
     * 
     * 320 / 320 test cases passed.
     * Status: Accepted
     * Runtime: 9 ms
     * Memory Usage: 42.2 MB
     */
    static public int trap0(int[] height) {
        
        // **** set n ****
        int n = height.length;

        // ???? ????
        System.out.println("<<< n: " + n);

        // **** sanity check ****
        if (n == 1) return 0;

        // **** initialization ****
        int waterTrapped    = 0;

        int left            = 0;
        int right           = n - 1;

        int leftMax         = height[left];
        int rightMax        = height[right];

        // **** traverse height array - O(n) ****
        while (left < right) {

            // **** update left or right ****
            if (leftMax < rightMax) {

                // **** update left pointer ****
                left++;

                leftMax = Math.max(leftMax, height[left]);
                waterTrapped += leftMax - height[left];
            } else {

                // **** update right pointer ****
                right--;

                rightMax = Math.max(rightMax, height[right]);
                waterTrapped += rightMax - height[right];
            }

            // ???? ????
            System.out.println("<<< left: " + left + " right: " + right + " waterTrapped: " + waterTrapped);
        }

        // **** return water trapped ****
        return waterTrapped;
    }


    /**
     * Given n non-negative integers representing an 
     * elevation map where the width of each bar is 1, 
     * compute how much water it can trap after raining.
     * 
     * Runtime: O(n) - Space: O(n)
     * 
     * Runtime: 1 ms, faster than 92.42% of Java online submissions.
     * Memory Usage: 38.8 MB, less than 64.52% of Java online submissions.
     * 
     * 320 / 320 test cases passed.
     * Status: Accepted
     * Runtime: 1 ms
     * Memory Usage: 38.8 MB
     */
    static public int trap(int[] height) {
        
        // **** initialization ****
        int n               = height.length;
        int waterTrapped    = 0;

        int[] maxLeft       = new int[n];
        int[] maxRight      = new int[n];
        int[] minLR         = new int[n];

        int ml              = 0;
        int mr              = 0;

        // ???? ????
        System.out.println("<<<        n: " + n);

        // **** populate maxLeft - O(n) ****
        for (int i = 0; i < n; i++) {

            // **** ****
            if (i == 0) {
                maxLeft[i]  = height[i];
                ml          = 0;
            }

            // **** populate maxLeft ****
            maxLeft[i] = ml;
        
            // **** update ml ****
            if (ml < height[i])
                ml = height[i];
        }

        // ???? ????
        System.out.println("<<<  maxLeft: " + Arrays.toString(maxLeft));

        // **** populate maxRight and min(L,R) - O(n) ****
        for (int i = n - 1; i >= 0; i--) {

            // **** ****
            if (i == n - 1) {
                maxRight[i] = height[i];
                mr          = 0;
            }

            // **** populate maxRight ****
            maxRight[i] = mr;
        
            // **** update mr ****
            if (mr < height[i])
                mr = height[i];

            // **** poulate minLR ****
            minLR[i] = Math.min(maxLeft[i], maxRight[i]);
        }

        // ???? ????
        System.out.println("<<< maxRight: " + Arrays.toString(maxRight));
        System.out.println("<<<    minLR: " + Arrays.toString(minLR));

        // **** compute maxWater - O(n) ****
        for (int i = 0; i < n; i++) {

            // **** water in this cell (key computation) ****
            int water = minLR[i] - height[i];

            // **** increment water trapped (if needed) ****
            if (water > 0) {

                // ???? ????
                System.out.println("<<< i: " + i + " water: " + water);

                // **** increment water trapped ****
                waterTrapped += water;
            }
        }

        // **** return water trapped ****
        return waterTrapped;
    }


    /**
     * Test scaffold.
     * !!! NOT PART OF THE SOLUTION !!!
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        var br = new BufferedReader(new InputStreamReader(System.in));

        // **** read height int[] array ****
        int[] height = Arrays.stream(br.readLine().trim().split(","))
                            .map(s -> s.trim())
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** close buffered reader ****
        br.close();
        
        // ???? ????
        System.out.println("main <<< height: " + Arrays.toString(height));

        // **** call function of interest and display output ****
        System.out.println("main <<< trap0: " + trap0(height));

        // **** call function of interest and display output ****
        System.out.println("main <<<  trap: " + trap(height));
    }
}