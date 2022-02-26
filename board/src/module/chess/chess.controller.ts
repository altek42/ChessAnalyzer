import { Body, Controller, Get, Param, Post } from '@nestjs/common';
import { ApiResponse, ApiTags } from '@nestjs/swagger';
import { MoveDto } from 'src/model/move.dto';
import { ChessService } from './chess.service';

const API_NAME = 'chess';

@Controller(API_NAME)
export class ChessController {
  constructor(private readonly chessService: ChessService) {}

  @ApiTags(API_NAME)
  @ApiResponse({
    status: 200,
    description: 'Get board status in ascii',
    type: String,
  })
  @Get(':sessionId')
  getBoardAscii(@Param('sessionId') sessionId: string): string {
    return this.chessService.getBoardAscii(sessionId);
  }

  @ApiTags(API_NAME)
  @Post('move')
  moveAction(@Body() moveAction: MoveDto) {
    this.chessService.move(moveAction);
  }

  @ApiTags(API_NAME)
  @ApiResponse({
    status: 200,
    description: 'Get fen from actual board state',
    type: String,
  })
  @Get('fen/:sessionId')
  getFen(@Param('sessionId') sessionId: string): string {
    return this.chessService.generateFen(sessionId);
  }
}
