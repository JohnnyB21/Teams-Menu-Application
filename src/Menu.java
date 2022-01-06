import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Menu
 */
public class Menu {
        private TeamDao teamDao = new TeamDao();
        private MemberDao memberDao = new MemberDao();
        private Scanner scanner = new Scanner(System.in);
        private List<String> options = Arrays.asList("Display Teams", "Display a Team",
         "Create Team", "Delete Team", "Create Team Member", "Delete Team Member");
    public void start(){
        String selection = "";

        do {
            printMenu();
            selection = scanner.nextLine();
        try{
            if(selection.equals("1")){
                displayTeams();
            }
            else if(selection.equals("2")){
                displayTeam();
            }
            else if(selection.equals("3")){
                createTeam();
            }
            else if(selection.equals("4")){
                deleteTeam();
            }
            else if(selection.equals("5")){
                createMember();
            }
            else if(selection.equals("6")){
                deleteMember();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
            
            System.out.println("Press enter to continue....");
            scanner.nextLine();
            

        }
        while(!selection.equals("-1"));

        
    }
    private void deleteMember() throws SQLException {
        System.out.println("Enter member id to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        memberDao.deleteMemberById(id);

    }

    private void createMember() throws SQLException {
        System.out.print("Enter first name of new member: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name of new memeber: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter team id of new memeber: ");
        int teamId = Integer.parseInt(scanner.nextLine());
        memberDao.createNewMember(firstName, lastName, teamId);

    }

    private void deleteTeam() throws SQLException {
        System.out.println("Enter in team id to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        teamDao.deleteTeamById(id);
    
    }

    private void createTeam() throws SQLException {
        System.out.print("Enter new team name: ");
        String teamName = scanner.nextLine();
        teamDao.createNewTeam(teamName);
    }
    private void displayTeam() throws SQLException {
        System.out.println("Enter team id: ");
        int id = Integer.parseInt(scanner.nextLine());
        TeamEntity team = teamDao.getTeamById(id);
        System.out.println(team.getTeamId() + ": " + team.getName());
        for(MemberEntity member : team.getMembers()) {
            System.out.println("\tMember Id: " + member.getMemberId() + " - Name: "
            + member.getFirstName() + " " + member.getLastName());
        }
    }
    private void displayTeams() throws SQLException {
        List<TeamEntity> teams = teamDao.getTeams();
        for(TeamEntity team : teams) {
            System.out.println(team.getTeamId() + ": " + team.getName());
        }
    }
    private void printMenu(){
        System.out.println("Select an Option:\n-----------------");
        for(int i = 0; i < options.size(); i++){
            System.out.println(i + 1 + ") " + options.get(i));
        }
    }
}