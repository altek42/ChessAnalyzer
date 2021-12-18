package pl.altek.chessanalizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.altek.chessanalizer.entity.MoveRelation;
import pl.altek.chessanalizer.entity.StateNode;
import pl.altek.chessanalizer.enumerate.Player;
import pl.altek.chessanalizer.repository.StateRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public String test(StateRepository stateRepository) {
		StateNode node = new StateNode();
		node.setFen("abc123/asd312");
		node.setHash("sdjkfhiuwerfis");
		node.setNextPlayer(Player.BLACK);
		stateRepository.save(node);

		MoveRelation moveRelation = new MoveRelation();
		moveRelation.setState(node);
		moveRelation.setName("d4");
		moveRelation.setQuantity(2L);
		List<MoveRelation> moves = new ArrayList<>() ;
		moves.add(moveRelation);

		StateNode node2 = new StateNode();
		node2.setFen("angoidrng/sdfjos");
		node2.setHash("goirmboifm");
		node2.setNextPlayer(Player.WHITE);
		node2.setMoves(moves);
		stateRepository.save(node2);

		System.out.println("TEST");
		return "OK";
	}

	@Bean
	public String tSt(String test){
		return test;
	}
}
