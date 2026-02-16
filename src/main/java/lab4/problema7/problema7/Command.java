package lab4.problema7.problema7;

interface Command {
    void execute();
    void undo();
    String getDescription();
}