class Solution {
    public int[][] merge(int[][] intervals) {{
        // Sort by start time — O(n log n)
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= current[1]) {
                // Overlapping — extend the end if needed
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                // No overlap — commit current, start new
                merged.add(current);
                current = intervals[i];
            }
        }
        merged.add(current); // Don't forget the last interval

        return merged.toArray(new int[merged.size()][]);
    }
        
    }
}
