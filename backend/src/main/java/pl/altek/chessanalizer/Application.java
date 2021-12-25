package pl.altek.chessanalizer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.altek.chessanalizer.db.entity.UserEntity;
import pl.altek.chessanalizer.repository.UserRepository;

import java.util.UUID;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean("myTest")
//	public String myTest(UserRepository userRepository){
//		UserEntity user = new UserEntity();
//		user.setUsername("Altek42");
//		user.setEmail("altek42.altek42@gmail.com");
//		user.setChessId(UUID.fromString("d15b424c-2726-11eb-9577-df5e0a04388a"));
//		userRepository.save(user);
//		return "OK";
//	}
//
//	@Bean
//	public String startTest(@Qualifier("myTest") String myTest){
//		return myTest;
//	}

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
