package worldOfZuul;

public enum Commands
{
    LOOK("look"),USE("use"),
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?");
    
    private String commandName;
    
    Commands(String commandString)
    {
        this.commandName = commandString;
    }
    
    public String toString()
    {
        return commandName;
    }
}
