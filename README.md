# University Course Review System

This is a course review system for University Courses, kind of like RateMyProf with additional features.
Students and professors will be able to view courses, leave reviews/ratings, and chat in the comments section.

Go to the phase0 folder for phase0, I would recommend looking at progress_report.md first

Go to the phase1 folder for phase1, I would STRONGLY recommend starting with DesignDocument.md. It references all the
other documents.

# Running the program:
- If it can't run after cloning, try "new project from VCS" in the IntelliJ editor.
    - github desktop cloning seems to encounter issues
    - main method is in src/main/java/Outer/ScreenIO.
- the enter "help" to get help, or enter [commandname] -h for specific command help
- start by making a new user with the newuser command, then login as the new user.
- Try accessing a page

# Demo users/courses
- There are 2 demo users:
 - id: 12345, name: kevin
 - id: panchenxyz, name: PanChen

- There is 1 page that you can view
 - id: MAT137, Calculus with Proofs

# Sample commands to demonstrate functionality:
> login panchenxyz
> 
> checkout MAT137
> 
> print [prints all relevant info]
> 
> getcomments
> 
> displayfullthread
> 
> reply root [response here]
> 
> *you can use any other comment related commands at this point*
> 
> checkout MAT137 [goes back to looking at the course page]
> 
> end

# Sample commands for creating new users and saving to database(to demonstrate that feature)

> newuser student [name] [id]
> 
> newuser instructor [name] [id]
> 
> createcourse
> 
> *follow the prompts that it gives you*
> 
> saveall or end
