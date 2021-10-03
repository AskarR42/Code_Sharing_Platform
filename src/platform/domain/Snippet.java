package platform.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Snippet")
public class Snippet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name ="lastReachDate")
    private String lastReachDate;

    @Column(name = "code")
    private String code;

    @Column(name = "UUID")
    private String UUID;

    @Column(name = "isTimeRestricted")
    private boolean timeRestricted;

    @Column(name = "time")
    private int time;

    @Column(name = "isViewsRestricted")
    private boolean viewsRestricted;

    @Column(name = "views")
    private int views;

    public Snippet() {};

    public Snippet(String date, String code, String UUID, boolean timeRestricted, int time, boolean viewsRestricted, int views) {
        this.date = date;
        this.lastReachDate = date;
        this.code = code;
        this.UUID = UUID;
        this.timeRestricted = timeRestricted;
        this.time = time;
        this.viewsRestricted = viewsRestricted;
        this.views = views;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getLastReachDate() {
        return lastReachDate;
    }

    public String getCode() {
        return code;
    }

    public String getUUID() {
        return UUID;
    }

    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    public int getTime() {
        return time;
    }

    public boolean isViewsRestricted() {
        return viewsRestricted;
    }

    public int getViews() {
        return views;
    }

    public void setLastReachDate(String lastReachDate) {
        this.lastReachDate = lastReachDate;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "Snippet{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", lastReachDate='" + lastReachDate + '\'' +
                ", code='" + code + '\'' +
                ", UUID='" + UUID + '\'' +
                ", timeRestricted=" + timeRestricted +
                ", time=" + time +
                ", viewsRestricted=" + viewsRestricted +
                ", views=" + views +
                '}';
    }
}
