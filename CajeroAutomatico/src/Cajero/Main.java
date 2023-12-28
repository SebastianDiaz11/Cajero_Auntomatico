package Cajero;

import java.util.Scanner;

public class Main {
	static Scanner entrada = new Scanner(System.in);
	static Persona[] personas;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean salida = false;
		System.out.println("Bienvenidos al programa");
		int input = validarIngresoPersonas();
		personas = new Persona[input];
		do {
			int opcionElegida = validarOpcionMenu1();
			switch(opcionElegida) {
			case 1:
				Persona nuevaPersona = cargarPersona();
				if(personas[personas.length-1] == null) {
					for(int i=0; i<personas.length; i++) {
						if(personas[i] == null) {
							personas[i] = nuevaPersona;
							break;
						}
					}
				} else {					
					System.out.println("Ya no se pueden agregar mas personas.");
				}
				break;
				
			case 2:
				if(personas[0]==null) {
					System.out.println("No hay personas cargadas");
					break;
				}
				if(personas[1] == null) {
					System.out.println("La persona con mayor saldo es:");
					System.out.println(personas[0]);
					break;
				}
				
				int indice = 0;
				for(int i=1; personas[i]!=null && i<personas.length; i++) {
					if(personas[i].getSaldo()>personas[indice].getSaldo()) {
						indice = i;
					}
				}
				System.out.println("La persona con mayor saldo es: ");
				System.out.println(personas[indice]);
				break;
				
			case 3:
				if(personas[0] == null) {
					System.out.println("No hay personas cargadas");
					break;
				} 
				for(int i=0; personas[i] != null && i<personas.length; i++) {
					System.out.println("Persona ["+i+"]: nombre="+personas[i].getNombre()+", apellido="+personas[i].getApellido()+", dni="+personas[i].getDni());
				}
				break;
				
			case 4:
				if(personas[0] == null) {
					System.out.println("No hay personas ingresadas");
					break;
				}
				menuOpciones2();
				int dni = entrada.nextInt();
				Persona persona = busquedaPorDni(dni);
				if(persona == null) {
					System.out.println("No se encontró a la persona");
					break;
				}
				boolean contraseñaCorrecta = verificarContraseña(persona);
				if(!contraseñaCorrecta) {
					System.out.println("3 intentos fallidos. Regresando al menu 1...");
				}
				System.out.println("\nAcceso permitido. Ingresando al menu 3...\n");
				int opcion = validarOpcionMenu3();
				switch(opcion) {
				case 1:
					System.out.println("Cuanto desea depositar?");
					int saldo = entrada.nextInt();
					persona.setSaldo(saldo);
					System.out.println("Carga exitosa! El saldo actual de "+persona.getNombre()+" es: "+persona.getSaldo()+"$");
					break;
					
				case 2:
					System.out.println("Cuanto desea extraer?");
					int extraccion = entrada.nextInt();
					persona.setSaldo(persona.getSaldo()-extraccion);
					System.out.println("Extraccion exitosa! El saldo actual de "+persona.getNombre()+" es: "+persona.getSaldo()+"$");
					break;
					
				case 3:
					System.out.println("El saldo es: "+persona.getSaldo()+"$$");
					break;
					
				case 4:
					if(personas[1] == null) {
						System.out.println("Deben haber al menos 2 personas para utilizar esta funcion");
						break;
					}
					System.out.println("Cuanto desea transferir?");
					int importeATransferir = entrada.nextInt();
					if(importeATransferir > persona.getSaldo()) {
						System.out.println("No puede transferir mas de lo que tiene en cuenta");
						break;
					}
					
					System.out.println("Ingrese el dni de la persona a tranferir");
					int dniATransferir = entrada.nextInt();
					Persona personaATransferir = busquedaPorDni(dniATransferir);
					if(personaATransferir == null) {
						System.out.println("No se encontró a la persona.");
						break;
					}
					persona.setSaldo(persona.getSaldo()-importeATransferir);
					personaATransferir.setSaldo(personaATransferir.getSaldo()-importeATransferir);
					System.out.println("Transferencia exitosa.");
					System.out.println("El saldo actual de "+persona.getNombre()+" es: "+persona.getSaldo()+"$");
					System.out.println("El saldo actual de "+personaATransferir.getNombre()+" es: "+personaATransferir.getSaldo()+"$");
					break;
					
				case 5:
					System.out.println("Saliendo del programa...");
					salida = true;
					break;
					
				}
				break;	
			}
		} while (!salida);
		
		
	}
	
	
	public static int validarIngresoPersonas() {
		int input = 0;
		do {
			System.out.println("Cuantas personas desea cargar?(5-10):");
			input = entrada.nextInt();
			if(input<5 || input > 10) {
				System.err.println("Ingreso invalido. Minimo 5 - Máximo 10");
			}
		} while (input<5 || input>10);
		return input;
	}
	
	public static void menuOpciones1() {
		System.out.println("\n**** Opciones Menu#1 ****");
		System.out.println("**** Menu Principal ****");
		System.out.println("**** 1- Alta nueva persona ****");
		System.out.println("**** 2- Consulta persona con mayor saldo ****");
		System.out.println("**** 3- Imprimir listado personas ****");
		System.out.println("**** 4- Ingreso al cajero (DNI y contraseña) ****");
	}
	
	public static void menuOpciones2() {
		System.out.println("\n**** Opciones Menu#2 ****");
		System.out.println("**** Menu Login ****");
		System.out.println("**** Ingrese el DNI de la persona ****");
	}
	
	public static void menuOpciones3() {
		System.out.println("\n**** Opciones Menu#3 ****");
		System.out.println("**** Menu Cajero ****");
		System.out.println("**** 1- Depositar $$****");
		System.out.println("**** 2- Extraer $$****");
		System.out.println("**** 3- Consultar saldo ****");
		System.out.println("**** 4- Transferir $$ ****");
		System.out.println("**** 5- Salir ****");
	}
	
	public static int validarOpcionMenu1() {
		int input = 0;
		do {
			menuOpciones1();
			System.out.println("\nIngrese una de las cuatro opciones: ");
			input = entrada.nextInt();
			if(input<1 || input > 4) {
				System.err.println("Opcion invalida.");
			}
		} while (input<1 || input>4);
		return input;
	}
	
	public static int validarOpcionMenu3() {
		int input = 0;
		do {
			menuOpciones3();
			System.out.println("\nIngrese una de las cinco opciones: ");
			input = entrada.nextInt();
			if(input<1 || input > 5) {
				System.err.println("Opcion invalida.");
			}
		} while (input<1 || input>5);
		return input;
	}
	
	
	public static Persona busquedaPorDni(int dni) {
		for(int i=0; personas[i] != null && i<personas.length; i++) {
			if(personas[i].getDni() == dni) {
				return personas[i];
			}
		}
		return null;
	}
	
	
	public static Persona cargarPersona() {
		System.out.println("Ingrese el nombre(sin apellido):");
		String nombre = entrada.next();
		System.out.println("Ingrese el apellido:");
		String apellido = entrada.next();
		int dni = validarDni();
		int saldo = validarSaldo();
		System.out.println("Ingrese la contraseña:");
		String contraseña = entrada.next();
		Persona nuevaPersona = new Persona(nombre, apellido, dni, saldo, contraseña);
		return nuevaPersona;
	}
	
	public static boolean esEntero(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public static int validarDni() {
		String input = "";
		do {
			System.out.println("Ingrese el dni:");
			input = entrada.next();
			if(!esEntero(input)) {
				System.err.println("No se admiten caracteres. Reingrese.");
			}
		} while (!esEntero(input));
		int dni = Integer.parseInt(input);
		return dni;	
	}
	
	public static boolean verificarContraseña(Persona persona) {
		System.out.println("Ingrese la contraseña: (maximo 3 intentos):");
		String contraseña = entrada.next();
		for(int i=0; i<2; i++) {
			if(persona.getContraseña().equals(contraseña)) {
				return true;
			} else {				
				System.out.println("Ingreso incorrecto. Reingrese:");
				contraseña = entrada.next();
			}
		}
		return false;
	}
	public static int validarSaldo() {
		String input = "";
		do {
			System.out.println("Ingrese el Saldo:");
			input = entrada.next();
			if(!esEntero(input)) {
				System.err.println("No se admiten caracteres. Reingrese.");
			}
		} while (!esEntero(input));
		int saldo = Integer.parseInt(input);
		return saldo;	
	}
}
