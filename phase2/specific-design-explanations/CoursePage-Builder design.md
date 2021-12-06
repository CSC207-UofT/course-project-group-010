# CoursePage - Builder Design Pattern

We implemented a a Builder design pattern to separate the construction of a Pages (complex object) from its representation so that 
the same construction process can create different representations, i.e different types of pages may be created.

Currently, only a CoursePage object is needed, but for future extendability purposes there may be many other types of pages that may need to be built.


A Builder interface is implemented containing all necessary methods required to create a "Page".
If the program were extended, the Builder interface could be extended as well to accommodate the new additions to the CoursePage.

The **Director** has a ConstructCoursePage method which accepts a *CoursePageBuilder* object and required information for that page. 

The **CoursePageBuilder** is then used by the Director in the ConstructCoursePage method to build the objects required for initialization of the CoursePage.

In the future, our program may be extended to produce new types of pages, such as a "Review Page" or "Resources Page". 
In these cases, the Director would be extended to have *ConstructReviewPage*, *ConstructResourcesPage* methods, which accept a CoursePageBuilder object with necessary data as input,
and in the new Director methods, the CoursePageBuilder's methods may be used as needed to construct these new pages. 
 

