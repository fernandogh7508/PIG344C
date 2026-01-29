package com.empresa.gestion.app;
import com.empresa.gestion.model.Empleado;
import com.empresa.gestion.service.EmpleadoService;
import java.util.Scanner;

public class Main2 {
      public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        EmpleadoService service = new EmpleadoService();
        int opcion;
        do{
            System.out.println("\n === MENÚ DE EMPLEADOS ==="); //Syot
            System.out.println("1. Agregar empleado"); //Syot
            System.out.println("2. Listar empleado"); //Syot
            System.out.println("3. Consultar empleado"); //Syot
            System.out.println("4. Eliminar empleado"); //Syot
            System.out.println("0. Salir"); //Syot
            System.out.println("Seleccione una opción"); //Syot
            opcion = sc.nextInt();
//int id,String nombre, int edad,boolean activo,char genero, double salario, int horasTrabajadas
            switch (opcion) {
                case 1:
                    System.out.println("Id: ");
                    int id = sc.nextInt();
                    
                    System.out.println("Nombre: ");
                    sc.nextLine();//limpiar 
                    String nombre = sc.nextLine();
                    
                    System.out.println("Edad: ");
                    int edad = sc.nextInt();
                    
                    System.out.println("Activo: ");
                    boolean activo = sc.nextBoolean();
                    
                    System.out.println("Genero: ");
                    char genero = sc.next().charAt(0);
                    
                    System.out.println("Salario: ");
                    double salario = sc.nextDouble();

                    System.out.println("Horas Trabajadas: ");
                    int horasTrabajadas = sc.nextInt();

                    Empleado emp = new Empleado(id, nombre, edad, activo, genero, salario, horasTrabajadas);
                    service.agregarEmpleado(emp); 
                    break;
                    case 2:
                        service.listarEmpleados();
                        break;
                    case 3:
                        System.out.println("Ingrese Id: ");
                        service.consultarEmpleado(sc.nextInt());
                        break;
                    case 4:
                        System.out.println("Ingrese Id a eliminar: ");
                        service.eliminarEmpleado(sc.nextInt());
                        break;
                    case 0:
                        System.out.println(" Saliendo del sistema....");
                        break;
                default:
                    System.out.println("✖️ opción inválida ");
            }
    
        }while(opcion!=0);
        sc.close();
        
      }
}
