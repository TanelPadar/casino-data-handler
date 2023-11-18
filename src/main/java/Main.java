import model.Casino;
import model.Match;
import model.Player;
import service.GameService;
import utils.FileHandler;
import java.io.IOException;
import java.util.*;


public class Main {
    private static final String OUTPUTFILE = "src/main/java/results.txt";
    private static final String PLAYERFILE = "player_data.txt";
    private static final String MATCHFILE = "match_data.txt";

    public static void main(String[] args) throws IOException {
        FileHandler fileHandler = new FileHandler();
        Casino casino = new Casino();
        List<String> playerData = fileHandler.getFileContent(PLAYERFILE);
        List<String> matchData = fileHandler.getFileContent(MATCHFILE);
        Map<String, Player> playerMap = new HashMap<>();
        Map<String, Match> matchMap = new HashMap<>();

        GameService.mapMatchFileData(matchData, matchMap);
        GameService.handlePlayerAction(playerData, playerMap, matchMap, casino);
        List<String> outputContent = GameService.getOutputContent(playerMap, casino);
        fileHandler.writeListToFile(OUTPUTFILE, outputContent);
    }

}
