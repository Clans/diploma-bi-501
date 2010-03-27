package chstu.clans.mycms;

/**
 *
 * @author Clans
 */
public class Item {

    private String name;
    private String size;
    private String modified;
    private String lastsync;

    public String getDate() {
        return name;
    }

    public void setDate(String date) {
        this.name = date;
    }

    public String getMode() {
        return size;
    }

    public void setMode(String mode) {
        this.size = mode;
    }

    public String getUnit() {
        return modified;
    }

    public void setUnit(String unit) {
        this.modified = unit;
    }

    public String getCurrent() {
        return lastsync;
    }

    public void setCurrent(String current) {
        this.lastsync = current;
    }

    @Override
    public String toString() {
        return "File [lastsync=" + lastsync + ", name=" + name + ", size=" + size
                + ", modified=" + modified + "]";
    }
}
