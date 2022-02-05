import { ChessInstance } from 'chess.js';
import { SessionDto } from './session.dto';

export interface ISessionModel {
  getId(): string;
  getCreateDate(): Date;
  getChessGame(): ChessInstance;
  getDto(): SessionDto;
}

export default ISessionModel;
