package lab4.problema7.problema7;

public class Blinds {
    private int level = 0;
    public String setLevel(int l) { this.level = l; return "Persiana ajustada al " + l + "%"; }
    public int getLevel() { return level; }
}

