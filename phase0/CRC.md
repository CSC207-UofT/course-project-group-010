All child classes/implementations of interfaces must implement abstract methods.
Will not be listed in the CRC card.

## Entity Classes
Basic entity data

|User(abstract)||
|:-------------|:--|
|Responsibilities|Collaborators|
|getName||
|getID ||

Student Entity

|StudentEntityData||
|:-------------|:--|
|Responsibilities|Collaborators|
|getProgram|extends EntityData|
|getYearOfStudy||

Prof Entity

|ProfEntityData||
|:-------------|:--|
|Responsibilities|Collaborators|
|getCurrentCourses|extends EntityData|
|getPosition||

Course Data

|Course||
|:-------------|:--|
|Responsibilities|Collaborators|
|get/setName||
|getCode||
|get/setDescription||

Review[to be implemented later]

|Review||
|:-------------|:--|
|Responsibilities|Collaborators|
|getCourseID||
|get/setContent||
|getReplies||
|addReply||
|removeReply||

Rating processor for a course

|Rating||
|:-------------|:--|
|Responsibilities|Collaborators|
|processRating||
|getRating||

## Use Case Classes

|IDBSaveable|Interface|
|:-------------|:--|
|Responsibilities|Collaborators|
|getData||

Anything that has some sort of authorization requirement

|IAuthorizable|Interface|
|:-------------|:--|
|Responsibilities|Collaborators|
|getAuthRequirement||

Anything that has an authorization level and will try to do things

|IHasPermission|Interface|
|:-------------|:--|
|Responsibilities|Collaborators|
|getPermissionLevel||

Anything that will pass its data to a presenter

|IGettable|Interface|
|:-------------|:--|
|Responsibilities|Collaborators|
|getData||

Use case manager for course object[will break into helper classes]

|CourseManager||
|:-------------|:--|
|Responsibilities|Collaborators|
|addRating|Has a course, review, rating stored in the object|
|changeInfo|extends Authorizable, IGettable|
|getCommentSection||

Use case manager for comment/review section[will break into helper classes, will implement later]
Comments will be almost like a directory(with a tree structure or something)

|CommentManager||
|:-------------|:--|
|Responsibilities|Collaborators|
|getParentComment|implements IAuthorizable, IGettable|
|addComment||
|removeComment||
|rateComment||
|unrateComment||

Manages use cases for a user. Will currently only report on its data

|UserManager||
|:-------------|:--|
|Responsibilities|Collaborators|
|getUser|Implements IAuthorizable, IAuthorized, IGettable|
|getters for student attrs||

## Controllers, Presenters, Gateways

Breaks up tasks dealing with authorization. Helper class to commandExecutor

|AuthHelper||
|:-------------|:--|
|Responsibilities|Collaborators|
|checkAuth(action, Authorized a)||

Command Executor. Will probably create a package that has a separate class with each
command that all communicates with this class.

(note: it's a lot different in code by the way it is implemented, with many helper classes)

|CommandExecutor||
|:-------------|:--|
|Responsibilities|Collaborators|
|process Request|Interacts w\ Agent, all Managers|
|checkout Page||
|login||
|getInstance||

|Database<T>|Abstract|
|:-------------|:--|
|Responsibilities|Collaborators|
|getEntry||
|setEntry||

Databases for holding objects. This may be more like a gateway to some sort of database in the future
(eg. SQL)

|UserDB||
|:-------------|:--|
|Responsibilities|Collaborators|
||extends Database<UserManager>|

|CourseDB||
|:-------------|:--|
|Responsibilities|Collaborators|
||extends Database<CourseManager>|

## Outer Layer

takes text input

|ScreenIO||
|:-------------|:--|
|Responsibilities|Collaborators|
|sendCommand||

# How does this work?

- Entity classes store data for courses, users, etc.
- Use case classes hold data and manage use cases pertaining to the data.
  - eg. CourseManager holds everything related to a course: the course itself, the rating and review system
- Controllers break up tasks into smaller tasks
  - (eg. user wants to leave comment on a page. It deals with authorization then leaves the comment)