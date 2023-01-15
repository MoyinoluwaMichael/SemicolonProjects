package christmassSnacks.sevenSegmentDisplay;

public class SevenSegmentValidation {
    public static void validateArg(String binaryNumbers) {
        validateLength(binaryNumbers);
        validateBinaries(binaryNumbers);
    }

    private static void validateBinaries(String binaryNumbers) {
        for (int count = 0; count < binaryNumbers.length(); count++) {
            String index = String.valueOf(binaryNumbers.charAt(count));
            if (!index.equals("1") && !index.equals("0")) {
                throw new IllegalArgumentException(
                        "Binary numbers must be 1's or 0's");
            }
        }
    }

    private static void validateLength(String binaryNumbers) {
        if (binaryNumbers.length() != 8) {
            throw new IllegalArgumentException(
                    "Binary numbers must be of length 8. Please try again.");
        }
    }

}
