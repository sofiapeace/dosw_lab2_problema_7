package lab4.problema7.problema7;

public class OpenDoorCommand implements Command {
    private Door door;
    public OpenDoorCommand(Door d) { this.door = d; }
    public void execute() { System.out.println(door.open()); }
    public void undo() { System.out.println("Puerta cerrada"); }
    public String getDescription() { return "Abrir puerta"; }
}