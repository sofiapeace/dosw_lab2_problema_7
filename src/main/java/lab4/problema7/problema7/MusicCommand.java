package lab4.problema7.problema7;

public class MusicCommand implements Command {
    private Music music;
    public MusicCommand(Music m) { this.music = m; }
    public void execute() { System.out.println(music.play()); }
    public void undo() { System.out.println("Música detenida"); }
    public String getDescription() { return "Reproducir música"; }
}