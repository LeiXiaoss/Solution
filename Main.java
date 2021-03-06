import com.sun.org.apache.xpath.internal.operations.Or;
import sun.reflect.generics.tree.Tree;

import javax.naming.ldap.LdapContext;
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
//        System.out.println(main.MoreThanHalfNum_solution2(
// new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
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

//    把只包含质因子2、3和5的数称作丑数（Ugly Number）。
// 例如6、8都是丑数，但14不是，因为它包含质因子7。
// 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数

    public int GetUglyNumber_Solution(int index){
        if(index <= 0) return 0;

        int[] uglyNumbers = new int[index];

        uglyNumbers[0] = 1;
        int nextIndex = 1;

        int numberMyltiply2Index = 0;
        int numberMyltiply3Index = 0;
        int numberMyltiply5Index = 0;

        while(nextIndex < index){
            int min = min(uglyNumbers[numberMyltiply2Index]*2,
                    uglyNumbers[numberMyltiply3Index]*3,
                    uglyNumbers[numberMyltiply5Index]*5);
            uglyNumbers[nextIndex] = min;

            while (uglyNumbers[numberMyltiply2Index]*2 <= uglyNumbers[nextIndex]){
                numberMyltiply2Index++;
            }

            while (uglyNumbers[numberMyltiply3Index]*3 <= uglyNumbers[nextIndex]){
                numberMyltiply3Index++;
            }

            while (uglyNumbers[numberMyltiply5Index]*5 <= uglyNumbers[nextIndex]){
                numberMyltiply5Index++;
            }

            nextIndex++;
        }
        return uglyNumbers[index-1];
    }

    public int min(int number1,int number2,int number3){
        int min = (number1 > number2)?number2:number1;
        return min = (number3 > min)?min:number3;
    }

//    public static void main(String[] args){
//        Main main = new Main();
//        System.out.println(main.GetUglyNumber_Solution(1500));
//    }

//    在一个字符串(0<=字符串长度<=10000，全部由字母组成)
// 中找到第一个只出现一次的字符,并返回它的位置,
// 如果没有则返回 -1（需要区分大小写）.

    public int FirstNotRepeatingChar(String str){
        int length = str.length();

        if (length == 0) return -1;

        char[] chars = new char[length];
        chars = str.toCharArray();

        Map<Character,Integer> charHashMap = new LinkedHashMap<>();

        for (int i=0;i<length;i++){
            if (!charHashMap.containsKey(chars[i])){
                charHashMap.put(chars[i],1);
            }else {
                int times = charHashMap.get(chars[i]);
                charHashMap.put(chars[i],times+1);
            }
        }

        int firstNoRepIndex = -1;
        for (int i=0;i<length;i++){
            if(charHashMap.get(chars[i]) == 1){
                firstNoRepIndex = i ;
                break;
            }
        }

        return firstNoRepIndex;
    }

//    public static void main(String[] args){
//        System.out.println(new Main().FirstNotRepeatingChar("abaccdeff"));
//    }

//    在数组中的两个数字，如果前面一个数字大于后面的数字，
// 则这两个数字组成一个逆序对。输入一个数组,
// 求出这个数组中的逆序对的总数P。
// 并将P对1000000007取模的结果输出。 即输出P%1000000007

    public int InversePairs(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }

        int[] copy = new int[array.length];

        for (int i=0;i<array.length;i++){
            copy[i] = array[i];
        }

        int count = InversePairsCore(array,copy,0,array.length-1);

        return count;
    }

    public int InversePairsCore(int[] array,int[] copy,int start,int end){
        //终止条件
        if(start == end){
            return 0;
        }

        int length = (end - start)/2;

        int left = InversePairsCore(array,copy,start,start+length);
        int right = InversePairsCore(array,copy,start+length+1,end);

        int i = start + length;
        int j = end;

        int indexCopy = end;
        int count = 0;

        while(i>=start&&j>=start+length+1){
            if(array[i]>array[j]){
                copy[indexCopy--] = array[i--];
                count += j-start-length;
                if (count >= 1000000007){
                    count = count%1000000007;
                }
            }else {
                copy[indexCopy--] = array[j--];
            }
        }

        for (;i>=start;i--){
            copy[indexCopy--] = array[i];
        }

        for (;j>=start+length+1;j--){
            copy[indexCopy--] = array[j];
        }

        //将最新排序的结果从辅助数组复制到正式数组中
//        for (int k=0;k<=end;k++){
//            array[k] = copy[k];
//        }
        //如果每次都复制，时间复杂度太高，直接使用排序好的辅助数组

        return (left+right+count)%1000000007;
    }

//    public static void main(String[] args){
//        System.out.println(new Main().InversePairs(new int[]{7,5,6,4}));
//    }

