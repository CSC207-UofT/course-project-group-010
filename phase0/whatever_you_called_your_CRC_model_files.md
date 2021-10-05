|User(abstract)||
|:-------------|:--|
|Responsibilities|Collaborators|
|Provide interface for Subclasses|| 
Login through school email ||
Fetch course||
LeaveFeedback||

|StudentUser||
|:----------|:--|
|Responsibilities|Collaborators|
|Login(override)|extends User|
Reply to review prompts||
Modify profile||
Rate a course out of 10, provide their program of study||
Provide links to course material (external web servers, e.g. onedrive, drive, imgur,youtube, etc...)||
Provide tips in the tip page||
Upvote tips||
Upvote replies in the review prompts||

|ProfUser||
|:-------|:--|
|Responsibilities|Collaborators|
|Login(override)|extends User|
|Give review prompts||
|Modify profile||
|Upvote student replies under prompts||
|Leave review in the RateMyStudents page||


|Course(Abstract)||
|:---------------|:--|
|Responsibilities|Collaborators|
|processRating||
|modifyData||

|genericCourse(Abstract)||
|:----------------------|:--|
|Responsibilities|Collaborators|
|processRating||
|modifyData||

|Comment(Abstract) → tree structure kinda||
|:---------------------------------------|:--|
|Responsibilities|Collaborators|
|Replies(list of comments)||
|giveContent|| 

|UserManager||
|:----------|:--|
|Responsibilities|Collaborators|
|fetchUser||
|modifyUser||
|sendtoDB||
|fetchfromDB||


|CourseManager||
|:------------|:--|
|Responsibilities|Collaborators|
|storeInfo||
|fetchInfo||


|DataBase(abstract)||
|:-----------------|:--|
|Responsibilities|Collaborators|
|storeInfo||
|fetchInfo||






