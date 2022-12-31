package pl.altek.analyzer.module.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.altek.analyzer.openapi.api.AnalysisApiDelegate;
import pl.altek.analyzer.openapi.model.AnalysisAction;
import pl.altek.analyzer.openapi.model.AnalysisResponse;

@Service
public class AnalysisDelegate implements AnalysisApiDelegate {

    @Autowired
    private AnalysisService analysisService;

    @Override
    public ResponseEntity<AnalysisResponse> analysis(AnalysisAction body) {
        return ResponseEntity.ok(analysisService.analysis(body));
    }
}
