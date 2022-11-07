package worldOfZuul;

public enum Commands
{
    LOOK("kigge"),USE("brug"),Inventory("affaldspose"),
    GO("gå"), QUIT("afslut"), HELP("hjælp"), UNKNOWN("?");
    
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
