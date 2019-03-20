import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GetDate {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalDate yesterday = localDate.minusDays(1);

        String link = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";
        String date = DateTimeFormatter.ofPattern("dd.MM.yyy").format(yesterday);

        System.out.println(link + date);
    }
}
