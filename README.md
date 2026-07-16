# FazConta

Calculadora desktop simples inspirada na calculadora do Windows 11,
desenvolvida em Java 24 com JavaFX 24.0.1 (versão do pacote: **1.1**).

## Pré-requisitos

- **JDK 21+** com `jpackage` disponível no `PATH` (o runtime do
  executável é gerado a partir do JDK detectado; o código compila
  como Java 24 via `maven.compiler.release`).
- **Maven 3.9+** instalado e disponível no `PATH`.
- **JavaFX 24.0.1** resolvido automaticamente via dependências Maven.

> O projeto não inclui o Maven Wrapper (`mvnw`). Sem o Maven instalado,
> o build não é reproduzível fora deste ambiente.

## Build e execução

### Executar em modo desenvolvimento (JavaFX)

```bash
mvn clean javafx:run
```

### Executar os testes unitários

```bash
mvn clean test
```

### Gerar o executável para Windows 11 (.exe autocontido)

O perfil `dist` usa o `maven-antrun-plugin` para invocar o
`jpackage` nativo do JDK, gerando um `FazConta.exe` autocontido que
**não exige JDK instalado** na máquina do cliente:

```bash
mvn clean package -Pdist
```

Saída: `dist\FazConta\FazConta.exe` (app-image autocontido, v1.1,
sem janela de terminal — abre apenas a janela JavaFX).

Detalhes do empacotamento:
- O runtime é criado pelo `jpackage` a partir do JDK no PATH.
- `--add-modules javafx.controls,javafx.fxml,java.logging` garante
  que o JavaFX **e** o `java.util.logging` (usado pelo `CalculatorLogger`)
  sejam incluídos no runtime. Sem `java.logging`, a app crasha com
  `NoClassDefFoundError: java/util/logging/Logger`.
- O `jpackage` não sobrescreve o diretório de destino; o perfil `dist`
  limpa `dist/FazConta` antes de empacotar.

Para um instalador `.msi`, instale o **WiX Toolset** e troque
`--type app-image` por `msi` no perfil `dist`.

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
src/test/java/com/pororoca/  testes JUnit 5 (19 testes)
.agent/                       especificações dos agentes (AGENT.md, CI_AGENT.md)
```
