import { Module } from '@nestjs/common';
import { SessionModule } from '../session/session.module';
import { ChessController } from './chess.controller';
import { ChessService } from './chess.service';

@Module({
  controllers: [ChessController],
  providers: [ChessService],
  imports: [SessionModule],
})
export class ChessModule {}
