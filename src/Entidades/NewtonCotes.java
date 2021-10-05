package Entidades;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NewtonCotes {
    private double limiteInferior;
    private double limiteSuperior;
    private int cantidadTrapecios;
    private double porcentajeError;
    private String funcion;
    private final Derivadas derivada;

    public NewtonCotes() {
        this.limiteInferior = 0;
        this.limiteSuperior = 0;
        this.cantidadTrapecios = 0;
        this.porcentajeError = 0.0;
        this.funcion = "";
        this.derivada = new Derivadas();
    }
    
    // Metodos setter
    public void setCantidadTrapecios(int cantidadTrapecios) {
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

    public int getCantidadTrapecios() {
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
        // Definicion de variables
        double a = 0.0, b = 0.0;
        int n = 0;
        double segundaDerivadaResultado;
        
        a = this.getLimiteInferior();
        b = this.getLimiteSuperior();
        n = this.getCantidadTrapecios();
        
        segundaDerivadaResultado = (derivada.EvaluarFx(a,this.calcularSegundaDerivada()) > derivada.EvaluarFx(b,this.calcularSegundaDerivada()))
                                    ?derivada.EvaluarFx(a,this.calcularSegundaDerivada())
                                    :derivada.EvaluarFx(b,this.calcularSegundaDerivada());
        
        BigDecimal bd = new BigDecimal(segundaDerivadaResultado).setScale(2, RoundingMode.HALF_UP);
        double value = bd.doubleValue();
 
        bd = new BigDecimal(-1*((Math.pow((b-a), 3))/(12 * Math.pow(n, 2))) * value).setScale(4, RoundingMode.HALF_UP);
        value = bd.doubleValue();
        
        return value;
    }
    
    public String calcularSegundaDerivada(){
        this.derivada.setFuncionADerivar(this.funcion);
        this.derivada.derivar();
        String primeraDerivada = this.derivada.getFuncionDerivada();
        this.derivada.setFuncionADerivar(primeraDerivada);
        this.derivada.derivar();
        return this.derivada.getFuncionDerivada();
    }
    
    double calcularH(){
        return (this.limiteSuperior - this.limiteInferior)/this.cantidadTrapecios;
    }
    
    double[] calcularX(){
        double[] x = new double[this.cantidadTrapecios+1];
        
        x[0] = this.limiteInferior;
        
        for (int i = 1; i < x.length; i++) {
            x[i] = x[i-1] + this.calcularH();
        }
        
        return x; 
    }
   
    public double calcularIntegral(){
        double a = 0.0, b = 0.0 , integral = 0.0;
        int n = 0;

        a = this.getLimiteInferior();
        b = this.getLimiteSuperior();
        n = this.getCantidadTrapecios();
        
        integral = (b-a)*((a + (2 * this.sumatoriaFx()) + b)/(2*n));

        BigDecimal bd = new BigDecimal(integral).setScale(2, RoundingMode.HALF_UP);
        double value = bd.doubleValue();
        
        return integral;
    }
    
    double[] calcularFx(){
        double[] Fx = new double[this.cantidadTrapecios+1];
        double[] x = this.calcularX();
        
        for (int i = 0; i < Fx.length; i++) {
            Fx[i] = this.derivada.EvaluarFx(x[i], this.funcion.toLowerCase());
        }
        
        return Fx;
   }
    
    double sumatoriaFx(){
        double sumatoria = 0.0;
        double[] valoresFx = this.calcularFx();
        
        for (int i = 0; i < valoresFx.length; i++) {
            if (i == 0 || i == valoresFx.length - 1) {
                continue;
            }else{
                sumatoria += valoresFx[i];
            }
        }
        return sumatoria;
    }

}
