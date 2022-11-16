import { NestFactory } from '@nestjs/core';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
import { AppModule } from './module/app/app.module';
import * as yaml from 'yaml';
import * as fs from 'fs';
import { join } from 'path';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  const config = new DocumentBuilder()
    .setTitle('Board API')
    .setDescription('The board API description')
    .setVersion('1.0')
    .build();
  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('', app, document);

  const yamlString: string = yaml.stringify(document);
  fs.mkdirSync(join(__dirname, 'static'), { recursive: true });
  fs.writeFileSync(join(__dirname, 'static', 'doc.yaml'), yamlString);

  await app.listen(3000);
}
bootstrap();
