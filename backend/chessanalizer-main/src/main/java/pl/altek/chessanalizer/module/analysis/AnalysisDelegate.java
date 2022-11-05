package pl.altek.chessanalizer.module.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.altek.chessanalizer.openapi.api.AnalysisApiDelegate;
import pl.altek.chessanalizer.openapi.model.AnalysisAction;
import pl.altek.chessanalizer.openapi.model.AnalysisResponse;

@Service
public class AnalysisDelegate implements AnalysisApiDelegate {

    @Autowired
    private AnalysisService analysisService;

    @Override
    public ResponseEntity<AnalysisResponse> analysis(AnalysisAction body) {
        return ResponseEntity.ok(analysisService.analysis(body));
    }
}
