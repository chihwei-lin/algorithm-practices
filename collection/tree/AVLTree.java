package collection.tree;

public class AVLTree {
    class TreeNode{
        TreeNode parent;
        TreeNode left;
        TreeNode right;
        int height;
        int val;
        int balanceFactor;

        TreeNode(){ }

        public TreeNode(TreeNode parent, int val, int height) {
            this.parent = parent;
            this.val = val;
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public int getBalanceFactor() {
            return balanceFactor;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "parent=" + parent +
                    ", left=" + left +
                    ", right=" + right +
                    ", height=" + height +
                    ", val=" + val +
                    ", balanceFactor=" + balanceFactor +
                    '}';
        }
    }

    public TreeNode root;
    int size = 0;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean contains(int val){
        return contains(root, val);
    }

    // 利用递归找出该值是否存在在树中
    public boolean contains(TreeNode node, int val){
        if(node == null)    return false;

        // node 不为空
        if(node.val == val) return true;
        if(node.val > val)  return contains(node.left, val);
        if(node.val < val)  return contains(node.right, val);

        return false;
    }

    public TreeNode add(TreeNode node, int val){
        if(node == null){
            return new TreeNode(null, val, 1);
        }

        // 找到插入位置
        if(node.val > val)
            node.left = add(node.left, val);
        else if(node.val < val)
            node.right = add(node.right, val);
        else
            return node;

        // 更新树高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 检查树高度是否正常
        int balanceFactor = getBalanceFactor(node);

        // 不正常则做旋转（4 种情况）
        // 1. LL
        if(balanceFactor > 1 && val < node.left.val)
            return rightRotate(node);

        // 2. LR
        if(balanceFactor > 1 && val > node.left.val){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 3. RR
        if(balanceFactor < -1 && val > node.right.val)
            return leftRotate(node);

        // 4. RL
        if(balanceFactor < -1 && val < node.right.val){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // 返回没变的节点
        return node;
    }

    private TreeNode leftRotate(TreeNode x){
        TreeNode y = x.right;           // x 的右子节点
        TreeNode T2 = y.left;           // x 的右子节点的左子节点

        // 旋转
        y.left = x;         // y 的左子节点指向 x
        x.right = T2;

        //  x 和 y 的树高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // 返回新的节点
        return y;
    }

    private TreeNode rightRotate(TreeNode node){
        TreeNode leftChild = node.left;
        TreeNode rightGrandson = leftChild.right;

        // 旋转
        leftChild.right = node;
        node.left = rightGrandson;

        // 更新节点的树高度
        leftChild.height = 1 + Math.max(height(leftChild.left), height(leftChild.right));
        rightGrandson.height = 1 + Math.max(height(rightGrandson.left), height(rightGrandson.right));

        return node;
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(TreeNode node){
        if(node == null)       return true;

        int balanceFactor = getBalanceFactor(node);

        if(Math.abs(balanceFactor) > 1)
            return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }

    private int height(TreeNode node){
        if(node == null)
            return 0;
        return node.height;
    }

    private int getBalanceFactor(TreeNode node){
        if(node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    public void print(){
        if(root != null){
            print(root);
            System.out.println(" ");
            inorder(root);
        }
    }

    // 中序其实每个节点都是一个中间节点
    private void print(TreeNode node){
        if(node == null)    return;
        print(node.left);
        System.out.println("Value: " + node.toString() );
        print(node.right);
    }

    private void inorder(TreeNode node){
        if(node == null)    return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }
}
