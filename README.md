# QR-Code-generator


📦 QR Code Generator

Aplicação desenvolvida em Java + Spring Boot para geração de QR Codes dinâmicos, com arquitetura hexagonal (Ports & Adapters).
Permite criar QR Codes a partir de textos/URLs e disponibilizá-los via API REST.

✨ Funcionalidades

📌 Geração de QR Code a partir de qualquer texto/URL.

🖼️ Retorno da imagem no formato PNG.

🔗 Endpoint para acessar a imagem gerada.

🧩 Arquitetura Hexagonal, fácil de expandir para outros storages (ex: AWS S3).

🐳 Suporte a Docker.

🛠️ Tecnologias Utilizadas

Java 21+

Spring Boot 4-M1

ZXing (biblioteca para geração de QR Codes)

Docker

Arquitetura Hexagonal (Ports & Adapters)

🗂️ Estrutura do Projeto
br.com.judev.qrcodegenerator
 ├── application
 │    └── service         # Regras de negócio (QrCodeGeneratorService)
 │
 ├── domain
 │    └── ports           # Interfaces (ex: StoragePort)
 │
 ├── infra
 │    └── storage         # Implementações concretas (ex: InMemoryStorage)
 │
 ├── dto                  # DTOs de request/response
 │
 ├── controller                  # Controllers REST

🚀 Como Rodar Localmente
Pré-requisitos

Java 21+

Maven

Rodando a aplicação
# Clonar o repositório
git clone https://github.com/SEU-USUARIO/qrcode-generator.git
cd qrcode-generator

# Rodar com Maven
./mvnw spring-boot:run


Aplicação disponível em:
👉 http://localhost:8080

🐳 Rodando com Docker
Build da imagem
docker build -t qrcode-generator .

Rodar o container
docker run -p 8080:8080 qrcode-generator

📡 Endpoints
1. Gerar QR Code
POST /api/qrcode
Content-Type: application/json


Body:

{
  "text": "https://github.com/Junior-Stranner/
}


Response:

{
  "url": "http://localhost:8080/storage/abc123.png",
  "fileName": "abc123.png",
  "format": "PNG",
  "width": 200,
  "height": 200
}

2. Acessar QR Code
GET /storage/{fileName}


Exemplo:

http://localhost:8080/storage/abc123.png


Retorna a imagem PNG diretamente.

🔮 Próximos Passos

 Adicionar persistência em AWS S3.

 Permitir múltiplos formatos de imagem (JPG, SVG).

 Configurar CI/CD para deploy automático.

👨‍💻 Autor

Desenvolvido por Heinz Stranner Junior
🔗 LinkedIn
 | GitHub


 
<img width="553" height="421" alt="meu-qrcode" src="https://github.com/user-attachments/assets/858d7003-9840-40ff-a40a-272f0337be61" />
