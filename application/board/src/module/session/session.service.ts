import { Injectable } from '@nestjs/common';
import { ChessInstance } from 'chess.js';
import { SessionDto } from 'src/model/session/session.dto';
import { SessionModel } from 'src/model/session/session.model';
import ISessionModel from '../../model/session/session.interface';

@Injectable()
export class SessionService {
  private sessions: { [key: string]: ISessionModel } = {};

  createSession(): string {
    const s: ISessionModel = new SessionModel();
    this.sessions[s.getId()] = s;
    return s.getId();
  }

  getSessionDto(sessionId: string): SessionDto {
    const session = this.sessions[sessionId];
    return session?.getDto();
  }

  getAllSessionDto(): SessionDto[] {
    return Object.values(this.sessions).map((x) => x.getDto());
  }

  getSession(sessionId: string): ISessionModel {
    return this.sessions[sessionId];
  }

  getGame(sessionId: string): ChessInstance {
    return this.getSession(sessionId).getChessGame();
  }

  deleteSession(sessionId: string) {
    delete this.sessions[sessionId];
  }
}
