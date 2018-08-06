import java.util.*;

public class Main {

    public int RectCover(int target) {
        if (target == 1 || target == 2) {
            return target;
        } else {
            return RectCover(target - 1) + RectCover(target - 2);
        }
    }

    public int NumberOf(int n) {
        String num = Integer.toBinaryString(n);

        return num.replaceAll("0", "").length();
    }

    public int NumberOf2(int n) {
        return Integer.bitCount(n);
    }

    public int NumberOf3(int n) {
        int count = 0;

        while (n != 0) {
            count++;
            //&与运算
            n = n & (n - 1);
        }

        return count;
    }

    public double Power(double base, int exponent) {
        double result = 1.0;

        for (int i = 0; i < Math.abs(exponent); i++) {
            result *= base;
        }

        if (exponent < 0) {
            result = 1 / result;
        }

        return result;
    }

    //很漂亮的递归
    public double Power2(double base, int exponent) {
        int n = Math.abs(exponent);

        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return base;
        }

        double result = Power2(base, n >> 1);
        result *= result;

        if ((n & 1) == 1) {
            result *= base;
        }

        return exponent > 0 ? result : 1 / result;
    }

    public void reOrderArray(int[] array) {
        if (array.length == 0 || array.length == 1) return;

        int length = array.length;

        int[] orderArray = new int[length];
        int oddCount = 0;

        for (int j = 0; j < length; j++) {
            if ((array[j] & 1) == 1) {
                oddCount++;
            }
        }

        int m = 0, n = 0;
        for (int i = 0; i < length; i++) {
            if ((array[i] & 1) == 1) {
                orderArray[m] = array[i];
                m++;
            } else {
                orderArray[oddCount + n] = array[i];
                n++;
            }
        }

        for (int k = 0; k < length; k++) {
            array[k] = orderArray[k];
        }
    }

    public ListNode FindKthToTail(ListNode head, int k) {

        if (head == null || k <= 0) return null;

        ListNode a = head;
        ListNode b = head;

        for (int i = 1; i < k; i++) {
            if (b.next != null) {
                b = b.next;
            } else {
                return null;
            }

        }

        while (b.next != null) {
            b = b.next;
            a = a.next;
        }

        return a;
    }

    public ListNode ReverseList(ListNode head) {
        if (head == null) return null;

        ListNode beforeNode = null;
        ListNode afterNode = null;

        while (head != null) {
            afterNode = head.next;
            head.next = beforeNode;
            beforeNode = head;
            head = afterNode;
        }

        return beforeNode;
    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;
        if (list1 == null && list2 != null) return list2;
        if (list1 != null && list2 == null) return list1;

        ListNode mergeHead = null;
        ListNode current = null;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                if (mergeHead == null) {
                    mergeHead = current = list1;
                } else {
                    current.next = list1;
                    current = current.next;
                }
                list1 = list1.next;
            } else {
                if (mergeHead == null) {
                    mergeHead = current = list2;
                } else {
                    current.next = list2;
                    current = current.next;
                }
                list2 = list2.next;
            }
        }

        if (list2 == null) {
            current.next = list1;
        } else if (list1 == null) {
            current.next = list2;
        }

        return mergeHead;
    }

    public ListNode Merge2(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val <= list2.val) {
            list1.next = Merge2(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge2(list1, list2.next);
            return list2;
        }
    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;

        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                result = doesTree1HaveTree2(root1, root2);
            }

            if (!result) {
                result = HasSubtree(root1.left, root2);
            }

            if (!result) {
                result = HasSubtree(root1.right, root2);
            }
        }

        return result;
    }

    public boolean doesTree1HaveTree2(TreeNode node1, TreeNode node2) {
        if (node2 == null) {
            return true;
        }

        if (node1 == null) {
            return false;
        }

        if (node1.val != node2.val) {
            return false;
        }

        return doesTree1HaveTree2(node1.left, node2.left) && doesTree1HaveTree2(node1.right, node2.right);
    }

    public void Mirror(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;

        TreeNode tmp = null;
        tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        if (root.left != null) {
            Mirror(root.left);
        }

        if (root.right != null) {
            Mirror(root.right);
        }
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> printList = new ArrayList<Integer>();

        int y = matrix.length;
        int x = matrix[0].length;
        while (y != 0) {
            for (int i = 0; i < matrix[0].length; i++) {
                printList.add(matrix[0][i]);
            }

            if (y == 1) break;
            matrix = doReverse(matrix);
            y = matrix.length;
        }
        return printList;
    }

    public int[][] doReverse(int[][] matrix) {
        int y = matrix.length;
        int x = matrix[0].length;
        int[][] reMatrix = new int[x][y - 1];
//        for(int i = x-1;i>=0;i--){
//            for(int j=1;j<y;j++){
//                reMatrix[x-1-i][j-1] = matrix[j][i];
//            }
//        }
        for (int i = 1; i < y; i++) {
            for (int j = 0; j < x; j++) {
                reMatrix[x - 1 - j][i - 1] = matrix[i][j];
            }
        }
        return reMatrix;
    }

