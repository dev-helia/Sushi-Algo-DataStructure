class Solution {
    /**
     * 455. Assign Cookies
     * 注意关键句没有说“第 i 个孩子必须用第 i 块饼干”！
     * 
     * @param g the array of the greedy candies of the greedy kids
     * @param s the array of the candies of the kind parents
     * @return the sastisfied monsters
     * TC:
     * SC:
     */
    public int findContentChildren(int[] g, int[] s) {
        // egde
        if (s.length == 0)
            return 0;
        // iteration
        Arrays.sort(g);
        Arrays.sort(s);

        int child = 0, cookie = 0;
        while (child < g.length && cookie < s.length) {
            if (g[child] > s[cookie]) {
                child++;
            }
            cookie++;
        }
        return child;
    }

    /*
     * 860. Lemonade Change
     * 怎么做啊
     * 突然觉得有点想用queue
     * TC
     * SC
     */
    public boolean lemonadeChange(int[] bills) {
        int fives = 0;
        int tens = 0;
        for (int i = 0; i < bills.length; i++) {
            int current = bills[i];
            if (current == 5) {
                fives++;
            } else if (current == 10) {
                if (fives <= 0)
                    return false;
                tens++;
                fives--;
            } else { // 20
                if (tens > 0 && fives > 0) {
                    tens--;
                    fives--;
                } else if(fives >= 3) {
                    fives -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }





}