package pl.altek.chessanalizer.module.game.analizer;

import lombok.Data;
import pl.altek.chessanalizer.db.domain.move.MoveRelationType;
import pl.altek.chessanalizer.db.domain.state.StateNode;

import java.util.function.Supplier;

@Data
class Context {
    StateNode state;
    String session;
    String move;
    Boolean isPlayerMove;

    public void toggleIsPlayerMove(){
        this.setIsPlayerMove(!this.getIsPlayerMove());
    }

    public void execIsPlayerMove(Runnable isPlayer, Runnable isEnemy){
        if(this.getIsPlayerMove()){
            isPlayer.run();
        }else {
            isEnemy.run();
        }
    }

    public <T> T execIsPlayerMove(Supplier<T> isPlayer, Supplier<T> isEnemy){
        if(this.getIsPlayerMove()){
            return isPlayer.get();
        }else {
            return isEnemy.get();
        }
    }

    public MoveRelationType getMoveRelationType(){
        if(getIsPlayerMove()){
            return MoveRelationType.PLAYER;
        }
        return MoveRelationType.ENEMY;
    }
}
