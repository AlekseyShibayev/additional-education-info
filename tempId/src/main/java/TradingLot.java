import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class TradingLot {

    private String name;
    private String location;
    private String guild;
    private Date createdDate;

    TradingLot fillAndGet(List<String> strings) {
        TradingLot tradingLot = new TradingLot();
        this. name = splitByDelimiterAndGetSecond(strings, "\"item-quality-fine\">", 0);
        this. location = splitByDelimiterAndGetSecond(strings, "<div>", 5);
        this. guild = splitByDelimiterAndGetSecond(strings, "<div>", 6);
        this. createdDate = new Date();
        return tradingLot;
    }

    private String splitByDelimiterAndGetSecond(List<String> strings, String delimiter, int position) {
        String string = strings.get(position);
        String[] split = string.split(delimiter);
        return split[1].trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradingLot that = (TradingLot) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        return guild != null ? guild.equals(that.guild) : that.guild == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (guild != null ? guild.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(createdDate);
        return createdDate.toString() + " / " + name + " / " + location + " / " + guild;
    }
}
