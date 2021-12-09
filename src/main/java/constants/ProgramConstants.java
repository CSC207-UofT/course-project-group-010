package constants;

import java.util.List;

public class ProgramConstants {

    private final List<String> possiblePrograms;
    public static final String NO_PROGRAM = "N/A";

    public ProgramConstants() {
        possiblePrograms = List.of("ACCOUNTING", "ACTUARIAL SCIENCE", "ANTHROPOLOGY", "APPLIED MATHEMATICS", "APPLIED STATISTICS","COMPUTER SCIENCE", "DATA SCIENCE", NO_PROGRAM);
    }

    public List<String> getPossiblePrograms() {
        return possiblePrograms;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String s : possiblePrograms) {
            str.append(s).append("\n");
        }
        return str.toString();
    }

    public boolean contains(String s) {
        return possiblePrograms.contains(s);
    }
}
