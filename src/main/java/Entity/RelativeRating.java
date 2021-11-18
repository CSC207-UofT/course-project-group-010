package Entity;

import java.util.HashMap;
import java.util.List;

public class RelativeRating {

    private HashMap<String, HashMap<String, List<Rating>>> programToRating;

    /**
     *    { Mathematics:  { Specialist: [Rating1, Rating2, Rating3]
     *                       Major: [Rating1, Rating2, Rating3]
     *                       Minor: [Rating1, Rating2, Rating3] }
     *
     *      Philosophy:   { Specialist: [Rating1, Rating2, Rating3]
     *                       Major: [Rating1, Rating2, Rating3]
     *                       Minor: [Rating1, Rating2, Rating3] }
     *
     *
     *      Computer Science: { Specialist: [Rating1, Rating2, Rating3]
     *                              Major: [Rating1, Rating2, Rating3]
     *                              Minor: [Rating1, Rating2, Rating3] }
     *
     */

    public RelativeRating(List<Rating> ratings){
        HashMap<String, HashMap<String, List<Rating>>> programToRating = new HashMap<>();
        for (Rating r : ratings) {
            programToRating.put()


        }

    }
}