//  定义栈的数据结构，
// 请在该类型中实现一个能够得到栈中所含最小元素的min函数
// （时间复杂度应为O（1））。

    private Stack<Integer> data = new Stack<>();
    private Stack<Integer> min = new Stack<>();
    private Integer temp;

    public void push(int node) {
        if (temp != null) {
            if (node > temp) {
                data.push(node);
            } else {
                data.push(node);
                min.push(node);
                temp = node;
            }
        } else {
            temp = node;
            data.push(node);
            min.push(node);
        }
    }

    public void pop() {
        int num = data.pop();
        int num2 = min.pop();

        if (num != num2) {
            min.push(num2);
        }
    }

    public int top() {
        int top = data.pop();
        data.push(top);
        return top;
    }

    public int min() {
        int num = min.pop();
        min.push(num);
        return num;
    }

//    输入两个整数序列，第一个序列表示栈的压入顺序，
// 请判断第二个序列是否可能为该栈的弹出顺序。
// 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
// 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
// 但4,3,5,1,2就不可能是该压栈序列的弹出序列。
// （注意：这两个序列的长度是相等的）

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> temp = new Stack<Integer>();

        if (pushA.length == 0) return true;

        int length = pushA.length;
        int popIndex = 0;

        for (int i = 0; i < length; i++) {
            temp.push(pushA[i]);
            while (!temp.empty() && temp.peek() == popA[popIndex]) {
                temp.pop();
                popIndex++;
            }
        }

        return temp.empty();
    }

    //  从上往下打印出二叉树的每个节点，同层节点从左至右打印。
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> valList = new ArrayList<Integer>();
        ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();

        if (root == null) return valList;

        nodeList.add(root);

        while (nodeList.size() != 0) {
            TreeNode temp = nodeList.remove(0);
            if (temp.left != null) {
                nodeList.add(temp.left);
            }

            if (temp.right != null) {
                nodeList.add(temp.right);
            }
            valList.add(temp.val);
        }
        return valList;
    }

    //    输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
