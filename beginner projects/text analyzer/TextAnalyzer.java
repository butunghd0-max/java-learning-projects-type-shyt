import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzer {
    private static final Pattern WORD_PATTERN = Pattern.compile("\\b[\\p{L}\\p{N}']+\\b");
    private static final Pattern SENTENCE_PATTERN = Pattern.compile("[.!?]+");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Text Analyzer ===");
        System.out.print("Enter a block of text: ");
        String input = scanner.nextLine();

        int charCount = input.length();
        int charNoWhitespace = input.replaceAll("\\s+", "").length();
        int wordCount = countMatches(WORD_PATTERN, input);
        int sentenceCount = countMatches(SENTENCE_PATTERN, input);

        System.out.print("Enter a keyword to search (optional): ");
        String keyword = scanner.nextLine().trim();

        boolean keywordFound = false;
        int keywordOccurrences = 0;
        if (!keyword.isEmpty()) {
            keywordOccurrences = countKeywordOccurrences(input, keyword);
            keywordFound = keywordOccurrences > 0;
        }

        double averageWordLength = wordCount == 0 ? 0.0 : (double) charNoWhitespace / wordCount;
        String complexity = classifyComplexity(averageWordLength);

        System.out.println("\n--- Analysis Report ---");
        System.out.println("Total characters: " + charCount);
        System.out.println("Characters (excluding whitespace): " + charNoWhitespace);
        System.out.println("Total words: " + wordCount);
        System.out.println("Total sentences: " + sentenceCount);

        if (keyword.isEmpty()) {
            System.out.println("Keyword search: skipped");
        } else {
            System.out.println("Keyword: \"" + keyword + "\"");
            System.out.println("Keyword found: " + (keywordFound ? "Yes" : "No"));
            System.out.println("Keyword occurrences: " + keywordOccurrences);
        }

        System.out.printf("Average word length: %.2f%n", averageWordLength);
        System.out.println("Complexity estimate: " + complexity);

        scanner.close();
    }

    private static int countMatches(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private static int countKeywordOccurrences(String text, String keyword) {
        String escapedKeyword = Pattern.quote(keyword.toLowerCase());
        Pattern keywordPattern = Pattern.compile("\\b" + escapedKeyword + "\\b");
        Matcher matcher = keywordPattern.matcher(text.toLowerCase());

        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private static String classifyComplexity(double averageWordLength) {
        if (averageWordLength >= 7.0) {
            return "Complex";
        }
        if (averageWordLength >= 5.0) {
            return "Moderate";
        }
        return "Simple";
    }
}
