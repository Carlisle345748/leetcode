import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Solution56 {
    public int[][] merge(int[][] intervals) {
        // 0-1个区间，不需合并
        if (intervals.length <= 1) { return intervals; }
        // 根据第一个区间第一个元素排序
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));
        
        // 存放merge后区间的ArrayList
        ArrayList<int[]> result = new ArrayList<>();
        // 先放第一个元素进去
        result.add(intervals[0]);

        // 遍历剩下的区间
        for (int i = 1; i < intervals.length; i++) {
            // 取result里最后一个区间为peek
            int[] peek = result.get(result.size() - 1);
            // 如果当前区间和peek区间有交叉
            if (intervals[i][0] <= peek[1]) {
                // merge两个区间
                peek[1] = Math.max(peek[1], intervals[i][1]);
            }
            else {
                // 否则直接加入result
                result.add(intervals[i]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] test = new int[][] {{1,4}, {4,5}};
        System.out.println(Arrays.deepToString(new Solution56().merge(test)));
    }
}