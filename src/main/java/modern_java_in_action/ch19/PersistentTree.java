package modern_java_in_action.ch19;

public class PersistentTree {
    static class Tree {
        private String key;
        private int val;
        private Tree left, right;

        public Tree(String key, int val, Tree left, Tree right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class TreeProcessor {
        public static int lookup(String k, int defaultVal, Tree t) {
            if (t == null) {
                return defaultVal;
            }
            if (k.equals(t.key)) {
                return t.val;
            }
            return lookup(k, defaultVal, k.compareTo(t.key) < 0 ? t.left : t.right);
        }

        public static void update(String k, int newVal, Tree t) {
            if (t == null) {
                // 새로운 노드 추가해야 함
            } else if (k.equals(t.key)) {
                t.val = newVal;
            } else {
                update(k, newVal, k.compareTo(t.key) < 0 ? t.left : t.right);
            }
        }

        public static Tree update2(String k, int newVal, Tree t) {
            if (t == null) {
                t = new Tree(k, newVal, null, null);
            } else if (k.equals(t.key)) {
                t.val = newVal;
            } else if (k.compareTo(t.key) <  0) {
                t.left = update2(k, newVal, t.left);
            } else {
                t.right = update2(k, newVal, t.right);
            }
            return t;
        }

        public static Tree fupdate(String k, int newVal, Tree t) {
            return (t == null) ?
                    new Tree(k, newVal, null, null) :
                        k.equals(t.key) ?
                            new Tree(k, newVal, t.left, t.right) :
                        k.compareTo(t.key) < 0 ?
                            new Tree(t.key, t.val, fupdate(k, newVal, t.left), t.right) :
                            new Tree(t.key, t.val, t.left, fupdate(k, newVal, t.right));
        }

        public static Tree fupdate2(String k, int newVal, Tree t) {
            if (t == null) {
                return new Tree(k, newVal, null, null);
            } else if (k.equals(t.key)) {
                return new Tree(k, newVal, t.left, t.right);
            } else if (k.compareTo(t.key) < 0) {
                return new Tree(t.key, t.val, fupdate2(k, newVal, t.left), t.right);
            } else {
                return new Tree(t.key, t.val, t.left, fupdate2(k, newVal, t.right));
            }
        }
    }
}
