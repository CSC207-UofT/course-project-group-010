Equitable use: all users have the same commands available to them, we aren't discriminating against anyone. Our app is usable by any student or instructor that can use a computer, and I think that's good enough.

Flexibility in use: users have the option to not enter program detail when creating a new user, if that's too much work for them. They can choose how to interact with pages and stuff, they're not required to do anything. It's a command line app, so I don't think there's much else that the user can customize. It provides adaptability to the user's pace. They also get to choose when to enter their commands and stuff, and can go at whatever pace they want.

Simple and intuitive use: we made a help command that gets users started. We arrange commands in order of importance, so the user can quickly get around the program when they first use it, then use more complex commands later. We prompt the user whenever we need their input.

Perceptible information: we display things on a command line, so there isn't much room for this, but for example the comment graph string representation clearly illustrates a tree structure, showing comments and replies in an intuitive way to the users.

it's structured like

comment

  subcomment
  
     sub-subcomment
     
  subcomment
  
etc.

Also when an exception is thrown, we always write a detailed message that clearly tells the user how to correct their mistakes, or what went wrong.

Tolerance for error: we catch exceptions, and exceptions have detailed messages that tell the user what to do from there, or what went wrong. So this is a fail-safe feature: instead of crashing the program, it tells the user the error message and what they can do about it.

Low physical effort: this program only requires the user to type to operate it. They can go at their own pace.

Size+space for approach and use. If the computer/CLI satisfies this, then so does our thing. Our thing operates exactly like a command line on a computer, so if the computer screen is not that big, the keyboard is easy to use, then everything is good.
