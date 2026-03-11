/**
 * Parses user input into command parts.
 */
public class Parser {
    /**
     * Returns the command word from user input.
     *
     * @param input full user input
     * @return command word in lowercase, or empty if missing
     */
    public String getCommandWord(String input) {
        if (input == null) {
            return "";
        }
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            return "";
        }
        String[] parts = trimmed.split("\\s+", 2);
        return parts[0].toLowerCase();
    }

    /**
     * Returns the keyword part of a find command.
     *
     * @param input full find command
     * @return normalized keyword text
     */
    public String parseFindKeyword(String input) {
        String keyword = input.substring("find".length()).trim().toLowerCase();
        if (keyword.isEmpty()) {
            throw new ChotuException("Sir, please include a keyword. Example: find book");
        }
        return keyword;
    }
}
