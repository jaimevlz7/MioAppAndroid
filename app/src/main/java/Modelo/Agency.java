package Modelo;

/**
 * Created by jncv17 on 16/11/2017.
 */

public class Agency {

    private String agency_name;
    private String agency_url;
    private String agency_timezone;
    private String agency_language;
    private String agency_phone;
    private String agency_email;
    private Route[] routes;

    public Agency(String agency_name, String agency_url, String agency_timezone, String agency_language, String agency_phone, String agency_email, Route[] routes) {
        this.agency_name = agency_name;
        this.agency_url = agency_url;
        this.agency_timezone = agency_timezone;
        this.agency_language = agency_language;
        this.agency_phone = agency_phone;
        this.agency_email = agency_email;
        this.routes = routes;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public String getAgency_url() {
        return agency_url;
    }

    public void setAgency_url(String agency_url) {
        this.agency_url = agency_url;
    }

    public String getAgency_timezone() {
        return agency_timezone;
    }

    public void setAgency_timezone(String agency_timezone) {
        this.agency_timezone = agency_timezone;
    }

    public String getAgency_language() {
        return agency_language;
    }

    public void setAgency_language(String agency_language) {
        this.agency_language = agency_language;
    }

    public String getAgency_phone() {
        return agency_phone;
    }

    public void setAgency_phone(String agency_phone) {
        this.agency_phone = agency_phone;
    }

    public String getAgency_email() {
        return agency_email;
    }

    public void setAgency_email(String agency_email) {
        this.agency_email = agency_email;
    }

    public Route[] getRoutes() {
        return routes;
    }

    public void setRoutes(Route[] routes) {
        this.routes = routes;
    }


}
