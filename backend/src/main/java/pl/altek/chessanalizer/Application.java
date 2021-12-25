package pl.altek.chessanalizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean("myTest")
//	public String myTest(PlayerApi playerApi){
//		GameList gameList = playerApi.playerGameMonthlyArchive("altek42", 2021, 10);
//		Game game = gameList.getGames().get(0);
//		return game.getFen();
//	}
//
//	@Bean
//	public String startTest(@Qualifier("myTest") String myTest){
//		return myTest;
//	}

//	@Bean
//	public String test(StateRepository stateRepository) {
//		StateNode node = new StateNode();
//		node.setFen("abc123/asd312");
//		node.setHash("sdjkfhiuwerfis");
//		node.setNextPlayer(Player.BLACK);
//		stateRepository.save(node);
//
//		MoveRelation moveRelation = new MoveRelation();
//		moveRelation.setState(node);
//		moveRelation.setName("d4");
//		moveRelation.setQuantity(2L);
//		List<MoveRelation> moves = new ArrayList<>() ;
//		moves.add(moveRelation);
//
//		StateNode node2 = new StateNode();
//		node2.setFen("angoidrng/sdfjos");
//		node2.setHash("goirmboifm");
//		node2.setNextPlayer(Player.WHITE);
//		node2.setMoves(moves);
//		stateRepository.save(node2);
//
//		System.out.println("TEST");
//		return "OK";
//	}

}
