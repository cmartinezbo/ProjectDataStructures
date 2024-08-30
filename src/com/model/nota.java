

public class nota {

    private double valor;
    private String nombre;
    private double porcentaje;

    // Constructor
    public nota(double valor, String nombre, double porcentaje) {
        this.valor = valor;
        this.nombre = nombre;
        this.porcentaje = porcentaje; // Convertir el porcentaje formateado a double
    }

    // Métodos getter
    public double getValor() {
        return valor;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    // Métodos setter
    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método setter para establecer el porcentaje
    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    /*public String toString() {
        return "Valor: " + valor + ", Nombre: " + nombre + ", Porcentaje: " + porcentaje;
    }*/
    
    public String toString() {
        return porcentaje + ";" + nombre + ";" + valor + ";";
    }
    
    public String[] toArray() {
        String[] arreglo = {String.valueOf(getPorcentaje()), getNombre(), String.valueOf(getValor())};
        return arreglo;
    }
}
