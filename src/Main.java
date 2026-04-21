import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1. CLASE ABSTRACTA (Herencia)

abstract class Usuario {
    protected int id;
    protected String nombre;

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Método que cada hijo usará a su manera
    public abstract void mostrarDatos();
}

// 2. INTERFAZ (Contrato)
// Obliga a que el sistema tenga estos métodos sí o sí.
interface IOperaciones {
    void guardar(Usuario u);

    void listar();
}

// 3. CLASE HIJA (Especialización)
class Cliente extends Usuario {
    public Cliente(int id, String nombre) {
        super(id, nombre);
    }

    @Override
    public void mostrarDatos() {
        System.out.println("ID: " + id + " | Nombre: " + nombre + " [Rol: Cliente]");
    }
}

// 4. LÓGICA Y POLIMORFISMO
class GestionNegocio implements IOperaciones {
    // Polimorfismo: Una lista de "Usuarios" que guarda "Clientes"
    private List<Usuario> db = new ArrayList<>();

    @Override
    public void guardar(Usuario u) {
        db.add(u);
        System.out.println("¡Guardado con éxito!");
    }

    @Override
    public void listar() {
        for (Usuario u : db) {
            u.mostrarDatos(); // Polimorfismo: llama al método del hijo
        }
    }
}

// 5. SIMULADOR DE FRONTEND (Menú)
public class Main {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        GestionNegocio negocio = new GestionNegocio();
        int opcion = 0;

        do {
            System.out.println("\n--- MI NEGOCIO (BACKEND SIMULATOR) ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Ver Lista");
            System.out.println("3. Salir y Dormir");
            System.out.print("Elige: ");
            opcion = leer.nextInt();

            if (opcion == 1) {
                System.out.print("ID: ");
                int id = leer.nextInt();
                System.out.print("Nombre: ");
                String nom = leer.next();
                negocio.guardar(new Cliente(id, nom));
            } else if (opcion == 2) {
                negocio.listar();
            }
        } while (opcion != 3);

        System.out.println("¡Suerte en el examen!");
        leer.close();
    }
}