package Cajero;

public class Persona {
	private String nombre;
	private String apellido;
	private int dni;
	private int saldo;
	private String contraseña;
	
	public Persona(String nombre, String apellido, int dni, int saldo, String contraseña) {
		setNombre(nombre);
		setApellido(apellido);
		setDni(dni);
		setSaldo(saldo);
		setContraseña(contraseña);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", saldo=" + saldo+"]";
	}
	
	
}
