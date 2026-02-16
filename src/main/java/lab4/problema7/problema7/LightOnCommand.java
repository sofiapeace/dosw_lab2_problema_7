package lab4.problema7.problema7;

public class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light l) { this.light = l; }
    public void execute() { System.out.println(light.on()); }
    public void undo() { System.out.println("Luz apagada"); }
    public String getDescription() { return "Encender luz"; }
}
