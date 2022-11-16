import { Module } from '@nestjs/common';
import { ServeStaticModule } from '@nestjs/serve-static';
import { join } from 'path';
import { ChessModule } from '../chess/chess.module';
import { SessionModule } from '../session/session.module';

@Module({
  imports: [
    ServeStaticModule.forRoot({
      rootPath: join(__dirname, '..', '..', 'static'),
    }),
    ChessModule,
    SessionModule,
  ],
})
export class AppModule {}
