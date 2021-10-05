# Specification

- Students and profs can sign into the app using their school email
- Students and profs can review courses(how)

- Each course has a page, with 2 or more sub-tabs consisting of
  1. Material page
   
        Where instructors and students can upload the course materials(Syllabus, marking scheme,textbook created by the course instructor etc) with comments.
   
        a) Copyright issue  - work with UT so we could get the authorization

        b) Link to UofT library (direct link to book sign out page)

  2. Review page

        a) Question for students : where we have a series of questions that students can respond under/create threads; for example “What was the hardest part of this course?” ,”What do you wish you knew before you took this course?” etc.

        b)Response from instructors: Additionally, only instructors of that course can add/revise questions on the course page, maybe for survey purposes or general inquiry.

        c) Review rating:The review system is “relative”; each students’ rating input is pooled together with other students of the same program, and we output the average of the ratings of the students in the same program

        d) Function - nice to have 
        fill by year 
- We can connect to some sort of database to load a list of all courses
    - UofT JSON 
    - database
- We will save to some sort of database to save comments and stuff
- We need to be able to read from a database to fetch existing comments/ratings for a course

## The Basic Specification
- Students and profs can log into the platform through UT email 
- Students can modify their profile, writing in what major and stuff they are in.
- Each course has a page on this platform
Students can leave a rating of the course on the page(add features later)
- The platform will be able to save student responses in some sort of database(don’t implement that for now), and will be able to fetch from said database.

- Option :
    - Timetable 
    - RateMyStudents page for instructors of the course
