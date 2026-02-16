package lab4.problema7.problema7;

public class HistoryEntry {
    Command command; String user; boolean undone;
    public HistoryEntry(Command c, String u) { this.command = c; this.user = u; this.undone = false; }
}