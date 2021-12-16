public class BitsBasics {

    /*
     * bit operations are |(or),&(and),<<(left shift),>>(RightShift),>>>(Triple
     * right shift),~(compliment/not),^(XOR)
     * 
     * left shift= removes bits from left and adds zeroes to right
     * 
     * rigth shift=removes bits from right and add 0s or 1s according to lsb.
     * 
     * triple rigth shift =removes bits from right and add 0s to left
     * 
     * xor= (a)&(~b) | (b)&(~a)
     */

    // 1->0 0->0
    public static void OnToOff(int n, int k) {
        System.out.println((n & ~(1 << k)));
    }

    // 0->1 1->1
    public static void OffToON(int n, int k) {
        System.out.println((n | (1 << k)));
    }

    // O(n)
    public static int countSetBits_02(int n) {
        int count = 0;
        int len = 0;
        int temp = n;
        while (temp != 0) {
            temp = (temp >>> 1);
            len++;
        }
        int mask = 1;
        while (len-- > 0) {
            if ((n & mask) > 0)
                count++;
            mask = (mask << 1);
        }
        return count;
    }

    // log(n) base 2
    public static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1)
                count++;
            n = (n >>> 1);
        }
        return count;
    }

    // O(no of ones)
    public static int countSetBits_03(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    // Formulas-
    // 1) a= n&(n-1); removes least most siginificant set bit (leftmost one)
    // 2) b= n&(-n); gives only the least most siginificant set bit (leftmost one)
    // 3) n=a|b; combination of above two formulas and return n again

    public static boolean powerOfTwoLeetcode(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static boolean PowerOf4Leetcode(int n) {
        if (n > 0 && (n & (n - 1)) == 0) {
            while (n != 1 && n != 0) {
                n = (n >>> 2);
            }
            if (n == 1)
                return true;
            else
                return false;
        } else
            return false;
    }

    public int singleNumberLeetcode(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }

    public int[] countBitsLeetcode(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i & (i - 1)] + 1;
        }
        return arr;
    }

    public int missingNumberLeetcode(int[] nums) {
        int ans = 0;
        int i = 0;
        for (int e : nums) {
            ans ^= e;
            ans ^= i;
            i++;
        }
        return ans ^ i;
    }

    public int missingNumberLeetcode2(int[] nums) {
        int arrSum = 0;
        int n = nums.length;
        for (int i : nums)
            arrSum += i;
        int rangeSum = (n * (n + 1)) / 2;
        return rangeSum - arrSum;
    }

    public int reverseBitsLeetcode(int n) {
        int len = 32;
        int ans = 0;
        while (len-- > 0) {
            int lastBit = 1 & n;
            if (lastBit == 1) {
                ans = ans | (lastBit << len);
            }
            n >>>= 1;
        }
        return ans;
    }

    public int[] singleNumber3Leetcode(int[] nums) {
        int xor = 0;
        for (int i : nums)
            xor ^= i;
        int a = 0;
        int b = 0;
        int mask = (xor & (-xor));
        for (int i : nums) {
            if ((mask & i) == 0)
                a ^= i;
            else
                b ^= i;
        }
        return new int[] { a, b };
    }

    public int singleNumber2Leetcode(int[] nums) {
        int k = 3;
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            int mask = (1 << i);
            for (int ele : nums) {
                if ((mask & ele) != 0)
                    count++;
            }
            if (count % k != 0) {
                ans |= mask;
            } else {
                ans |= 0;
            }
        }
        return ans;
    }

    public int singleNumber2Generic(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            int mask = (1 << i);
            for (int ele : nums) {
                if ((mask & ele) != 0)
                    count++;
            }
            if (count % k != 0) {
                ans |= mask;
            } else {
                ans |= 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        OnToOff(24, 3);
        OffToON(24, 2);
        System.out.println(countSetBits(24));
    }

}