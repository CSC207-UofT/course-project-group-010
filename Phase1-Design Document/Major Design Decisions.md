# Major Design Decisions

##Relative Rating 
The main purpose of our system is to help the students most.
So, we realize that students from the different programs may experience different levels of difficulty when taking the same course. 
But providing relative ratings based on the program detail will give students true insights into the courses. 



##Database 
We want to find an easier way to access the data.
Thus, we decide to experiment serializable interface since it is Java's built-in interface and allows us to transfer objects through the network.


##Comment 
The main purpose of our system is to help the students in the most effective way. 
Thus,we decide to let users answer the list of general questions and upvote/downvote for the responses to these questions. 
Through this way, the comments shown on our course page will focus on the most important/relevant area of the course. 
