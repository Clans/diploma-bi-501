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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getLastsync() {
        return lastsync;
    }

    public void setLastsync(String lastsync) {
        this.lastsync = lastsync;
    }

    @Override
    public String toString() {
        return "File [lastsync=" + lastsync + ", name=" + name + ", size=" + size
                + ", modified=" + modified + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.size == null) ? (other.size != null) : !this.size.equals(other.size)) {
            return false;
        }
        if ((this.modified == null) ? (other.modified != null) : !this.modified.equals(other.modified)) {
            return false;
        }
        if ((this.lastsync == null) ? (other.lastsync != null) : !this.lastsync.equals(other.lastsync)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 31 * hash + (this.size != null ? this.size.hashCode() : 0);
        hash = 31 * hash + (this.modified != null ? this.modified.hashCode() : 0);
        hash = 31 * hash + (this.lastsync != null ? this.lastsync.hashCode() : 0);
        return hash;
    }    
}
