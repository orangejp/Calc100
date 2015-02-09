package samples.calc100;

import java.util.Stack;

/**
 * Created by orange on 15/02/07.
 */
public class Calc {

    Stack<VirtNumeric> stack = new Stack<>();

    public static void main(String[] args) {
        System.out.println(args[0]);
        VirtNumeric v = new Calc().calc(args[0]);
        System.out.println(v.toRealValue());
        System.out.println(v.eval10());
    }

    /**
     * RPNを計算。
     *
     * @param rpn
     */
    VirtNumeric calc(String rpn) {

        for (int i = 0; i < rpn.length(); i++) {
            char s = rpn.charAt(i);
            //演算子
            if (s == '+') {
                VirtNumeric v1 = stack.pop();
                VirtNumeric v2 = stack.pop();
                stack.push(VirtNumeric.virtAdd(v2, v1));
            } else if (s == '-') {
                VirtNumeric v1 = stack.pop();
                VirtNumeric v2 = stack.pop();
                stack.push(VirtNumeric.virtSub(v2, v1));
            } else if (s == '*') {
                VirtNumeric v1 = stack.pop();
                VirtNumeric v2 = stack.pop();
                stack.push(VirtNumeric.virtMulti(v2, v1));
            } else if (s == '/') {
                VirtNumeric v1 = stack.pop();
                VirtNumeric v2 = stack.pop();
                VirtNumeric res = VirtNumeric.virtDiv(v2, v1);
                if(res==null){
                    throw new RuntimeException();
                }
                stack.push(res);
            }
            //数値
            else {
                stack.push(new VirtNumeric(new Integer(Character.getNumericValue(s)), 1));
            }
        }
        VirtNumeric result = stack.pop();
        return result;
    }

    /**
     * 10か評価。
     * @param rpn
     * @return
     */
    public boolean eval10(String rpn) {
        return calc(rpn).eval10();
    }

    /**
     * 100か評価。
     * @param rpn
     * @return
     */
    public boolean eval100(String rpn) {
        return calc(rpn).eval100();
    }
}