// 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0) return false;
        if (sequence.length == 1) return true;

        return judge(sequence, 0, sequence.length - 1);
    }

    public boolean judge(int[] a, int start, int end) {
        //如果整个数组循环结束都没有问题
        if (start >= end) {
            return true;
        }
        int i = start;
        while (a[i] < a[end]) {
            i++;
        }
        for (int j = i; j < end; j++) {
            if (a[j] < a[end]) {
                return false;
            }
        }

        return judge(a, start, i - 1) && judge(a, i, end - 1);
    }

    //输入一颗二叉树的跟节点和一个整数，
    // 打印出二叉树中结点值的和为输入整数的所有路径。
    // 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
    // (注意: 在返回值的list中，数组长度大的数组靠前)
    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> list = new ArrayList<Integer>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) return listAll;
        list.add(root.val);
        target -= root.val;
        if (target == 0 && root.right == null && root.left == null) {
            listAll.add(new ArrayList<Integer>(list));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size() - 1);

        return listAll;
    }

    //输入一个复杂链表（每个节点中有节点值，
    // 以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
    // 返回结果为复制后复杂链表的head。
    // （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）

    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }

        //在原节点后插入复制节点
        RandomListNode currentNode = pHead;
        while (currentNode != null) {
            RandomListNode cloneNode = new RandomListNode(currentNode.label);
            RandomListNode nextNode = currentNode.next;
            currentNode.next = cloneNode;
            cloneNode.next = nextNode;
            currentNode = nextNode;
        }

        //给复制节点的随接节点赋值
        currentNode = pHead;
        while (currentNode != null) {
            currentNode.next.random = currentNode.random == null ? null : currentNode.random.next;
            currentNode = currentNode.next.next;
        }

        //拆分原节点与复制节点
        currentNode = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (currentNode != null) {
            RandomListNode cloneNode = currentNode.next;
            currentNode.next = cloneNode.next;
            cloneNode.next = cloneNode.next == null ? null : cloneNode.next.next;
            currentNode = currentNode.next;
        }

        return pCloneHead;
    }

    //递归方式(不对，没有深度复制，pHead.random 的复制是引用，会改变原结构)
    public RandomListNode Clone2(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode pCloneHead = new RandomListNode(pHead.label);
        pCloneHead.next = pHead.next;
        pCloneHead.random = pHead.random;
        pCloneHead.next = Clone2(pHead.next);
        return pCloneHead;
    }

    //递归(有问题)
    public RandomListNode Clone4(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode pCloneHead = new RandomListNode(pHead.label);
        pCloneHead.next = new RandomListNode(pHead.next.label);
        pCloneHead.random = new RandomListNode(pHead.random.label);
        pCloneHead.next = new RandomListNode(Clone4(pHead.next).label);
        return pCloneHead;
    }

    //另一种深度复制
    public RandomListNode clone3(RandomListNode pHead) {
        if (pHead == null) return null;

        RandomListNode cloneHead = new RandomListNode(pHead.label);
        RandomListNode temp = cloneHead;

        while (pHead.next != null) {
            temp.next = new RandomListNode(pHead.next.label);
            if (pHead.random != null) {
                temp.random = new RandomListNode(pHead.random.label);
            }
            pHead = pHead.next;
            temp = temp.next;
        }

        return cloneHead;
    }

    // ***
    //输入一棵二叉搜索树，
    // 将该二叉搜索树转换成一个排序的双向链表。
    // 要求不能创建任何新的结点，只能调整树中结点指针的指向。

    //递归方式转为中序遍历
    TreeNode leftHead = null;
    TreeNode rightHead = null;

    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;

        Convert(pRootOfTree.left);
        if (rightHead == null) {
            leftHead = rightHead = pRootOfTree;
        } else {
            rightHead.right = pRootOfTree;
            pRootOfTree.left = rightHead;
            rightHead = pRootOfTree;
        }

        Convert(pRootOfTree.right);

        return leftHead;
    }

    //非递归方式
    public TreeNode Convert2(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;

        TreeNode list = null;
        Stack<TreeNode> s = new Stack<>();

        while (pRootOfTree != null || !s.isEmpty()) {
            if (pRootOfTree != null) {
                s.push(pRootOfTree);
                pRootOfTree = pRootOfTree.right;
            } else {
                pRootOfTree = s.pop();
                if (list == null) {
                    list = pRootOfTree;
                } else {
                    list.left = pRootOfTree;
                    pRootOfTree.right = list;
                    list = pRootOfTree;
                }
                pRootOfTree = pRootOfTree.left;
            }
        }
        return list;
    }

    //输入一个字符串,按字典序打印出该字符串中字符的所有排列。
    // 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的
    // 所有字符串abc,acb,bac,bca,cab和cba。

//    public static void main(String[] args){
//        Main main = new Main();
//        System.out.println(main.Permutation("abc").toString());
//    }

    public ArrayList<String> Permutation(String str) {
        List<String> list = new ArrayList<String>();
        if (str != null && str.length() > 0) {
            PermutationHelper(str.toCharArray(), 0, list);
            Collections.sort(list);
        }

        return (ArrayList) list;
    }

    public void PermutationHelper(char[] cs, int i, List<String> list) {
        if (i == cs.length - 1) {
            String val = String.valueOf(cs);
            if (!list.contains(val)) {
                list.add(val);
            }
        } else {
            for (int j = i; j < cs.length; j++) {
                swap(cs, i, j);
                PermutationHelper(cs, i + 1, list);
                swap(cs, i, j);
            }
        }
    }

    public void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

