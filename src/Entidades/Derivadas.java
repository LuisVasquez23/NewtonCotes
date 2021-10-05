package Entidades;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;

/**
 *
 * @author El código de Adrian
 */
public class Derivadas {

    //Variable que almacena las funciones a derivar

    private String funcion = "";
    // DJep es la clase encargada de la derivacion en su escencia
    DJep djep;
    Node nodoFuncion;
    Node nodoDerivada;

    public Derivadas() {
        //...
    }

    public void setFuncionADerivar(String funcion) {
        this.funcion = funcion;
    }

    public String getFuncionDerivada() {
        return this.funcion;
    }

    public void derivar() {

        try {

            this.djep = new DJep();
            // agrega funciones estandares cos(x), sin(x)
            this.djep.addStandardFunctions();

            // agrega constantes estandares, pi, e, etc
            this.djep.addStandardConstants();

            // por si existe algun numero complejo
            this.djep.addComplex();

            // permite variables no declarables
            this.djep.setAllowUndeclared(true);

            // permite asignaciones
            this.djep.setAllowAssignment(true);

            // regla de multiplicacion o para sustraccion y sumas
            this.djep.setImplicitMul(true);

            // regla de multiplicacion o para sustraccion y sumas
            this.djep.addStandardDiffRules();

            // coloca el nodo con una funcion preestablecida
            this.nodoFuncion = this.djep.parse(this.funcion);

            // deriva la funcion con respecto a x
            Node diff = this.djep.differentiate(nodoFuncion, "x");

            // Simplificamos la funcion con respecto a x
            this.nodoDerivada = this.djep.simplify(diff);

            // Convertimos el valor simplificado en un String
            this.funcion = this.djep.toString(this.nodoDerivada);

        } catch (Exception e) {
            this.funcion = "NaN";
            System.out.println("Error: " + e.getMessage());
        }

    }
    
    public double EvaluarFx(Double valor, String funcion){
        
        BigDecimal bd = new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP);
        double value = bd.doubleValue();
        
        JEP j = new JEP();
        j.addStandardConstants();
        j.addStandardFunctions();
        j.addVariable("x", value); //("variable", numero a evaluar) 
        j.parseExpression(funcion);
        
        bd = new BigDecimal(j.getValue()).setScale(2, RoundingMode.HALF_UP);
        value = bd.doubleValue();
        
        return value;
    }
}
