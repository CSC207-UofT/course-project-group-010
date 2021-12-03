# Project Accessibility Report

For each Principle of Universal Design, write 2-5 sentences or point form notes explaining which features your program
adhere to that principle.

## Principle 1: Equitable Use

Features our program has: We do not discriminate or stigmatize any users. Furthermore, we provide simple help strings
for commands to help users with any level of tech skills use this software.

Features planned to implement in the future:

1. the option of colour and brightness: this option will provide equitable use for the people with colour blindness and
   version issues.

## Principle 2: Flexibility in Use

Features our program has: users have the option to not enter program detail when creating a new user, if that's too much work for them. 
It's a command line app, so users can work at their own pace. Also, the vote/reply commands(used for voting/replying to comments) have an option for the user to not
enter a specific comment ID to vote/reply to, and will automatically vote/reply to the comment they are currently viewing. Users can choose whichever option is more convenient(navigating to a comment using the "cd" command
and voting, or using "vote [commentid]")

Features planned to implement in the future:

1.the option of size: increase the size of display content.

2. the option of choosing to read or listen

## Principle 3: Simple and Intuitive Use

Features our program has: Commands are intuitive, eg. the print command can print whatever page the user
is currently viewing(course page, comment section, user profile). We eliminated unnecessary complexity of using different
print commands for different pages. Also, we prompt users a lot, eg. when they open the app, we print "enter help for help," and you can type
[commandname] -h for specific help on commands. Also also, commands always print the result back to the user, eg. after entering
"login 123" you get "logged in as [name]" to ensure the user that they logged in as the correct user.


Features planned to implement in the future:

1. Accomodate more language skills with the option to choose from many common display languages: English, French, Mandarin, Japanese and Korean.

## Principle 4: Perceptible Information

Features our program has: Comments are structured to give a visual representation of the graph data structure,
showing comments and subcomments in a perceptible manner. It looks approximately like

-- comment 

------ subcomment id:123

-- comment2

Features planned to implement in the future:

1. maximize "legibility" of essential information. -add the verification process before the rating or commend threads
   could be posted. For example, our rating range is from 0 to 5 and we prohibit the user to leave 0 as a rating. This
   verification process will reduce the chance of misleading information. Also, if the user comments on a comment thread,
   the program administrator will check the content of commend threads before posting it to the app.

## Principle 5: Tolerance for Error

Features our program has:

1. failsafe features:
   when an exception is thrown, we print a descriptive error message and ensure that the program doesn't break,
rather than crashing the program. We expect most exceptions that are caught, and they are dealt with accordingly.
2. provide warnings of errors. We then explain what went wrong to the user, and what they can do about it. Eg. when you enter
   an invalid id when creating a user, you get "Invalid. IDs consist of 1 or more letters, followed by 0 or more numbers."

Features planned to implement in the future: Arrange elements to minimize hazards and errors, we will
try to make warnings, rules and error messages stand out using different colored text or something.

## Principle 6: Low Physical Effort

Features our program has: The CLI doesn't exactly take much effort to operate. There aren't any features
built into the actual program, except for the fact that the user can wait as long as they want to enter commands.

Features planned to implement in the future:
1.Minimize sustained physical effort:
-the option of size: increase the size of display content. This help reduce eye pressure. -the option of colour and
brightness: this option will provide equitable use for the people with colour blindness and version issue. This help
reduce eye pressure.

## Principle 7: Size and Space for Approach and Use

Features our program has: We do not have any explicit features, except for the fact that this is
meant to be run on a computer, which typically provides appropriate size/space for use.

Features planned to implement in the future: do not plan future implementation

This principle does not apply to this program because our program will always be run
on a computer of phone, that already follows this principl(hopefully). 
If the computer/phone doesn't require it, our app will not require the user to posture or move in any specific way.

## Target users of our program if you were to sell or license your program to customers.

Our target users are the following:
- University Students who are deciding which course to take and want somewhat advance metrics
by which they can judge the difficulty of a course. Students who may be applying for university
or selecting courses, that want comments from peers
on their experience with the course.
- Schools and Professors who want to hear feedback from students to improve their
courses, and those who want to be more interactive with their students.

## Less likely to be used by certain demographics

Our program is less likely to be used by people who are not in University, as they would not be interested in hearing about university courses. Also, it would not be used
by those who do not use a computer or smartphone. Also, people who do not really care about ratings of
university courses, such as Math and Physics students that are required to take difficult math courses.

