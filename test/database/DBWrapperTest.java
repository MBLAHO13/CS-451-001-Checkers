package database;

import game.Game;
import main.ServerMain;
import network.Utils;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by rachelgoeken on 8/18/16.
 */
public class DBWrapperTest {
    public static final String TEST_GAME_NAME = "UNIT_TEST_GAME_UNIT_TEST_GAME";
    public static final String TEST_USER_ONE = "UNIT_TEST_USER1_UNIT_TEST_USER1_UNIT_TEST_USER1";
    public static final String TEST_USER_TWO = "UNIT_TEST_USER2_UNIT_TEST_USER2_UNIT_TEST_USER2";
    Game game_test = new Game(TEST_GAME_NAME, TEST_USER_ONE, TEST_USER_TWO);


    @BeforeClass
    public static void init() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        DBWrapper.ip = "98.114.253.148";
        ServerMain.testConnectDB();
    }
    @Test
    public void Game() {
        DBWrapper.saveGame(game_test);
        Game pulled_game = DBWrapper.getGame(TEST_GAME_NAME);
        // cursory check to see if we got back good data
        assertEquals(pulled_game.getDisk(1), game_test.getDisk(1));
        DBWrapper.deleteGame(TEST_GAME_NAME);
    }

    @Test
    public void getPublicGames() throws Exception {

    }

    @Test
    public void getPrivateGames() throws Exception {

    }

    @Test
    public void getGames() throws Exception {

    }

    @Test
    public void User() throws Exception {
        String salt = Utils.generateSalt();
        String hash = Utils.hash("fksdjfkldsjfkldsjfkljdsf", salt);
        String token = "fdkfkdsjfdf"; //whatever lawl
        Credentials creds = new Credentials(TEST_USER_ONE, salt, hash);
        DBWrapper.saveUser(creds);
        //can we pull out the user by token
        String retrievedUser = DBWrapper.getUserByToken(token);
        assertNotNull(retrievedUser);
        assertEquals(TEST_USER_ONE, retrievedUser);
        // can we pull out the user by username
        Credentials retrievedCreds = DBWrapper.getUser(TEST_USER_ONE);
        assertEquals(retrievedCreds.getHash(), creds.getHash());


    }

    @Test
    public void saveGame() throws Exception {

    }

    @Test
    public void saveUser() throws Exception {

    }

}