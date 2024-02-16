# MandaCaru Broker API

## Descrição
A Mandacaru Broker API é uma solução robusta e flexível desenvolvida com o framework Spring Boot, projetada para facilitar a gestão eficiente de informações relacionadas a ações no mercado financeiro. Esta API oferece um conjunto abrangente de operações CRUD (Create, Read, Update, Delete), permitindo aos usuários criar, consultar, atualizar e remover dados sobre ações de forma simples e intuitiva. Ideal para desenvolvedores de software financeiro, plataformas de negociação de ações e analistas de mercado, a Mandacaru Broker API é a ferramenta perfeita para quem busca integrar funcionalidades de gerenciamento de ações em suas aplicações com facilidade e eficiência.


## Recursos

### Listar Todas as Ações
Retorna uma lista de dados com todas as ações registradas até o momento. 

**Endpoint:**
```http
GET /stocks
```
Parametros

ID: Numero identificador da ação gerado.
symbol(String): Retorna um Identificador de três letras seguido por um numero.
companyName(String): Retorna o nome da companhia dona da ação.
price(Double): Retorna o valor referente aquela ação naquele momento.

```JSON
{
  "ID": "73e32309-2da9-4be4-95a9-119b0cc82228"
  "symbol": "BBS3",
  "companyName": "Banco do Brasil SA",
  "price": 56.97
}
{
  "ID": "73e32458-2da9-4be4-95a9-119b0cc72228"
  "symbol": "GRD1",
  "companyName": "Gerdau Aços",
  "price": 46.70
}

```


### Obter uma Ação por ID

Retorna os detalhes de uma ação específica com base no ID.

**Endpoint:**
```http
GET /stocks/{id}
```
ID(*Requerido): Numero identificador da Ação.

```JSON
{
  "ID": "73e32309-2da9-4be4-95a9-119b0cc82228"
  "symbol": "BBS3",
  "companyName": "Banco do Brasil SA",
  "price": 56.97
}

```

### Criar uma Nova Ação
Cria uma nova ação com base nos dados fornecidos.

**Endpoint:**
```http
POST /stocks
```
symbol(String): Envia um Identificador de três letras seguido por um numero.
companyName(String): Envia o nome da companhia dona da ação.
price(Double): Envia o valor referente aquela ação naquele momento.

**Corpo da Solicitação (Request Body):**

```JSON
{
  "symbol": "BBS3",
  "companyName": "Banco do Brasil SA",
  "price": 56.97
}

```
### Atualizar uma Ação por ID
Atualiza os detalhes de uma ação específica com base no ID.

**Endpoint:**
```http
PUT /stocks/{id}
```
ID(*Requerido): Numero identificador da Ação.
symbol(String): Altera um Identificador de três letras seguido por um numero.
companyName(String): Altera o nome da companhia dona da ação.
price(Double): Altera o valor referente aquela ação naquele momento de forma que faz uma soma ou subtração da ação depende do valor referenciado.

**Corpo da Solicitação (Request Body):**

```JSON
{
  "symbol": "BBS3",
  "companyName": "Banco do Brasil SA",
  "price": 59.97
}

```

### Excluir uma Ação por ID
Exclui uma ação específica com base no ID.

**Endpoint:**
```http
DELETE /stocks/{id}
```

```TEXT

Stock with ID 73e32309-2da9-4be4-95a9-119b0cc82228 was deleted successfully.

```

## Uso
1. Clone o repositório: `git clone https://github.com/seu-usuario/MandaCaruBrokerAPI.git`
2. Importe o projeto em sua IDE preferida.
3. Configure o banco de dados e as propriedades de aplicação conforme necessário.
4. Execute o aplicativo Spring Boot.
5. Acesse a API em `http://localhost:8080`.

## Requisitos
- Java 11 ou superior
- Maven
- Banco de dados

## Tecnologias Utilizadas
- Spring Boot
- Spring Data JPA
- Maven
- PostgreSQL

## Contribuições
Contribuições são bem-vindas!

## Licença
Este projeto está licenciado sob a [Licença MIT](LICENSE).

