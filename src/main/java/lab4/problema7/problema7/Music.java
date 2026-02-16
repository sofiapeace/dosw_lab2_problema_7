package lab4.problema7.problema7;

public class Music {
    private int volume = 0;
    public String play() { return "Música reproducida"; }
    public String stop() { return "Música detenida"; }
    public String setVolume(int v) { this.volume = v; return "Volumen ajustado a " + v + "%"; }
    public int getVolume() { return volume; }
}

