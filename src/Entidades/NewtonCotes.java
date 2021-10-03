package Entidades;

public class NewtonCotes {
    
    private double limiteInferior;
    private double limiteSuperior;
    private double cantidadTrapecios;
    private double porcentajeError;
    private String funcion;
    private final Derivadas derivada;

    public NewtonCotes() {
        this.limiteInferior = 0.0;
        this.limiteSuperior = 0.0;
        this.cantidadTrapecios = 0.0;
        this.porcentajeError = 0.0;
        this.derivada = new Derivadas();
    }
    
    // Metodos setter
    public void setCantidadTrapecios(double cantidadTrapecios) {
        this.cantidadTrapecios = cantidadTrapecios;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public void setLimiteInferior(double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public void setLimiteSuperior(double limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public void setPorcentajeError(double porcentajeError) {
        this.porcentajeError = porcentajeError;
    }
    
    // Metodos getter

    public double getCantidadTrapecios() {
        return cantidadTrapecios;
    }

    public String getFuncion() {
        return funcion;
    }

    public double getLimiteInferior() {
        return limiteInferior;
    }

    public double getLimiteSuperior() {
        return limiteSuperior;
    }

    public double getPorcentajeError() {
        return porcentajeError;
    }
    
    public double calcularError(){
        return 0.0;
    }
    
    double calcularSegundaDerivada(){
        return 0.0;
    } 
}
