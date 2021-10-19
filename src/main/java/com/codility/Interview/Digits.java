





public class Digits extends Parent implements Interface {
	



	public static int changeNumbers(int n) {

        char[] ch = String.valueOf(n).toCharArray();

        int N = ch.length;
        int result = 0;

        boolean isDec = true;

        for (int i = 0; i < N; i++, isDec = !isDec) {

            int digit = Integer.parseInt(ch[i] + "");


            int index = N - 1 - i;
            int updatedIndex = isDec ? --index : ++index;
            
            int power = Math.max(0, updatedIndex);

            result += digit * Math.pow(10, power);

            // result += digit * Math.pow(10, Math.max(0, isDec ? --index : ++index));
        }

        return result;
    }


    public static void main(String[] args) {

        int n = 12345;

        int res = changeNumbers(n);
        System.out.println(n + " \n" + res);
    }


}