# Running the program

Here is how to run the program. If you need further explanation about the source code, look at the specific-design-explanations folder,
which explains specific design patterns/decisions that we used.

## How to run

- Clone the code using github desktop
- Make sure you mark src/main/java as sources root(rightclick > mark directory as > sources root)
- Also mark src/test/java as test sources root(rightclick > mark directory as > test sources root)
- Configure your Java VM
- Run the main() method in the outer.ScreenIO class

Running the program:
- the enter "help" to get help, or enter [commandname] -h for specific command help
- listusers and listcourses to see available course and users.
- You can start by making a new user with the newuser command, then login as the new user.
- Try accessing a page with the checkout command.

Testing the program
- make sure src/test/java is marked as test sources root
- rightclick > run with coverage
- you may need to scroll down a bit to find relevant classes, or you can look at the left toolbar to see code coverage
for individual packages
- IMPORTANT - please delete the contents of courses.ser and users.ser before running tests. These interfere with the tests.

# Sample commands to demonstrate functionality:
You may be prompted to enter additional info while running these commands. We will load some sample data into the system for you to check out.

> newuser [follow CLI prompts to make a new user]

> createcourse [follow CLI prompts to make a new course]

> login [id here]

> checkout [course here]

These will allow you to create a new user/course, login to the user and view the course!

### Seeing Basic information

> print

This will print all data for the course you made

> checkout -u

> print

This will checkout your user profile and print your profile information

### Interacting with the comment thread

> checkout [course code here]
>
> checkout -c
>
> print
>
> reply root OR reply [will automatically reply to your current viewing comment]
>
> print

### Check out our comment navigation feature

> print [this will print two comments, now that you have replied. You will navigate to your own reply]
>
> cd [id of your recent comment]
>
> print
>
> cd .. [navigates backwards]
>
> print

### Rating the course

> checkout [course code]
>
> rate 10
>
> print [average rating will be 10(if you were the only one to rate)]
> 
> rate 5
> 
> print [average rating should be 5, as your rating was updated from 10 to 5]

Your rating in RelativeRatings should also have updated. Check that out!
You can try making a new user and rating the course, to check how rating and relativeRatings updates.

### Showing our serialization functionality

> end [ends program, automatically saves]
> OR saveall(to save the program without terminating)
>
> [run ScreenIO.main() again]
>
> listusers
>
> listcourses
>
> [check things out again, it should have saved your progress]
