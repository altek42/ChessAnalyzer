package pl.altek.chessanalizer.module.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import pl.altek.chessanalizer.common.Constants;
import pl.altek.chessanalizer.db.domain.move.MoveRelation;
import pl.altek.chessanalizer.db.domain.move.repository.MoveRelationRepository;
import pl.altek.chessanalizer.module.user.UserService;
import pl.altek.chessanalizer.openapi.model.AnalysisAction;
import pl.altek.chessanalizer.openapi.model.AnalysisMoveEntry;
import pl.altek.chessanalizer.openapi.model.AnalysisResponse;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AnalysisService {

    @Autowired
    private UserService userService;

    @Autowired
    private MoveRelationRepository moveRelationRepository;

    public AnalysisResponse analysis(AnalysisAction body) {
        String hash = getHash(body.getFen());
        List<MoveRelation> moves = findMoves(hash, body.isIsPlayer());
        List<AnalysisMoveEntry> analysisMoveEntryList = mapMoveToAnalysisMoveEntry(moves);
        Long countMoves = countMoves(moves);

        AnalysisResponse response = new AnalysisResponse();
        response.setTotalGames(countMoves);
        response.setMoveList(analysisMoveEntryList);
        return response;
    }

    private List<MoveRelation> findMoves(String hash, Boolean isPlayer) {
        UUID currentUserId = userService.getCurrentUserId();
        if (isPlayer) {
            return moveRelationRepository.findAllByNodeHashAndUserId(hash, currentUserId);
        } else {
            return moveRelationRepository.findAllByNodeHashAndNotUserId(hash, currentUserId);
        }
    }

    private String getHash(String fen) {
        if (null == fen) {
            return Constants.INITIAL_HASH;
        }
        return DigestUtils.md5DigestAsHex(fen.getBytes(StandardCharsets.UTF_8));
    }

    private Long countMoves(List<MoveRelation> moveRelationList) {
        Long sum = 0L;
        for (MoveRelation moveRelation : moveRelationList) {
            sum += moveRelation.getQuantity();
        }
        return sum;
    }

    private List<AnalysisMoveEntry> mapMoveToAnalysisMoveEntry(List<MoveRelation> moveRelationList) {
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
