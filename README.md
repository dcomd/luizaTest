
## Projeto teste luiza
Projeto consiste em consumir API do git hub buscando os repositorios.

![Captura de Tela 2025-02-25 às 10 45 17](https://github.com/user-attachments/assets/f441ac37-199e-4348-9317-3b5f62430918)

## Tecnologias usadas

Jetpack Compose
Este aplicativo usa o Jetpack Compose para construir a interface do usuário (UI). O Compose nos permite criar interfaces flexíveis e altamente personalizáveis com menos código repetitivo e uma sintaxe mais declarativa. Os componentes da UI são integrados de forma eficiente com a arquitetura MVVM.

Arquitetura MVVM
Seguimos a arquitetura MVVM (Model-View-ViewModel), que promove a separação de responsabilidades, garantindo que:

Model: Responsável pela lógica de negócios e operações de dados.
View: Exibe a interface do usuário e interage com o usuário.
ViewModel: Atua como um intermediário entre o Model e a View, gerenciando dados relacionados à UI de forma ciente do ciclo de vida.
Flow para Dados Reativos
Para fluxos de dados assíncronos, usamos Flow (parte das Kotlin Coroutines) para emitir atualizações para a UI. Isso é especialmente útil ao lidar com operações em segundo plano, como busca de dados de APIs ou bancos de dados locais, e atualiza automaticamente a UI quando os dados mudam.

Koin para Injeção de Dependência
Usamos Koin como framework de injeção de dependência para gerenciar a criação de objetos e dependências. O Koin simplifica o gerenciamento de dependências e melhora a modularidade, tornando o aplicativo mais testável e de fácil manutenção.

Clean Architecture
O projeto segue os princípios da Clean Architecture para manter uma separação clara de responsabilidades, tornando o código mais escalável e testável. O projeto é dividido nas seguintes camadas:

Domain: Contém a lógica de negócios e as entidades do domínio.
Data: Responsável pela manipulação de dados, incluindo acesso à rede e ao banco de dados.
Presentation: Responsável pela camada de UI, incluindo componentes de UI, ViewModel e lógica da UI.
DDD (Domain-Driven Design)
Adotamos o Domain-Driven Design (DDD) para organizar nossa aplicação em torno do domínio, tornando o código mais compreensível e adaptável a mudanças. A lógica de negócios da aplicação está no centro do sistema, e todas as outras partes do sistema interagem com ela.

Banco de Dados Room
Para persistência local de dados, utilizamos o Room Database, que faz parte da suíte Android Jetpack. Ele fornece uma camada de abstração sobre o SQLite, permitindo um acesso e gerenciamento de banco de dados mais simples.

Retrofit
Usamos o Retrofit para operações de rede, lidando com requisições HTTP. O Retrofit simplifica as chamadas de API e fornece uma interface fácil de usar para interagir com serviços RESTful.

Kotlin Coroutines
As Kotlin Coroutines são usadas para gerenciar tarefas em segundo plano, como chamadas de API e operações no banco de dados. Elas ajudam a escrever código assíncrono de maneira sequencial e de fácil leitura.


## organização do projeto

Compose UI: Declarative UI components powered by Jetpack Compose.

ViewModel: Centralizes UI-related data and logic.

Flow & LiveData: Reactive data streams for observing data changes.

Koin Dependency Injection: Modularizes the app and simplifies dependency management.

Retrofit: Network.

Clean Architecture: Separation of concerns for scalability and maintainability.

Unit Tests: Tests for various components of the application using  Mockk.


![Captura de Tela 2025-02-25 às 11 44 10](https://github.com/user-attachments/assets/3493e17c-070d-4037-9d7d-ec9f7c000f37)
