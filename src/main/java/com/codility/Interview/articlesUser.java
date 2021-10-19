





class articlesUser extends Parent implements Interface {
	


    /*
     * Complete the 'getUsernames' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts INTEGER threshold as parameter.
     *
     * URL for cut and paste
     * https://jsonmock.hackerrank.com/api/article_users?page=<pageNumber>
     */

    public static List<String> getUsernames(int threshold) {


        final String urlStr = "https://jsonmock.hackerrank.com/api/article_users?page=";
        JSONObject jsonObject1 = getResponseFromURL(urlStr + 1);


        int totalPageCount = ((Long) jsonObject1.get("total_pages")).intValue();

        List<String> us = new ArrayList<>();

        IntStream.range(1, totalPageCount + 1).forEach(pageNumber -> {

            List<String> tt = getUserNamesList(threshold, pageNumber);
            us.addAll(tt);
        });


        return us;
    }

    private static JSONObject getResponseFromURL(String urlStr) {

        try {

            URL url = new URL(urlStr);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int statusCode = con.getResponseCode();

            if (statusCode != 200) {
                return null;
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder content = new StringBuilder();

            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {

                content.append(inputLine);
            }

            String response = content.toString();

            bufferedReader.close();
            con.disconnect();


            if (response.isEmpty()) {
                return null;
            }

            JSONParser parser = new JSONParser();

            try {
                return (JSONObject) parser.parse(response);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<String> getUserNamesList(int threshold, int page) {

        List<String> userNames = new ArrayList<>();

        String currentURL = "https://jsonmock.hackerrank.com/api/article_users?page=" + page;

        JSONObject jsonObject = getResponseFromURL(currentURL);
        assert jsonObject != null;
        JSONArray jsonDataArray = (JSONArray) jsonObject.get("data");

        for (Object o : jsonDataArray) {

            JSONObject jsonDataObject = (JSONObject) o;

            String username = (String) jsonDataObject.get("username");
            int submissionCount = ((Long) jsonDataObject.get("submission_count")).intValue();

            if (submissionCount > threshold) {
                userNames.add(username);
            }
        }

        return userNames;
    }

    public static void main(String[] args) {
    	
    	System.out.println("Hellos");
    }
}