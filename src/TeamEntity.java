
import java.util.List;

public class TeamEntity {
    private int teamId;
    private String name;
    private List<MemberEntity> members;

    public TeamEntity(int teamId, String name, List<MemberEntity> members) {
        this.teamId = teamId;
        this.name = name;
        this.members = members;
    }

    public int getTeamId() {
        return this.teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MemberEntity> getMembers() {
        return this.members;
    }

    public void setMembers(List<MemberEntity> members) {
        this.members = members;
    }

}
