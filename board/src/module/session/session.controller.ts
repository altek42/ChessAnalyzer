import { Controller, Delete, Get, Param, Post } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { SessionDto } from 'src/model/session/session.dto';
import { SessionService } from './session.service';

const API_NAME = 'session';

@ApiTags(API_NAME)
@Controller(API_NAME)
export class SessionController {
  constructor(private readonly sessionService: SessionService) {}

  @Post()
  createSession(): string {
    return this.sessionService.createSession();
  }

  @Get(':sessionId')
  sessionInfo(@Param('sessionId') sessionId: string): SessionDto {
    return this.sessionService.getSessionDto(sessionId);
  }

  @Get()
  sessionInfoAll(): SessionDto[] {
    return this.sessionService.getAllSessionDto();
  }

  @Delete(':sessionId')
  deleteSession(@Param('sessionId') sessionId: string) {
    this.sessionService.deleteSession(sessionId);
  }
}
