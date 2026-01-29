package com.empresa.gestion.model;

public class Empleado extends Persona{
    private double salario;
    private int horasTrabajadas;
    
    
    public Empleado(int id,String nombre, int edad,boolean activo,char genero, double salario, int horasTrabajadas) {
        super(id,nombre, edad,activo,genero);
        this.salario = salario;
        this.horasTrabajadas= horasTrabajadas;
    }
    
    public double getSalario() {
        return salario;
    }
    
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
    

   // ♻️Sobreescritura del método presentarse Polimorfismo
    @Override
    public void presentarse() {
        System.out.println("Soy el empleado " + getNombre() + " y misalario es " + salario);
    }

    public double calcularSalarioMensual(){
        double valorHora =salario/160;
        double salarioFinal= valorHora * horasTrabajadas;
        return salarioFinal;
    }
    // Agregamos reglas de negocio
    public boolean esMayorDeEdad(){
        return edad>=18;  
    }
    public boolean puedeTrabajar(){
        return edad >=18 && activo==true;
    }
    public String clasificarEmpleado(){
        //Usamos if cuando el programa debe decidir según una condición
        if(edad < 18){
            return "Menor de edad (No puede trabjar)";
        }
        else if(edad<=60){
                 return "Empleado activo";
                }
        else{
            return "Empleado en edad de retiro";
        }
        
    }
    public String obtenerTipoEmpleado(){
        //switch se usa cuando hay opciones definidas 
        switch (genero) {
            case 'M':
                return "Masculino";
            case 'F':
                return "Femenino";
            default:
                return "No especificado";
        }
    }
    public String resumen(){
        return "ID: "+id+
                "| Nombre: " + nombre +
                "| Edad: " + edad +
                "| Activo: " + activo +
                "| Genero: "+ genero +
                "| Salario: " + salario +
                "| Hora Trabajadas: "+ horasTrabajadas
                ;
    }

    
}
