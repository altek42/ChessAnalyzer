import { Module } from '@nestjs/common';
import { ServeStaticModule } from '@nestjs/serve-static';
import { join } from 'path';
import { ChessModule } from '../chess/chess.module';
import { SessionModule } from '../session/session.module';
import { ScheduleModule } from '@nestjs/schedule';

@Module({
  imports: [
    ServeStaticModule.forRoot({
      rootPath: join(__dirname, '..', '..', 'static'),
    }),
    ChessModule,
    SessionModule,
    ScheduleModule.forRoot(),
  ],
})
export class AppModule {}
