
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MemberDao {

private Connection connection;
private final String Get_Members_By_Team_Id_Query = "Select * from members Where team_Id = ?";
private final String DELETE_MEMBERS_BY_TEAM_ID_QUERY = "DELETE FROM members WHERE team_id = ?";
private final String CREATE_NEW_MEMBER_QUERY = "INSERT INTO members(first_name, last_name, team_id) VALUES(?,?,?)";
private final String DELLETE_MEMBER_BY_ID_QUERY = "DELETE FROM members WHERE id = ?";


public MemberDao(){
    connection = DBConnection.getConnection();
}

    public List<MemberEntity> getMembersByTeamId(int teamId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(Get_Members_By_Team_Id_Query);
        ps.setInt(1, teamId);
        ResultSet rs = ps.executeQuery();
        List<MemberEntity> members = new ArrayList<MemberEntity>();
        

        while(rs.next()){
            members.add(new MemberEntity(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        return members;
    }
    public void deleteMembersByTeamId(int teamId) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(DELETE_MEMBERS_BY_TEAM_ID_QUERY);
        ps.setInt(1, teamId);
        ps.executeUpdate();
    }
    public void createNewMember(String firstName, String lastName, int teamId) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(CREATE_NEW_MEMBER_QUERY);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setInt(3, teamId);
        ps.executeUpdate();

    }
    public void deleteMemberById(int id) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(DELLETE_MEMBER_BY_ID_QUERY);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
