public class Student extends Member {

    // Constructor
    public Student(String memberId, String name) {
        super(memberId, name);
    }

    // Define student's borrowing limit
    @Override
    public int getBorrowLimit() {
        return 3;
    }
}