//    输入两个链表，找出它们的第一个公共结点。

    //借助栈，用空间换时间，时间复杂度O(m+n),空间复杂度O(m+n)
    public ListNode FindFirstCommonNode(ListNode pHead1,ListNode pHead2){
        if(pHead1 == null || pHead2 == null){
            return null;
        }

        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();

        while (pHead1 != null){
            stack1.push(pHead1);
            pHead1 = pHead1.next;
        }

        while (pHead2 != null){
            stack2.push(pHead2);
            pHead2 = pHead2.next;
        }

        ListNode currentListNode = null;

        while(!stack1.isEmpty() && !stack2.isEmpty() && stack1.peek() == stack2.peek()){
            stack1.pop();
            currentListNode = stack2.pop();
        }

        return currentListNode;
    }

    //不借助栈
    public ListNode FindFirstCommonNode2(ListNode pHead1,ListNode pHead2){
        if(pHead1 == null || pHead2 == null) return null;

        int length1 = getListNodeLength(pHead1);
        int length2 = getListNodeLength(pHead2);
        int lengthDiff = length1 - length2;

        ListNode pHeadLong = pHead1;
        ListNode pHeadShort = pHead2;
        if(length1 < length2){
            pHeadLong = pHead2;
            pHeadShort = pHead1;
            lengthDiff = length2 - length1;
        }

        for (int i=0;i<lengthDiff;i++){
            pHeadLong = pHeadLong.next;
        }

        ListNode commonNode = null;
        while (pHeadLong != null && pHeadShort != null){
            if(pHeadLong == pHeadShort){
                commonNode = pHeadShort;
                break;
            }else {
                pHeadLong = pHeadLong.next;
                pHeadShort = pHeadShort.next;
            }
        }
        return commonNode;
    }

    private int getListNodeLength(ListNode pHead) {
        int length = 0;

        while(pHead != null){
            pHead = pHead.next;
            length++;
        }

        return length;
    }

//统计一个数字在排序数组中出现的次数。

    //实用递归求解，用二分分别查找第一次和第二次出现，时间复杂度O(logn)
    public int GetNumberOfK(int[] array,int k){
        if(array == null || array.length == 0){
            return 0;
        }

        if(k < array[0] || array[array.length-1] < k){
            return 0;
        }

        int firstIndex = getFirstK(array,k,0,array.length-1);
        int lastIndex = getLastK(array,k,0,array.length-1);

        int count = 0;

        if(firstIndex > -1 && lastIndex > -1){
            count = lastIndex - firstIndex + 1;
        }

        return count;
    }

    public int getFirstK(int[] array,int k,int start,int end){
        if(start == end){
            if(array[start] == k){
                return start;
            }else {
                return -1;
            }
        }

        int middleIndex = (start + end)/2;
        int middleNum = array[middleIndex];

        if (middleNum == k){
            if(middleIndex > start && array[middleIndex-1] != k){
                return middleIndex;
            }else if(middleIndex == start){
                return middleIndex;
            }else {
                end = middleIndex-1;
            }
        }else if(middleNum > k){
            end = middleIndex - 1;
        }else {
            start = middleIndex + 1;
        }

        return getFirstK(array,k,start,end);
    }

    public int getLastK(int[] array,int k,int start,int end){
        if(start == end){
            if(array[start] == k){
                return start;
            }else {
                return -1;
            }
        }

        int middleIndex = (start + end)/2;
        int middleNum = array[middleIndex];

        if(middleNum == k){
            if(middleIndex < end && array[middleIndex+1] != k){
                return middleIndex;
            }else if (middleIndex == end){
                return middleIndex;
            }else {
                start = middleIndex + 1;
            }
        }else if(middleNum > k){
            end = middleIndex - 1;
        }else {
            start = middleIndex + 1;
        }

        return getLastK(array,k,start,end);
    }

//    public static void main(String[] args){
//        System.out.println(new Main().GetNumberOfK(new int[]{1,2,3,3,3,3,4,5},3));
//    }

    //不使用递归，使用循环查找（只实现一个函数），时间复杂度是一致的
    public int getFirstK2(int[] array,int k,int start,int end){

        while(start != end){
            int middleIndex = (start + end)/2;
            int middleNum = array[middleIndex];

            if(middleNum == k){
                if(middleIndex > start && array[middleIndex-1]!=k){
                    return middleIndex;
                }else if(middleIndex == start){
                    return middleIndex;
                }else {
                    end = middleIndex - 1;
                }
            }else if(middleNum > k){
                end = middleIndex - 1;
            }else {
                start = middleIndex + 1;
            }
        }

        if(array[start] == k){
            return start;
        }else {
            return -1;
        }
    }

    //不查找K，查找k+0.5和k-0.5的位置。算法时间复杂度依旧是O(logn),但是明显比第一种做法容易实现。
    public int GetNumberOfK2(int[] array,int k){
        return KSearch(array,k+0.5) - KSearch(array,k-0.5);
    }

    private int KSearch(int[] array,double k){
        int start = 0;
        int end = array.length-1;

        while (start <= end){
            int middle = (start + end)/2;
            if(array[middle] > k){
                end = middle - 1;
            }else if(array[middle] < k){
                start = middle + 1;
            }
        }

        return start;
    }

