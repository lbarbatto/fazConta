# FazConta

Calculadora desktop simples inspirada na calculadora do Windows 11,
desenvolvida em Java 24 com JavaFX 24.

## Pré-requisitos

- **JDK 24** (ou superior, compatível com o `maven.compiler.release` do `pom.xml`)
- **Maven 3.9+** instalado e disponível no `PATH`
- **JavaFX 24** resolvido automaticamente via dependências Maven

> O projeto não inclui o Maven Wrapper (`mvnw`). Sem o Maven instalado,
> o build não é reproduzível fora deste ambiente.

## Build e execução

```bash
# Compilar e rodar em modo desenvolvimento (JavaFX)
mvn clean javafx:run

# Executar os testes unitários
mvn clean test

# Gerar o pacote executável (jpackage)
mvn clean package
```

## Arquitetura

O projeto aplica padrões GoF:

- **State** (`com.pororoca.state`): gerencia o comportamento conforme
  o estado da calculadora (entrando número, operador selecionado, resultado).
- **Strategy** (`com.pororoca.strategy`): encapsula cada operação aritmética.
- **Factory** (`com.pororoca.factory`): cria comandos e estratégias.
- **Command** (`com.pororoca.command`): representa ações dos botões.
- **Facade** (`com.pororoca.service.Calculator`): expõe a API de alto nível.

## Estrutura

```
src/main/java/com/pororoca/   código-fonte
src/main/resources/           FXML e CSS
src/test/java/com/pororoca/  testes JUnit 5
agent/                       especificação do agente programador
```
