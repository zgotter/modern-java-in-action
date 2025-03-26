package modern_java_in_action.ch19;

import java.util.function.Function;
import java.util.function.Supplier;

public class PatternMatching {
    public static void main(String[] args) {
        Expr e = new BinOp("+", new Number(5), new Number(0));
        Expr match = simplify(e);
        System.out.println(match);
    }

    static class Expr {}

    static class Number extends Expr {
        int val;

        public Number(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "" + val;
        }
    }

    static class BinOp extends Expr {
        String opname;
        Expr left, right;

        public BinOp(String opname, Expr left, Expr right) {
            this.opname = opname;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "(" + left + " " + opname + " " + right + ")";
        }
    }

    static <T> T myIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
        return b ? truecase.get() : falsecase.get();
    }

    static interface TriFunction<S, T, U, R> {
        R apply(S s, T t, U u);
    }

    static <T> T patternMatchExpr(
            Expr e,
            TriFunction<String, Expr, Expr, T> binopcase,
            Function<Integer, T> numcase,
            Supplier<T> defaultcase
    ) {
        return
            (e instanceof BinOp) ? binopcase.apply(
                ((BinOp) e).opname,
                ((BinOp) e).left,
                ((BinOp) e).right
            ) :
            (e instanceof Number) ? numcase.apply(
                    ((Number) e).val
            ) :
            defaultcase.get();
    }

    static <T> T patternMatchExpr2(
            Expr e,
            TriFunction<String, Expr, Expr, T> binopcase,
            Function<Integer, T> numcase,
            Supplier<T> defaultcase
    ) {
        if (e instanceof BinOp) {
            return binopcase.apply(((BinOp) e).opname, ((BinOp) e).left, ((BinOp) e).right);
        } else if (e instanceof  Number) {
            return numcase.apply(((Number) e).val);
        } else {
            return defaultcase.get();
        }
    }

    static Expr simplify(Expr e) {
        // BinOp 표현식 처리
        TriFunction<String, Expr, Expr, Expr> binopcase = (opname, left, right) -> {
            if ("+".equals(opname)) { // 더하기 처리
                if (left instanceof Number && ((Number) left).val == 0) {
                    return right;
                }
                if (right instanceof Number && ((Number) right).val == 0) {
                    return left;
                }
            }
            if ("*".equals(opname)) { // 곱하기 처리
                if (left instanceof Number && ((Number) left).val == 1) {
                    return right;
                }
                if (right instanceof Number && ((Number) right).val == 1) {
                    return left;
                }
            }
            return new BinOp(opname, left, right);
        };

        // 숫자 처리
        Function<Integer, Expr> numcase = val -> new Number(val);

        // 수식을 인식할 수 없을 때 기본 처리
        Supplier<Expr> defaultcase = () -> new Number(0);

        // 패턴 매칭 적용
        return patternMatchExpr(e, binopcase, numcase, defaultcase);
    }
}