//    public static void main(String[] args){
//        System.out.println(new Main().GetNumberOfK2(new int[]{1,2,3,3,3,3,4,5},3));
//    }

    //一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字的范围都在0~n-1之内。
    //在范围0~n-1内的n个数字有且只有一个数字不在该数组，请找出这个数字

    //转化为在排序数组中找出第一个值与下标不相等的元素，
    //使用二分查找，时间复杂度O(logn)。
    public int getMissingNumber(int[] array,int length){
        if(array == null || length <= 0){
            return -1;
        }

        int left = 0;
        int right = length-1;

        while (left <= right){
            int middleIndex = (right+left)>>1;
            if(array[middleIndex] != middleIndex){
                if(array[middleIndex-1] == middleIndex-1 || middleIndex == 0){
                    return middleIndex;
                }else {
                    right = middleIndex-1;
                }
            }else if (array[middleIndex] == middleIndex){
                left = middleIndex+1;
            }
        }

        if(left == length){
            return length;
        }else {
            return -1;
        }
    }

//    public static void main(String[] args){
//        System.out.println(new Main().getMissingNumber(new int[]{0,1,2,3,4,6,7},7));
//    }


    //单调递增数组，每个元素整数且唯一。找出数组中任意一个数值等于其下标的元素。
    //{-3,-1,1,3,5} 输出3

    //二分查找，算法复杂度O(logn)。
    public int getNumberSameAsIndex(int[] array,int length){
        if (array == null || length <= 0){
            return -1;
        }

        int left = 0;
        int right = length-1;

        while(left <= right){
            int middleIndex = (right + left)>>1;
            if(array[middleIndex] == middleIndex){
                return middleIndex;
            }else if(array[middleIndex] > middleIndex){
                right = middleIndex - 1;
            }else if(array[middleIndex] < middleIndex){
                left = middleIndex + 1;
            }
        }
        return -1;
    }

//    public static void main(String[] args){
//        System.out.println(new Main().getNumberSameAsIndex(new int[]{-3,-1,1,3,5},5));
//    }

    //二叉搜索树的第K大节点
    //求二叉搜索树的中序遍历，遍历序列的数值是递增排序的

    //递归实现
    int index = 0;
    public  TreeNode KthNode(TreeNode root,int k){
        if(root != null){
            TreeNode node = KthNode(root.left,k);
            if(node != null){
                return node;
            }
            index++;

            if(index == k){
                return root;
            }

            node = KthNode(root.right,k);
            if(node != null){
                return node;
            }
        }
        return null;
    }

    //非递归实现二叉搜索树中序遍历,使用栈实现，空间复杂度大于使用递归。（用栈保存了数据）
    public TreeNode KthTreeNode(TreeNode pRoot,int k){
        if(pRoot == null || k <= 0){
            return null;
        }

        int count = 0;

        Stack<TreeNode> LDRStack = new Stack<>();
        TreeNode KthNode = null;

        while(pRoot != null || !LDRStack.isEmpty()){
            while (pRoot != null){
                LDRStack.push(pRoot);
                pRoot = pRoot.left;
            }

            pRoot = LDRStack.pop();
            count++;

            if(count == k){
                KthNode = pRoot;
            }

            pRoot = pRoot.right;
        }

        return KthNode;
    }


    //输入一棵二叉树，求该树的深度。
    // 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，
    // 最长路径的长度为树的深度。

    //递归写法
    public int TreeDepth(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);

        return Math.max(left,right)+1;
    }

    //二叉树层次遍历
    public int TreeDepth2(TreeNode root){
        if(root == null){
            return 0;
        }

        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        //depth深度，count标记，nextCount这一层的大小。
        int depth=0,count=0,nextCount=1;

        while (list.size() != 0){
            TreeNode top = list.poll();
            count++;

            if(top.left != null){
                list.add(top.left);
            }

            if(top.right != null){
                list.add(top.right);
            }

            if(count == nextCount){
                nextCount = list.size();
                count = 0;
                depth++;
            }
        }

        return depth;
    }

    //平衡二叉树
    //输入一棵二叉树，判断该二叉树是否是平衡二叉树。

    //递归做法,函数调用太多，时间复杂度过高
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null){
            return true;
        }

        int leftDepth = TreeDepth(root.left);
        int rightDepth = TreeDepth(root.right);

        int dex = leftDepth - rightDepth;

        if(dex > 1 || dex < -1){
            return false;
        }

        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    //从后向前遍历，如果有任意一个节点不平衡返回-1(合理剪枝)；
    public boolean IsBalanced(TreeNode root){
        return getDepth(root) != -1;
    }

    private int getDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        int left = getDepth(root.left);
        if(left == -1) return -1;

        int right = getDepth(root.right);
        if(right == -1) return -1;

        if(Math.abs(left - right) > 1) {
            return -1;
        }else {
            return Math.max(right,left)+1;
        }
    }

    //一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。
    // 请写程序找出这两个只出现一次的数字。

    //异或运算
    public void FindNumsAppearOnce(int[] array,int num1[],int num2[]){
        if(array == null || array.length<2){
            return;
        }

        int OrResult = array[0];
        for (int i=1;i<array.length;i++){
            OrResult = array[i]^OrResult;
        }

        if(OrResult == 0) return;

        int FirstOneIndex = findTheFirstOne(OrResult);

        for (int i=0;i<array.length;i++){
            if(isBit1(array[i],FirstOneIndex)){
                num1[0] = num1[0]^array[i];
            }else {
                num2[0] = num2[0]^array[i];
            }
        }
    }

    private int findTheFirstOne(int OrResult){
        String result = Integer.toBinaryString(OrResult);

        int index = result.indexOf('1');
        return result.length() - index - 1;
    }

    private boolean isBit1(int num,int index){
        num = num >> index;
        return (num & 1)==1;
    }
