import { Injectable } from '@nestjs/common';
import { MoveDto } from 'src/model/move.dto';
import { SessionService } from '../session/session.service';

@Injectable()
export class ChessService {
  constructor(private readonly sessionService: SessionService) {}

  getBoardAscii(sessionId: string): string {
    const game = this.sessionService.getGame(sessionId);
    return game.ascii();
  }

  move(move: MoveDto) {
    const game = this.sessionService.getGame(move.sessionId);
    game.move(move.move);
  }

  generateFen(sessionId: string): string {
    const game = this.sessionService.getGame(sessionId);
    return game.fen();
  }
}
