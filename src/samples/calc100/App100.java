package samples.calc100;

/**
 * Created by orange on 15/02/08.
 */
public class App100 {

    private static final int SIZE = 20;

    private static boolean[] isUsed = new boolean[SIZE];
    private static StringBuilder rpnString;
    private static StringBuilder numberString = null;
    private static int counter = 0;

    public static void main(String[] args) {
        try {
            checkArgs(args);
        }catch (Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
        App100.numberString = new StringBuilder(args[0]);
        createRpn(0, 0);
        if (counter == 0) {
            //解がない場合。
            System.out.println("no answer");
        }
    }

    /**
     * 引数チェック
     * @param args
     * @throws Exception
     */
    private static void checkArgs(String[] args) throws Exception {
        String[] msg = {"第1引数は整数(0-9)の20桁を指定してください"};
        try {
            if (args.length !=1) {
                throw new Exception(msg[0]);
            }
            for (int i=0;i<args[0].length();i++) {
                if (!Character.isDigit(args[0].charAt(i))){
                    throw new Exception(msg[0]);
                }
            }
        } catch (Exception e) {
            throw new Exception(msg[0]);
        }

    }


    private static boolean createRpn(int digit, int ope) {

        //初期化
        if (digit == 0 && ope == 0) {
            rpnString = new StringBuilder();
            for (int i = 0; i < SIZE; i++) {
                isUsed[i] = false;
            }
        }

        //
        if (digit + ope == (2*SIZE - 1)) {
            try {
                if (new Calc().eval100((rpnString.toString()))) {
                    counter++;
                    System.out.println("OK - " + rpnString);
                }
            } catch (RuntimeException e) {
            }
            return false;
        } else {
            if (digit - ope >= 2) {
                int n = rpnString.length();

                //加算
                rpnString.append('+');
                if (createRpn(digit, ope + 1)) {
                    return true;
                }
                rpnString.deleteCharAt(n);

                //引き算
                rpnString.append('-');
                if (createRpn(digit, ope + 1)) {
                    return true;
                }
                rpnString.deleteCharAt(n);

                //乗算
                rpnString.append('*');
                if (createRpn(digit, ope + 1)) {
                    return true;
                }
                rpnString.deleteCharAt(n);

                //除算
                rpnString.append('/');
                if (createRpn(digit, ope + 1)) {
                    return true;
                }
                rpnString.deleteCharAt(n);
            }
            if (digit <= SIZE - 1) {
                for (int i = 0; i < SIZE; i++) {
                    if (!isUsed[i]) {
                        isUsed[i] = true;
                        rpnString.append(numberString.charAt(i));
                        if (createRpn(digit + 1, ope)) {
                            isUsed[i] = false;
                            return true;
                        }
                        rpnString.deleteCharAt(rpnString.length() - 1);
                        isUsed[i] = false;
                    }
                }
            }
        }
        return false;
    }
}
