package christmassSnacks.sevenSegmentDisplay;

public class SevenSegmentDisplayTest {
    public static void main(String[] args) {
        try {
            SevenSegmentDisplay.display();
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

}
