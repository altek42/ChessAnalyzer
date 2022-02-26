import { Controller, Delete, Get, Param, Post } from '@nestjs/common';
import { ApiResponse, ApiTags } from '@nestjs/swagger';
import { SessionDto } from 'src/model/session/session.dto';
import { SessionService } from './session.service';

const API_NAME = 'session';

@ApiTags(API_NAME)
@Controller(API_NAME)
export class SessionController {
  constructor(private readonly sessionService: SessionService) {}

  @ApiResponse({
    status: 201,
    description: 'Create new session',
    type: String,
  })
  @Post()
  createSession(): string {
    return this.sessionService.createSession();
  }

  @ApiResponse({
    status: 200,
    description: 'Show session info',
    type: SessionDto,
  })
  @Get(':sessionId')
  sessionInfo(@Param('sessionId') sessionId: string): SessionDto {
    return this.sessionService.getSessionDto(sessionId);
  }

  @ApiResponse({
    status: 200,
    description: 'List of sessions',
    type: SessionDto,
    isArray: true,
  })
  @Get()
  sessionInfoAll(): SessionDto[] {
    return this.sessionService.getAllSessionDto();
  }

  @Delete(':sessionId')
  deleteSession(@Param('sessionId') sessionId: string) {
    this.sessionService.deleteSession(sessionId);
  }
}
