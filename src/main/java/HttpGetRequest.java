import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class HttpGetRequest extends UserInterface{
    StringBuffer response = new StringBuffer();

    void httpGetRequest() throws Exception {
        LocalDate localDate = LocalDate.now();
        LocalDate yesterday = localDate.minusDays(2);

        String url = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";
        String date = DateTimeFormatter.ofPattern("dd.MM.yyy").format(yesterday);

        URL obj = new URL(url + date);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url + date);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
    }
}