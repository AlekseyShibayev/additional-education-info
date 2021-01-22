package MainLogic;

import java.util.Date;

public class TradingLot {

    private String name;
    private String location;
    private String guild;
    private String price;
    private String lastSeen;
    private Date createdDate;

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
        return name + " :\n " + location + " / " + guild + "\n" + price + " / " + lastSeen;
    }

    public TradingLot getEmptyTradingLot() {
        this.name = "empty";
        this.location = "empty";
        this.guild = "empty";
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }
}
