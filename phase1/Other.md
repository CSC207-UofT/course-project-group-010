# Other 

### Refactoring

- We refactored the names of variables a lot.
- In the commit "refactored databasegetters, added savall"(Around 11/06), we refactored DatabaseGetters
to the Controllers/Presenters/Gateways("Controller") package, because we realized that DatabaseGetters was more of
a gateway between the .ser file and the program, than an actual database.
- Changed type and level classes to be enums for better code style

### Testing

- Basic unit tests have been run
- See issue with testing commands in the Progress Report.md