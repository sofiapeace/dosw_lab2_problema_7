package lab4.problema7.problema7;

public class AdjustVolumeCommand implements Command {
    private Music music;
    private int prevVolume;
    private int newVolume;
    public AdjustVolumeCommand(Music m, int vol) { this.music = m; this.newVolume = vol; }
    public void execute() { prevVolume = music.getVolume(); System.out.println(music.setVolume(newVolume)); }
    public void undo() { System.out.println("Volumen regresado a " + prevVolume + "%"); music.setVolume(prevVolume); }
    public String getDescription() { return "Ajustar volumen a " + newVolume + "%"; }
}