//
//    public static void main(String[] args){
//        new Main().FindNumsAppearOnce(new int[]{2,4,3,6,3,2,5,5},new int[1],new int[1]);
//    }

    //一个数组中除了一个数字只出现一次之外，其他数字都出现了三次
    //找出只出现一次的数字

    //转换为二进制，二进制每一位对应的数字相加，如果可以被3整除，这一位为0，否则为1。
    //注意位运算的操作
    public int findNumberAppearingOnce(int[] number){
        if(number == null || number.length <= 0){
            throw new RuntimeException("Invaild input");
        }

        int[] bitSum = new int[32];
        for(int i=0;i<number.length;i++){
            int bitMask = 1;
            for (int j=31;j>=0;j--){
                int bit = number[i] & bitMask;

                if(bit != 0){
                    bitSum[j] += 1;
                }
                bitMask = bitMask << 1;
            }
        }

        int result = 0;
        for (int i=0;i<32;i++){
            result = result << 1;
            result += bitSum[i]%3;
        }

        return result;
    }

//    public static void main(String[] args){
//        System.out.println(new Main().findNumberAppearingOnce(new int[]{28,2,2,2}));
//    }

    //输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
    // 如果有多对数字的和等于S，输出两个数的乘积最小的。
    public ArrayList<Integer> FindNumbersWithSum(int[] array,int sum){
        ArrayList<Integer> numbers = new ArrayList<>();

        if(array == null || array.length == 0 || sum == 0){
            return numbers;
        }

        int leftIndex = 0;
        int rightindex = array.length - 1;

        while(leftIndex < rightindex){
            if (array[leftIndex] + array[rightindex] == sum){
                numbers.add(array[leftIndex]);
                numbers.add(array[rightindex]);

                break;
            }else if(array[leftIndex] + array[rightindex] > sum){
                rightindex --;
            }else {
                leftIndex ++;
            }
        }

        return numbers;
    }


    //输入一个正数s,打印出所有和为s的连续正数序列（至少含有两个数）。
    //例如，输入15，打印出三个连续序列1～5，4～7，7～8

    private ArrayList<ArrayList<Integer>> sequenceList = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum){
        if(sum < 3) return sequenceList;

        int small = 1;
        int big = 2;
        int middle = (sum+1)/2;
        int curSum = small + big;

        while(small < middle){
            if(curSum == sum){
                addSequence(small,big);
            }

            while(small < middle && curSum > sum){
                curSum -= small;
                small++;

                if(curSum == sum){
                    addSequence(small,big);
                }
            }

            big++;
            //只在上一个序列的基础上相加，提高效率
            curSum += big;
        }

        return sequenceList;
    }

    public void addSequence(int small,int big){
        ArrayList<Integer> sequence = new ArrayList<>();
        for (int i=small;i<=big;i++){
            sequence.add(i);
        }

        sequenceList.add(sequence);
    }

    //翻转单词顺序。翻转句子中单词顺序，但单词内字符顺序不变
    //输入“I am a student.” 输出“student. a am I”

    //先翻转整个句子，再翻转句子中每个单词
    public String ReverseSentence(String str) {
        if(str == null || str.length() == 0){
            return str;
        }

        char[] arr = str.toCharArray();
        reverse(arr,0,arr.length-1);

        int start,end;
        start = end = 0;
        while(start < arr.length-1){
            if(arr[start] == ' '){
                start++;
                end++;
            }else if(arr[end]==' '){
                reverse(arr,start,end-1);
                start = (end++);
            }else if(end == arr.length-1){
                reverse(arr,start,end);
                start = (end++);
            }else {
                end++;
            }
        }

        String reverStr = "";
        for (char ch : arr){
            reverStr += ch;
        }

        return reverStr;
    }

    public char[] reverse(char[] arr,int start,int end){

        while (start < end){
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }

        return arr;
    }

