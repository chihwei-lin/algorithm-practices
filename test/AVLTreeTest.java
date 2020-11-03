package test;

import collection.tree.AVLTree;

public class AVLTreeTest {
    public static void main(String[] args){
        AVLTree tree = new AVLTree();

        // 插入数值
        tree.root = tree.add(tree.root, 1);
        tree.root = tree.add(tree.root, 2);
        tree.root = tree.add(tree.root, 3);
        tree.root = tree.add(tree.root, 4);
        tree.root = tree.add(tree.root, 5);
        tree.root = tree.add(tree.root, 6);

        System.out.println(tree.isBalanced());

        // 印出 AVL 树
        tree.print();
    }
}
