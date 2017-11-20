package Modelo;

/**
 * Created by jncv17 on 16/11/2017.
 */

public class Trip {
    private String route_id;
    private String service_id;
    private String trip_id;
    private String trip_headsign;
    private String direction_id;
    private String block_id;
    private StopTime[] stopTimes;

    public Trip(String route_id, String service_id, String trip_id, String trip_headsign, String direction_id, String block_id, StopTime[] stopTimes) {
        this.route_id = route_id;
        this.service_id = service_id;
        this.trip_id = trip_id;
        this.trip_headsign = trip_headsign;
        this.direction_id = direction_id;
        this.block_id = block_id;
        this.stopTimes = stopTimes;
    }

    public StopTime[] getStopTimes() {
        return stopTimes;
    }

    public void setStopTimes(StopTime[] stopTimes) {
        this.stopTimes = stopTimes;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getTrip_headsign() {
        return trip_headsign;
    }

    public void setTrip_headsign(String trip_headsign) {
        this.trip_headsign = trip_headsign;
    }

    public String getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(String direction_id) {
        this.direction_id = direction_id;
    }

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }


}
