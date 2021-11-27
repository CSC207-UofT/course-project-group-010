# Commands
This document will explain how commands approximately work in the code. Commands seem confusing, as there are a lot of different classes, but it is necessary for the code.

All commands extend the Command class, so you only need to understand how the parent class works.

### Command has 4 important points:

- Its initializer sets its minimum and maximum arguments. All derived classes will call super([minargs], [maxargs]) to set their individual minimum and maximum arguments
- The run method is called to run the command. Each child class overrides it. This will do something in the program(like edit data, call use case classes, etc.), then return a string or throw an exception
- The help method is expected to be overridden by child classes. It is implemented in the parent class with a default message "help is not implemented yet," and child classes can override the help string that is returned
- There are numerous protected check___ methods, that check things such as: whether the correct number of arguments were passed in, whether the user is logged in/viewing a page, etc.
These typically get run at the start of the run() method.

### How does a command get called?

CommandConstants maps each command to a method. It is a factory, in a sense.

For example, if you call CommandConstants.get("help), it will return a new HelpCommand.

Therefore, in order to run a command,
- the user inputs something (eg. "checkout abc")
- the input gets split into a method and arguments(method="checkout", arguments=["abc"])
- We get the corresponding command from the factory(new CheckoutCommand())
- We run the command's run() method with the arguments(CheckoutCommand.run(arguments))

I'm using CheckoutCommand to denote an object of type CheckoutCommand here.

### What next?
Some final words:
- You can check out our command classes to see how run() methods actually work. Some methods have helper classes that isolate certain functionalities, which can be found in the controller.commands.commandHelpers package.
- Commands have subpackages such as CommentCommands, which contains comment related commands, and CourseCommands, which contains course related commands

Hopefully you understand how commands work now.
