All child classes/implementations of interfaces must implement abstract methods.
Will not be listed in the CRC card.

## Entity Classes
Basic entity data

|EntityData(abstract)||
|:-------------|:--|
|Responsibilities|Collaborators|
|getProfile||
getID ||

Student Entity

|StudentEntityData||
|:-------------|:--|
|Responsibilities|Collaborators|
||extends EntityData|

Prof Entity

|ProfEntityData||
|:-------------|:--|
|Responsibilities|Collaborators|
||extends EntityData|

Course Data

|CourseData||
|:-------------|:--|
|Responsibilities|Collaborators|
|get/setInfo||
|get/setRating||
|get/setMainComment||

Comment, tree structure

|Comment||
|:-------------|:--|
|Responsibilities|Collaborators|
|get/setContent|Inside CourseData|
|getReplies||
|addReply||
|removeReply||

## Use Case Classes

|IDBSaveable|Interface|
|:-------------|:--|
|Responsibilities|Collaborators|
|giveEntries||

Anything that has some sort of authorization requirement

|IAuthorizable|Interface|
|:-------------|:--|
|Responsibilities|Collaborators|
|getAuthLevel||

Anything that has an authorization level and will try to do things

|IAuthorized|Interface|
|:-------------|:--|
|Responsibilities|Collaborators|
|getAuthLevel||

Anything that will pass its data to a presenter

|IGettable|Interface|
|:-------------|:--|
|Responsibilities|Collaborators|
|getData||

Use case manager for course object[will break into helper classes]

|CourseManager||
|:-------------|:--|
|Responsibilities|Collaborators|
|addRating|Interacts w/ course|
|changeInfo|extends Authorizable, IGettable|
|getCommentSection||

Use case manager for comment section[will break into helper classes]
Comments will be almost like a directory(has a tree structure, right?)

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
|<getters for instance attrs here>||

Manages use cases for authorization of actions

|AuthHelper||
|:-------------|:--|
|Responsibilities|Collaborators|
|checkAuth(action, Authorized a)||

We may change this later, I feel like we can make classes that help with specific
actions, that implement Authorizable, and we can pass that in as a parameter.

## Controllers

Command Executor. Will probably create a package that has a separate class with each
command that all communicates with this class.

Thinking about making it a client-side singleton

|CommandExecutor||
|:-------------|:--|
|Responsibilities|Collaborators|
|viewCourse|Interacts w\ Agent, all Managers|
|rateCourse||
|comment||
|voteComment||
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

Idk what im doing now

|main||
|:-------------|:--|
|Responsibilities|Collaborators|
|main() may just create a ScreenIO or something||