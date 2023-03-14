
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest(classes = CheckPermutation.class)
public class CheckPermutation {

    @Test
    public void Check() {
        String s1 = "abc";
        String s2 = "bca";
        if(s1.length() != s2.length()){
            System.out.println(false);;
        }

        HashMap<Character, Integer> s1Map = new HashMap<>();
        HashMap<Character, Integer> s2Map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            Integer s1Num = s1Map.get(s1.charAt(i));
            s1Map.put(s1.charAt(i), s1Num == null ? 1 : ++s1Num);

            Integer s2Num = s2Map.get(s2.charAt(i));
            s2Map.put(s2.charAt(i), s2Num == null ? 1 : ++s2Num);
        }

        for (Character character : s1Map.keySet()) {
            Integer s1Int = s1Map.get(character);
            Integer s2Int = s2Map.get(character);
            if(!s1Int.equals(s2Int)){
                System.out.println(false);;
            }
        }

        System.out.println(true);
    }

    @Test
    public void isMatch() {
        String s = "aaa";
        String p = "aaaa";
        int jump = 0;
        char temp = '\u0000';
        for(int i = 0; i < s.length(); i++){
            if((temp != '\u0000' && s.charAt(i) == temp) || temp == '.'){
                jump += 1;
                continue;
            }
            if(i - jump >= p.length()){
                System.out.println(false);
                return;
            }
            if(s.charAt(i) == p.charAt(i - jump) || p.charAt(i - jump) == '.'){
                if(i == s.length() - 1 && (i - jump) != p.length() - 1){
                    System.out.println(false);
                    return;
                }
                temp = '\u0000';
                continue;
            }else if(p.charAt(i - jump) == '*'){
                temp = p.charAt(i - jump - 1);
            }else if(p.charAt(i - jump + 1)  == '*'){
                for(int j = i - jump + 2; j < p.length(); j+=2){
                    if(p.charAt(j - 1) != '*'){
                        System.out.println(false);
                        return;
                    }
                    if(s.charAt(i) == p.charAt(j)){
                        jump -= (j - i + jump);
                    }
                }
            }else{
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }

    @Test
    public void threeSum() {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        int length = nums.length;
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < length - 1; ++i){
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int k = length - 1;
            int target = -nums[i];
            for(int j = i + 1; j < length; ++j){
                if(j > i && nums[j] == nums[j - 1]){
                    continue;
                }
                while(j < k && nums[j] + nums[k] > target){
                    --k;
                }
                if(j == k){
                    break;
                }
                if(nums[j] + nums[k] == target){
                    List<Integer> intList = new ArrayList<Integer>();
                    intList.add(nums[i]);
                    intList.add(nums[j]);
                    intList.add(nums[k]);
                    list.add(intList);
                }
            }
        }
        System.out.println(list);
    }
    public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    @Test
    public void mergeKLists() {
        ListNode[] lists = new ListNode[3];

        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode list3 = new ListNode(2);
        list3.next = new ListNode(6);

        lists[0] = list1;
        lists[1] = list2;
        lists[2] = list3;

        ListNode prehead = new ListNode(-1);
        ListNode here = new ListNode();
        prehead.next = here;
        boolean isDone = false;
        while(!isDone){
            boolean done = true;
            int min = Integer.MAX_VALUE;
            int index = lists.length;
            for(int i = 0; i < lists.length; i++){
                if(lists[i] != null && lists[i].val <= min){
                    min = lists[i].val;
                    index = i;
                }
                done &= (lists[i] == null);
                if(i == lists.length - 1){
                    isDone = done;
                    if(index != lists.length){
                        here.next = lists[index];
                        lists[index] = lists[index].next;
                        here = here.next;
                    }
                }
            }
        }
    }
    @Test
    public void longestValidParentheses() {
        String s = "(()";
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    start = i + 1;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        max = Math.max(max, i - start + 1);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
        }
        System.out.println(max);
    }

    @Test
    public void searchRange() {
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 8;
        int left = 0;
        int right = nums.length - 1;
        if(target < nums[left] || target > nums[right]){
            System.out.println(Arrays.toString(new int[]{-1, -1}));
        }
        while(left <= right){
            int mid = (right + left) / 2;
            if(nums[mid] == target){
                int min = mid;
                int max = mid;
                for(int i = mid; i <= right; i++){
                    if(nums[i] > target){
                        break;
                    }
                    max = i;
                }
                for(int i = mid; i >= left; i--){
                    if(nums[i] < target){
                        break;
                    }
                    min = i;
                }
                System.out.println(Arrays.toString(new int[]{min, max}));
                break;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
    }
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;

         TreeNode() {
         }

         TreeNode(int val) {
             this.val = val;
         }

         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

     @Test
    public void inorderTraversal() {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2, treeNode1, null);
        TreeNode root = new TreeNode(1, null, treeNode2);
        List<Integer> result = new ArrayList<>();
        if(root.left != null){
            inorderTraversal(root.left);
        }
        result.add(root.val);
        if(root.right != null){
            inorderTraversal(root.right);
        }
        System.out.println(result);
    }

    public void inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root.left != null){
            inorderTraversal(root.left);
        }
        result.add(root.val);
        if(root.right != null){
            inorderTraversal(root.right);
        }
        System.out.println(result);
    }

    @Test
    public void combinationSum() {
        int[] candidates = new int[]{2,3,6,7};
        int target = 7;
        List<List<Integer>> back = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combination(candidates, 0, target, list, back);
        System.out.println(back);
    }

    public void combination(int[] candidates, int index, int target,
                            List<Integer> list, List<List<Integer>> back){
        if(index == candidates.length){
            return;
        }
        if(target == 0){
            back.add(new ArrayList<Integer>(list));
            return;
        }
        //跳过
        combination(candidates, index + 1, target, list, back);
        if(target - candidates[index] >= 0){
            list.add(candidates[index]);
            combination(candidates, index, target - candidates[index], list, back);
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void trap() {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};

        int rain = 0;

        //结算雨水
        for(int i = 0; i < height.length - 2; i++){
            for(int j = 0; j < height[i]; j++){
                for(int k = i + 1; k < height.length; k++){
                    if(height[k] >= j + 1){
                        rain += (k-i-1);
                        break;
                    }
                }
            }
        }
        System.out.println(rain);
    }

    @Test
    public void permute() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        addPermute(0, nums, result);
        System.out.println(result);
    }

    public void addPermute(int index, int[] nums, List<List<Integer>> result){
        if(index >= nums.length - 1){
            result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        //不交换
        addPermute(index + 1, nums, result);

        //交换
        for(int i = index + 1; i < nums.length; i++){
            swap(index, i, nums);
            addPermute(index + 1, nums, result);
            swap(index, i ,nums);
        }
    }

    public void swap(int first, int second, int[] nums){
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    @Test
    public void permutes() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for(int num : nums){
            list.add(num);
        }
        addPermute(nums.length, 0, list, result);
        System.out.println(result);
    }

    public void addPermute(int length, int index, List<Integer> list, List<List<Integer>> result){
        if(index == length - 1){
            result.add(new ArrayList<>(list));
            return;
        }

        //交换
        for(int i = index; i < length; i++){
            Collections.swap(list, index, i);
            addPermute(length, index + 1, list, result);
            Collections.swap(list, index, i);
        }
    }

    @Test
    public void subsets() {
        int[] nums = new int[]{1,2,3};
        int length = nums.length;
//        if(length == 0){
//            return new ArrayList<>();
//        }
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        reback(0, subsets, intList, length, nums);
        subsets.add(new ArrayList<>());
        System.out.println(subsets);
    }

    public void reback(int index, List<List<Integer>> subsets, List<Integer> intList, int length, int[] nums){
        if(index >= length){
            subsets.add(new ArrayList<>(intList));
            return;
        }
        //有两种方式选择，第一种不添加自己直接跳过进入递归，第二种添加自己然后进入递归
        index++;
        reback(index, subsets, intList, length, nums);
        intList.add(nums[index - 1]);
        reback(index, subsets, intList, length, nums);
        intList.remove(intList.size() - 1);
    }

    int maxLength = Integer.MIN_VALUE;

    @Test
    public void maxPathSum() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        maxLength = dfs(root, maxLength);
        System.out.println(maxLength);
    }

    public int dfs(TreeNode root, int maxLength){
        if(root == null){
            return 0;
        }
        int leftMaxLength = dfs(root.left, maxLength);
        int rightMaxLength = dfs(root.right, maxLength);
        maxLength = Math.max(root.val + leftMaxLength + rightMaxLength, maxLength);
        return maxLength;
    }

    @Test
    public void wordBreak() {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        Set<String> wordDictSet = new HashSet(wordDict);

            int maxLength = 0;

            for(String word : wordDictSet){
                if(word.length() > maxLength){
                    maxLength = word.length();
                }
            }
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = i; j >= 0; j--) {
                    if(i - j > maxLength){
                        break;
                    }
                    if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        System.out.println(
                dp[s.length()]
        );
    }

    @Test
    public void topKFrequent() {
        int[] nums = new int[]{4,1,-1,2,-1,2,3};
        int k = 2;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            Integer value = map.get(nums[i]);
            if(value != null){
                map.put(nums[i], value + 1);
            }else{
                map.put(nums[i], 1);
            }
        }
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        entries = entries.stream().sorted(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (int)o2.getValue() - o1.getValue();
            }
        }).collect(Collectors.toList());
        int length = Math.min(k, entries.size());
        int[] result = new int[length];
        for(int i = 0; i < length; i++){
            result[i] = entries.get(i).getKey();
            System.out.println(result[i]);
        }
    }

    @Test
    public void sortList() {
        ListNode head = new ListNode(4);
        head.next = new ListNode(3);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        List<ListNode> list = new ArrayList<>();
        while(head != null){
            list.add(head);
            head = head.next;
        }

        Collections.sort(list, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for(int i = 0; i < list.size() - 1; i++){
            list.get(i).next = list.get(i+1);
        }
        System.out.println(list.get(0));
    }

    @Test
    public void rob() {
        int[] nums = new int[]{2,1,1,2};
        Map<Integer, int[]> dpMap = new HashMap<>();
        dpMap.put(0, new int[]{nums[0], 0});
        for(int i = 1; i < nums.length; i++){
            int[] prevMaxMoney = dpMap.get(i - 1);
            int[] maxMoney = new int[2];
            maxMoney[0] = prevMaxMoney[1] + nums[i];
            maxMoney[1] = Math.max(prevMaxMoney[0], prevMaxMoney[1]);
            dpMap.put(i, maxMoney);
        }
        int[] robMoney = dpMap.get(nums.length - 1);
        System.out.println(Math.max(robMoney[0], robMoney[1]));
    }

    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }




        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    @Test
    public void numIslands() {
        char[][] grid = new char[][]{
                new char[]{'1','1','1','1','0'},
                new char[]{'1','1','0','1','0'},
        new char[]{'1','1','0','0','0'},
        new char[]{'0','0','0','0','0'}};
        if (grid == null || grid.length == 0) {
            return;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return;
    }

    List<List<Integer>> edges;
    int[] isVisited;
    boolean isValid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        isVisited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }

        for(int i = 0; i < numCourses && isValid; i++){
            if(isVisited[i] == 0){
                dfs(i);
            }
        }
        return isValid;
    }

    public void dfs(Integer num){
        isVisited[num] = 1;
        if(!isValid){
            return;
        }

        for (Integer v : edges.get(num)) {
            if(isVisited[v] == 0){
                dfs(v);
            }else if(isVisited[v] == 1){
                isValid = false;
                return;
            }
        }
        isVisited[num] = 2;
    }

    class Trie {
        Trie[] children;
        boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie trie = this;
            for(int i = 0; i < word.length(); i++){
                char wordChar = word.charAt(i);
                int index = wordChar - 'a';
                Trie[] currChildren = trie.children;
                if(currChildren[index] == null){
                    currChildren[index] = new Trie();
                }
                trie = currChildren[index];
            }
            trie.isEnd = true;
        }

        public boolean search(String word) {
            Trie trie = searchPrefix(word);
            return trie != null && trie.isEnd;
        }

        public boolean startsWith(String prefix) {
            Trie trie = searchPrefix(prefix);
            return trie != null;
        }

        public Trie searchPrefix(String word){
            Trie trie = this;
            for(int i = 0; i < word.length(); i++){
                char wordChar = word.charAt(i);
                int index = wordChar - 'a';
                Trie[] currChildren = trie.children;
                if(currChildren[index] == null){
                    return currChildren[index];
                }
                trie = currChildren[index];
            }
            return trie;
        }
    }
    @Test
    public void findKthLargest() {
        int[] nums = new int[]{2, 1};
        int k = 2;
        int[] firstK = new int[k];
        System.arraycopy(nums, 0, firstK, 0, k);
        Arrays.sort(firstK);
        for (int i = 0; i < firstK.length/2; i++) {
            int temp = firstK[i];
            firstK[i] = firstK[firstK.length-1-i];
            firstK[firstK.length-1-i] = temp;
        }
        for(int i = k; i < nums.length; i++){
            int num = firstK[k - 1];
            if(nums[i] > num){
                int start = 0;
                for(int j = 0; j < k; j++){
                    if(firstK[j] < nums[i]){
                        start = j;
                        break;
                    }
                }

                System.arraycopy(firstK, start, firstK, start + 1, k - 1 - start);
                firstK[start] = nums[i];
            }
        }
        System.out.println(firstK[k - 1]);
    }

    Random random = new Random();



    public int quickSelect(int[] a, int l, int r, int index){
        int q = randomPartition(a, l, r);
        if(q == index){
            return a[q];
        }else{
            return q < index ? quickSelect(a, l, q-1, index) : quickSelect(a, q+1, r, index);
        }
    }

    public int randomPartition(int[] a, int l, int r){
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i ,r);
        return partition(a, l, r);
    }

    public int  partition(int[] a, int l, int r){
        int x = a[r], i= l;
        for(int j = l; j < r; j++){
            if(a[j] < x){
                swap(a, i++, j);
            }
        }
        swap(a, r, i+1);
        return i+1;
    }

    public void swap(int[] a, int l, int r){
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }

    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for(int i = nums.length - 1; i > nums.length- k; i--){
            swap(nums, 0, i);
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] nums, int heapSize){
        for(int i = heapSize / 2; i >= 0; i--){
            maxHeapify(nums, i, heapSize);
        }
    }

    public void maxHeapify(int[] nums, int i, int heapSize){
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if(l < heapSize && nums[l] > nums[largest]) {
            largest = l;
        }

        if(r < heapSize && nums[r] > nums[largest]) {
            largest = r;
        }

        if(largest != i){
            swap(nums, i, largest);
            maxHeapify(nums, largest, heapSize);
        }
    }

    int maxSquare = 0;

    @Test
    public void maximalSquare() {
        char[][] matrix = new char[][]{
                new char[]{'1','1','1','1','1','1','1','1'},
                new char[]{'1','1','1','1','1','1','1','0'},
                new char[]{'1','1','1','1','1','1','1','0'},
                new char[]{'1','1','1','1','1','0','0','0'},
                new char[]{'0','1','1','1','1','0','0','0'}};
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == '1'){
                    updateMaxSquare(matrix, i, j);
                }
            }
        }
        System.out.println(maxSquare);
    }

    public void updateMaxSquare(char[][] matrix, int i, int j){
        int length = 0;
        int width = 0;
        for(int m = j; m < matrix[0].length && matrix[i][m] == '1'; m++){
            length++;
        }
        for(int n = i; n < matrix.length && matrix[n][j] == '1'; n++){
            width++;
        }
        int square = Math.min(length, width);
        while(square > maxSquare){
            boolean flag = false;
            for(int k = i; k <= i + square - 1; k++){
                for(int l = j; l <= j + square - 1; l++){
                    if(matrix[k][l] != '1'){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    break;
                }
            }
            if(!flag){
                maxSquare = Math.max(maxSquare, square);
            }
            square--;
        }
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> valList = new ArrayList<>();
        while(head != null){
            valList.add(head.val);
            head = head.next;
        }
        for(int i = 0, j = valList.size() - 1; i <= j; i++, j--){
            int first = valList.get(i);
            int second = valList.get(j);
            if(first != second){
                return false;
            }
        }
        return true;
    }

    private TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    public boolean dfs(TreeNode root, TreeNode p, TreeNode q){
        if(root == null){
            return false;
        }
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.left, p, q);
        if((lson && rson) || ((root == p || root == q) && (lson || rson))){
            ans = root;
        }
        return lson || rson || root == p || root == q;
    }

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];
        answer[0] = 1;
        for(int i = 1; i < length; i++){
            answer[i] = answer[i-1] * nums[i];
        }
        int rightProduct = 1;
        for(int i = length - 1; i >= 0; i--){
            answer[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        return answer;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int x= 0, y = n-1;
        while(x < m && y >=0){
            if(matrix[x][y] == target){
                return true;
            }
            if(matrix[x][y] > target){
                --y;
            }else{
                ++x;
            }
        }
        return false;
    }

    int nums = 0;
    int n = 12;

    @Test
    public void numSquares() {

        int[] dp = new int[n + 1];
        for(int i = 0; i < n; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 1; j*j<=i; j++){
                min = Math.min(min, dp[j]);
            }
            dp[i] = min + 1;
        }
        return;
    }

    @Test
    public void moveZeroes() {
        int[] nums = new int[]{0,0,1};
        int length = nums.length;
        int first = 0;
        int second = length - 1;
        while(second >= 0 && nums[second] == 0){
            second--;
        }

        while(first <= second){
            if(nums[first] == 0){
                moveAhead(nums, first, second);
                nums[second] = 0;
                second--;
                while(second >= first && nums[second] == 0){
                    second--;
                }
            }
        }
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public void moveAhead(int[] nums, int first, int second){
        System.arraycopy(nums, first + 1, nums, first, second - first);
    }
}
