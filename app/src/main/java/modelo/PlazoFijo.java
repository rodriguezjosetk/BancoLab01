package modelo;

import android.util.Log;

import java.util.Arrays;

public class PlazoFijo {

    private Integer dias;
    private Double monto;
    private Boolean avisarVencimiento;
    private Boolean renovarAutomaticamente;
    private Moneda moneda;
    private String[] tasas;
    private Cliente cliente;

    //CONSTRUCTOR

        public PlazoFijo(String[] tasas){
            Log.d("APP_01", tasas+"");
            Log.d("APP_01", Arrays.toString(tasas));
            this.tasas = tasas;
            this.monto = 0.0;
            this.dias = 10; //LO COLOCO EN 10 PORQUE ES EL MÍNIMO Y SI NO SE TOCA LA BARRA DE DIAS QUEDARÍA EN 0
            this.avisarVencimiento=false;
            this.renovarAutomaticamente=false;
            this.moneda = Moneda.PESO;
        }

    //GETTERS & SETTERS


    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setAvisarVencimiento(Boolean avisarVencimiento) {
        this.avisarVencimiento = avisarVencimiento;
    }

    public void setRenovarAutomaticamente(Boolean renovarAutomaticamente) {
        this.renovarAutomaticamente = renovarAutomaticamente;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public void setTasas(String[] tasas) {
        this.tasas = tasas;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getDias() {
        return dias;
    }

    public Double getMonto() {
        return monto;
    }

    public Boolean getAvisarVencimiento() {
        return avisarVencimiento;
    }

    public Boolean getRenovarAutomaticamente() {
        return renovarAutomaticamente;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public String[] getTasas() {
        return tasas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    //METODO TO STRING
    @Override
    public String toString() {
        return "PlazoFijo{" +
                "dias=" + dias +
                ", monto=" + monto +
                ", avisarVencimiento=" + avisarVencimiento +
                ", renovarAutomaticamente=" + renovarAutomaticamente +
                ", moneda=" + moneda +
                ", tasas=" + Arrays.toString(tasas) +
                ", cliente=" + cliente +
                '}';
    }

    //METODO PARA RETORNAR LA TASA DE INTERES DEL PLAZO FIJO

        private Double tasa(){

//            System.out.println(dias);
//            System.out.println(monto);



            if(dias < 30 && monto <= 5000) return Double.valueOf(tasas[0]);
            if(dias >= 30 && monto <= 5000) return Double.valueOf(tasas[1]);
            if(dias < 30 && monto > 5000 && monto <= 99999) return Double.valueOf(tasas[2]);
            if(dias >= 30 && monto > 5000 && monto <= 99999) return Double.valueOf(tasas[3]);
            if(dias < 30 && monto > 99999) return Double.valueOf(tasas[4]);
            if(dias >= 30 && monto > 99999) return Double.valueOf(tasas[1]);

            return 0.0;
        }

    //MÉTODO PARA EL CÁLCULO DE LOS INTERESES DEL PLAZO FIJO

        public Double intereses(){
            Double dias1 = Double.parseDouble(dias.toString());
            Double potencia = Math.pow(1+ tasa()/100, dias1/360.0);
            Double interes = monto * (potencia -1);


            return interes;
        }
}
