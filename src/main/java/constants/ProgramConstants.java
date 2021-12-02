package constants;

import java.util.List;

public class ProgramConstants {

    private List<String> possiblePrograms;

    public ProgramConstants() {
        possiblePrograms = List.of("ACCOUNTING", "ACTUARIAL SCIENCE", "ANTHROPOLOGY", "APPLIED MATHEMATICS", "APPLIED STATISTICS","COMPUTER SCIENCE", "DATA SCIENCE", "N/A");
    }

    public List<String> getPossiblePrograms() {
        return possiblePrograms;
    }

    @Override
    public String toString() {
        String str = "";
        for (String s : possiblePrograms) {
            str += s + "\n";
        }
        return str;
    }

    public boolean contains(String s) {
        return possiblePrograms.contains(s);
    }
}