//    public static void main(String[] args){
//        System.out.println(new Main().ReverseSentence("Wonderful"));
//    }

    //还可以使用栈来实现
    public String ReverseSentence2(String str) {
        if(str == null || str.length() == 0) return str;

        str.trim();

        String str2 = str.replaceAll(" ","");
        int spaceCount = str.length() - str2.length();

        if(spaceCount == 0) return str;

        Stack<String> sequence = new Stack<>();

        StringBuffer temp = new StringBuffer(str);
        while (temp.indexOf(" ") != -1){
            int index = temp.indexOf(" ");
            sequence.push(temp.substring(0,index));
            temp.delete(0,index+1);
        }
        sequence.push(temp.toString());

        String reverseSen = "";
        while (!sequence.empty()){
            reverseSen = reverseSen + sequence.pop()+" ";
        }

        return reverseSen.trim();
    }

//    public static void main(String[] args){
//        System.out.println(new Main().ReverseSentence2("    a"));
//    }

    //左旋转字符串
    //字符的左旋操作是把字符串前面若干个字符移到字符串的尾部
    //请定义一个函数实现字符串的左旋功能
    //输入：“abcdefg”,2 输出：“cdefgab”

    //创建一个字符数组就可以了，空间复杂度过高
    public String LeftRotateString(String str,int n){
        if(str.length() <= n) return str;

        char[] leftArr = str.substring(0,n).toCharArray();
        char[] rightArr = str.substring(n,str.length()).toCharArray();

        leftArr = reverse(leftArr,0,leftArr.length-1);
        rightArr = reverse(rightArr,0,rightArr.length-1);

        char[] arr = new char[str.length()];
        for(int i=0;i<leftArr.length;i++){
            arr[i] = leftArr[i];
        }
        for(int j=0;j<rightArr.length;j++){
            arr[j+leftArr.length] = rightArr[j];
        }

        arr = reverse(arr,0,arr.length-1);
        String leftRever = "";
        for (int k=0;k<arr.length;k++){
            leftRever = leftRever + arr[k];
        }
        return leftRever;
    }

//    public static void main(String[] args){
//        System.out.println(new Main().LeftRotateString("abcdefg",2));
//    }

    public String LeftRotateString2(String str,int n){
        if(str.length() <= n) return str;

        char[] arr = str.toCharArray();

        reverse(arr,0,n-1);
        reverse(arr,n,arr.length-1);

        reverse(arr,0,arr.length-1);

        String leftRever = String.valueOf(arr);

        return leftRever;
    }

//    public static void main(String[] args){
//        System.out.println(new Main().LeftRotateString2("abcdefg",2));
//    }

    //滑动窗口的最大值
    //给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
    // 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
    // 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}

    //维持一个两端开口的队列，保存可能的最大值的下标
    public ArrayList<Integer> maxInWindows(int[] num ,int size){
        LinkedList<Integer> index = new LinkedList<>();
        ArrayList<Integer> ret = new ArrayList<>();

        if(num.length < size || size<1) return ret;

        for(int i=0;i<size;i++){
            while(!index.isEmpty() && num[i] >= num[index.getLast()]){
                index.removeLast();
            }
            index.addLast(i);
        }

        for(int i=size-1;i<num.length;i++){
            while(!index.isEmpty() && num[i] >= num[index.getLast()]){
                index.removeLast();
            }
            index.addLast(i);

            if(i-index.getFirst()+1>size){
                index.removeFirst();
            }
            ret.add(num[index.getFirst()]);
        }

        return ret;
    }

    //从扑克牌中抽出5张牌，判断是不是一个顺子，即这五张牌是不是连续的。
    //A-1,J-11,Q-12,K-13,大王小王可以看成任意数字
    public boolean isContinuous(int[] numbers){
        if(numbers == null || numbers.length != 5) return false;

        Arrays.sort(numbers);

        int zeroCount = 0;
        int gapCount = 0;

        for (int i=0;i<5;i++){
            if(numbers[i] == 0){
                zeroCount++;
            }
        }

        int small = zeroCount;
        int big = small + 1;

        while(big < numbers.length){
            if (numbers[small] == numbers[big]) {
                return false;
            }

            gapCount += numbers[big]-numbers[small]-1;
            small = big;
            big++;

        }
        return (zeroCount >= gapCount)?true:false;
    }

//    public static void main(String[] args){
//
//        System.out.println(new Main().isContinuous(new int[]{1,3,4,5,0}));
//    }

    //0,1,...,n-1这n个数字排成一个圆圈，从数字0开始，每次从圆圈里删除第m个数字。
    //求最后一个数字
    public int LastRemaining_Solution(int n, int m) {
        LinkedList<Integer> kids = new LinkedList<>();

        for (int i=0;i<n;i++){
            kids.add(i);
        }

        int flag = 0;
        while (kids.size() > 1){
            flag = (flag+m-1)%kids.size();
            kids.remove(flag);
        }

        return (kids.size() == 1)?kids.get(0):-1;
    }

    public int LastRemaining_Solution2(int n,int m){
        if(n<1 || m<1) return -1;

        int[] cycle = new int[n];
        int count = n;
        int i = -1;
        int step = 0;

        while (count > 0){
            i++;
            if(i >= n) i=0;
            if(cycle[i] == -1) continue;

            step++;
            if(step == m){
                cycle[i] = -1;
                step = 0;
                count--;
            }
        }
        return i;
    }

    //求一组数的前后最大差值
    public int MaxDiff(int[] number){
        if(number == null || number.length == 0){
            return 0;
        }

        int min = number[0];
        int maxDif = number[1] - min;

        for (int i=2;i<number.length;i++){
            if(number[i] < min) min = number[i];

            int currentDiff = number[i] - min;
            if(currentDiff > maxDif) maxDif = currentDiff;
        }

        return maxDif;
    }

