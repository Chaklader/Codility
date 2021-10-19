


class DistinctCountries{

	
    public static class Pair<A, B> {

        private A first;
        private B second;

        public Pair(A first, B second) {
            super();
            this.first = first;
            this.second = second;
        }

        public int hashCode() {
            int hashFirst = first != null ? first.hashCode() : 0;
            int hashSecond = second != null ? second.hashCode() : 0;

            return (hashFirst + hashSecond) * hashSecond + hashFirst;
        }

        public boolean equals(Object other) {
            if (other instanceof Pair) {
                Pair otherPair = (Pair) other;
                return
                        ((  this.first == otherPair.first ||
                                ( this.first != null && otherPair.first != null &&
                                        this.first.equals(otherPair.first))) &&
                                (  this.second == otherPair.second ||
                                        ( this.second != null && otherPair.second != null &&
                                                this.second.equals(otherPair.second))) );
            }

            return false;
        }

        public String toString()
        {
            return "(" + first + ", " + second + ")";
        }

        public A getFirst() {
            return first;
        }

        public void setFirst(A first) {
            this.first = first;
        }

        public B getSecond() {
            return second;
        }

        public void setSecond(B second) {
            this.second = second;
        }
    }

    public static int solution(int[][] A) {

        int rows = A.length;

        if (rows == 0)
            return -1;

        int cols = A[0].length;

        boolean[][] isVisited = new boolean[rows][cols];

        List<ArrayList<Pair<Integer, Integer>>> totalNumberOfCountries = new ArrayList<>();

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (!isVisited[i][j]) {

                    int startOfRow = i;
                    int startOfColumns = j;

                    ArrayList<Pair<Integer, Integer>> isCountry = new ArrayList<>();
                    isValidCountry(A[i][j], A, i, j, startOfRow, startOfColumns, isVisited, isCountry);

                    totalNumberOfCountries.add(isCountry);
                }
            }
        }

        return totalNumberOfCountries.size();
    }

    public static void isValidCountry(int value, int[][] B, int row, int col, int x, int y, boolean[][] visited, ArrayList<Pair<Integer, Integer>> isCountry) {

        int H = B.length;
        int L = B[0].length;

        if (row < 0 || col < 0 || row >= H || col >= L || visited[row][col] || B[row][col] != value)
            return;

        isCountry.add(new Pair<>(row - x, col - y));

        visited[row][col] = true;

        isValidCountry(value, B, row + 1, col, x, y, visited, isCountry); // go right
        isValidCountry(value, B, row - 1, col, x, y, visited, isCountry); //go left
        isValidCountry(value, B, row, col + 1, x, y, visited, isCountry); //go down
        isValidCountry(value, B, row, col - 1, x, y, visited, isCountry); // go up
    }


    public static void main(String[] args) {

        int[][] A = new int[7][3];

        A[0][0] = 5;
        A[0][1] = 4;
        A[0][2] = 4;
        A[1][0] = 4;
        A[1][1] = 3;
        A[1][2] = 4;
        A[2][0] = 3;
        A[2][1] = 2;
        A[2][2] = 4;
        A[3][0] = 2;
        A[3][1] = 2;
        A[3][2] = 2;
        A[4][0] = 3;
        A[4][1] = 3;
        A[4][2] = 4;
        A[5][0] = 1;
        A[5][1] = 4;
        A[5][2] = 4;
        A[6][0] = 4;
        A[6][1] = 1;
        A[6][2] = 1;

//        int[][] A = {
//
//                {1, 1, 0, 1, 1},
//                {1, 0, 1, 0, 0},
//                {0, 0, 1, 1, 0},
//                {1, 0, 0, 0, 0},
//                {1, 1, 0, 1, 1}};

        System.out.println(solution(A));
    }
	
}