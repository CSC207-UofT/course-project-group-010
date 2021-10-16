package Controller.Commands.CommandExceptions;

public class CommandNotAuthorizedException extends Exception{

    public CommandNotAuthorizedException() {
        super("Command not Authorized");
    }
    public CommandNotAuthorizedException(String message){
        super(message);
    }
}
