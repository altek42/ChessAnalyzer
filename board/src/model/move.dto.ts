import { ApiProperty } from '@nestjs/swagger';

export class MoveDto {
  @ApiProperty()
  sessionId: string;
  @ApiProperty()
  move: string;
}
