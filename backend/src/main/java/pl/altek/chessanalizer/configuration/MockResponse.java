package pl.altek.chessanalizer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.altek.chessanalizer.openapi.client.chessapi.model.Game;
import pl.altek.chessanalizer.openapi.client.chessapi.model.GameList;
import pl.altek.chessanalizer.openapi.client.chessapi.model.GamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class MockResponse {

    @Bean("mockUserId")
    public UUID mockUserId() {
        return UUID.fromString("09014f5d-1f4b-4e7e-a64c-51d0938d3567");
    }

    @Bean("mockedGameList")
    public GameList mockedGameListResponse(){
        GameList gameList = new GameList();
        Game game1 = new Game();
        game1.setUrl("https://www.chess.com/game/live/6054698595");
        game1.setPgn("[Event \"Live Chess\"]\n[Site \"Chess.com\"]\n[Date \"2020.12.25\"]\n[Round \"-\"]\n[White \"Altek42\"]\n[Black \"Remouze\"]\n[Result \"0-1\"]\n[CurrentPosition \"1k6/1p5p/p5q1/8/2N5/1P6/P1KP3P/8 w - -\"]\n[Timezone \"UTC\"]\n[ECO \"C28\"]\n[ECOUrl \"https://www.chess.com/openings/Vienna-Game-Falkbeer-Vienna-Gambit\"]\n[UTCDate \"2020.12.25\"]\n[UTCTime \"13:04:49\"]\n[WhiteElo \"805\"]\n[BlackElo \"983\"]\n[TimeControl \"1200\"]\n[Termination \"Remouze won by resignation\"]\n[StartTime \"13:04:49\"]\n[EndDate \"2020.12.25\"]\n[EndTime \"13:37:58\"]\n[Link \"https://www.chess.com/game/live/6054698595\"]\n\n1. e4 {[%clk 0:19:57.8]} 1... e5 {[%clk 0:19:58.4]} 2. Nc3 {[%clk 0:19:54.8]} 2... Nf6 {[%clk 0:19:52.3]} 3. f4 {[%clk 0:19:47.9]} 3... exf4 {[%clk 0:19:47.6]} 4. e5 {[%clk 0:19:46.3]} 4... Nh5 {[%clk 0:19:31.9]} 5. Qxh5 {[%clk 0:19:42.9]} 5... g6 {[%clk 0:18:56.4]} 6. Qg4 {[%clk 0:19:04.9]} 6... g5 {[%clk 0:18:37.5]} 7. Nf3 {[%clk 0:18:48.6]} 7... d6 {[%clk 0:18:26.8]} 8. Qxg5 {[%clk 0:18:06.2]} 8... Qe7 {[%clk 0:18:08.2]} 9. Bb5+ {[%clk 0:16:50.9]} 9... Bd7 {[%clk 0:17:53.6]} 10. Nd5 {[%clk 0:16:41.6]} 10... Qd8 {[%clk 0:17:01.5]} 11. Qf6 {[%clk 0:14:51.4]} 11... Rg8 {[%clk 0:16:10.8]} 12. b3 {[%clk 0:12:53]} 12... c6 {[%clk 0:15:06.6]} 13. Qxd8+ {[%clk 0:09:06.8]} 13... Kxd8 {[%clk 0:14:59.7]} 14. Bc4 {[%clk 0:08:27.9]} 14... cxd5 {[%clk 0:14:57.3]} 15. Bxd5 {[%clk 0:08:26.5]} 15... Rxg2 {[%clk 0:14:37.6]} 16. Ba3 {[%clk 0:08:09.8]} 16... Kc7 {[%clk 0:14:00.6]} 17. O-O-O {[%clk 0:07:37.9]} 17... Bc6 {[%clk 0:13:21.4]} 18. c4 {[%clk 0:07:00.8]} 18... Bxd5 {[%clk 0:13:07.8]} 19. cxd5 {[%clk 0:06:57.9]} 19... Nd7 {[%clk 0:12:24.4]} 20. Kb1 {[%clk 0:06:18]} 20... dxe5 {[%clk 0:10:45.4]} 21. Rc1+ {[%clk 0:06:09.8]} 21... Bc5 {[%clk 0:10:18.8]} 22. Nxe5 {[%clk 0:05:46.9]} 22... Rd8 {[%clk 0:08:45.8]} 23. Nxf7 {[%clk 0:05:26.6]} 23... Re8 {[%clk 0:08:22.6]} 24. Bxc5 {[%clk 0:04:50.4]} 24... Nxc5 {[%clk 0:08:09.5]} 25. Rxc5+ {[%clk 0:04:45.6]} 25... Kb8 {[%clk 0:08:06.7]} 26. Rhc1 {[%clk 0:04:41.7]} 26... f3 {[%clk 0:07:13.6]} 27. Nd6 {[%clk 0:04:36.2]} 27... a6 {[%clk 0:04:51.9]} 28. Rc8+ {[%clk 0:04:20]} 28... Rxc8 {[%clk 0:04:46.3]} 29. Nxc8 {[%clk 0:04:16.1]} 29... f2 {[%clk 0:04:36.4]} 30. Nb6 {[%clk 0:04:01.1]} 30... Rg1 {[%clk 0:04:33.8]} 31. d6 {[%clk 0:03:32.2]} 31... Rxc1+ {[%clk 0:04:30.5]} 32. Kxc1 {[%clk 0:03:30.4]} 32... f1=Q+ {[%clk 0:04:27.9]} 33. Kb2 {[%clk 0:03:28]} 33... Qf6+ {[%clk 0:04:23.7]} 34. Kc2 {[%clk 0:03:14.8]} 34... Qxd6 {[%clk 0:04:19.4]} 35. Nc4 {[%clk 0:03:06.8]} 35... Qg6+ {[%clk 0:04:02.4]} 0-1\n");
        game1.setTimeControl("1200");
        game1.setEndTime(1608903478);
        game1.setRated(true);
        game1.setTcn("mC0Kbs!TnDKDCKTNdN2UNEUMgvZREM70fH6ZsJ07MT?!jrYQT787HAQJAJ!ocq7YecZQkAQJAJ5ZcbRKdc9IvK47K178qIZIcIY5hcDv1RWOI686R6vn6PogJRgcbcn~cjfTjkTRPARU");
        game1.setUuid(UUID.fromString("bffc239a-46b1-11eb-9c8f-0069e4010001"));
        game1.setInitialSetup("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        game1.setFen("1k6/1p5p/p5q1/8/2N5/1P6/P1KP3P/8 w - -");
        game1.setTimeClass("rapid");
        game1.setRules("chess");

        GamePlayer player1 = new GamePlayer();
        player1.setRating(805);
        player1.setResult("resigned");
        player1.setAtId("https://api.chess.com/pub/player/altek42");
        player1.setUsername("Altek42");
        player1.setUuid(UUID.fromString("d15b424c-2726-11eb-9577-df5e0a04388a"));

        GamePlayer player2 = new GamePlayer();
        player2.setRating(983);
        player2.setResult("win");
        player2.setAtId("https://api.chess.com/pub/player/remouze");
        player2.setUsername("Remouze");
        player2.setUuid(UUID.fromString("8012ad4c-291c-11eb-970b-e5f348592065"));

        game1.setWhite(player1);
        game1.setBlack(player2);

        Game game2 = new Game();
        game2.setUrl("https://www.chess.com/game/live/6187081399");
        game2.setPgn("[Event \"Live Chess\"]\n[Site \"Chess.com\"]\n[Date \"2021.01.09\"]\n[Round \"-\"]\n[White \"Rovhatt\"]\n[Black \"Altek42\"]\n[Result \"1-0\"]\n[CurrentPosition \"r1b1k2r/pppn2p1/1b2p2p/7q/3P4/2P2PPB/PP2Q2P/RN2K1NR w KQkq -\"]\n[Timezone \"UTC\"]\n[ECO \"C00\"]\n[ECOUrl \"https://www.chess.com/openings/French-Defense\"]\n[UTCDate \"2021.01.09\"]\n[UTCTime \"15:24:32\"]\n[WhiteElo \"871\"]\n[BlackElo \"818\"]\n[TimeControl \"600\"]\n[Termination \"Rovhatt won by resignation\"]\n[StartTime \"15:24:32\"]\n[EndDate \"2021.01.09\"]\n[EndTime \"15:28:38\"]\n[Link \"https://www.chess.com/game/live/6187081399\"]\n\n1. e4 {[%clk 0:10:00]} 1... e6 {[%clk 0:09:59]} 2. c3 {[%clk 0:09:55.1]} 2... d5 {[%clk 0:09:49.2]} 3. f3 {[%clk 0:09:37.7]} 3... Nf6 {[%clk 0:09:32.2]} 4. Qe2 {[%clk 0:09:20.3]} 4... Bc5 {[%clk 0:09:03.3]} 5. d4 {[%clk 0:09:15.7]} 5... Bb6 {[%clk 0:08:46.6]} 6. Bg5 {[%clk 0:09:14.1]} 6... h6 {[%clk 0:08:34.6]} 7. Bxf6 {[%clk 0:09:07.8]} 7... Qxf6 {[%clk 0:08:25.4]} 8. exd5 {[%clk 0:08:58.9]} 8... Qh4+ {[%clk 0:08:09.2]} 9. g3 {[%clk 0:08:56]} 9... Qh5 {[%clk 0:07:45.9]} 10. Bh3 {[%clk 0:08:52.8]} 10... Nd7 {[%clk 0:07:20.6]} 11. dxe6 {[%clk 0:08:50.4]} 11... fxe6 {[%clk 0:07:18.9]} 1-0\n");
        game2.setTimeControl("600");
        game2.setEndTime(1610206118);
        game2.setRated(true);
        game2.setTcn("mC0SksZJnv!Tdm9IlBIPcM3VMT7TCJTFowFNfx5ZJS1S");
        game2.setUuid(UUID.fromString("c364f884-528e-11eb-bc90-0069e4010001"));
        game2.setInitialSetup("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        game2.setFen("r1b1k2r/pppn2p1/1b2p2p/7q/3P4/2P2PPB/PP2Q2P/RN2K1NR w KQkq -");
        game2.setTimeClass("rapid");
        game2.setRules("chess");

        GamePlayer player3 = new GamePlayer();
        player3.setRating(871);
        player3.setResult("win");
        player3.setAtId("https://api.chess.com/pub/player/rovhatt");
        player3.setUsername("Rovhatt");
        player3.setUuid(UUID.fromString("cc533c98-1941-11eb-8b38-937ac2ab51c4"));

        GamePlayer player4 = new GamePlayer();
        player4.setRating(818);
        player4.setResult("resigned");
        player4.setAtId("https://api.chess.com/pub/player/altek42");
        player4.setUsername("Altek42");
        player4.setUuid(UUID.fromString("d15b424c-2726-11eb-9577-df5e0a04388a"));

        game2.setWhite(player3);
        game2.setBlack(player4);

        List<Game> gameList1 = new ArrayList<>();
        gameList1.add(game1);
        gameList1.add(game2);
        gameList.setGames(gameList1);
        return gameList;
    }
}
