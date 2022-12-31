package pl.altek.analyzer.module.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import pl.altek.analyzer.common.Constants;
import pl.altek.analyzer.db.neo4j.model.Move;
import pl.altek.analyzer.db.neo4j.repository.MoveRepository;
import pl.altek.analyzer.module.user.UserService;
import pl.altek.analyzer.openapi.model.AnalysisAction;
import pl.altek.analyzer.openapi.model.AnalysisMoveEntry;
import pl.altek.analyzer.openapi.model.AnalysisResponse;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AnalysisService {

    @Autowired
    private UserService userService;

    @Autowired
    private MoveRepository moveRepository;

    public AnalysisResponse analysis(AnalysisAction body) {
        String hash = getHash(body.getFen());
        List<Move> moves = findMoves(hash, body.isIsPlayer());
        List<AnalysisMoveEntry> analysisMoveEntryList = mapMoveToAnalysisMoveEntry(moves);
        Long countMoves = countMoves(moves);

        AnalysisResponse response = new AnalysisResponse();
        response.setTotalGames(countMoves);
        response.setMoveList(analysisMoveEntryList);
        return response;
    }

    private List<Move> findMoves(String hash, Boolean isPlayer) {
        UUID currentUserId = userService.getCurrentUserId();
        if (isPlayer) {
            return moveRepository.findAllPlayerMoveByNodeAndUserId(hash, currentUserId);
        } else {
            return moveRepository.findAllEnemyMoveByNodeAndUserId(hash, currentUserId);
        }
    }

    private String getHash(String fen) {
        if (null == fen) {
            return Constants.INITIAL_HASH;
        }
        return DigestUtils.md5DigestAsHex(fen.getBytes(StandardCharsets.UTF_8));
    }

    private Long countMoves(List<Move> moveRelationList) {
        Long sum = 0L;
        for (Move moveRelation : moveRelationList) {
            sum += moveRelation.getQuantity();
        }
        return sum;
    }

    private List<AnalysisMoveEntry> mapMoveToAnalysisMoveEntry(List<Move> moveRelationList) {
        return moveRelationList.stream().map(x -> {
            AnalysisMoveEntry entry = new AnalysisMoveEntry();
            entry.setName(x.getName());
            entry.setWin(x.getWin().intValue());
            entry.setDraw(x.getDraw().intValue());
            entry.setLose(x.getLose().intValue());
            return entry;
        }).collect(Collectors.toList());
    }
}
