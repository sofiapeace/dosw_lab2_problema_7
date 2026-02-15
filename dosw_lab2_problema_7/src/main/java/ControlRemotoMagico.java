import java.util.*;

// INTERFAZ COMANDO
interface Command {
    void execute();
    void undo();
    String getDescription();
}

// RECEPTORES (Los electrodomésticos)
class Light { public String on() { return "Luz encendida"; } public String off() { return "Luz apagada"; } }
class Door { public String open() { return "Puerta abierta"; } public String close() { return "Puerta cerrada"; } }

class Music { 
    private int volume = 0;
    public String play() { return "Música reproducida"; }
    public String stop() { return "Música detenida"; }
    public String setVolume(int v) { this.volume = v; return "Volumen ajustado a " + v + "%"; }
    public int getVolume() { return volume; }
}

class Blinds {
    private int level = 0;
    public String setLevel(int l) { this.level = l; return "Persiana ajustada al " + l + "%"; }
    public int getLevel() { return level; }
}

// COMANDOS CONCRETOS 
class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light l) { this.light = l; }
    public void execute() { System.out.println(light.on()); }
    public void undo() { System.out.println("Luz apagada"); }
    public String getDescription() { return "Encender luz"; }
}

class OpenDoorCommand implements Command {
    private Door door;
    public OpenDoorCommand(Door d) { this.door = d; }
    public void execute() { System.out.println(door.open()); }
    public void undo() { System.out.println("Puerta cerrada"); }
    public String getDescription() { return "Abrir puerta"; }
}

class MusicCommand implements Command {
    private Music music;
    public MusicCommand(Music m) { this.music = m; }
    public void execute() { System.out.println(music.play()); }
    public void undo() { System.out.println("Música detenida"); }
    public String getDescription() { return "Reproducir música"; }
}

class AdjustVolumeCommand implements Command {
    private Music music;
    private int prevVolume;
    private int newVolume;
    public AdjustVolumeCommand(Music m, int vol) { this.music = m; this.newVolume = vol; }
    public void execute() { prevVolume = music.getVolume(); System.out.println(music.setVolume(newVolume)); }
    public void undo() { System.out.println("Volumen regresado a " + prevVolume + "%"); music.setVolume(prevVolume); }
    public String getDescription() { return "Ajustar volumen a " + newVolume + "%"; }
}

class AdjustBlindsCommand implements Command {
    private Blinds blinds;
    private int prevLevel;
    private int newLevel;
    public AdjustBlindsCommand(Blinds b, int lvl) { this.blinds = b; this.newLevel = lvl; }
    public void execute() { prevLevel = blinds.getLevel(); System.out.println(blinds.setLevel(newLevel)); }
    public void undo() { System.out.println("Persiana regresada al " + prevLevel + "%"); blinds.setLevel(prevLevel); }
    public String getDescription() { return "Ajustar persiana al " + newLevel + "%"; }
}

class HistoryEntry {
    Command command; String user; boolean undone;
    public HistoryEntry(Command c, String u) { this.command = c; this.user = u; this.undone = false; }
}

// CLASE PRINCIPAL 
public class ControlRemotoMagico {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<HistoryEntry> history = new ArrayList<>();
        Map<String, Integer> userStats = new LinkedHashMap<>();

        // Inicializar dispositivos
        Light light = new Light();
        Door door = new Door();
        Music music = new Music();
        Blinds blinds = new Blinds();

        System.out.print("Número de acciones a registrar: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.print("\nUsuario: ");
            String user = sc.next();
            System.out.println("Seleccione: 1. Encender luz | 2. Abrir puerta | 3. Reproducir música | 4. Ajustar volumen | 5. Ajustar persiana");
            int choice = sc.nextInt();
            
            Command cmd = null;
            switch (choice) {
                case 1 -> cmd = new LightOnCommand(light);
                case 2 -> cmd = new OpenDoorCommand(door);
                case 3 -> cmd = new MusicCommand(music);
                case 4 -> {
                    System.out.print("Ingrese valor (0-100): ");
                    cmd = new AdjustVolumeCommand(music, sc.nextInt());
                }
                case 5 -> {
                    System.out.print("Ingrese valor (0-100): ");
                    cmd = new AdjustBlindsCommand(blinds, sc.nextInt());
                }
            }

            if (cmd != null) {
                System.out.print("Acción " + i + " ejecutada por " + user + ": ");
                cmd.execute();
                
                System.out.print("¿Deshacer acción? (si/no): ");
                HistoryEntry entry = new HistoryEntry(cmd, user);
                if (sc.next().equalsIgnoreCase("si")) {
                    cmd.undo();
                    entry.undone = true;
                }
                history.add(entry);
                userStats.put(user, userStats.getOrDefault(user, 0) + 1);
            }
        }

        System.out.println("\n-- Historial completo ---");
        for (int i = 0; i < history.size(); i++) {
            HistoryEntry e = history.get(i);
            System.out.println((i+1) + ": " + e.command.getDescription() + " - Usuario: " + e.user + (e.undone ? " (deshecha)" : ""));
        }

        System.out.println("\n-- Investigando quién desconfiguró los electrodomésticos");
        userStats.forEach((name, count) -> 
            System.out.println("Se detecta que " + name + " realizó " + count + " acción(es)."));
        
        sc.close();
    }
}