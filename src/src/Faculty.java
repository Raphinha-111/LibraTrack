public class Faculty extends Member {

    // Constructor
    public Faculty(String memberId, String name) {
        super(memberId, name);
    }

    // Define faculty borrowing limit
    @Override
    public int getBorrowLimit() {
        return 5;
    }
}