package Modelo;

/**
 * Created by jncv17 on 16/11/2017.
 */

public class CalendarDate {
    private Service[] service;
    private String date;

    public CalendarDate(Service[] service, String date, String exception_type) {
        this.service = service;
        this.date = date;
        this.exception_type = exception_type;
    }

    private String exception_type;

    public Service[] getService() {
        return service;
    }

    public void setService(Service[] service_id) {
        this.service = service_id;
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
