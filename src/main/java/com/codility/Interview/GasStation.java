

/*Gas Station
Have the function GasStation(strArr) take strArr which will be an an array consisting of the
following elements: N which will be the number of gas stations in a circular route and each subsequent
element will be the string g:c where g is the amount of gas in gallons at that gas station and c will
be the amount of gallons of gas needed to get to the following gas station. For example strArr may
be: ["4","3:1","2:2","1:2","0:1"]. Your goal is to return the index of the starting gas station that
will allow you to travel around the whole route once, otherwise return the string impossible.
For the example above, there are 4 gas stations, and your program should return the string 1 because
starting at station 1 you receive 3 gallons of gas and spend 1 getting to the next station.
Then you have 2 gallons + 2 more at the next station and you spend 2 so you have 2 gallons when you get
to the 3rd station. You then have 3 but you spend 2 getting to the final station, and at the final
station you receive 0 gallons and you spend your final gallon getting to your starting point.
Starting at any other gas station would make getting around the route impossible, so the answer is 1.
If there are multiple gas stations that are possible to start at, return the smallest index (of the gas station).
N will be >= 2.
Hard challenges are worth 15 points and you are not timed for them.
Examples
Input: new String[] {"4","1:1","2:2","1:2","0:1"}
Output: "impossible"
Input: new String[] {"4","0:1","2:2","1:2","3:1"}
Output: "4"
*/


public class GasStation {


	public static String GasStation(String[] strArr) {

        int routeLength = strArr.length - 1;

        int[] gArray = new int[routeLength];
        int[] cArray = new int[routeLength];

        fillGasAndCosts(gArray, cArray, strArr);

        for (int i = 0; i < routeLength; i++) {

            String k = findStarting(gArray, cArray, i);

            if ("impossible".equals(k)) {
                continue;
            }

            return k;
        }

        return "impossible";
    }

    private static void fillGasAndCosts(int[] gas, int[] costs, String[] strArr) {

        int routeLength = strArr.length - 1;

        for (int i = 1; i <= routeLength; i++) {

            String[] gasAndCost = strArr[i].split(":");

            gas[i - 1] = Integer.parseInt(gasAndCost[0]);
            costs[i - 1] = Integer.parseInt(gasAndCost[1]);
        }
    }

    private static String findStarting(int[] gas, int[] costs, int start) {

        int routeLen = gas.length;
        int next = start < (routeLen - 1) ? start + 1 : 0;

        int current = start;
        int storage = 0;

        for (int i = 0; i < routeLen; i++) {

            storage += gas[current] - costs[current];

            if (storage < 0) {
                return "impossible";
            }

            //
            else if (i == (routeLen - 1)) {
                return String.valueOf(start + 1);
            }

            next = next < (routeLen - 1) ? next + 1 : 0;
            current = current < (routeLen - 1) ? current + 1 : 0;
        }

        return "impossible";
    }



    public static void main(String[] args) {
    	
    	System.out.println("Hello");
    }
	
}