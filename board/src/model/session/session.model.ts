import ISessionModel from './session.interface';
import { v4 as uuidV4 } from 'uuid';
import { Chess, ChessInstance } from 'chess.js';
import { SessionDto } from './session.dto';

export class SessionModel implements ISessionModel {
  private readonly id: string;
  private readonly createDate: Date;
  private chessGame: ChessInstance;

  constructor() {
    this.id = uuidV4();
    this.createDate = new Date();
    this.chessGame = new Chess();
  }
  getChessGame(): ChessInstance {
    return this.chessGame;
  }
  getDto(): SessionDto {
    const dto = new SessionDto();
    dto.id = this.getId();
    dto.createDate = this.getCreateDate();
    return dto;
  }
  getId(): string {
    return this.id;
  }
  getCreateDate(): Date {
    return this.createDate;
  }
}
