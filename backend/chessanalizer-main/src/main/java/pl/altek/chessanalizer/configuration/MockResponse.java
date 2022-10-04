package pl.altek.chessanalizer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.Game;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.GameList;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.GamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class MockResponse {

    @Bean("mockDbUserId")
    public UUID mockDbUserId() {
        return UUID.fromString("09014f5d-1f4b-4e7e-a64c-51d0938d3567");
    }
    @Bean("mockUserId")
    public UUID mockUserId() {
        return UUID.fromString("d15b424c-2726-11eb-9577-df5e0a04388a");
    }

    @Bean("mockedGameList")
    public GameList mockedGameListResponse(){
        GameList gameList = new GameList();
        Game game1 = new Game();
        game1.setUrl("https://www.chess.com/game/live/6054698595");
        game1.setPgn("[Event \"Live Chess\"]\n[Site \"Chess.com\"]\n[Date \"2021.01.03\"]\n[Round \"-\"]\n[White \"Ladi3sman\"]\n[Black \"Altek42\"]\n[Result \"1-0\"]\n[CurrentPosition \"2kr4/p1p1np1p/1pn5/2N1p1p1/6P1/N1P1BP1P/PP2BR2/R3K3 b - -\"]\n[Timezone \"UTC\"]\n[ECO \"B01\"]\n[ECOUrl \"https://www.chess.com/openings/Scandinavian-Defense\"]\n[UTCDate \"2021.01.03\"]\n[UTCTime \"13:24:21\"]\n[WhiteElo \"825\"]\n[BlackElo \"825\"]\n[TimeControl \"600\"]\n[Termination \"Ladi3sman won by resignation\"]\n[StartTime \"13:24:21\"]\n[EndDate \"2021.01.03\"]\n[EndTime \"13:32:22\"]\n[Link \"https://www.chess.com/game/live/6131469212\"]\n\n1. e4 {[%clk 0:09:56.7]} 1... d5 {[%clk 0:09:57]} 2. d3 {[%clk 0:09:50]} 2... dxe4 {[%clk 0:09:49.6]} 3. dxe4 {[%clk 0:09:44]} 3... Qxd1+ {[%clk 0:09:44.9]} 4. Kxd1 {[%clk 0:09:41.8]} 4... Bg4+ {[%clk 0:09:38.5]} 5. f3 {[%clk 0:09:39.8]} 5... Bh5 {[%clk 0:09:29]} 6. g4 {[%clk 0:09:37.1]} 6... Bg6 {[%clk 0:09:22.3]} 7. h3 {[%clk 0:09:35.7]} 7... e5 {[%clk 0:09:12.4]} 8. Rh2 {[%clk 0:09:34.6]} 8... Nc6 {[%clk 0:09:06.2]} 9. c3 {[%clk 0:09:32.2]} 9... O-O-O+ {[%clk 0:08:56.4]} 10. Ke1 {[%clk 0:09:16.5]} 10... Bc5 {[%clk 0:08:42.3]} 11. Ne2 {[%clk 0:09:11.4]} 11... Nge7 {[%clk 0:08:15.5]} 12. Na3 {[%clk 0:09:08.3]} 12... Rd3 {[%clk 0:07:47]} 13. Rf2 {[%clk 0:08:30.6]} 13... Rhd8 {[%clk 0:07:35.9]} 14. Ng3 {[%clk 0:07:45.8]} 14... Re3+ {[%clk 0:07:26]} 15. Be2 {[%clk 0:07:37.5]} 15... Bxe4 {[%clk 0:05:53]} 16. Nxe4 {[%clk 0:07:20.8]} 16... g5 {[%clk 0:05:42.8]} 17. Nxc5 {[%clk 0:07:11.3]} 17... b6 {[%clk 0:05:20.4]} 18. Bxe3 {[%clk 0:06:57.6]} 1-0\n");
        game1.setTimeControl("1200");
        game1.setEndTime(1608903478);
        game1.setRated(true);
        game1.setTcn("mC0Kbs!TnDKDCKTNdN2UNEUMgvZREM70fH6ZsJ07MT?!jrYQT787HAQJAJ!ocq7YecZQkAQJAJ5ZcbRKdc9IvK47K178qIZIcIY5hcDv1RWOI686R6vn6PogJRgcbcn~cjfTjkTRPARU");
        game1.setUuid(UUID.fromString("bffc239a-46b1-11eb-9c8f-0069e4010001"));
        game1.setInitialSetup("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        game1.setFen("2kr4/p1p1np1p/1pn5/2N1p1p1/6P1/N1P1BP1P/PP2BR2/R3K3 b - -");
        game1.setTimeClass("rapid");
        game1.setRules("chess");

        GamePlayer player1 = new GamePlayer();
        player1.setRating(805);
        player1.setResult("win");
        player1.setAtId("https://api.chess.com/pub/player/Ladi3sman");
        player1.setUsername("Ladi3sman");
        player1.setUuid(UUID.fromString("a53c59ea-1dc0-11eb-99b9-b51bcac03fff"));

        GamePlayer player2 = new GamePlayer();
        player2.setRating(983);
        player2.setResult("resigned");
        player2.setAtId("https://api.chess.com/pub/player/altek42");
        player2.setUsername("Altek42");
        player2.setUuid(UUID.fromString("d15b424c-2726-11eb-9577-df5e0a04388a"));

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
//        gameList1.add(game2);
        gameList.setGames(gameList1);
        return gameList;
    }
}
