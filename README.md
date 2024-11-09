
# Votação

API desenvolvida para o processo seletivo Sicredi, em parceria com a DB.

---

## Requisitos Mínimos

Para executar a aplicação **fora do ambiente de containers**, é necessário ter instalado:

- **Java** - versão 17
- **PostgreSQL** - versão 12

Para executar a aplicação **dentro de containers**, é necessário apenas ter o **Docker** instalado.

---

## Instruções de Uso

### Configuração do Ambiente

A aplicação utiliza um arquivo **.env** para facilitar a escalabilidade e garantir a segurança das configurações sensíveis.

1. Crie o arquivo `.env` no diretório principal do projeto (exemplo: `~/DesafioVotacao/.env`) com as seguintes propriedades:

   | Variável           | Descrição                                                                                  |
   |--------------------|--------------------------------------------------------------------------------------------|
   | `DB_PORT`          | Porta do banco de dados                                                                    |
   | `DB_NAME`          | Nome do banco de dados                                                                     |
   | `DB_USER`          | Nome do usuário do banco de dados                                                          |
   | `DB_PASS`          | Senha do banco de dados                                                                    |
   | `DB_HOST`          | IP do banco de dados (se estiver executando a aplicação no container, deve ser `postgres`) |
   | `APPLICATION_PORT` | Porta da aplicação                                                                         |

2. Após a criação do arquivo com as variáveis necessárias, execute o seguinte comando para subir os containers:

   ```bash
   docker-compose up -d
   ```

### Testes de Funcionalidade

Para facilitar o teste das funcionalidades da API, você pode importar a collection do **Postman**:

- **Arquivo**: `desafio-votacao.postman_collection.json`
- **Localização**: Diretório principal do projeto

Essa collection já contém as rotas e exemplos de requisições para facilitar os testes.

---
