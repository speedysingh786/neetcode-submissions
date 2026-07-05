class Solution {
    public String minWindow(String S, String T) {
        if (S == null || S.isEmpty() || T == null || T.isEmpty()) {
            return "";
        }
        Map<Character, Integer> mapT = new HashMap<>();
        for (char c : T.toCharArray()) {
            mapT.put(c, mapT.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> mapWindow = new HashMap<>();
        int[] res = {-1, 0, 0};
        int have = 0, need = mapT.size();
        int left = 0, right = 0;
        for (right = 0; right < S.length(); right++) {
            char c = S.charAt(right);
            mapWindow.put(c, mapWindow.getOrDefault(c, 0) + 1);
            if (mapT.containsKey(c) && mapWindow.get(c).equals(mapT.get(c))) {
                have++;
            }
            while (have == need) {
                // Update result if this window is smaller
                if (res[0] == -1 || right - left + 1 < res[0]) {
                    res[0] = right - left + 1;
                    res[1] = left;
                    res[2] = right;
                }
                // pop from left window
                char leftChar = S.charAt(left);
                mapWindow.put(leftChar, mapWindow.getOrDefault(leftChar, 0) - 1);
                if (mapT.containsKey(leftChar) && mapWindow.get(leftChar) < mapT.get(leftChar)) {
                    have--;
                }
                left++;
            }
        }
        return res[0] == -1 ? "" : S.substring(res[1], res[2] + 1);
    }
}
