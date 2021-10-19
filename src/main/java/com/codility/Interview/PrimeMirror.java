


    /*
     * find prime numbers within 1 to N that are mirror to each other
     */
    public static List<Integer> solution(int N) {

        List<Integer> primes = findPrimes(N);
        Set<Integer> set = new LinkedHashSet<>();

        for (int i = 0; i < primes.size(); i++) {

            int prime = primes.get(i);
            
            if(set.contains(prime)){
                continue;
            }

            int mirror = hasMirror(prime, primes);

            if (mirror == 0) {
                continue;
            }

            if(prime == mirror){
                
                set.add(prime);
                continue;
            }

            set.add(prime);
            set.add(mirror);
        }

        return new ArrayList<>(set);
    }


    /*
     * find the mirror of a number
     * */
    private static int findMirror(int P) {

        // 154   451
        int R = 0;

        while (P != 0) {

            int D = P % 10;
            R = R * 10 + D;
            P /= 10;
        }

        return R;
    }


    private static int hasMirror(int P, List<Integer> B) {

        Integer[] A = B.toArray(new Integer[0]);

        int N = A.length;
        int R = findMirror(P);

        for (int i = N - 1; i >= 0; i--) {

            if (A[i] == R) {
                return R;
            }
        }

        return 0;
    }

    public static List<Integer> findPrimes(int N) {


        int[] F = new int[N + 1];
        List<Integer> result = new ArrayList<>();

        for (int i = 2; i <= N; i++) {

            if (F[i] == 0) {

                // the prime value need to be 2 digit for the mirror image
                // if (i < 10 || i == findMirror(i)) {
                //     continue;
                // }

                result.add(i);

                for (int k = i * i; k <= N; k += i) {

                    if (F[k] == 0) {
                        F[k] = 1;
                    }
                }
            }
        }

        return result;
    }
