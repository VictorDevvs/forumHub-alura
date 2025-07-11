Fórum Hub da Alura

Bem-vindo ao repositório do Fórum Hub, um projeto desenvolvido como parte do desafio da Alura. Este projeto implementa uma API REST robusta para gerenciar tópicos de discussão, servindo como a espinha dorsal de um futuro fórum interativo.
🚀 Sobre o Projeto

O Fórum Hub é um desafio de backend que visa replicar o processo de gerenciamento de tópicos em um fórum. Utilizando o poderoso framework Spring, esta API RESTful oferece funcionalidades completas de CRUD (Create, Read, Update, Delete) para tópicos, permitindo aos usuários interagir com o sistema de forma eficiente e segura.
Funcionalidades Principais:

    Criação de Tópicos: Permite aos usuários criar novos tópicos de discussão.

    Listagem de Tópicos: Exibe todos os tópicos existentes.

    Visualização de Tópico Específico: Permite consultar os detalhes de um tópico individual.

    Atualização de Tópicos: Possibilita a modificação de tópicos existentes.

    Exclusão de Tópicos: Permite a remoção de tópicos.

Destaques da Implementação:

    API RESTful: Rotas implementadas seguindo as melhores práticas do modelo REST.

    Validações de Negócio: Regras de negócio aplicadas para garantir a integridade dos dados.

    Persistência de Dados: Utilização de um banco de dados relacional (MySQL) para armazenamento eficiente.

    Segurança: Serviço de autenticação e autorização via JWT (JSON Web Token), garantindo que todas as rotas, exceto as de registro e login, sejam privadas e exijam autenticação.

🎯 Objetivo Principal

O objetivo primordial deste projeto é a criação de uma API REST completa e funcional para o Fórum Hub da Alura, servindo como um desafio de aprendizado prático e aprofundado em desenvolvimento backend com Spring.
📈 Status do Projeto

O projeto encontra-se Concluído em relação aos requisitos iniciais do desafio. No entanto, há um vasto espaço para futuras melhorias e expansões para torná-lo um sistema de fórum totalmente funcional e robusto.
🛠️ Tecnologias Utilizadas

    Linguagem: Java 21

    Frameworks/Libraries:

        Spring Web

        Spring Data JPA

        Spring Security

        Spring Validation

        Lombok

        JWT (JSON Web Token)

        Spring Boot DevTools

        Flyway (para migrações de banco de dados)

    Banco de Dados: MySQL 8.0

⚙️ Pré-requisitos

Para executar o projeto localmente, você precisará ter instalado:

    Java Development Kit (JDK): Versão 21 ou superior.

    MySQL Server: Versão 8.0 ou superior.

🚀 Como Instalar e Executar Localmente

Siga os passos abaixo para configurar e rodar o projeto em sua máquina:

    Clone o Repositório:

    git clone https://github.com/SeuUsuario/forum-hub.git # Substitua pelo link do seu repositório
    cd forum-hub

    Configurar Banco de Dados:

        Crie um banco de dados MySQL para o projeto (ex: forum_hub_db).

        Atualize as configurações de conexão do banco de dados no arquivo application.properties (ou application.yml) localizado em src/main/resources/.

        spring.datasource.url=jdbc:mysql://localhost:3306/forum_hub_db
        spring.datasource.username=seu_usuario_mysql
        spring.datasource.password=sua_senha_mysql
        spring.jpa.hibernate.ddl-auto=update # ou none, se preferir usar apenas Flyway
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true

    Executar o Projeto:
    Você pode executar o projeto usando sua IDE favorita (IntelliJ IDEA, Eclipse, VS Code com extensões Spring) ou via linha de comando com Maven/Gradle:

    # Se estiver usando Maven
    ./mvnw spring-boot:run

    O aplicativo estará disponível em http://localhost:8080 (ou na porta configurada).

💡 Como Usar na Prática

Este projeto é, primariamente, um desafio de aprendizado e uma demonstração das capacidades de desenvolvimento de API REST com Spring. Embora as funcionalidades de CRUD para tópicos estejam implementadas, o código atual serve como um ponto de partida para um sistema de fórum mais completo.

Em um cenário real, esta API seria a base para um fórum de interação entre alunos e instrutores, permitindo a postagem de dúvidas e respostas. No entanto, para ser funcional nesse contexto, seriam necessárias várias outras camadas (frontend, mais validações, regras de negócio complexas, etc.) que não foram o foco deste desafio.
👤 Autor

    Victor

🤝 Contribuições

Contribuições são bem-vindas! Se você tiver sugestões de melhoria, encontrar bugs ou quiser adicionar novas funcionalidades, sinta-se à vontade para abrir uma issue ou enviar um pull request.

🏷️ Tags e Temas

API Rest Java Spring Backend Desafio Alura JWT MySQL
