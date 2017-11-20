package Modelo;

/**
 * Created by jncv17 on 16/11/2017.
 */

public class Service {
    private Service service;

    public Service(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }


}
