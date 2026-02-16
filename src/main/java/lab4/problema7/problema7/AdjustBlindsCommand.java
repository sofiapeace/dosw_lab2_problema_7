package lab4.problema7.problema7;

public class AdjustBlindsCommand implements Command {
    private Blinds blinds;
    private int prevLevel;
    private int newLevel;
    public AdjustBlindsCommand(Blinds b, int lvl) { this.blinds = b; this.newLevel = lvl; }
    public void execute() { prevLevel = blinds.getLevel(); System.out.println(blinds.setLevel(newLevel)); }
    public void undo() { System.out.println("Persiana regresada al " + prevLevel + "%"); blinds.setLevel(prevLevel); }
    public String getDescription() { return "Ajustar persiana al " + newLevel + "%"; }
}