//    public static void main(String[] args){
//        System.out.println(new Main().MaxDiff(new int[]{9,11,8,5,7,12,16,14}));
//    }

    //给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
    // 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
    // 不能使用除法。

    public int[] multipiy(int[] A){
        int length = A.length;

        int[] B = new int[length];

        int[] C = new int[length];
        int[] D = new int[length];

        C[0] = 1;
        D[length-1] = 1;

        for(int i=1;i<length;i++){
            C[i] = A[i-1] * C[i-1];
            D[length-1-i] = A[length-i] * D[length-i];
        }

        for(int i=0;i<length;i++){
            B[i] = C[i] * D[i];
        }

        return B;
    }

//    public static void main(String[] args){
//        System.out.println(new Main().multipiy(new int[]{1,2,3,4,5}));
//    }

    //请实现一个函数用来匹配包括'.'和'*'的正则表达式。
    // 模式中的字符'.'表示任意一个字符，
    // 而'*'表示它前面的字符可以出现任意次（包含0次）。
    // 在本题中，匹配是指字符串的所有字符匹配整个模式。
    // 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配

    public boolean match(char[] str,char[] pattern){
        if(str == null || pattern == null){
            return false;
        }

        int strIndex = 0;
        int patternIndex = 0;

        return matchCore2(str,strIndex,pattern,patternIndex);
    }

    public boolean matchCore2(char[] str, int strIndex, char[] pattern, int patternIndex) {
        //有效性检验：str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        //pattern先到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                return matchCore2(str, strIndex, pattern, patternIndex + 2)//模式后移2，视为x*匹配0个字符
                        || matchCore2(str, strIndex + 1, pattern, patternIndex + 2)//视为模式匹配1个字符
                        || matchCore2(str, strIndex + 1, pattern, patternIndex);//*匹配1个，再匹配str中的下一个
            } else {
                return matchCore2(str, strIndex, pattern, patternIndex + 2);
            }
        }
        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchCore2(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }

    //实现一个函数用来找出字符流中第一个只出现一次的字符。
    // 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
    // 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。

    int[] count = new int[256];
    int charIndex = 1;

    public void Insert(char ch){
        if(count[ch] == 0){
            count[ch] = charIndex++;
        }else {
            count[ch] = -1;
        }
    }
    public char FirstAppearingOnce(){
        int temp = Integer.MAX_VALUE;
        char ch = '#';

        for (int i=0;i<256;i++){
            if(count[i]!=-1&&count[i]!=0&&count[i]<temp){
                temp = count[i];
                ch=(char) i;
            }
        }
        return ch;
    }

    //给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。

    public ListNode entryNodeOfLoop(ListNode pHead){
        if(pHead == null){
            return null;
        }

        HashSet<ListNode> nodeSet = new HashSet<>();
        ListNode pNode = pHead;
        while(pNode != null){
            if(!nodeSet.add(pNode)){
                return pNode;
            }
            pNode = pNode.next;
        }

        return null;
    }

    public ListNode entryNodeOfLoop2(ListNode pHead){
        ListNode fast = pHead;
        ListNode slow = pHead;
        while(fast.next != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if(slow == fast){
                ListNode p = pHead;
                while (p != slow){
                    p = p.next;
                    slow = slow.next;
                }
                return p;
            }
        }
        return null;
    }

    //在一个排序的链表中，存在重复的结点，
    // 请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
    // 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5

    public ListNode deleteDuplication(ListNode pHead){
        ListNode first = new ListNode(-1);
        first.next = pHead;

        ListNode p = pHead;
        ListNode last = first;

        while(p != null && p.next != null){
            if(p.val == p.next.val){
                int val = p.val;
                while(p != null && p.val == val){
                    p = p.next;
                }
                last.next = p;
            }else {
                last = p;
                p = p.next;
            }
        }
        return first.next;
    }

