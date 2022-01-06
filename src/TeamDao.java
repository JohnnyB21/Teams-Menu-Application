
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {
    private MemberDao memberDao;
    private Connection connection;
    private final String Get_Teams_Query = "Select * from teams";
    private final String Get_Team_By_Id_Query = "Select * from teams Where id = ?";
    private final String Create_New_Team_QUERY = "INSERT INTO teams(name) VALUES(?)";
    private final String DELETE_TEAM_BY_ID_QUERY = "DELETE FROM teams WHERE id = ?";

    public TeamDao(){
        connection = DBConnection.getConnection();
        memberDao = new MemberDao();
    }
    public List<TeamEntity> getTeams() throws SQLException{
        ResultSet rs = connection.prepareStatement(Get_Teams_Query).executeQuery();
        List<TeamEntity> teams = new ArrayList<TeamEntity>();

        while (rs.next()){
            teams.add(populateTeam(rs.getInt(1), rs.getString(2)));
        }
        return teams;
    }
    public TeamEntity getTeamById(int id) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(Get_Team_By_Id_Query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return populateTeam(rs.getInt(1), rs.getString(2));
    }
    public void createNewTeam(String teamName) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(Create_New_Team_QUERY);
        ps.setString(1, teamName);
        ps.executeUpdate();
        
        
    }

    public void deleteTeamById(int id) throws SQLException{
        memberDao.deleteMembersByTeamId(id);
        PreparedStatement ps = connection.prepareStatement(DELETE_TEAM_BY_ID_QUERY);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    private TeamEntity populateTeam(int teamId, String name) throws SQLException {
        return new TeamEntity(teamId, name, memberDao.getMembersByTeamId(teamId));
    }
}
