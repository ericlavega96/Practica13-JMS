package entidades;

import java.util.Date;

public class Sensor {
    String sensor;
    double valor;
    Date fechaRecoleccion;

    public Sensor(String sensor, double valor, Date fechaRecoleccion) {
        this.sensor = sensor;
        this.valor = valor;
        this.fechaRecoleccion = fechaRecoleccion;
    }

    public Sensor() {
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getFechaRecoleccion() {
        return fechaRecoleccion;
    }

    public void setFechaRecoleccion(Date fechaRecoleccion) {
        this.fechaRecoleccion = fechaRecoleccion;
    }
}
