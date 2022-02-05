import { Body, Controller, Get, Param, Post } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { MoveDto } from 'src/model/move.dto';
import { ChessService } from './chess.service';

const API_NAME = 'chess';

@ApiTags(API_NAME)
@Controller(API_NAME)
export class ChessController {
  constructor(private readonly chessService: ChessService) {}

  @Get(':sessionId')
  getBoardAscii(@Param('sessionId') sessionId: string): string {
    return this.chessService.getBoardAscii(sessionId);
  }

  @Post('move')
  moveAction(@Body() moveAction: MoveDto) {
    this.chessService.move(moveAction);
  }

  @Get('fen/:sessionId')
  getFen(@Param('sessionId') sessionId: string): string {
    return this.chessService.generateFen(sessionId);
  }
}
