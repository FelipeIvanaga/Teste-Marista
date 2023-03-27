# Teste Grupo Marista

## Rodar localmente

- `./gradlew bootRun`

## Deploy Railway

- O projeto pode ser testado através do link
- https://teste-marista-production.up.railway.app/school
- Foi criado uma collection do Postman na raiz do projeto para facilitar o teste

## Detalhes de implementação

- Foi utilizado armezanamento em Memoria com o H2
- A entidade Level é criada junto a entidade School, porém não é cadastrado dois Levels com o mesmo code
- Foi utilizado a lib https://github.com/turkraft/springfilter que facilita a utilização do Specification do Spring

## O que não foi implementado e pode melhorar

- Testes de Integração e Unitários
- Conexão com um banco de dados fisico.
- Spring Security paro uso de Bearer token ou outro metodo de autentificação
- Regras de negócio reais
- Swagger
