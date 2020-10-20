import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePeriod implements Comparable<TimePeriod> {
    private long from;
    private long to;
    private final SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    /**
     * Time period within one day
     *
     * @param from
     * @param to
     */
    public TimePeriod(long from, long to) {
        this.from = from;
        this.to = to;
        // SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
        if (!dayFormat.format(new Date(from)).equals(dayFormat.format(new Date(to))))
            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day!");
    }

//    public TimePeriod(Date from, Date to) {
//        this.from = from.getTime();
//        this.to = to.getTime();
//        // SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
//        if (!dayFormat.format(from).equals(dayFormat.format(to)))
//            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day!");
//    }

//    public void appendTime(Date visitTime) {
//        //  SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
//        if (!dayFormat.format(new Date(from)).equals(dayFormat.format(visitTime)))
//            throw new IllegalArgumentException("Visit time must be within the same day as the current TimePeriod!");
//        long visitTimeTs = visitTime.getTime();
//        if (visitTimeTs < from) {
//            from = visitTimeTs;
//        }
//        if (visitTimeTs > to) {
//            to = visitTimeTs;
//        }
//    }

    public void appendTime(long visitTime) {
        if (!dayFormat.format(new Date(from)).equals(dayFormat.format(visitTime)))
            throw new IllegalArgumentException("Visit time must be within the same day as the current TimePeriod!");
        if (visitTime < from) {
            from = visitTime;
        }
        if (visitTime > to) {
            to = visitTime;
        }
    }

    public String toString() {
//        String from = dateFormat.format(this.from);
//        String to = timeFormat.format(this.to);
        String from = dateFormat.format(new Date(this.from));
        String to = timeFormat.format(new Date(this.to));
        return from + "-" + to;
    }

    @Override
    public int compareTo(TimePeriod period) {
        long delta = 86400000;
        long current = from;
        long compared = period.from;
        if (Math.abs(current - compared) < delta) {
            return 0;
        } else return -1;
//        return dayFormat.format(from).compareTo(dayFormat.format(period.from));
    }
}