//    数组中有一个数字出现的次数超过数组长度的一半，
// 请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
// 由于数字2在数组中出现了5次，超过数组长度的一半，
// 因此输出2。如果不存在则输出0。

    public int MoreThanHalfNum_Solution(int[] array) {
        if (array.length == 0) return 0;

        Map<Integer, Integer> numTimes = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (numTimes.containsKey(array[i])) {
                int times = numTimes.get(array[i]) + 1;
                numTimes.put(array[i], times);
            } else {
                numTimes.put(array[i], 1);
            }
        }

        Collection<Integer> c = numTimes.values();
        int maxTime = Collections.max(c);
        int maxNum = 0;
        int half = array.length / 2;

        for (Integer maxKey : numTimes.keySet()) {
            if (numTimes.get(maxKey).equals(maxTime)) {
                //返回的一定是最后一个满足条件的key
                //但不影响结果
                maxNum = maxKey;
            }
        }

        return (maxTime > half) ? maxNum : 0;
    }

    //先排序，如果存在长度超过数组长度一半的元素，那么这个数字一定位于排序数组中间
    public int MoreThanHalfNum_solution2(int[] array) {
        if (array.length == 0) return 0;

        Arrays.sort(array);
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == array[array.length / 2]) {
                count++;
            }
        }

        return (count > array.length/2) ? array[array.length/2] : 0;
    }

//    public static void main(String[] args){
//        Main main = new Main();
//        System.out.println(main.MoreThanHalfNum_solution2(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
//    }

//    输入n个整数，找出其中最小的K个数。
//    例如输入4,5,1,6,2,7,3,8这8个数字，
//    则最小的4个数字是1,2,3,4,。

    public ArrayList<Integer> GetLeastNumber_Solution(int[] input,int k){
        //Arrays.sort()方法本质
        // 如果数组长度大于等于286且连续性好的话，
        // 就用归并排序，如果大于等于286且连续性不好的话就用双轴快速排序。
        // 如果长度小于286且大于等于47的话就用双轴快速排序，
        // 如果长度小于47的话就用插入排序
        Arrays.sort(input);

        ArrayList<Integer> leastNum = new ArrayList<>();

        if(input.length == 0 || input.length<k) return leastNum;

        for (int i=0;i<k;i++){
            leastNum.add(input[i]);
        }

        return leastNum;
    }

//    public static void main(String[] args){
//        Main m = new Main();
//        System.out.println(m.GetLeastNumber_Solution(new int[]{4,5,1,6,2,7,3,8},4).toString());
//    }

    //求数组连续子元素的最大和

    //动态规划
    //求解递推表达式
    //F(i) = MAX(F(i-1)+array[i],array[i])
    public int FindGreatestSumOfSubArray(int[] array){
        int max = array[0];
        int res = array[0];

        for(int i=1;i<array.length;i++){
            max = Math.max(max+array[i],array[i]);
            res = Math.max(max,res);
        }

        return res;
    }

    public int FindGreatestSumOfSubArray2(int[] array){
        if(array.length == 0) return 0;

        int curMax = array[0];
        int greatestMax = array[0];

        for (int i=1;i<array.length;i++){
            if(curMax<0){
                curMax = array[i];
            }else {
                curMax += array[i];
            }

            if(curMax>greatestMax){
                greatestMax = curMax;
            }
        }

        return greatestMax;
    }

//    public static void main(String[] args){
//        Main m = new Main();
//        System.out.println(m.FindGreatestSumOfSubArray2(new int[]{-2,-8,-1,-5,-9}));
//    }

//    求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
//    1~13中包含1的数字有1、10、11、12、13因此共出现6次

    //unfinished
    public int NumberOf1Between1AndN(int n){
        return 0;
    }

//    输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
//    打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
//    则打印出这三个数字能排成的最小数字为321323。

    //基本思路，先比较数字的首位，如果首位相同，再依次比较
    public String PrintMinNumber(int[] numbers){
        if (numbers.length == 0) return "";

        int len = numbers.length;
        String[] str = new String[len];

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<len;i++){
            str[i] = String.valueOf(numbers[i]);
        }

        //
        Arrays.sort(str,new Comparator<String>(){

            @Override
            public int compare(String s1, String s2) {
                String c1 = s1 + s2;
                String c2 = s2 + s1;
                return c1.compareTo(c2);
            }
        });

        for(int i=0;i<len;i++){
            sb.append(str[i]);
        }

        return sb.toString();

    }
}


