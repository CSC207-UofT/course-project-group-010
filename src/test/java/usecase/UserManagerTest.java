//package UseCase;
//
//import org.junit.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserManagerTest {
//
//    // TODO New implementation of UserManager, check then change tests
//    // or explain how to implement into commandExecutor.
//    @Test(timeout = 100)
//    public void testUserManager() {
//        UserManager userManager = new UserManager();
//        InstructorUser i1 = userManager.createInstructorUser("Prof", "Prof@gmail.com", "Professor");
//        StudentUser s1 = userManager.createStudentUser("Kevin", "Kevin@gmail.com", "Computer Science Major");
//
//        HashMap<String, Object> result1 = userManager.getData(i1);
//        assertEquals("Prof", result1.get("displayName"));
//        assertEquals("Prof@gmail.com", result1.get("ID"));
//        assertEquals("Professor", result1.get("position"));
//        result1 = userManager.getData(s1);
//        assertEquals("Kevin", result1.get("displayName"));
//        assertEquals("Kevin@gmail.com", result1.get("ID"));
//        assertEquals("Computer Science Major", result1.get("programDetail"));
//        assertEquals(true, ((HashMap<Integer, List<Course>>) result1.get("courses")).isEmpty());
//
//        userManager.userSetDisplayName(s1, "Changed");
//        assertEquals("Changed", s1.getdisplayName());
//    }
//}