//    public static void main(String[] args){
//        ListNode pHead = new ListNode(1);
//        ListNode p2 = new ListNode(2);
//        ListNode p3 = new ListNode(3);
//        ListNode p4 = new ListNode(4);
//        ListNode p5 = new ListNode(5);
//
//        pHead.next = p2;
//        p2.next = p3;
//        p3.next = p4;
//        p4.next = p5;
//
//        System.out.println(new Main().deleteDuplication(pHead));
//    }

    //给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
    // 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

    public TreeLinkNode GetNext(TreeLinkNode pNode){
        if(pNode == null) return null;

        if(pNode.right != null){
            pNode = pNode.right;
            while (pNode.left != null){
                pNode = pNode.left;
            }
            return pNode;
        }
        while(pNode.next != null){
            if(pNode.next.right == pNode){
                pNode = pNode.next;
            }else if(pNode.next.left == pNode){
                return pNode.next;
            }
        }
        return null;
    }

    //请实现一个函数，用来判断一颗二叉树是不是对称的。
    // 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。

    public boolean isSymmetrical(TreeNode pRoot){
        if(pRoot == null) return true;

        return isSymmetrical(pRoot.left,pRoot.right);
    }

    public boolean isSymmetrical(TreeNode left,TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;

        return (left.val == right.val)
                && isSymmetrical(left.left,right.right)
                && isSymmetrical(left.right,right.left);
    }

    //DFS,stack保存节点
    public boolean isSymmetrical2(TreeNode pRoot){
        if(pRoot == null) return true;

        Stack<TreeNode> s = new Stack<>();

        s.push(pRoot.left);
        s.push(pRoot.right);

        while (!s.isEmpty()){
            TreeNode right = s.pop();
            TreeNode left = s.pop();

            if(right == null && left == null) continue;
            if(right == null || left == null) return false;
            if(right.val != left.val) return false;

            s.push(left.left);
            s.push(right.right);
            s.push(left.right);
            s.push(right.left);
        }

        return true;
    }

    //请实现一个函数按照之字形打印二叉树，
    // 即第一行按照从左到右的顺序打印，
    // 第二层按照从右至左的顺序打印，
    // 第三行按照从左到右的顺序打印，其他行以此类推。

    public ArrayList<ArrayList<Integer>> print(TreeNode pRoot){
        ArrayList<ArrayList<Integer>> printList = new ArrayList<>();
        if(pRoot == null) return printList;

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        int levels = 0;//0代表偶数层，1代表奇数层
        ArrayList<Integer> levelList = new ArrayList<>();

        stack2.push(pRoot);
        while (!stack1.isEmpty() || !stack2.isEmpty()){

            if(levels == 0){
                TreeNode node = stack2.pop();
                levelList.add(node.val);

                if(node.left != null){
                    stack1.push(node.left);
                }
                if(node.right != null){
                    stack1.push(node.right);
                }
            }else if(levels == 1){
                TreeNode node = stack1.pop();
                levelList.add(node.val);

                if(node.right != null){
                    stack2.add(node.right);
                }
                if(node.left != null){
                    stack2.add(node.left);
                }
            }

            if(levels==0&&stack2.isEmpty()){
                levels = 1;
                printList.add(levelList);
                //不能使用clear，否则printList中的对象也会被清空，
                // 而new相当于创建了一个新的对象，原对象不会被清空
                levelList = new ArrayList<Integer>();
                continue;
            }
            if(levels==1&&stack1.isEmpty()){
                levels = 0;
                printList.add(levelList);
                levelList = new ArrayList<Integer>();
                continue;
            }
        }
        return printList;
    }

    //从上到下按层打印二叉树，同一层结点从左至右输出。
    // 每一层输出一行。

    public ArrayList<ArrayList<Integer>> printTree(TreeNode pRoot){
        ArrayList<ArrayList<Integer>> printList = new ArrayList<>();
        if(pRoot == null) return printList;

        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        ArrayList<Integer> levelsList = new ArrayList<>();
        int levelNum = 1;
        int printNum = 0;

        nodeQueue.add(pRoot);
        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.pop();
            levelNum--;
            levelsList.add(node.val);

            if(node.left != null){
                nodeQueue.addLast(node.left);
                printNum++;
            }
            if(node.right != null){
                nodeQueue.addLast(node.right);
                printNum++;
            }

            if(levelNum == 0){
                printList.add(levelsList);
                levelsList = new ArrayList<Integer>();
                levelNum = printNum;
                printNum = 0;
            }
        }
        return printList;
    }

    //请实现两个函数，分别用来序列化和反序列化二叉树

    //从前序遍历和中序遍历可以重建二叉树
    //要求：1/二叉树中不能有数值重复的节点 2/两个序列全部读出后才可以进行反序列化

    //使用前序遍历，用$来代替空节点
    public String Serialize(TreeNode root){
        StringBuffer sb = new StringBuffer();

        if(root == null){
            sb.append("$,");
            return sb.toString();
        }

        sb.append(root.val+",");
        sb.append(Serialize(root.left));
        sb.append(Serialize(root.right));

        return sb.toString();
    }
    public int sIndex = -1;
    public TreeNode ReSerialize(String str){
        sIndex++;

        int len = str.length();
        if(sIndex >= len){
            return null;
        }

        String[] strr = str.split(",");
        TreeNode node = null;
        if(!strr[sIndex].equals("$")){
            node = new TreeNode(Integer.valueOf(strr[sIndex]));
            node.left = ReSerialize(str);
            node.right = ReSerialize(str);
        }

        return node;
    }

