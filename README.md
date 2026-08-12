# 📅 Agendador de Tarefas API

Microsservico de agendamento de tarefas desenvolvido com Java 17, Spring Boot 3 e MongoDB, com autenticacao JWT, integracao entre microsservicos via OpenFeign e Docker.

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.5
- Spring Security
- JWT Authentication (jjwt 0.12.6)
- MongoDB
- Spring Data MongoDB
- Spring Cloud OpenFeign
- MapStruct
- Docker
- Lombok
- Gradle
- GitHub Actions (CI)

---

## 📋 Funcionalidades

### 📝 Tarefas

- Criar nova tarefa com data de evento
- Listar tarefas do usuario autenticado
- Buscar tarefas agendadas por periodo
- Atualizar tarefa existente
- Deletar tarefa por ID
- Alterar status de notificacao (PENDENTE, NOTIFICADO, CANCELADO)

### 🔗 Integracao

- Comunicacao com microsservico de usuarios via **OpenFeign**
- Validacao de usuario por email antes de operacoes

---

## 🔐 Seguranca

- Spring Security com filtro JWT
- Sessao stateless
- Endpoints publicos: login e cadastro de usuario
- Demais endpoints protegidos por Bearer Token

---

## 🔑 Endpoints

| Metodo | Rota | Auth | Descricao |
|--------|------|------|-----------|
| `POST` | `/tarefas` | Sim | Criar nova tarefa |
| `GET` | `/tarefas` | Sim | Listar tarefas do usuario |
| `GET` | `/tarefas/eventos?dataInicial=&dataFinal=` | Sim | Buscar tarefas por periodo |
| `PUT` | `/tarefas?id=` | Sim | Atualizar tarefa |
| `DELETE` | `/tarefas?id=` | Sim | Deletar tarefa |
| `PATCH` | `/tarefas?status=&id=` | Sim | Alterar status da tarefa |

---

## 🐳 Docker

- **Dockerfile** multi-stage (build com Gradle + runtime com Eclipse Temurin 17)
- Porta: **8081**

### Executar localmente:

```bash
docker build -t agendador-tarefas .
docker run -p 8081:8081 agendador-tarefas
```

> **Nota:** Necessita de uma instancia MongoDB rodando em `localhost:27017`.

---

## 📂 Estrutura do Projeto

```
src/main
┣ controller         → Endpoints REST
┣ business           → Logica de negocio (TarefasService)
┣ business/dto       → DTOs com Java Records
┣ business/mapper    → Conversores MapStruct
┣ client             → Cliente Feign para microsservico de usuarios
┣ entity             → Entidade MongoDB (@Document)
┣ enums              → Status de notificacao
┣ repository         → Repositorio Spring Data MongoDB
┣ security           → Configuracao JWT e Spring Security
┗ execeptions        → Tratamento global de excecoes
```

---

## 📦 Como Executar Localmente

1. Clonar o projeto
```bash
git clone https://github.com/aureoandradedev/agendadores-tarefas.git
```

2. Entrar na pasta
```bash
cd agendadores-tarefas
```

3. Subir MongoDB (ex: via Docker)
```bash
docker run -d -p 27017:27017 --name mongo mongo:latest
```

4. Rodar a aplicacao
```bash
./gradlew bootRun
```

---

## 🏗️ Arquitetura

Este microsservico faz parte de um sistema distribuido:

```
BFF (porta 8083) → Agendador de Tarefas (porta 8081) → MongoDB
                 → Usuario (porta 8080) → PostgreSQL
                 → Notificacao (porta 8082)
```

---

## 👨‍💻 Autor

**Aureo Andrade**

- GitHub: [aureoandradedev](https://github.com/aureoandradedev)
- LinkedIn: [aureoandrade](https://www.linkedin.com/in/aureoandrade/)
