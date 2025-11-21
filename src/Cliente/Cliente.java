package Cliente;

public class Cliente {
    //Atributos
    private int idCliente;
    private String nombre;
    private String email;

    //Constructor
    public Cliente(int idCliente, String nombre, String email){
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
    }

    //Getter y setter
    public int getId(){
        return idCliente;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEmail(){
        return email;
    }

    public String toString(){
        return getId() + "," + getNombre() + "," + getEmail();
    }

    //Mostrar toda la info del cliente
    public void mostrarInfo(){
        System.out.println("Id: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Email: " + getEmail());
        System.out.println("-------------------------------");
    }

}