//    public static void main(String[] args){
//        TreeNode root = new TreeNode(8);
//        TreeNode root1 = new TreeNode(10);
//        TreeNode root2 = new TreeNode(6);
//        TreeNode root3 = new TreeNode(5);
//        TreeNode root4 = new TreeNode(7);
//        TreeNode root5 = new TreeNode(9);
//        TreeNode root6 = new TreeNode(11);
//
//        root.left = root1;
//        root.right = root2;
//        root1.left = root3;
//        root1.right = root4;
//        root2.left = root5;
//        root2.right = root6;
//
//        new Main().printTree(root);
//    }

    //如何得到一个数据流中的中位数？
    // 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
    // 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
    // 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。

    int numCount;
    //默认是小顶堆
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    //11是初始化容量大小，也可以不指定，默认是11
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>
            (11,new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public void Insert(Integer num) {
        numCount++;
        //偶数
        if((numCount&1) == 0){
            if(!maxHeap.isEmpty() && num<maxHeap.peek()){
                maxHeap.add(num);
                num = maxHeap.poll();
            }
            minHeap.add(num);
        }else {
            if(!minHeap.isEmpty() && num>minHeap.peek()){
                minHeap.add(num);
                num = minHeap.poll();
            }
            maxHeap.add(num);
        }
    }

    public Double GetMedian() {
        if(numCount == 0){
            throw new RuntimeException("no number input");
        }

        double result;
        if((numCount&1) == 0){
            result = (maxHeap.peek()+minHeap.peek())/2.0;
        }else {
            result = maxHeap.peek();
        }
        return result;
    }

    //回溯法

    //矩阵中的路径
    //请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
    // 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，
    // 向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
    // 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，
    // 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，
    // 路径不能再次进入该格子。

    public boolean hasPath(char[] matrix,int rows,int cols,char[] str){
        if(matrix == null || rows<1 || cols<1 || str == null){
            return false;
        }

        int pathLength = 0;

        boolean[] hasVisited = new boolean[rows*cols];
        for (int i=0;i<rows*cols-1;i++){
            hasVisited[i] = false;
        }

        for (int row=0;row<rows;row++){
            for (int col=0;col<cols;col++){
                if(hasPathCore(matrix,rows,cols,row,col,str,pathLength,hasVisited)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasPathCore(char[] matrix,int rows,int cols,int row,int col,
                               char[] str,int pathLenfth,boolean[] hasVisited){
        if(str.length == pathLenfth){
            return true;
        }

        boolean result = false;
        if(row>=0 && row<rows && col>=0 && col<cols &&
                matrix[row*cols+col] == str[pathLenfth]&&
                !hasVisited[row*cols+col]){
            pathLenfth++;

            hasVisited[row*cols+col] = true;

            result = hasPathCore(matrix,rows,cols,row,col-1,
                    str,pathLenfth,hasVisited)||
                    hasPathCore(matrix,rows,cols,row-1,col,
                            str,pathLenfth,hasVisited)||
                    hasPathCore(matrix,rows,cols,row+1,col,
                            str,pathLenfth,hasVisited)||
                    hasPathCore(matrix,rows,cols,row,col+1,
                            str,pathLenfth,hasVisited);
            if(!result){
                --pathLenfth;
                hasVisited[row*cols+col] = false;
            }
        }
        return result;
    }

    //机器人的运动范围
    //地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
    // 每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
    // 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
    // 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？

    public int movingCount(int threshold, int rows, int cols){
        if(threshold<=0 || rows<=0 || cols<=0){
            return 0;
        }

        boolean[] visited = new boolean[rows*cols];
        for(int i=0;i<rows*cols;i++){
            visited[i] = false;
        }

        int count = movingCountCore(threshold,rows,cols,0,0,visited);

        return count;
    }
    public int movingCountCore(int threshold,int rows,int cols,
                               int row,int col,boolean[] visited){
        int count = 0;

        if(check(threshold,rows,cols,row,col,visited)){
            visited[row*cols+col] = true;

            count =1+ movingCountCore(threshold,rows,cols,row,col+1,visited)+
                    movingCountCore(threshold,rows,cols,row,col-1,visited)+
                    movingCountCore(threshold,rows,cols,row+1,col,visited)+
                    movingCountCore(threshold,rows,cols,row-1,col,visited);
        }
        return count;
    }
    public boolean check(int threshold,int rows,int cols,
                         int row,int col,boolean[] visited){
        if(row<rows&&col<cols&&row>=0&&col>=0&&
                !visited[row*cols+col]&&getDigitSum(row)+getDigitSum(col)<=threshold){
            return true;
        }
        return false;
    }
    public int getDigitSum(int num){
        int sum = 0;

        while(num>0){
            sum += num%10;
            num = num/10;
        }

        return sum;
    }

  }


