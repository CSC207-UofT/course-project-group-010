# Packaging Strategy

We decide to use packaging by layer of clean architecture
strategy. 

As we decide to follow the clean architecture structure to design our system, using packaging by layer strategy provides us the following benefits: 

1.	divide our system into logical pieces so it is easier to understand and follow. For example, we grouped all entities into an entity folder.

2.	easier to find the codes/bugs/errors. 

3.	easier to check if there are violations of clean architecture. For example, it is easier to make sure that the inner layer does not use the methods from the outer layer because we grouped the same layer into one folder. 

4.	provide flexibility for future expansion. If we want to implement this system into the android app, we can just go to the gateways/presenters(Outer) folder to make the change. 

We believe that packaging by layer is quite effective for the current scale of the project. Classes can be easily refactored in the future, if we scale the project and have to use a different system.
