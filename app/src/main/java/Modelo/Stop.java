package Modelo;

/**
 * Created by aburr on 16/11/2017.
 */

public class Stop {

    private String stop_id;
    private String stop_name;
    private String stop_lat;
    private String stop_long;
    private String location_type;
    private String parent_station;
    private String platform_code;

    public Stop(String stop_id, String stop_name, String stop_lat, String stop_long, String location_type, String parent_station, String platform_code) {
        this.stop_id = stop_id;
        this.stop_name = stop_name;
        this.stop_lat = stop_lat;
        this.stop_long = stop_long;
        this.location_type = location_type;
        this.parent_station = parent_station;
        this.platform_code = platform_code;
    }


    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public String getStop_lat() {
        return stop_lat;
    }

    public void setStop_lat(String stop_lat) {
        this.stop_lat = stop_lat;
    }

    public String getStop_long() {
        return stop_long;
    }

    public void setStop_long(String stop_long) {
        this.stop_long = stop_long;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public String getParent_station() {
        return parent_station;
    }

    public void setParent_station(String parent_station) {
        this.parent_station = parent_station;
    }

    public String getPlatform_code() {
        return platform_code;
    }

    public void setPlatform_code(String platform_code) {
        this.platform_code = platform_code;
    }

}
