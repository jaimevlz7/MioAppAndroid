package Modelo;

/**
 * Created by aburr on 16/11/2017.
 */

public class StopTime {

    private String arrival_time;
    private String stop_id;
    private StopTime[] stop_sequence;
    private String stop_headsign;

    public StopTime(String trip_id, String arrival_time, String stop_id, StopTime[] stop_sequence, String stop_headsign) {
        this.trip_id = trip_id;
        this.arrival_time = arrival_time;
        this.stop_id = stop_id;
        this.stop_sequence = stop_sequence;
        this.stop_headsign = stop_headsign;
    }

    private String trip_id;

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public StopTime[] getStop_sequence() {
        return stop_sequence;
    }

    public void setStop_sequence(StopTime[] stop_sequence) {
        this.stop_sequence = stop_sequence;
    }

    public String getStop_headsign() {
        return stop_headsign;
    }

    public void setStop_headsign(String stop_headsign) {
        this.stop_headsign = stop_headsign;
    }

}
