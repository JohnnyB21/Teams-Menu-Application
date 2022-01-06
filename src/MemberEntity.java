

public class MemberEntity {
    private int memberId;
    private String firstName;
    private String lastName;

//constructor
public MemberEntity(int memberId, String firstName, String lastName){
    this.memberId = memberId;
    this.firstName = firstName;
    this.lastName = lastName;
}


    public int getMemberId() {
        return this.memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
