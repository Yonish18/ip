public class Parser {
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
}
