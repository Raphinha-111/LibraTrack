public abstract class Member {

    // 1. Private fields
    private String memberId;
    private String name;

    // 2. Constructor
    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    // 3. Getters
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    // 4. Abstract method for borrowing limit
    public abstract int getBorrowLimit();

}