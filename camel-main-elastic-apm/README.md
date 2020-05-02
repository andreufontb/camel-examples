# camel-main-elastic-apm

Example camel aplication with APM traces enabled using `camel-main`.

APM agent is attached using self-attach, you can remove self-attach and do it manualy using -javaagent command.

> To run this aplication you will need to setup an existing `server_urls` in `src/resources/elasticapm.properties`

## Run localy

```shell
mvn compile exec:java
```
