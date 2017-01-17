# Pré-requisitos

- Git instalado;
- Gradle 3.3 instalado;

# Execução

- Clone o projeto git usando o comando: git clone https://github.com/humbertosena/teste-csv-java.git
- Na pasta raiz do projeto execute o comando gradle build;
- Acesse o subdiretório build/libs;
- Execute: java -jar teste-csv-java-0.1.jar

# Comandos
- count *;
- count distinct [propriedade];
- filter [propriedade] [valor]

# Teste
- Ao executar o gradle build, os testes são executados e o resultado está disponível em: build/test-results;

#Nota
No pacote teste-csv-java-0.1.jar contém:
- Classes java
- application.properties : Arquivo de propriedades padrão para execução da aplicação;
- cidades.csv : Arquivo com fonte de dados padrão;

#application.properties
- app.dataSourceDrive=br.hss.app.ds.csv.DataSourceFileCSV
- app.fileSource=/cidades.csv
- app.headerLine=tru

#Execuçoes alternativas
- java -jar teste-csv-java-0.1.jar config=/home/<usr>/application.properties app.fileSource=/home/<usr>/cidadesDoMundo.csv
