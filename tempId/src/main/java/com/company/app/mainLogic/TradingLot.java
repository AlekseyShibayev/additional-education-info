package com.company.app.mainLogic;

import java.util.Date;

public class TradingLot {

    private String name;
    private String location;
    private String guild;
    private String price;
    private String lastSeen;
    private Date createdDate;
    private boolean isReadyToPrint;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradingLot lot = (TradingLot) o;

        if (name != null ? !name.equals(lot.name) : lot.name != null) return false;
        if (location != null ? !location.equals(lot.location) : lot.location != null) return false;
        if (guild != null ? !guild.equals(lot.guild) : lot.guild != null) return false;
        return price != null ? price.equals(lot.price) : lot.price == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (guild != null ? guild.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + "\n" + location + "\n" + guild + "\n" + price + "\n" + lastSeen;
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

    public boolean isReadyToPrint() {
        return isReadyToPrint;
    }

    public void setReadyToPrint(boolean readyToPrint) {
        isReadyToPrint = readyToPrint;
    }
}
