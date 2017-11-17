package Modelo;

/**
 * Created by aburr on 16/11/2017.
 */

public class CalendarDate {
    private String service_id;
    private String date;

    public CalendarDate(String service_id, String date, String exception_type) {
        this.service_id = service_id;
        this.date = date;
        this.exception_type = exception_type;
    }

    private String exception_type;

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getException_type() {
        return exception_type;
    }

    public void setException_type(String exception_type) {
        this.exception_type = exception_type;
    }
}
